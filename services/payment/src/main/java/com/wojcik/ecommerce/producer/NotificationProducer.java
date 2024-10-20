package com.wojcik.ecommerce.producer;

import com.wojcik.ecommerce.model.command.PaymentNotificationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationCommand> paymentKafkaTemplate;

    public void sendNotification(PaymentNotificationCommand paymentNotificationCommand) {
        log.info("Sending notification with body <{}>", paymentNotificationCommand);
        Message<PaymentNotificationCommand> message = MessageBuilder
                .withPayload(paymentNotificationCommand)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();
        paymentKafkaTemplate.send(message);
    }
}
