package ecommerce.com;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import ecommerce.com.CheckoutController;

public class emailConfirmation  {
	private static final String EMAIL_FROM = "rdelgado5050@gmail.com"; //DO NOT CHANGE THIS
	private static final String EMAIL_TO = "rdelgado134@gmail.com";
	private static final String APP_PASSWORD = "seps bcuv sdmp okmy"; //DO NOT CHANGE THIS
    private CheckoutController checkoutController;

    //private String test= getEmail();
	
	public static void main(String[] args) throws Exception {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(EMAIL_FROM));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO));
		message.setSubject("CoffeeAndCode :: Purchase Confirmation");
		message.setText("Thank you for your ordering from CoffeeAndCode, go Matadors! \n\n --sent from Ecommerce project");
		Transport.send(message);
	}

        public emailConfirmation(CheckoutController controller) {
        this.checkoutController = controller;
    }
	
	private static Session getEmailSession() {
		return Session.getInstance(getGmailProperties(), new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
		    }
		});
	}
	
	private static Properties getGmailProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return prop;
	}
}