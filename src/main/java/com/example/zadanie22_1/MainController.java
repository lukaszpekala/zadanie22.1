package com.example.zadanie22_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    private static final String COMPANY_MAIL = "contact@company.com";
    private static final String MAIL_SUBJECT = "Wiadomość od ";
    private static final String CONFIRMATION_MAIL_SUBJECT = "Dziękujemy za wiadomość";
    private static final String CONFIRMATION_MAIL_TEXT =
            "Odpowiemy możliwie najszybciej. Prosimy nie odpowiadać na tą wiadomość automatyczną!";

    @Autowired
    private JavaMailSender mailSender;


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
        mailSender.send(createContactMail(message));
        mailSender.send(createConfirmationMail(message));
        return "redirect:/";
    }

    private SimpleMailMessage createContactMail(Message message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(COMPANY_MAIL);
        mail.setSubject(MAIL_SUBJECT + message.getName());
        mail.setText(message.getText());
        mail.setReplyTo(message.getEmail());
        mail.setFrom(message.getName());
        return mail;
    }

    private SimpleMailMessage createConfirmationMail(Message message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(message.getEmail());
        mail.setSubject(CONFIRMATION_MAIL_SUBJECT);
        mail.setText(CONFIRMATION_MAIL_TEXT);
        mail.setFrom(COMPANY_MAIL);
        return mail;
    }
}
