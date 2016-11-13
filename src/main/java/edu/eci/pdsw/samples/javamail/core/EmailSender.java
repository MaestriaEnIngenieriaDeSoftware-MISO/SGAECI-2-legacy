package edu.eci.pdsw.samples.javamail.core;

import javax.mail.MessagingException;

public interface EmailSender {

    void send(Email email) throws MessagingException;

}
