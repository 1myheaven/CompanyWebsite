package in.sp.main.controller.publicsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import in.sp.main.entity.ConsultationRequest;
import in.sp.main.repository.ConsultationRepository;
import in.sp.main.service.EmailService;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationRepository repository;

    @Autowired
    private EmailService emailService;

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
        // Step 1: Save to DB (Quick)
        repository.save(consultationRequest);

        // Step 2: Send Email (Background - Async)
        emailService.sendEmail(consultationRequest);

        model.addAttribute("successMessage", "Request submitted! Email notification sent.");
        model.addAttribute("consultationRequest", new ConsultationRequest());

        return "layout/consultancy";
    }
}