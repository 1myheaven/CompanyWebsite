package in.sp.main.controller.publicsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import in.sp.main.entity.ConsultationRequest;
import in.sp.main.repository.ConsultationRepository;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    private final ConsultationRepository repository;

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
        // Database mein save karein
        repository.save(consultationRequest);

        model.addAttribute("successMessage", "Request submitted! We will contact you soon.");
        model.addAttribute("consultationRequest", new ConsultationRequest());

        return "layout/consultancy";
    }
}