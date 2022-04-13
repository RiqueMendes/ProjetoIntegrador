package com.ProjetoIntegrador.feeling.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.feeling.model.Email;
import com.ProjetoIntegrador.feeling.service.EmailService;

@RestController
@RequestMapping("/email-send")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping
	public String sendEmail(@Valid @RequestBody Email email) {
		return emailService.sendMail(email);
	}

}
