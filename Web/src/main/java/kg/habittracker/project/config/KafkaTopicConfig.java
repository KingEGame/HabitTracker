package kg.habittracker.project.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic webTopic(){
        return TopicBuilder.name("web").build();
    }

    @Bean public NewTopic load_user_by_username_request(){return TopicBuilder.name("load_user_by_username_request").build();}
    @Bean public NewTopic db_otp_required_request(){return TopicBuilder.name("db_otp_required_request").build();}
    @Bean public NewTopic db_otp_required_respond(){return TopicBuilder.name("db_otp_required_respond").build();}
    @Bean public NewTopic register_user_request(){return TopicBuilder.name("register_user_request").build();}
}
