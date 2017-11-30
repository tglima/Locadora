package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpCombustiveis;
import br.edu.tglima.locadora.models.veiculo.OpCores;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.repository.VeiculoRepository;

@Named
@ApplicationScoped
public class GeVeic implements Serializable {

	private static final long serialVersionUID = 7468745842374434002L;

	/*
	 * TODO Trocar escopo da aplicação por outro mais adequado
	 */

	@Inject
	private VeiculoRepository repository;
	private List<Veiculo> veicEncontrados = new ArrayList<Veiculo>();
	private Veiculo selectedVeic;
	private boolean resultEmpty;
	private OpStatus status;
	private OpMarcas marca;
	private OpCategorias categoria;
	private byte tipoDeBusca = 0;
	private Integer kmInicial;

	public void buscarPorMarca() {
		try {
			veicEncontrados = repository.buscarPorMarca(marca, OpStatus.ALUGADO, false);
			if (veicEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
		}

	}

	public void buscarPorCategoria() {
		try {
			veicEncontrados = repository.buscaPorCategoria(categoria, OpStatus.ALUGADO, false);
			if (veicEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
		}

	}

	public void buscarPorStatus() {
		try {
			veicEncontrados = repository.buscaPorStatus(status);
			if (veicEncontrados.isEmpty()) {
				resultEmpty = true;
			}
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
		}

	}

	public void atualizaKmInicial() {
		this.kmInicial = selectedVeic.getKmInicial();
	}

	public void salvarStatus(Long id, OpStatus novoStatus) {

		try {
			Veiculo veic = repository.buscarPorId(id);
			veic.setStatus(novoStatus);
			repository.salvarEdicao(veic);
			enviarMsgSucesso(
					"O Status do veículo " + veic.getPlaca().toUpperCase() + ", foi alterado com sucesso");
		} catch (Exception e) {
			enviarMsgErro("Erro, não foi possível salvar o status do veículo");
			enviarMsgErro(e.getMessage());
		}
		refazerPesquisa();
	}

	public void salvar() {
		try {
			repository.salvarEdicao(selectedVeic);
			enviarMsgSucesso("As alterações do veículo " + selectedVeic.getPlaca().toUpperCase()
					+ " foram salvas com sucesso!");
			refazerPesquisa();
		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("A placa informada já pertence a outro veículo.");
			} else {
				enviarMsgErro(e.getMessage());
			}
		}

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

	public OpStatus[] getSituacoes() {
		OpStatus[] status = { OpStatus.DISPONIVEL, OpStatus.INOPERANTE, OpStatus.MANUTENCAO };
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

	public OpStatus getStatus() {
		return status;
	}

	public void setStatus(OpStatus status) {
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
