package com.ProjetoIntegrador.feeling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.feeling.service.EmailService;

	@RestController
	@RequestMapping("/email-send")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public class EmailController {
		
		@Autowired
		private EmailService emailService;
		
		@GetMapping("")
		public String sendEmail() {
			return emailService.sendMail();
		}
	    
	}

