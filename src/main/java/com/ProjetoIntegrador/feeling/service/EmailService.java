package com.ProjetoIntegrador.feeling.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@SuppressWarnings("deprecation")
	public String sendMail() {
		try {
	    	SimpleEmail htmlEmail = new SimpleEmail();
	    	htmlEmail.addTo("feeling.rede@gmail.com");
	    	htmlEmail.setSubject("Assunto");
	    	htmlEmail.setFrom("feeling.rede@gmail.com");
	    	htmlEmail.setHostName("smtp.googlemail.com");
	    	htmlEmail.setMsg("teste mensagem");
	    	htmlEmail.setSmtpPort(587);
	    	htmlEmail.setTLS(true);
	    	htmlEmail.setSSL(true);                
	    	htmlEmail.setAuthenticator(new DefaultAuthenticator("feeling.rede@gmail.com", "1feeling-rede"));            
	    	htmlEmail.send();
	    	return "mensagem enviada com sucesso!";
		}
		catch (Exception e) {
			// TODO: handle exception
			return "Erro de envio "+ e;
		}
    }
}
