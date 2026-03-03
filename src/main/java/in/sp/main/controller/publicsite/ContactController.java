package in.sp.main.controller.publicsite;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entity.ContactMessage;
import in.sp.main.service.ContactService;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "layout/contact";
    }

    @PostMapping("/contact")
    public String submitContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message,
            Model model) {

        ContactMessage contact = new ContactMessage();
        contact.setName(name);
        contact.setEmail(email);
        contact.setSubject(subject);
        contact.setMessage(message);

        contactService.saveContact(contact);

        model.addAttribute("successMessage",
                "Your message saved successfully!");

        return "layout/contact";
    }
    
}
