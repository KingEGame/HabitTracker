package kg.habit.traker.dbservice.componets;

import kg.habit.traker.dbservice.entity.User;
import kg.habit.traker.dbservice.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
public class KafkaListeners {

    private final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RegistrationService registrationService;

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

    public String registration(@ModelAttribute User request) {
//        registrationService.register(request);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("web", request.getUsername());

        SuccessCallback<SendResult<String, String>> successCallback = sendResult -> {

            System.out.println("Success");
        };

        FailureCallback failureCallback = throwable -> {

            System.out.println("failed!!!!!!!!!!!!!!!!!!!!!");
        };

        future.addCallback( successCallback, failureCallback);
        return "";
    }

}
