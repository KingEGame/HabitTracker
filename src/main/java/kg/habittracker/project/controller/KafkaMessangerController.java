package kg.habittracker.project.controller;

import kg.habittracker.project.records.MessageRequest;
//import kg.habittracker.project.service.CustomerDetailsConsumer;
//import kg.habittracker.project.service.CustomerDetailsProducer;
//import org.apache.kafka.common.protocol.types.Field;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/messages")
public class KafkaMessangerController {


    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessangerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String sendMessageToKafkaTopic(@RequestBody MessageRequest message){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("web", message.message());

        SuccessCallback<SendResult<String, String>> successCallback = sendResult -> {

            System.out.println("Success");
        };

        FailureCallback failureCallback = throwable -> {

            System.out.println("failed!!!!!!!!!!!!!!!!!!!!!");
        };

        future.addCallback( successCallback, failureCallback);
        return "Published successfully!!";
    }
}
