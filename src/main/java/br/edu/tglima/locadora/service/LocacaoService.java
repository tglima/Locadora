package br.edu.tglima.locadora.service;

import static br.edu.tglima.locadora.models.locacao.LocacaoStatus.ATIVO;
import static br.edu.tglima.locadora.models.locacao.LocacaoStatus.CANCELADO;
import static br.edu.tglima.locadora.models.locacao.LocacaoStatus.FINALIZADO;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;
import static br.edu.tglima.locadora.util.TempoUtil.calcDifDias;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.edu.tglima.locadora.models.locacao.Cancelamento;
import br.edu.tglima.locadora.models.locacao.Locacao;
import br.edu.tglima.locadora.repository.CancLocacaoRepository;
import br.edu.tglima.locadora.repository.LocacaoRepository;

public class LocacaoService implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Inject LocacaoRepository repository;
	private @Inject CancLocacaoRepository cancRepository;

	public Locacao efetuarLocacao(Locacao loc) {

		try {
			repository.salvarNovo(setInitValues(loc));
		} catch (Exception e) {
			enviarMsgErro("Erro, locação não realizada!");
			enviarMsgErro(e.getMessage());
		}

		return loc;
	}

	private Locacao setInitValues(Locacao loc) {
		loc.setDataInicial(new Date());
		loc.setKmPercorrida(0);
		loc.setStatus(ATIVO);
		loc.setValorDiaria(loc.getVeicLocado().getValorDiaria());
		return loc;
	}

	public boolean cliComLocacaoAtiva(Long idCli) {
		Locacao cliEmLocacao = null;
		try {
			cliEmLocacao = repository.buscarCliente(idCli, ATIVO);
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
			} else {
				System.err.println("Erro detectado: " + e.getMessage());
			}
		}

		return (cliEmLocacao != null);
	}

	public Locacao buscarPeloId(Long idLocacao) {
		Locacao loc = null;
		try {
			loc = repository.buscarPorId(idLocacao);
		} catch (Exception e) {
			System.err.println("Erro detectado: " + e.getMessage());
		}
		return loc;
	}

	public List<Locacao> buscarPelaCNH(String cnhCliente) {
		List<Locacao> results = null;
		try {
			results = repository.buscarPelaCNH(cnhCliente);
		} catch (Exception e) {
			System.err.println("Causa do erro: " + e.getCause());
		}
		return results;
	}

	public List<Locacao> buscarLocAtivas() {
		List<Locacao> results = null;

		try {
			results = repository.buscarPorStatus(ATIVO);
		} catch (Exception e) {
			System.err.println("Causa do erro: " + e.getCause());
		}

		return results;
	}

	public List<Locacao> buscarLocFinalizadas() {
		List<Locacao> results = null;
		try {
			results = repository.buscarPorStatus(FINALIZADO);
		} catch (Exception e) {
			System.err.println("Causa do erro: " + e.getCause());
		}

		return results;
	}

	public List<Locacao> buscarLocCanceladas() {
		List<Locacao> results = null;
		try {
			results = repository.buscarPorStatus(CANCELADO);
		} catch (Exception e) {
			System.err.println("Causa do erro: " + e.getCause());
		}

		return results;
	}

	public Integer calcKmPercorridos(Integer kmInicial, Integer kmFinal) {
		return (kmFinal - kmInicial);
	}

	public Long totDias(Date dataInicial, Date dataFinal) {
		Long totDias = calcDifDias(dataInicial, dataFinal);

		if (totDias < 1l) {
			totDias = 1l;
		}
		return totDias;
	}

	public Double calcValorTotal(Long totDias, Double valorDiaria) {
		return (totDias * valorDiaria);
	}

	public Locacao encerrarLocacao(Locacao loc) {
		try {
			repository.salvarEdicao(loc);
		} catch (Exception e) {
			loc.setDataFinal(null);
			loc.setValorTotal(null);
			loc.setKmPercorrida(0);
			loc.setStatus(ATIVO);
			enviarMsgErro("Erro detectado: " + e.getMessage());
			System.err.println("Causa do erro: " + e.getCause());
		}
		return loc;
	}

	public void registrarCancelamento(Cancelamento locCancelada) {
		try {
			cancRepository.salvarNovo(locCancelada);
		} catch (Exception e) {
			enviarMsgErro("Erro ao registrar o cancelamento \n " + e.getMessage());
			System.err.println("Causa do erro: " + e.getCause());
		}
	}

	public Cancelamento buscarPorIdLocacao(Long idLocacao) {
		Cancelamento result = null;
		try {
			result = cancRepository.buscarCancPorIdLocacao(idLocacao);
		} catch (Exception e) {
			enviarMsgErro("Erro ao buscar o cancelamento \n " + e.getMessage());
			System.err.println("Causa do erro: " + e.getCause());
		}

		return result;
	}

}
