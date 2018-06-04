package com.JMS.jms;

import com.JMS.model.Change;
import com.JMS.service.ChangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * Created by Elvira on 03.06.2018.
 */
public class ReceiveMessage implements SessionAwareMessageListener<TextMessage> {

    private static final Logger logger = Logger.getLogger(ReceiveMessage.class.getName());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ChangeService changesService;

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        logger.info(MessageFormat.format("Received message: {0}", textMessage.getText()));
        try {
            final ChangesDTO msg = objectMapper.readValue(textMessage.getText(), ChangesDTO.class);
            changesService.save(msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

