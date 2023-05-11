package kg.habittracker.project.componets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(
            topics = "web",
            groupId = "group_id"
    )
    void listener(String data){
        logger.info(String.format("#### -> Consumed message -> %s", data));
    }
}
