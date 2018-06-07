package org.syntinel.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import com.syntinel.model.Customer;
import com.syntinel.model.Food;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

abstract public class Utilities 
{
	private final static String key = "saltandpepper";
	private final static String username = "chollacasestudy@gmail.com";
	private final static String password = "Chrysanth3mum";
	private static Properties props = new Properties();
	
	public final static String getToday()
	{
		SimpleDateFormat fDate = new SimpleDateFormat("dd-MM-yyyy");
		Date now = new Date();
		String today = fDate.format(now);
		return today;
	}
	
	//TODO: change to return an int instead of string
	public final static String createUniqueId()
	{
		int randomID = (int) Math.round((Math.random() * System.nanoTime()));
		randomID = Math.abs(randomID);
		return Integer.toString(randomID);
	}
	
	public final static String encryptPassword(String password)
	{
		String encrypted_password = null;
		try
		{
			SecretKeySpec skeyspec = new SecretKeySpec(key.getBytes(), "Blowfish");
			Cipher cipher = null;
			cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
			byte[] encrypted = cipher.doFinal(password.getBytes());
			encrypted_password = new String(encrypted);
		}catch(Exception e)
		{
			e.getMessage();
		}
		
		return encrypted_password;
	}
	
	public final static void sendMail(Customer customer)
	{
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		String subject = "Welcome to Mummy's";
		
		Session session = Session.getInstance(props, 
				new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}}	
			);
		
		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(customer.getEmail()));
			
		
			if(customer.getItems() != null)
			{
				String buildOrderMessage = "";
				subject = "Your Order Receipt";
				message.setSubject(subject);
				
				buildOrderMessage += "<h1>Hello " + customer.getFirst_name() + "</h1><br>";
				buildOrderMessage += "We've place your order<br><hr>";
				buildOrderMessage += "order #: " + customer.getOrderId() + "<br>";
				buildOrderMessage += "Expected date: " + customer.getExpectedDate() + "<br><hr>";
				buildOrderMessage += "<table>";
				for(Food item : customer.getItems())
				{
					buildOrderMessage += "<tr>";
					buildOrderMessage += "<td>"+ item.getName() +"</td>";
					buildOrderMessage += "<td>"+ item.getDescription() +"</td>";
					buildOrderMessage += "<td>"+ item.getFoodGroup() +"</td>";
					buildOrderMessage += "<td>"+ item.getPrice() +"</td>";
					buildOrderMessage += "</tr>";
				}
				buildOrderMessage += "</table>";
				buildOrderMessage += "<hr><p><b>Instructions:</b>&nbsp;" + customer.getInstructions() + "</p>";
				switch(customer.getPaymentType())
				{
					case "0":
						buildOrderMessage += "<p>Paid with PayPal</p>";
						break;
					case "1":
						buildOrderMessage += "<p>Paid with Credit</p>";
						break;
					case "2":
						buildOrderMessage += "<p>Paid with Cash</p>";
						break;
				}
				buildOrderMessage += "<p><b>Total: " + customer.getRunning_total() + "</b></p><br>";
				buildOrderMessage += "<h2>Thank You</h2>";
				
				message.setContent(buildOrderMessage,"text/html");
			}
			else
			{
				message.setSubject(subject);
				message.setContent(""
						+ "<h1>Mummy's Restaurant</h1>"
						+ "<h2>Hello: "+ customer.getFirst_name() +"</h2>"
						+ "<p>We want to welcome you Mummy's Restaurant&nbsp;we "
						+ "hope you find our meal selection exquisite.</p>",
			             "text/html");
				
			}
			
			Transport.send(message);
		}catch(MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
}
