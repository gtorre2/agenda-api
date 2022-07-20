package com.example.algamoney.api.resource;

import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Agenda;
import com.example.algamoney.api.repository.AgendaRepository;
import com.example.algamoney.api.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaResource {

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private AgendaService agendaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_AGENDA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Agenda> criar(@Valid @RequestBody Agenda pessoa, HttpServletResponse response) {
		Agenda agendaSalva = agendaService.salvar(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, agendaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(agendaSalva);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_AGENDA') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Agenda> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Agenda> agenda = agendaRepository.findById(codigo);
		return agenda.isPresent() ? ResponseEntity.ok(agenda.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_AGENDA') and hasAuthority('SCOPE_write')")
	public void remover(@PathVariable Long codigo) {
		this.agendaRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_AGENDA') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Agenda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Agenda agenda) {
		Agenda agendaSalva = agendaService.atualizar(codigo, agenda);
		return ResponseEntity.ok(agendaSalva);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_AGENDA')")
	public Page<Agenda> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return agendaRepository.findByNomeContaining(nome, pageable);
	}
	
}