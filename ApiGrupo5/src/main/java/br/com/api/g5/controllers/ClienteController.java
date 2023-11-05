package br.com.api.g5.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g5.dto.ClienteAtualizarDTO;
import br.com.api.g5.dto.ClienteDTO;
import br.com.api.g5.services.ClienteService;
import br.com.api.g5.services.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	private EmailService emailService;
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
	
	@Autowired
	ClienteService clienteService;
	
	@ApiOperation(value="Retorna um cliente", notes="Cliente")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um cliente"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
	@GetMapping("/buscar/{id}")
	public ClienteDTO buscarPorId(@PathVariable Integer id) {
		return clienteService.buscarPorId(id);
	}

	@GetMapping("/listar")
	public List<ClienteDTO> listarTodos() {
		return clienteService.listarTodos();
	}
	
//  @PostMapping("/salvar")
//	public Cliente salvar(@Valid @RequestBody Cliente cliente) {
//		return clienteService.salvar(cliente);
//	}
	
	@PutMapping("/atualizar/{id}")
	public ClienteAtualizarDTO atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteAtualizarDTO cliente) {
		return clienteService.atualizar(id, cliente);
	}
	
	@PutMapping("/ativar/{id}")
	public void ativarLogico(@PathVariable Integer id) {
		ClienteDTO clienteDTO = clienteService.buscarPorId(id);
		clienteService.ativarLogico(id);
		emailService.envioEmailAtivacaoContaCliente(clienteDTO);
	}
	
	@DeleteMapping("/remover/{id}")
	public void removerLogico(@PathVariable Integer id) {
		ClienteDTO clienteDTO = clienteService.buscarPorId(id);
		clienteService.removerLogico(id);
		emailService.envioEmailDesativacaoContaCliente(clienteDTO);
	}
			
}
