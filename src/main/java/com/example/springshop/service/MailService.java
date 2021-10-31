package com.example.springshop.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import lombok.SneakyThrows;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

public class MailService {
    private static final String APPLICATION_NAME = "spring-shop";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = List.of(GmailScopes.GMAIL_LABELS, GmailScopes.MAIL_GOOGLE_COM, GmailScopes.GMAIL_SEND);
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credential.json";

    private static Credential getCredentials(NetHttpTransport httpTransport) {
        try (InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH)) {
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        try {
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            String user = "me";
            Message message = createMessageWithEmail(createEmail(user));
            service.users().messages().send(user, message).execute();
            System.out.println("Email sent");
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private static MimeMessage createEmail(String user) {
        Session session = Session.getDefaultInstance(new Properties(), null);
        MimeMessage email = new MimeMessage(session);
        try {
            email.setFrom(new InternetAddress(user));
            email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress("xzi123@mail.ru"));
            email.setSubject("Spring-shop homework 8");
            email.setText("Spring-shop with GMAIL from Tarkhov Evgeniy");
//            email.setContent(createAttachment());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return email;
    }

    private static Message createMessageWithEmail(MimeMessage email){
        String encodedEmail = "";
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            email.writeTo(buffer);
            byte[] bytes = buffer.toByteArray();
            encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return new Message().setRaw(encodedEmail);
    }

    private static Multipart createAttachment() throws MessagingException {
        String file = "src/main/resources/application.yaml";
        String fileName = "properties";
        DataSource source = new FileDataSource(file);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }
}
