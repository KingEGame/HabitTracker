package kg.habittracker.project.service;

import com.google.gson.Gson;
import kg.habittracker.project.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Getter
@Setter
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private DefaultEmailService emailService;
    private final static String USER_NOT_FOUND = "user with email %s not found";
    private PasswordEncoder passwordEncoder;


    public String getOneTimePassword() {
        String OTP = String.valueOf(new Random().nextInt(999999));
        return passwordEncoder.encode(OTP);
    }

    public void sendOTPEmail(String email, String OTP) {

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        String content = "<p>Hello " + email + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        emailService.sendSimpleEmail(email, subject, content);


    }

    public void clearOTP(UserDTO user) {
        kafkaTemplate.send("user_clear_otp", new Gson().toJson(user));
    }

    private void save(UserDTO user) {
        kafkaTemplate.send("save_user_request", new Gson().toJson(user));

    }

    @Override
    @KafkaListener(topics = "load_user_by_username_respond")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        kafkaTemplate.send("load_user_by_username_request", username);
        UserDTO userDTO = new Gson().fromJson(username, UserDTO.class);
        return userDTO;
    }

//    @KafkaListener(topics = "load_user_by_username_respond")
//    public UserDTO listenLoadUserbyUserName(String data){
//        userDTO = new Gson().fromJson(data, UserDTO.class);
//        return userDTO;
//    }

    private UserDTO findUserByUsername(String username) {
        return null;
    }

    private UserDTO findUserByEmail(String username) {
        return null;
    }

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @KafkaListener(topics = "db_otp_required_respond")
    public boolean isOTPRequired(String user) {
        kafkaTemplate.send("db_otp_required_request", new Gson().toJson(user));

        return Boolean.valueOf(user);
    }

}
