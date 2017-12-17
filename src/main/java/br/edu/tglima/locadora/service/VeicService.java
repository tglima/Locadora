package br.edu.tglima.locadora.service;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.VeicStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.repository.VeiculoRepository;

public class VeicService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoRepository repository;

	public Veiculo cadastrar(Veiculo veic) {
		try {
			repository.salvarNovo(fmtToSave(veic));
			enviarMsgSucesso("Veículo cadastrado com sucesso!");
			veic = null;

		} catch (Exception e) {
			enviarMsgErro("Erro, veículo não cadastrado!");

			if (e.getMessage().contains("ConstraintViolationException")) {

				enviarMsgErro("A placa informada já pertence a outro veículo.");

			} else {
				enviarMsgErro(e.getMessage());
			}

		}

		return veic;
	}

	public Veiculo salvarEdicao(Veiculo veic) {
		try {
			repository.salvarEdicao(fmtToSave(veic));

			enviarMsgSucesso("As alterações do veículo " + veic.getPlaca().toUpperCase()
					+ " foram salvas com sucesso!");

		} catch (Exception e) {
			enviarMsgErro("Erro, as alterações não foram salvas!");
			if (e.getMessage().contains("ConstraintViolationException")) {
				enviarMsgErro("A placa informada já pertence a outro veículo.");
			} else {
				enviarMsgErro(e.getMessage());
			}
		}

		return veic;
	}

	public void alterarStatus(Long id, VeicStatus status) {
		try {
			Veiculo veic = repository.buscarPorId(id);
			veic.setStatus(status);
			repository.salvarEdicao(fmtToSave(veic));
			enviarMsgSucesso(
					"O Status do veículo " + veic.getPlaca().toUpperCase() + ", foi alterado com sucesso");
		} catch (Exception e) {
			enviarMsgErro("Erro, não foi possível salvar o status do veículo");
			enviarMsgErro(e.getMessage());
		}

	}

	public Veiculo alterarStatus(Veiculo veic, VeicStatus status) {
		try {
			veic.setStatus(status);
			repository.salvarEdicao(fmtToSave(veic));

		} catch (Exception e) {
			System.err.println(
					"Erro ao alterar o status do veículo " + veic.getPlaca() + " \n\n" + e.getMessage());
		}

		return veic;
	}

	public List<Veiculo> buscarPorStatus(VeicStatus status) {
		List<Veiculo> result = new ArrayList<Veiculo>();

		try {
			result = repository.buscaPorStatus(status);
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
			result = null;
		}

		return result;
	}

	public List<Veiculo> buscarPorMarcaENaoAlugado(OpMarcas marca) {
		List<Veiculo> result = new ArrayList<Veiculo>();

		try {
			result = repository.buscarPorMarca(marca, VeicStatus.ALUGADO, false);
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
			result = null;
		}

		return result;
	}

	public List<Veiculo> buscarPorCategoriaENaoAlugado(OpCategorias categoria) {
		List<Veiculo> result = new ArrayList<Veiculo>();

		try {
			result = repository.buscaPorCategoria(categoria, VeicStatus.ALUGADO, false);
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
			result = null;
		}

		return result;
	}

	public List<Veiculo> buscarPorMarcaEDisponivel(OpMarcas marca) {
		List<Veiculo> result = new ArrayList<Veiculo>();

		try {
			result = repository.buscarPorMarca(marca, VeicStatus.DISPONIVEL, true);
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
			result = null;
		}

		return result;
	}

	public List<Veiculo> buscarPorCatEDisponivel(OpCategorias categoria) {
		List<Veiculo> result = new ArrayList<Veiculo>();

		try {
			result = repository.buscaPorCategoria(categoria, VeicStatus.DISPONIVEL, true);
		} catch (Exception e) {
			enviarMsgErro("Erro ao realizar a pesquista! " + e.getMessage());
			result = null;
		}

		return result;
	}

	private Veiculo fmtToSave(Veiculo v) {
		v.setPlaca(v.getPlaca().toLowerCase());
		v.setModelo(v.getModelo().trim());
		v.setModelo(v.getModelo().toLowerCase());

		if (v.getDataCadastro() == null && v.getKmAtual() == null) {
			v.setDataCadastro(new Date());
			v.setKmAtual(v.getKmInicial());
			v.setStatus(VeicStatus.INOPERANTE);
		}

		return v;
	}

}
