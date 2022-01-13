package com.example.zadanie22_1;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    private final MailService mailService;

    public MainController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/contact_form")
    public String contactForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact_form";
    }

    @PostMapping("/send")
    public String send(Message message) {
        mailService.sendContactAndConfirmationMail(message);
        return "redirect:/";
    }
}
