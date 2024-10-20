package com.wojcik.ecommerce.service;

import com.wojcik.ecommerce.model.Payment;
import com.wojcik.ecommerce.model.command.PaymentCommand;
import com.wojcik.ecommerce.model.command.PaymentNotificationCommand;
import com.wojcik.ecommerce.model.dto.PaymentDto;
import com.wojcik.ecommerce.model.mapper.PaymentMapper;
import com.wojcik.ecommerce.producer.NotificationProducer;
import com.wojcik.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    @Transactional
    public PaymentDto createPayment(PaymentCommand paymentCommand) {
        Payment payment = paymentRepository.save(paymentMapper.toEntity(paymentCommand));
        notificationProducer.sendNotification(
                PaymentNotificationCommand.builder()
                        .orderReference(paymentCommand.getOrderReference())
                        .amount(paymentCommand.getAmount())
                        .paymentMethod(paymentCommand.getPaymentMethod())
                        .customerFirstName(paymentCommand.getCustomer().getFirstname())
                        .customerLastName(paymentCommand.getCustomer().getLastname())
                        .customerEmail(paymentCommand.getCustomer().getEmail())
                        .build());

        return PaymentDto.builder()
                .id(payment.getId())
                .build();
    }
}
