package com.reinaldoapi.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.ClientEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldoapi.domain.model.Funcionario;
import com.reinaldoapi.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioRepository fr;

	@GetMapping
	public List<Funcionario> listar() {
		return fr.findAll();
	}

	@GetMapping("/{funcionarioId}")
	public ResponseEntity<Funcionario> buscar(@PathVariable Long funcionarioId) {
		Optional<Funcionario> funcionario = fr.findById(funcionarioId);
		if (funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario salvar(@RequestBody Funcionario funcionario) {
		return fr.save(funcionario);
	}

	@PutMapping("/{funcionarioId}")
	public ResponseEntity<Funcionario> editar(@PathVariable Long funcionarioId, 
			@RequestBody Funcionario funcionario) {
		if(fr.existsById(funcionarioId)) {
			funcionario.setId(funcionarioId);
			return ResponseEntity.ok(fr.save(funcionario));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{funcionarioId}")
	public ResponseEntity<Funcionario> deletar(@PathVariable Long funcionarioId){
		if (fr.existsById(funcionarioId)) {
			fr.deleteById(funcionarioId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
		
		
	}
	
	
}
