package kg.habit.traker.dbservice.service;


import com.google.gson.Gson;
import kg.habit.traker.dbservice.entity.User;
import kg.habit.traker.dbservice.entity.UserRoles;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = "register_user_request")
    public User register(String request) {
        User users = new Gson().fromJson(request, User.class);
        boolean isValid= Pattern.compile("^(.+)@(\\S+)$")
                .matcher(users.getEmail())
                .matches();
        if(!isValid){
            throw new IllegalStateException("email not valid");
        }


        if( userService.getUserRepository().findUserByEmail(users.getEmail()).isPresent()){
            throw new IllegalStateException("email already taken");
        }

        User user = User
                .builder()
                .username(users.getUsername())
                .email(users.getEmail())
                .password(userService.getPasswordEncoder().encode(users.getPassword()))
                .userRole(UserRoles.USER)
                .otpRequestedTime(Date.from(Instant.now()))
                .build();

        String OTP = userService.getOneTimePassword();

        userService.sendOTPEmail(user.getEmail(), OTP);

        return userService.getUserRepository()
                .saveAndFlush(user);
    }
}
