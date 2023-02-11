package io.aptech.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

public class SendMail {
    public static int send() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        final String username = "tien.pv.2054@aptechlearning.edu.vn";
        final String password = "d a n j d r z k u r m r i p e j";

        // dang nhap gmail
        Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("tien.pv.2054@aptechlearning.edu.vn", "Phung Van Tien"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("phungtien07062003@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mr.Tien,\n\n Số định danh của bạn là: "+ randomNumber);

            Transport.send(message);

            System.out.println("Done");
        }catch(MessagingException | UnsupportedEncodingException e ){
            e.printStackTrace();
        }
        return randomNumber;
    }
}
