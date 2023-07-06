package com.wcs.Security.servicesImplem;

import com.wcs.Security.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplem implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    /*    @Autowired
        public EmailServiceImplem(JavaMailSender emailSender){
            this.emailSender=emailSender;
        }*/
    @Override
    public void sendEmail(String toUser, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toUser);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);



    }
}
