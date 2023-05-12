package kg.habittracker.project.service;

import com.google.gson.Gson;
import kg.habittracker.project.DTO.UserDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private KafkaTemplate<String, String> kafkaTemplate;

    public RegistrationService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void register(UserDTO request) {
        kafkaTemplate.send("register_user_request", new Gson().toJson(request));
    }

}
