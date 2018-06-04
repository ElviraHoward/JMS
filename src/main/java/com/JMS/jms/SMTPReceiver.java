package com.JMS.jms;

import com.JMS.model.Change;
import com.JMS.model.Receiver;
import com.JMS.service.ReceiverService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Elvira on 04.06.2018.
 */
public class SMTPReceiver  implements SessionAwareMessageListener<TextMessage> {

    private static final Logger logger = Logger.getLogger(SMTPReceiver.class.getName());

    @Autowired
    private CustomObjectMapper objectMapper;

    @Autowired
    private ReceiverService receiverService;

    @Autowired
    private EmailService emailService;

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        logger.info(MessageFormat.format("Received message: {0}", textMessage.getText()));
        try {
            final ChangesDTO msg = objectMapper.readValue(textMessage.getText(), ChangesDTO.class);
            final List<Receiver> subscribers = receiverService.findByTypeAndClassEntity(msg.getChangeType(), msg.getChangeClass());
            subscribers.forEach(subscriber -> {
                emailService.sendChangesMessage(subscriber.getEmail(),
                        String.format("%s: changes notification", msg.getChangeClass()),
                        msg);
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
