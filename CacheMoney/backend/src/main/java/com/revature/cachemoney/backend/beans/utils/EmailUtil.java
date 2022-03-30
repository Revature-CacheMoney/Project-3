package com.revature.cachemoney.backend.beans.utils;

import lombok.Data;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Data
public class EmailUtil {
    private static EmailUtil emailUtil = null;
    Properties properties;
    String host;
    String emailAcc;
    String emailPass;
    Session session;
    public static EmailUtil getInstance() {
        if (emailUtil == null) {
            emailUtil = new EmailUtil();
        }
        return emailUtil;
    }

    private EmailUtil() {
        properties = System.getProperties();

        host = "smtp.gmail.com";

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        String resourcePath = "src/main/resources/application.properties";
        emailAcc = PropertiesUtil.getProperty(resourcePath, "gmailuser");
        emailPass = PropertiesUtil.getProperty(resourcePath, "gmailpass");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAcc, emailPass);
            }
        });

        session.setDebug(true);
    }

    public void sendEmailTemplate(String recipient, String subject, String body) {
        EmailUtil emailUtil = EmailUtil.getInstance();
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(emailUtil.getSession());

            // Set From: header field of the header.
            message.setFrom(emailUtil.getEmailAcc());

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
