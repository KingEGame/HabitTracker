package kg.habittracker.project.controller;

import kg.habittracker.project.service.CustomerDetailsConsumer;
import kg.habittracker.project.service.CustomerDetailsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafkaDemo")
public class CustomerDetailsController {
    private final CustomerDetailsProducer producer;
    private final CustomerDetailsConsumer consumer;

    @Autowired
    public CustomerDetailsController(CustomerDetailsProducer producer, CustomerDetailsConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @GetMapping(value = "publisher/{message}")
    public String sendMessageToKafkaTopic(@PathVariable String message){
        this.producer.sendMessage(message);
        return "Published successfully!!";
    }
}
