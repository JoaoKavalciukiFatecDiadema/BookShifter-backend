package com.example.dslist.events.listener;

import com.example.dslist.entities.User;
import com.example.dslist.events.PasswordRecoveryEvent;
import com.example.dslist.events.RestPasswordRecoveryEvent;
import com.example.dslist.services.PasswordResetTokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestPasswordRecoveryEventListener implements ApplicationListener<RestPasswordRecoveryEvent> {


    private final PasswordResetTokenService service;
    private final JavaMailSender mailSender;
    private User user;

    @Override
    public void onApplicationEvent(RestPasswordRecoveryEvent event) {
        user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.saveResetPasswordToken(user, token);

        String url = event.getConfirmationURL() + "/rest-forgot-password/password-reset?token=" + token;

        try {
            sendResetPasswordEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendResetPasswordEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Recupere sua senha";
        String senderName = "Projeto DSList";
        String content = "<p> Olá, "+ user.getFirstName() + "</p>"
                + "<p><strong> Foi requisatado uma mudança de senha para a conta DSList atrelada a este email.</strong></p>"
                +  "<p> Clique no link abaixo para recuperar sua senha</p>"
                + "<a href=\"" + url + "\">Recupere sua senha</a>"
                + "<p> Projeto DS Game List</p>";
        emailMessage(subject, senderName, content, mailSender, user);
    }
    private static void emailMessage(String subject, String senderName,
                                     String mailContent, JavaMailSender mailSender, User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("book.shifter.brazil@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

}
