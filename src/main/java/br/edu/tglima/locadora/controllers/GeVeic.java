package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.VeicStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.service.VeicService;

@Named
@ViewScoped
public class GeVeic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private VeicService service;

	private List<Veiculo> veicEncontrados;
	private Veiculo selectedVeic;
	private boolean resultEmpty;
	private VeicStatus status;
	private OpMarcas marca;
	private OpCategorias categoria;
	private byte tipoDeBusca = 0;
	private Integer kmInicial;

	public void buscarPorMarca() {
		veicEncontrados = service.buscarPorMarcaENaoAlugado(marca);
		if (veicEncontrados.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void buscarPorCategoria() {
		veicEncontrados = service.buscarPorCategoriaENaoAlugado(categoria);
		if (veicEncontrados.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void buscarPorStatus() {
		veicEncontrados = service.buscarPorStatus(status);
		if (veicEncontrados.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void atualizaKmInicial() {
		this.kmInicial = selectedVeic.getKmInicial();
	}

	public void salvarStatus(Long id, VeicStatus novoStatus) {
		service.alterarStatus(id, novoStatus);
		refazerPesquisa();
	}

	public void salvar() {
		selectedVeic = service.salvarEdicao(selectedVeic);
		refazerPesquisa();
	}

	private void refazerPesquisa() {
		if (tipoDeBusca == 1) {
			buscarPorStatus();
		} else if (tipoDeBusca == 2) {
			buscarPorMarca();
		} else if (tipoDeBusca == 3) {
			buscarPorCategoria();
		}

	}

	public void clearOption() {
		resultEmpty = false;
		if (tipoDeBusca == 1) {
			categoria = null;
			marca = null;
		} else if (tipoDeBusca == 2) {
			status = null;
			categoria = null;
		} else if (tipoDeBusca == 3) {
			status = null;
			marca = null;
		}

	}

	/***************************************
	 * Getters de acesso aos Enums
	 ***************************************/

	public OpCategorias[] getCategorias() {
		return OpCategorias.values();
	}

	public OpMarcas[] getMarcas() {
		return OpMarcas.values();
	}

	public VeicStatus[] getSituacoes() {
		VeicStatus[] status = { VeicStatus.DISPONIVEL, VeicStatus.INOPERANTE, VeicStatus.MANUTENCAO };
		return status;
	}

	public OpCores[] getCores() {
		return OpCores.values();
	}

	public OpCombustiveis[] getCombustiveis() {
		return OpCombustiveis.values();
	}

	public byte getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(byte tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public Integer getKmInicial() {
		return kmInicial;
	}

	public OpCategorias getCategoria() {
		return categoria;
	}

	public void setCategoria(OpCategorias categoria) {
		this.categoria = categoria;
	}

	public OpMarcas getMarca() {
		return marca;
	}

	public void setMarca(OpMarcas marca) {
		this.marca = marca;
	}

	public VeicStatus getStatus() {
		return status;
	}

	public void setStatus(VeicStatus status) {
		this.status = status;
	}

	public boolean isResultEmpty() {
		return resultEmpty;
	}

	public List<Veiculo> getVeicEncontrados() {
		return veicEncontrados;
	}

	public Veiculo getSelectedVeic() {
		return selectedVeic;
	}

	public void setSelectedVeic(Veiculo selectedVeic) {
		this.selectedVeic = selectedVeic;
	}

}
