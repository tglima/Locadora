package br.edu.tglima.locadora.controllers;

import java.io.Serializable;

import javax.annotation.PostConstruct;
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
public class EditFunc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Funcionario sf;
	private String confPassword;

	@PostConstruct
	public void init() {
		sf = FuncUtil.criarFuncExemplo(sf);
		System.out.println("::::::::::::::::::::::::::::::::: FUNCIONÁRIO CARREGADO :::::::::::::::::::::::::::::::::");
		FuncUtil.exibirDadosNoConsole(sf);
		confPassword = sf.getPassword();
	}

	public void salvarEdicao() {
		if (this.sf.getPassword().equals(confPassword)) {
			FacesUtil.enviarMsgSucesso(null, "Alterações no Funcionário salvas com sucesso!");
			System.out.println(
					"::::::::::::::::::::::::::::::::::: FUNCIONÁRIO SALVO :::::::::::::::::::::::::::::::::::");
			FuncUtil.exibirDadosNoConsole(sf);
		} else {
			FacesUtil.enviarMsgErro(null, "As senhas informadas não são iguais!");
		}
	}

	// Getters de acesso aos Enums
	public OpGeneros[] getGeneros() {
		return OpGeneros.values();
	}

	public TiposCargo[] getCargos() {
		return TiposCargo.values();
	}

	public Funcionario getSf() {
		return sf;
	}

	public void setSf(Funcionario sf) {
		this.sf = sf;
	}

	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

}
