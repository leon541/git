package com.leon;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;      

public class SendMail {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.debug", "true");
		props.setProperty("mail.smtp.auth", "false");
		props.setProperty("mail.host", "10.206.12.109");
		props.setProperty("mail.transport.protocol", "smtp");

		

		for(int i = 0 ; i< 10000 ; i ++) {
			Session session = Session.getInstance(props);
			Message msg = new MimeMessage(session);
			msg.setSubject("JavaMail");
			msg.setText("JavaMail");
			msg.setFrom(new InternetAddress("java_mail_001" + i +  "@163.com"));

			Transport transport = session.getTransport();
			transport.connect("java_mail_001", "javamail");
			Address[] recips = new Address[] {new InternetAddress("lwang@tom.com"),
					new InternetAddress("admin@tom.com") ,
					new InternetAddress("adminx" + i +"@tom.com"),
					new InternetAddress("adminy" + i +"@tom.com"),
					new InternetAddress("adminz" + i +"@tom.com"),
					new InternetAddress("admina" + i +"@tom.com"),
					new InternetAddress("adminb" + i +"@tom.com"),
					new InternetAddress("adminc" + i +"@tom.com"),
					new InternetAddress("admin" + i + "@tom.com"),
					new InternetAddress(i+"@tom.com")};
			transport.sendMessage(msg, recips);
			transport.close();
		
			Thread.sleep(10);
		}
	}
}