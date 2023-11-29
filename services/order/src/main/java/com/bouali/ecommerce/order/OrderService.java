package com.bouali.ecommerce.order;

import com.bouali.ecommerce.customer.CustomerClient;
import com.bouali.ecommerce.exception.BusinessException;
import com.bouali.ecommerce.kafka.OrderConfirmation;
import com.bouali.ecommerce.kafka.OrderProducer;
import com.bouali.ecommerce.orderline.OrderLineRequest;
import com.bouali.ecommerce.orderline.OrderLineResponse;
import com.bouali.ecommerce.orderline.OrderLineService;
import com.bouali.ecommerce.payment.PaymentClient;
import com.bouali.ecommerce.payment.PaymentRequest;
import com.bouali.ecommerce.product.ProductClient;
import com.bouali.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository repository;
  private final OrderMapper mapper;
  private final CustomerClient customerClient;
  private final PaymentClient paymentClient;
  private final ProductClient productClient;
  private final OrderLineService orderLineService;
  private final OrderProducer orderProducer;

  @Transactional
  public Integer createOrder(OrderRequest request) {
    if (this.customerClient.customerNotExists(request.customerId())) {
      throw new BusinessException("Cannot create order:: No customer exists with the provided ID");
    }
    productClient.purchaseProducts(request.products());
    var order = this.repository.save(mapper.toOrder(request));
    for (PurchaseRequest purchaseRequest: request.products()) {
      orderLineService.saveOrderLine(
              new OrderLineRequest(
                      null,
                      order.getId(),
                      purchaseRequest.productId(),
                      purchaseRequest.quantity()
              )
      );
    }
    var paymentRequest = new PaymentRequest(
        request.amount(),
        request.paymentMethod(),
        order.getId()
    );
    paymentClient.requestOrderPayment(paymentRequest);
    orderProducer.sendOrderConfirmation(
            new OrderConfirmation(
                    request.reference(),
                    request.amount(),
                    request.paymentMethod(),
                    request.products().size()
            )
    );
    return order.getId();
  }

  public List<OrderResponse> findAllOrders() {
    return this.repository.findAll()
        .stream()
        .map(this.mapper::fromOrder)
        .collect(Collectors.toList());
  }

  public OrderResponse findById(Integer id) {
    return this.repository.findById(id)
        .map(this.mapper::fromOrder)
        .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
  }
}
