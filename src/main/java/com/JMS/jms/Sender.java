package com.JMS.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import java.io.Serializable;
import java.text.MessageFormat;


/**
 * Created by Elvira on 03.06.2018.
 */
@Service
public class Sender {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Sender.class.getName());

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(final Serializable obj) {
        try {
            final String text = objectMapper.writeValueAsString(obj);
            logger.info(MessageFormat.format("Sending message: {0}", text));
            this.jmsTemplate.send(session -> {
                Message message = session.createTextMessage(text);
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                return message;
            });
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
