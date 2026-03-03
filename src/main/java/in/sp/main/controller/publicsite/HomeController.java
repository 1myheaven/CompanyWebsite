package in.sp.main.controller.publicsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "layout/home";   // templates/home.html
    }
    @GetMapping("/services")
    public String services() {
        return "layout/services";
    }
    @GetMapping("/case-studies")
    public String caseStudies() {
        return "layout/case-studies";
    }
    @GetMapping("/about")
    public String about() {
        return "layout/about";
    }

}
