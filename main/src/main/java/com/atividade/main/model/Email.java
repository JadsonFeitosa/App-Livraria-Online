package com.atividade.main.model;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Email {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String email, String title, String mensagem) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(title);
		msg.setText(mensagem);
		javaMailSender.send(msg);
	}

	public void sendEmailComAnexo(String email, String title, String mensagem, File anexo)throws MessagingException, IOException {
		// instaciando objeto da mensagem para o envio
		MimeMessage msg = javaMailSender.createMimeMessage();

		// instaciando as partes da menssagem
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//      aqui colocamos o destinatario do email
		helper.setTo(email);
// titulo do email
		helper.setSubject(title);

// codigo para colocar o texto em html no email
		helper.setText("<h1>" +"Livraria Online"+ "</h1>"+"\n\r"+mensagem, true);
		
//nesta linha fica o anexo do email a ser enviado
		helper.addAttachment(anexo.getName(), new FileSystemResource(anexo));

//comando de envio
		javaMailSender.send(msg);

	}

}
