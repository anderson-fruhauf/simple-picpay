package tech.lumos.picpay.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.lumos.picpay.client.NotificationClient;
import tech.lumos.picpay.entity.Transfer;

@Service
@AllArgsConstructor
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public void sendNotification(Transfer transfer){
        try {
            logger.info("Sending notification");
            var response = notificationClient.sendNotification(transfer);

            if(response.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not ok");
            }
        }catch (Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}
