@Controller
public class ConsultationController {
    
    @Autowired
    private EmailService emailService; // Service use karein

    @PostMapping
    public String submitForm(@ModelAttribute ConsultationRequest request, Model model) {
        repository.save(request);
        
        // Ye line ab turant execute ho jayegi, wait nahi karegi
//        emailService.sendEmailToAdmin(request); 

        model.addAttribute("successMessage", "Submitted!");
        return "layout/consultancy";
    }
}