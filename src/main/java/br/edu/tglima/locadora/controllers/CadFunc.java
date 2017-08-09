package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.models.pessoa.TiposCargo;
import br.edu.tglima.locadora.util.FacesUtil;

@Named
@ViewScoped
public class CadFunc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionario nf;

	private String confPassword;

	public void cadastrar() {
		
		if (this.nf.getPassword().equals(confPassword)) {
			FacesUtil.enviarMsgSucesso(null, "Funcionário cadastrado com sucesso!");
			exibirDadosInformados();
		} else {
			FacesUtil.enviarMsgErro(null, "As senhas informadas não são iguais!");
		}
		this.nf = new Funcionario();
	}

	private void exibirDadosInformados() {
		System.out.println("###########################################################");
		System.out.println("                   Dados informados");
		System.out.println("Nome do funcionário: " + this.nf.getNome());
		System.out.println("Sobrenome do funcionário: " + this.nf.getSobrenome());
		System.out.println("Gênero do funcionário: " + this.nf.getGenero());
		System.out.println("Data de nascimento: " + this.nf.getDataNasc());
		System.out.println("Telefone principal: " + this.nf.getTelefone());
		System.out.println("Cargo: " + this.nf.getCargo());
		System.out.println("Senha informada: " + this.nf.getPassword());
		System.out.println("###########################################################\n");

	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

	public TiposCargo[] getCargos() {
		return TiposCargo.values();
	}

	// Getter e Setters Padrões
	public Funcionario getNf() {
		return nf;
	}

	public String getConfPassword() {
		return confPassword;
	}
	
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	

}
