package in.sp.main.controller.publicsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entity.ConsultationRequest;
import in.sp.main.repository.ConsultationRepository;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationRepository repository;

    @Autowired
    private JavaMailSender mailSender; // ✉️ Email bhejne wali machine

    public ConsultationController(ConsultationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("consultationRequest", new ConsultationRequest());
        return "layout/consultancy";
    }

    @PostMapping
    public String submitForm(@ModelAttribute ConsultationRequest consultationRequest, Model model) {

        // 1. Database mein save karein
        repository.save(consultationRequest);

        // 2. Email bhejne ka process (Try-Catch zaroori hai taaki error na aaye)
        try {
            sendEmailToAdmin(consultationRequest);
        } catch (Exception e) {
            System.out.println("Email Error: " + e.getMessage());
        }

        model.addAttribute("successMessage", "Request submitted! Admin has been notified.");
        model.addAttribute("consultationRequest", new ConsultationRequest());

        return "layout/consultancy";
    }

    // Yeh function email banayega aur bhejega
    private void sendEmailToAdmin(ConsultationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo("abhisheksingh09072001@gmail.com"); // Jaha aapko notification chahiye
        message.setSubject("New Consultation Request: " + request.getName());
        
        String emailBody = "Hi Admin,\n\n" +
                           "New consultancy Request:\n" +
                           "---------------------------\n" +
                           "Name: " + request.getName() + "\n" +
                           "Email: " + request.getEmail() + "\n" +
                           "Message: " + request.getMessage() + "\n" +
                           "PhoneNumber: " + request.getPhone() + "\n"+
                           "---------------------------\n" +
                           "Please follow up soon.";
        
        message.setText(emailBody);
        
        mailSender.send(message); // 🚀 Email send!
    }
}