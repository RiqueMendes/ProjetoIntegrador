package com.ProjetoIntegrador.feeling.repository;

import java.util.List;

import com.ProjetoIntegrador.feeling.model.Tema;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

    public List<Tema> findAllByTopicContainingIgnoreCase(String topic);
}