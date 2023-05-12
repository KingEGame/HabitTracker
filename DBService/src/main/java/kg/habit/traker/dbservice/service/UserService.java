package kg.habit.traker.dbservice.service;

import com.google.gson.Gson;
import kg.habit.traker.dbservice.entity.User;
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
import kg.habit.traker.dbservice.repository.*;

import java.util.Random;

@Service
@Getter
@Setter
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DefaultEmailService emailService;

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private PasswordEncoder passwordEncoder;
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserService(PasswordEncoder passwordEncoder, KafkaTemplate<String, String> kafkaTemplate) {
        this.passwordEncoder = passwordEncoder;
        this.kafkaTemplate = kafkaTemplate;
    }

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

    public void clearOTP(User user) {
        user.setOneTimePassword(null);
        user.setOtpRequestedTime(null);
        userRepository.saveAndFlush(user);
    }

    @KafkaListener(topics = "load_user_by_username_request")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElse(userRepository.findUserByUsername(username));

        return user;
    }

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    @KafkaListener(topics = "db_otp_required_request")
    public void isOTPRequired(User user) {
        if (user.getOneTimePassword() == null) {
            kafkaTemplate.send("db_otp_required_respond", "false");
            return;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = user.getOtpRequestedTime().getTime();

        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            kafkaTemplate.send("db_otp_required_respond", "false");
            return;
        }

        kafkaTemplate.send("db_otp_required_respond", "true");
    }

}
