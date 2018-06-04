package com.JMS.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * Created by Elvira on 03.06.2018.
 */
@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SimpleMailMessage mailMessage;

    @Override
    public void sendMessage(String to, String subject, String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendChangesMessage(String to, String subject, ChangesDTO changes) {
        final String changesInfo = MessageFormat.format("Type: {0}\nEntityId: {1}\nEntityClass: {2}\nValues:\n\t{3}",
                Objects.toString(changes.getChangeType()),
                changes.getId_entity(),
                changes.getChangeClass(),
                changes.getNewValues());
        sendMessage(to, subject, String.format(mailMessage.getText(), changesInfo));
    }
}
