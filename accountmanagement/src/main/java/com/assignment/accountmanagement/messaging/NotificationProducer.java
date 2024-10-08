package com.assignment.accountmanagement.messaging;

import com.assignment.accountmanagement.exception.SendNotificationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    @Value("${application.kafka.notification.topic}")
    private String topic;
    @Value("${application.name}")
    private String messageSource;
    private final KafkaTemplate<String, NotificationDTO> notificationKafkaTemplate;

    public void sendNotification(NotificationContentDTO content) {
        log.info("Sending notification with content: {}", content.toString());
        NotificationDTO notification = new NotificationDTO(
                messageSource,
                content
        );
        try {
            notificationKafkaTemplate.send(topic, notification.getMessageSource(), notification);
        }
        catch (KafkaException e){
            String errMsg = String.format("Error occurred sending notification with conten: %s", content.toString());
            log.error(errMsg, e);
            throw new SendNotificationError(errMsg);
        }
    }
}
