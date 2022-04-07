package com.ProjetoIntegrador.feeling.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import com.ProjetoIntegrador.feeling.model.UserLoginDTO;
import com.ProjetoIntegrador.feeling.model.UserModel;
import com.ProjetoIntegrador.feeling.repository.UserRepository;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel registerUser(UserModel user) {

        Optional<UserModel> userM = repository.findByEmail(user.getEmail());

        if (userM.isPresent()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email ja cadastrado");

        } else {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String passwordEncoder = encoder.encode(user.getPassword());
            user.setPassword(passwordEncoder);

            return repository.save(user);
        }
    }

    public ResponseEntity<UserLoginDTO> userLogin(Optional<UserLoginDTO> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<UserModel> userM = repository.findByEmail(user.get().getEmail());

        if (userM.isPresent()) {
            if (encoder.matches(user.get().getPassword(), userM.get().getPassword())) {
                String auth = user.get().getEmail() + ":" + user.get().getPassword();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(authHeader);
                user.get().setId(userM.get().getId());
                user.get().setName(userM.get().getName());
                user.get().setPhoto(userM.get().getPhoto());
                user.get().setType(userM.get().getType());
                
                return ResponseEntity.status(HttpStatus.OK).body(user.get());
            }
            
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario nao encontrado");
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity deleteById(Long id) {
        Optional<UserModel> user = repository.findById(id);

        if (user.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<UserModel>> listUsers(){
        List<UserModel> users = repository.findAll();
        
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
    }

    public ResponseEntity<UserModel> findById(Long id) {
        Optional<UserModel> user = repository.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

    public ResponseEntity<UserModel> updateUser(UserModel user) {
        Optional<UserModel> userM = repository.findById(user.getId());

        if (userM.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            repository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }

}
