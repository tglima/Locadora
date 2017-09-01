package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.models.pessoa.TiposCargo;
import br.edu.tglima.locadora.util.FacesUtil;
import br.edu.tglima.locadora.util.FuncUtil;

@Named
@ViewScoped
public class CadFunc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Funcionario nf;

	private String confPassword;

	public void cadastrar() {
		if (this.nf.getPassword().equals(confPassword)) {
			this.nf = FuncUtil.fmtFuncToSave(nf);
			FacesUtil.enviarMsgSucesso(null, "Funcionário cadastrado com sucesso!");
			System.out.println("::::::::::::::::::::::::::::::::::: FUNCIONÁRIO SALVO :::::::::::::::::::::::::::::::::::");
			FuncUtil.exibirDadosNoConsole(nf);
		} else {
			FacesUtil.enviarMsgErro(null, "As senhas informadas não são iguais!");
		}
		this.nf = new Funcionario();
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
