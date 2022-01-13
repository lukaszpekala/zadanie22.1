package com.example.zadanie22_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String COMPANY_MAIL = "contact@company.com";
    private static final String MAIL_SUBJECT = "Wiadomość od ";
    private static final String CONFIRMATION_MAIL_SUBJECT = "Dziękujemy za wiadomość";
    private static final String CONFIRMATION_MAIL_TEXT =
            "Odpowiemy możliwie najszybciej. Prosimy nie odpowiadać na tą wiadomość automatyczną!";


    public void sendContactAndConfirmationMail(Message message) {
        mailSender.send(createContactMail(message));
        mailSender.send(createConfirmationMail(message));
    }

    private SimpleMailMessage createContactMail(Message message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(COMPANY_MAIL);
        mail.setSubject(MAIL_SUBJECT + message.getName());
        mail.setText(message.getText());
        mail.setReplyTo(message.getEmail());
        mail.setFrom(message.getEmail());
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
