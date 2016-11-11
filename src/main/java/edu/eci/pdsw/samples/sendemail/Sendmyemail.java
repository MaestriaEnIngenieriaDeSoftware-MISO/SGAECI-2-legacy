/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.sendemail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class Sendmyemail {
    public static void main(String[] args){
        try{
            final String fromEmail = "wowpokemonwow@gmail.com"; //requires valid gmail id
            final String password = "@warlords29"; // correct password for gmail id
            
            //final String fromEmail = "carlosramirez-2997@hotmail.com"; //requires valid gmail id
            //final String password = "@informatica29"; // correct password for gmail id
            
            final String toEmail = "wowpokemonwow@gmail.com"; // can be any email id 

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            
            //props.put("mail.smtp.host", "smtp.live.com"); //SMTP Host
            //props.put("mail.smtp.port", "587"); //TLS Port
            
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

                //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            System.out.println("Mail Check 2");

            message.setSubject("parceros sirvio ");
            message.setText("Sirvio");

            System.out.println("Mail Check 3");

            for(int i = 0 ; i < 5 ; i++){
                Transport.send(message);
            }
            
            System.out.println("Mail Sent");
        }catch(Exception ex){
            System.out.println("Mail fail");
            System.out.println(ex);
        }
    }
}
