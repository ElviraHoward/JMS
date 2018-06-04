package com.JMS.jms;

/**
 * Created by Elvira on 03.06.2018.
 */
public interface EmailService {

    public void sendMessage(String to, String subject, String text);
    public  void sendChangesMessage(String to, String subject, ChangesDTO changes);
}
