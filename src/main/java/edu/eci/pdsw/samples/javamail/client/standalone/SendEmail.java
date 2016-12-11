package edu.eci.pdsw.samples.javamail.client.standalone;

import edu.eci.pdsw.samples.javamail.core.*;

import javax.mail.MessagingException;

public class SendEmail {

    public static void main(String[] args) {
        final String from = "5d8dd682c0-c92f3e@inbox.mailtrap.io";
        final String to = "carlos.ramirez-ot@mail.escuelaing.edu.co";
        final String subject = "test";
        final String message = "test";

        Email email = new SimpleEmail(from, to, subject, message);
        EmailSender sender = new SimpleEmailSender(new EmailConfiguration());
        try {
            sender.send(email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
