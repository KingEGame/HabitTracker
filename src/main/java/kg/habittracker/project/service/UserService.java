package kg.habittracker.project.service;

import kg.habittracker.project.details.UserDetail;
import kg.habittracker.project.entity.User;
import kg.habittracker.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DefaultEmailService emailService;

    private final static String USER_NOT_FOUND = "user with email %s not found";

//    @Autowired
    private PasswordEncoder passwordEncoder;


    public void generateOneTimePassword(User user) throws UnsupportedEncodingException, MessagingException {
        String OTP = String.valueOf(new Random().nextInt(999999));
        String encodedOTP = passwordEncoder.encode(OTP);

        user.setOneTimePassword(encodedOTP);
        user.setOtpRequestedTime(new Date());

        userRepository.save(user);

        sendOTPEmail(user, OTP);

    }

    public void sendOTPEmail(User user, String OTP) throws UnsupportedEncodingException, MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom("damirov.ilian@gmail.com", "habitTracker");
//        helper.setTo(user.getEmail());

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        String content = "<p>Hello " + user.getEmail() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

//        helper.setSubject(subject);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
        emailService.sendSimpleEmail(user.getEmail(), subject, content);


    }

    public void clearOTP(User user) {
        user.setOneTimePassword(null);
        user.setOtpRequestedTime(null);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);

        if(user == null){
            user = userRepository.findUserByUsername(username);
        }

        return UserDetail.builder().user(user).build();
    }

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes

    public boolean isOTPRequired(User user) {
        if (user.getOneTimePassword() == null) {
            return false;
        }

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = user.getOtpRequestedTime().getTime();

        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }

        return true;
    }

}
