package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Agenda;
import com.example.algamoney.api.repository.AgendaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
 	
	@Autowired
	private AgendaRepository pessoaRepository;

	public Agenda salvar(Agenda agenda) {
		return pessoaRepository.save(agenda);
	}

	public Agenda atualizar(Long codigo, Agenda agenda) {
		Agenda agendaSalva = buscarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(agenda, agendaSalva, "codigo");
		return pessoaRepository.save(agendaSalva);
	}

	public Agenda buscarPessoaPeloCodigo(Long codigo) {
		Agenda agendaSalva =  pessoaRepository.findById(codigo)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return agendaSalva;
	}
}
