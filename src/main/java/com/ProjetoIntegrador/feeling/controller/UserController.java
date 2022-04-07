package com.ProjetoIntegrador.feeling.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoIntegrador.feeling.model.UserLoginDTO;
import com.ProjetoIntegrador.feeling.model.UserModel;
import com.ProjetoIntegrador.feeling.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	/*
	 * Metodo logar
	 */

	@PostMapping("/login")
	public ResponseEntity<UserLoginDTO> autentication(@Valid @RequestBody Optional<UserLoginDTO> user) {
		return userService.userLogin(user);

	}

	/*
	 * Metodo cadastrar
	 */

	@PostMapping("/register")
	public ResponseEntity<UserModel> register(@Valid @RequestBody UserModel user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
	}

	/*
	 * Metodo buscar usuário por id
	 */

	@GetMapping("/id/{id}")
	public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
		return userService.findById(id);
	}

	/*
	 * Metodo listar usuários
	 */

	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAlllUsers() {
		return userService.listUsers();
	}

	/*
	 * Metodo deletar usuário
	 */

	@DeleteMapping("/delete/{id}")
	@SuppressWarnings("rawtypes")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		return userService.deleteById(id);
	}

	/*
	 * Metodo atualizar usuário
	 */

	@PutMapping("/update")
	public ResponseEntity<UserModel> updateUser(@Valid @RequestBody UserModel user) {
		return userService.updateUser(user);
	}

}
