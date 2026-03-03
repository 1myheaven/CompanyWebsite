//package in.sp.main.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import in.sp.main.entity.ConsultationRequest;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Async // Ab ye pakka background mein chalega
//    public void sendEmailToAdmin(ConsultationRequest request) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("abhisheksingh09072001@gmail.com");
//            message.setTo("abhisheksingh09072001@gmail.com");
//            message.setSubject("New Request: " + request.getName());
//            message.setText("Name: " + request.getName() + "\nPhone: " + request.getPhone());
//            
//            mailSender.send(message);
//            System.out.println("Email Sent Successfully in Background!");
//        } catch (Exception e) {
//            System.err.println("Email Error: " + e.getMessage());
//        }
//    }
//}