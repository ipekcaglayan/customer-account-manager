package com.assignment.transactionmanagement.messaging;

import com.assignment.transactionmanagement.dto.CreateTransactionDTO;
import com.assignment.transactionmanagement.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {
    private final TransactionService transactionService;
    @KafkaListener(
            topics = "${application.kafka.notification.topic}",
            groupId = "${application.kafka.notification.group-id}")
    public  void receive(@Payload ConsumerRecord<String, String> message) throws JsonProcessingException {
        String notificationString = message.value();
        NotificationDTO notification = new ObjectMapper().readValue(notificationString, NotificationDTO.class);
        NotificationContentDTO content = notification.getContent();
        log.info("Notification received: {}", notification);
        transactionService.createTransaction(new CreateTransactionDTO(
                content.getAccountId(),
                content.getAmount()
        ));
    }
}
