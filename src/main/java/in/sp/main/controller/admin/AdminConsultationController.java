package in.sp.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.service.ConsultationService;

@Controller
@RequestMapping("/admin/consultations")
public class AdminConsultationController {

    private final ConsultationService consultationService;

    public AdminConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute("total", consultationService.getTotalConsultations());
        model.addAttribute("requests", consultationService.getAllConsultations());

        return "admin/consultation-dashboard";
    }
    @PostMapping("/delete/{id}")
    public String deleteConsultation(@PathVariable Long id) {

        consultationService.deleteConsultation(id);

        return "redirect:/admin/consultations";
    }
}