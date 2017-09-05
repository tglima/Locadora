package br.edu.tglima.locadora.util;


import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.OpGeneros;
import br.edu.tglima.util.StringUtil;

public class ClienteUtil {
	
	public final static Cliente criarClienteExemplo(Cliente c){
		c.setId(200l);
		c.setDataCadastro(TempoUtil.setDateLastMouth());
		c.setNome("Franklin");
		c.setSobrenome("Delano Roosevelt");
		c.setGenero(OpGeneros.M);
		c.setDataNasc(TempoUtil.setDate20yearsAgo());
		c.setTelefone("(11) 9999-99999");
		c.setHabilitacao("9911223344");
		c.setValidadeHab(TempoUtil.setDate2Yearslater());
		return c;
	}
	
	
	public final static void exibirDadosNoConsole(Cliente c){
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
		System.out.println("Nº de Cadatro: " + c.getId());
		System.out.println("Data de cadastro: " + c.getDataCadastro());
		System.out.println("Nome: " + c.getNome());
		System.out.println("Sobrenome: " + c.getSobrenome());
		System.out.println("Gênero: " + c.getGenero().getLabel());
		System.out.println("Data de nascimento: "+ c.getDataNasc());
		System.out.println("Telefone de contato: " + c.getTelefone());
		System.out.println("CNH: " + c.getHabilitacao());
		System.out.println("Validade da CNH: " + c.getValidadeHab());
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
	}
	
	public final static Cliente fmtClienteToShow(Cliente c){
		c.setNome(StringUtil.capitalizeFirstLetters(c.getNome()));
		c.setSobrenome(StringUtil.capitalizeFirstLetters(c.getSobrenome()));
		return c;
	}
	
	public final static Cliente fmtClienteToSave(Cliente c){
		c.setNome(c.getNome().trim());
		c.setNome(c.getNome().toLowerCase());
		c.setSobrenome(c.getSobrenome().trim());
		c.setSobrenome(c.getSobrenome().toLowerCase());
		return c;
	}

}
