package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import in.sp.main.entity.ConsultationRequest;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(ConsultationRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("abhisheksingh09072001@gmail.com");
            message.setTo("abhisheksingh09072001@gmail.com");
            message.setSubject("New Consultation Request: " + request.getName());
            message.setText("Hi Admin,\n\nNew Request Details:\n" +
                           "Name: " + request.getName() + "\n" +
                           "Email: " + request.getEmail() + "\n" +
                           "Phone: " + request.getPhone() + "\n" +
                           "Message: " + request.getMessage());
            
            mailSender.send(message);
            System.out.println("Email Sent Successfully!");
        } catch (Exception e) {
            System.err.println("Email Error: " + e.getMessage());
        }
    }
}