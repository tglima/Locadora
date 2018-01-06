package br.edu.tglima.locadora.controllers;

import static br.edu.tglima.locadora.models.locacao.LocacaoStatus.ATIVO;
import static br.edu.tglima.locadora.models.locacao.LocacaoStatus.CANCELADO;
import static br.edu.tglima.locadora.models.locacao.LocacaoStatus.FINALIZADO;
import static br.edu.tglima.locadora.models.veiculo.VeicStatus.MANUTENCAO;
import static br.edu.tglima.locadora.util.FacesUtil.enviarMsgSucesso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.locacao.Cancelamento;
import br.edu.tglima.locadora.models.locacao.Locacao;
import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.service.FuncionarioService;
import br.edu.tglima.locadora.service.LocacaoService;
import br.edu.tglima.locadora.service.VeicService;

@Named
@ViewScoped
public class GeLoc implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte tipoDeBusca;
	private boolean resultEmpty;
	private boolean showInit;
	private boolean showDivData;
	private boolean showDivFinal;
	private byte typeOfEnd;
	private String searchParam;
	private List<Locacao> locacoes;
	private Locacao locSelected;
	private Date dataFinal;
	private Double valorTotal;
	private Long totDias;
	private Integer kmFinal;
	private Integer kmPercorrida;
	private String descCanc;
	private @Inject Funcionario gerente;
	private @Inject FuncionarioService funcService;
	private @Inject Cancelamento cancLoc;
	private @Inject LocacaoService service;
	private @Inject VeicService veicService;

	@PostConstruct
	public void init() {
		enableInit();
		totDias = 0l;
		gerente = funcService.buscarPorId(2017003l);
	}

	public void enableInit() {
		showInit = true;
		showDivData = false;
		tipoDeBusca = 0;
		locacoes = null;
		locSelected = null;
	}

	public void enableDivData() {
		showInit = false;
		showDivFinal = false;
		kmFinal = null;
		descCanc = null;
		typeOfEnd = 0;

		if (!showInit) {
			showDivData = true;
			clearMenuTipoDeBusca();
		}

		if (!locSelected.getStatus().equals(ATIVO)) {
			totDias = service.totDias(locSelected.getDataInicial(), locSelected.getDataFinal());
		}

		if (locSelected.getStatus().equals(CANCELADO)) {
			cancLoc = service.buscarPorIdLocacao(locSelected.getId());
		}

	}

	public void enableDivFinal(byte value) {
		showInit = false;
		showDivData = false;
		showDivFinal = true;
		typeOfEnd = value;
	}

	public void clearMenuTipoDeBusca() {
		searchParam = null;
		resultEmpty = false;
		locacoes = null;

		if (tipoDeBusca == 3) {
			buscarLocAtivas();
		} else if (tipoDeBusca == 4) {
			buscarLocFinalizadas();
		} else if (tipoDeBusca == 5) {
			buscarLocCanceladas();
		}
	}

	public void buscarPelaCNH() {
		locacoes = service.buscarPelaCNH(searchParam);
		if (locacoes.isEmpty()) {
			resultEmpty = true;
			System.out.println("Resultado vazio");
		}
	}

	public void buscarPeloID() {
		Long idLocacao = Long.parseLong(searchParam);
		Locacao loc = service.buscarPeloId(idLocacao);
		locacoes = new ArrayList<>();
		if (loc != null) {
			locacoes.add(loc);
		} else {
			resultEmpty = true;
		}

	}

	private void buscarLocAtivas() {
		locacoes = service.buscarLocAtivas();
		if (locacoes.isEmpty()) {
			resultEmpty = true;
		}
	}

	private void buscarLocFinalizadas() {
		locacoes = service.buscarLocFinalizadas();
		if (locacoes.isEmpty()) {
			resultEmpty = true;
		}
	}

	private void buscarLocCanceladas() {
		locacoes = service.buscarLocCanceladas();
		if (locacoes.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void calcValorLocacao() {
		dataFinal = new Date();
		totDias = service.totDias(locSelected.getDataInicial(), dataFinal);
		valorTotal = service.calcValorTotal(totDias, locSelected.getValorDiaria());
	}

	private Integer calcKmPercorridos() {
		return service.calcKmPercorridos(locSelected.getVeicLocado().getKmAtual(), kmFinal);
	}

	private void atualizarVeic(Veiculo veic) {
		veic.setKmAtual(kmFinal);
		veic.setStatus(MANUTENCAO);
		veicService.salvarEdicaoSemMsg(veic);
	}

	public void finalizarLocacao() {
		kmPercorrida = calcKmPercorridos();
		locSelected.setDataFinal(dataFinal);
		locSelected.setValorTotal(valorTotal);
		locSelected.setStatus(FINALIZADO);
		locSelected.setKmPercorrida(kmPercorrida);
		locSelected = service.encerrarLocacao(locSelected);

		if (locSelected.getValorTotal() != null) {
			atualizarVeic(locSelected.getVeicLocado());
			enviarMsgSucesso("Locação finalizada com sucesso!");
			enableDivData();
		}

	}

	public void cancelarLocacao() {

		dataFinal = new Date();
		kmPercorrida = calcKmPercorridos();
		locSelected.setDataFinal(dataFinal);
		locSelected.setValorTotal(0.0);
		locSelected.setStatus(CANCELADO);
		locSelected.setKmPercorrida(kmPercorrida);
		locSelected = service.encerrarLocacao(locSelected);

		if (locSelected.getStatus().equals(CANCELADO)) {
			cancLoc.setGerente(gerente);
			cancLoc.setLocacao(locSelected);
			cancLoc.setDescricao(descCanc);
			service.registrarCancelamento(cancLoc);
		}

		if (cancLoc.getId() != null) {
			atualizarVeic(locSelected.getVeicLocado());
			enviarMsgSucesso("Locação cancelada com sucesso!");
			enableDivData();
		}

	}

	/***************************************
	 * Getters e Setters do Controller
	 ***************************************/

	public byte getTypeOfEnd() {
		return typeOfEnd;
	}

	public boolean isShowDivFinal() {
		return showDivFinal;
	}

	public boolean isShowDivData() {
		return showDivData;
	}

	public boolean isShowInit() {
		return showInit;
	}

	public byte getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(byte tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public boolean isResultEmpty() {
		return resultEmpty;
	}

	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public Locacao getLocSelected() {
		return locSelected;
	}

	public void setLocSelected(Locacao locSelected) {
		this.locSelected = locSelected;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public Long getTotDias() {
		return totDias;
	}

	public Integer getKmFinal() {
		return kmFinal;
	}

	public void setKmFinal(Integer kmFinal) {
		this.kmFinal = kmFinal;
	}

	public Cancelamento getCancLoc() {
		return cancLoc;
	}

	public String getDescCanc() {
		return descCanc;
	}

	public void setDescCanc(String descCanc) {
		this.descCanc = descCanc;
	}

}
