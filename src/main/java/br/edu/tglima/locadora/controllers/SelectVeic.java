package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.repository.VeiculoRepository;

@Named
@ApplicationScoped
public class SelectVeic implements Serializable {
	private static final long serialVersionUID = 1L;

	// TODO Trocar o scopo por um mais adequado.

	@Inject
	private VeiculoRepository repository;

	private List<Veiculo> veicEncontrados = new ArrayList<Veiculo>();
	private Veiculo selectedVeic;
	private boolean resultEmpty;
	private final OpStatus DISPONIVEL = OpStatus.DISPONIVEL;
	private OpMarcas marca;
	private OpCategorias categoria;
	private byte tipoDeFiltro = 0;

	public void filtrarPorMarca() {
		try {
			veicEncontrados = repository.buscaPorParametros(DISPONIVEL, marca);
			if (veicEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
		}
	}

	public void filtrarPorCategoria() {
		try {
			veicEncontrados = repository.buscaPorParametros(DISPONIVEL, categoria);
			if (veicEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
		}
	}

	public void exibirTodos() {
		try {
			veicEncontrados = repository.buscaPorStatus(DISPONIVEL);
			if (veicEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquisa! " + e.getMessage());
		}
	}

	public void aplicarFiltro() {
		resultEmpty = false;
		if (tipoDeFiltro == 1) {
			exibirTodos();
			categoria = null;
			marca = null;
		} else if (tipoDeFiltro == 2) {
			categoria = null;
		} else if (tipoDeFiltro == 3) {
			marca = null;
		}
	}

	// Getters de acesso aos Enums
	public OpMarcas[] getMarcas() {
		return OpMarcas.values();
	}

	public OpCategorias[] getCategorias() {
		return OpCategorias.values();
	}

	public Veiculo getSelectedVeic() {
		return selectedVeic;
	}

	public void setSelectedVeic(Veiculo selectedVeic) {
		this.selectedVeic = selectedVeic;
	}

	public VeiculoRepository getRepository() {
		return repository;
	}

	public List<Veiculo> getVeicEncontrados() {
		return veicEncontrados;
	}

	public boolean isResultEmpty() {
		return resultEmpty;
	}

	public OpMarcas getMarca() {
		return marca;
	}

	public void setMarca(OpMarcas marca) {
		this.marca = marca;
	}

	public OpCategorias getCategoria() {
		return categoria;
	}

	public void setCategoria(OpCategorias categoria) {
		this.categoria = categoria;
	}

	public byte getTipoDeFiltro() {
		return tipoDeFiltro;
	}

	public void setTipoDeFiltro(byte tipoDeFiltro) {
		this.tipoDeFiltro = tipoDeFiltro;
	}

}
