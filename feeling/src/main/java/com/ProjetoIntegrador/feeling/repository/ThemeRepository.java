package com.ProjetoIntegrador.feeling.repository;

import java.util.List;

import com.ProjetoIntegrador.feeling.model.Theme;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    public List<Theme> findAllByTopicContainingIgnoreCase(String topic);
}