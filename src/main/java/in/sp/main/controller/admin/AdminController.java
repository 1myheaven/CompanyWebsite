package in.sp.main.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entity.ContactMessage;
import in.sp.main.repository.ContactRepository; // agar tumhare repository ka naam alag hai, use wahi use karo

@Controller
@RequestMapping("/admin/messages")
public class AdminController {

    private final ContactRepository contactRepository;

    public AdminController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // ================= LIST =================
    @GetMapping
    public String listMessages(Model model) {
        model.addAttribute("messages", contactRepository.findAll());
        return "admin/admin-messages"; // admin ke liye list template
    }s

    // ================= DELETE =================
    @GetMapping("/delete/{id}")
    public String deleteMessage(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return "redirect:/admin/messages";
    }

    // ================= ADD FORM =================
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "admin/admin-add";
    }

    // ================= SAVE NEW =================
    @PostMapping("/save")
    public String saveMessage(@ModelAttribute ContactMessage contactMessage) {
        contactRepository.save(contactMessage);
        return "redirect:/admin/messages";
    }

    // ================= EDIT FORM =================
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ContactMessage contactMessage = contactRepository.findById(id).orElse(null);
        model.addAttribute("contactMessage", contactMessage);
        return "admin/admin-edit";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateMessage(@ModelAttribute ContactMessage contactMessage) {
        contactRepository.save(contactMessage);
        return "redirect:/admin/messages";
    }
}
