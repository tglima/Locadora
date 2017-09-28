package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.repository.ClienteRepository;

@Named
@RequestScoped
public class CadCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cliente novoCliente;
	
	@Inject
	private ClienteRepository repository;
	
	public void cadastrar(){
		if (repository.salvarNovo(this.novoCliente)) {
			this.novoCliente = null;			
		} 
	}
	
	//Getters de acesso aos Enums
	public OpGeneros[] getGeneros(){
		return OpGeneros.values();
	}
	
	public Cliente getNovoCliente() {
		return novoCliente;
	}
}
