package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.repository.ClienteRepository;

@Named
@RequestScoped
public class EditCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteRepository repository;

	@Inject
	Cliente clienteEmEdicao;
	
	@PostConstruct
	public void init() {
		/* TODO
		 * O cliente a ser editado será passado por parâmetro e carregado aqui.
		 * Por enquanto será carregado um cliente específico do BD.
		 */
		clienteEmEdicao = repository.buscarPorId(100l);		
	}

	public void salvarEdicao() {
		clienteEmEdicao = repository.salvarEdicao(clienteEmEdicao);
	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}
	
	public Cliente getClienteEmEdicao() {
		return clienteEmEdicao;
	}


}
