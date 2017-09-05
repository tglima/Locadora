package br.edu.tglima.locadora.util;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;

public class VeiculoUtil {

	public final static Veiculo criarVeicExemplo(Veiculo v) {
		v.setId(483237l);
		v.setDataCadastro(TempoUtil.setDateLastMouth());
		v.setPlaca("aaa-0000");
		v.setMarca(OpMarcas.FORD);
		v.setModelo("ka");
		v.setCategoria(OpCategorias.HATCH);
		v.setCor(OpCores.PRETO);
		v.setAno(2015);
		v.setCombustivel(OpCombustiveis.FLEX);
		v.setStatus(OpStatus.INOPERANTE);
		v.setKmInicial(0);
		v.setKmAtual(100);
		v.setValorDiaria(18.30);
		return v;
	}

	public final static void exibirDadosNoConsole(Veiculo v) {
		System.out
				.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
		System.out.println("Nº de Cadatro: " + v.getId());
		System.out.println("Data de cadastro: " + v.getDataCadastro());
		System.out.println("Placa: " + v.getPlaca());
		System.out.println("Marca: " + v.getMarca());
		System.out.println("Modelo: " + v.getModelo());
		System.out.println("Categoria: " + v.getCategoria());
		System.out.println("Cor: " + v.getCor());
		System.out.println("Ano de fabricação: " + v.getAno());
		System.out.println("Combustível: " + v.getCombustivel());
		System.out.println("Situação atual do Veículo: " + v.getStatus());
		System.out.println("Km Inicial: " + v.getKmInicial());
		System.out.println("Km Atual: " + v.getKmAtual());
		System.out.println("Valor da locação: R$ " + v.getValorDiaria());
		System.out
				.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
	}
	
	public final static Veiculo fmtVeicToShow(Veiculo v){
		v.setPlaca(v.getPlaca().toUpperCase());
		v.setModelo(v.getModelo().toUpperCase());
		return v;
	}
	
	public final static Veiculo fmtVeicToSave(Veiculo v){
		v.setPlaca(v.getPlaca().toLowerCase());
		v.setModelo(v.getModelo().trim());
		v.setModelo(v.getModelo().toLowerCase());
		return v;
	}
	
	
}
