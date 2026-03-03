package in.sp.main.controller.publicsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async; // 1. Ye import karein
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
    private JavaMailSender mailSender;

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
        // 1. Database mein save turant hoga
        repository.save(consultationRequest);

        // 2. Email background mein jayegi (User wait nahi karega)
        try {
            sendEmailToAdmin(consultationRequest);
        } catch (Exception e) {
            System.out.println("Email Error: " + e.getMessage());
        }

        model.addAttribute("successMessage", "Request submitted! Admin has been notified.");
        model.addAttribute("consultationRequest", new ConsultationRequest());

        return "layout/consultancy";
    }

    @Async // <--- 2. Sabse zaroori annotation!
    protected void sendEmailToAdmin(ConsultationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abhisheksingh09072001@gmail.com"); 
        message.setTo("abhisheksingh09072001@gmail.com");
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
        
        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Background Email Failed: " + e.getMessage());
        }
    }
}