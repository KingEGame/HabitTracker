package kg.habittracker.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsConsumer {
    private final Logger logger = LoggerFactory.getLogger(CustomerDetailsConsumer.class);

    @KafkaListener(topics = "customer", groupId = "group_id")
    public void customerDetailConsumer(String message){
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}
