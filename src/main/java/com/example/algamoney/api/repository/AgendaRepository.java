package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Agenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    public Page<Agenda> findByNomeContaining(String nome, Pageable pageable);
    
}
