package kg.habittracker.project.componets;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaListeners(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(
            topics = "web",
            groupId = "group_id"
    )
    void listener(String data){
        logger.info(String.format("#### -> Consumed message -> %s", data));
    }


}
