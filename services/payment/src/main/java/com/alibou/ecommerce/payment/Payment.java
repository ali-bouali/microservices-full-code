package com.alibou.ecommerce.payment;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "payment")
public class Payment {

  @Id
  @GeneratedValue
  private Integer id;

  private BigDecimal amount;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private Integer orderId;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(insertable = false)
  private LocalDateTime lastModifiedDate;

}
