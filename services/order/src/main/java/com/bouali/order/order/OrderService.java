package com.bouali.order.order;

import com.bouali.order.customer.CustomerClient;
import com.bouali.order.exception.BusinessException;
import com.bouali.order.payment.PaymentClient;
import com.bouali.order.payment.PaymentRequest;
import com.bouali.order.validator.OrderValidator;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository repository;
  private final OrderValidator<OrderRequest> validator;
  private final OrderMapper mapper;
  private final CustomerClient customerClient;
  private final PaymentClient paymentClient;

  public Integer createOrder(OrderRequest request) {
    this.validator.validate(request);
    if (this.customerClient.customerNotExists(request.customerId())) {
      throw new BusinessException("Cannot create order:: No customer exists with the provided ID");
    }
    var order = this.repository.save(mapper.toOrder(request));
    var paymentRequest = new PaymentRequest(
        request.amount(),
        request.paymentMethod(),
        order.getId()
    );
    this.paymentClient.requestOrderPayment(paymentRequest);
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
