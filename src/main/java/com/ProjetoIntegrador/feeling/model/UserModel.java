package com.ProjetoIntegrador.feeling.model;

import java.util.List;

import javax.persistence.CascadeType;

/*
    Responsavel pela criação da tabela User, criando também os getters e setters.
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }

    @Size(min = 3)
    @NotNull(message = "O campo nome é obrigatório.")
    private String name;

    @Email(message = "E-mail inválido.")
    @Schema(example = "email@email.com")
    @NotNull(message = "O campo e-mail é obrigatório.")
    private String email;

    @NotNull(message = "O campo senha é obrigatório.")
    @Size(min = 6)
    private String password;

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("user")
    private List<Post> post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
