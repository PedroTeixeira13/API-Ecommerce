package br.com.api.g5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.EnderecoDto;
import br.com.api.g5.entities.Endereco;
import br.com.api.g5.services.EnderecoService;

@CrossOrigin
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping("/buscar/{id}")
	public Endereco buscarPorId(@PathVariable Integer id) {
		return enderecoService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<Endereco> listarTodos() {
		return enderecoService.listarTodos();
	}
	
	@PostMapping("/salvar")
	public Endereco salvar(@Valid @RequestBody EnderecoDto endereco) {
		return enderecoService.salvar(endereco);

	}
	
	@PutMapping("/atualizar/{id}")
	public Endereco atualizar(@PathVariable Integer id, @Valid @RequestBody Endereco endereco) {
		return enderecoService.atualizar(id, endereco);
	}
	
	@DeleteMapping("/remover/{id}")
	public void remover(@PathVariable Integer id) {
		enderecoService.remover(id);
	}
}
