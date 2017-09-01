package br.edu.tglima.locadora.util;

import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.locadora.models.pessoa.TiposCargo;
import br.edu.tglima.util.StringUtil;

public class FuncUtil {

	public final static Funcionario criarFuncExemplo(Funcionario f) {
		f.setId(635l);
		f.setDataCadastro(TempoUtil.setDateLastMouth());
		f.setNome("Ada");
		f.setSobrenome("Augusta Byron King");
		f.setGenero(OpGeneros.F);
		f.setDataNasc(TempoUtil.setDate20yearsAgo());
		f.setTelefone("(15) 8888-88888");
		f.setCargo(TiposCargo.GE);
		f.setPassword("123456");
		return f;
	}

	public final static void exibirDadosNoConsole(Funcionario f) {
		System.out
				.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
		System.out.println("Nº de Cadatro: " + f.getId());
		System.out.println("Data de cadastro: " + f.getDataCadastro());
		System.out.println("Nome: " + f.getNome());
		System.out.println("Sobrenome: " + f.getSobrenome());
		System.out.println("Gênero: " + f.getGenero().getLabel());
		System.out.println("Data de nascimento: " + f.getDataNasc());
		System.out.println("Telefone de contato: " + f.getTelefone());
		System.out.println("Cargo: " + f.getCargo().getLabel());
		System.out.println("Senha cadastrada: " + f.getPassword());
		System.out
				.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
	}

	public final static Funcionario fmtFuncToShow(Funcionario f){
		f.setNome(StringUtil.capitalizeFirstLetters(f.getNome()));
		f.setSobrenome(StringUtil.capitalizeFirstLetters(f.getSobrenome()));
		return f;
	}
	
	public final static Funcionario fmtFuncToSave(Funcionario f){
		f.setNome(f.getNome().toLowerCase());
		f.setSobrenome(f.getSobrenome().toLowerCase());
		return f;
	}
	
	
}
