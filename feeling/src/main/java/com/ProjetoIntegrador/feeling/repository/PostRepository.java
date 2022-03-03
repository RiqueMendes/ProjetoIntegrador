package com.ProjetoIntegrador.feeling.repository;

import java.util.List;

import com.ProjetoIntegrador.feeling.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findAllByTitleContainingIgnoreCase(String title);
    public List<Post> findAllByTextContainingIgnoreCase(String text);
}