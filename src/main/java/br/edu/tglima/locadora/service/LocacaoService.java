package br.edu.tglima.locadora.service;

import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgErro;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.edu.tglima.locadora.models.locacao.Locacao;
import br.edu.tglima.locadora.models.locacao.LocacaoStatus;
import br.edu.tglima.locadora.repository.LocacaoRepository;

public class LocacaoService implements Serializable {
	private static final long serialVersionUID = 1L;

	private @Inject LocacaoRepository repository;

	public Locacao efetuarLocacao(Locacao l) {

		try {
			repository.salvarNovo(setInitValues(l));
		} catch (Exception e) {
			enviarMsgErro("Erro, locação não realizada!");
			enviarMsgErro(e.getMessage());
		}

		return l;
	}

	private Locacao setInitValues(Locacao l) {
		l.setDataInicial(new Date());
		l.setKmPercorrida(0);
		l.setStatus(LocacaoStatus.ATIVO);
		l.setValorDiaria(l.getVeicLocado().getValorDiaria());
		return l;
	}

	public boolean cliComLocacaoAtiva(Long idCli) {
		Locacao cliEmLocacao = null;
		try {
			cliEmLocacao = repository.buscarCliente(idCli, LocacaoStatus.ATIVO);
		} catch (Exception e) {
			if (e.getMessage().contains("No entity found for query")) {
			} else {
				System.err.println("Erro detectado: " + e.getMessage());
			}
		}

		return (cliEmLocacao != null);
	}

	public Locacao getLocacao(Long id) {
		Locacao l = null;
		try {
			l = repository.buscarPorId(id);
		} catch (Exception e) {
			System.err.println("Erro detectado: " + e.getMessage());
		}

		return l;
	}

}
