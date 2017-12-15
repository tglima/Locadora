package br.edu.tglima.locadora.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.tglima.locadora.models.locacao.Locacao;
import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.veiculo.OpCategorias;
import br.edu.tglima.locadora.models.veiculo.OpMarcas;
import br.edu.tglima.locadora.models.veiculo.OpStatus;
import br.edu.tglima.locadora.models.veiculo.Veiculo;
import br.edu.tglima.locadora.service.ClienteService;
import br.edu.tglima.locadora.service.FuncionarioService;
import br.edu.tglima.locadora.service.LocacaoService;
import br.edu.tglima.locadora.service.VeicService;

@Named
@ViewScoped
public class NovaLoc implements Serializable {
	private static final long serialVersionUID = 1L;

	private byte step;
	private byte tipoDeBusca;
	private String inputSearchCli;
	private boolean resultEmpty;
	private boolean showDivCli;
	private boolean showDivVeic;
	private boolean showDivConfirmation;
	private boolean showDivResume;
	private List<Cliente> clients;
	private Cliente cliSelected;
	private Veiculo veicSelected;
	private List<Veiculo> veics;
	private OpMarcas marcaSelected;
	private OpCategorias catSelected;
	private @Inject ClienteService serviceCli;
	private @Inject VeicService serviceVeic;
	private String restricaoCli;
	private @Inject FuncionarioService funcService;
	private @Inject Funcionario funcSelected;
	private @Inject Locacao locacao;
	private @Inject LocacaoService serviceLocacao;

	@PostConstruct
	public void init() {
		enableDivCli();
		funcSelected = funcService.buscarPorCpf("111.111.111-11").get(0);
	}

	public void clearInputCli() {
		inputSearchCli = null;
		resultEmpty = false;
	}

	public void clearMenuVeic() {
		resultEmpty = false;
		veics = null;
		if (tipoDeBusca == 1) {
			catSelected = null;
		} else if (tipoDeBusca == 2) {
			marcaSelected = null;
		}

		else if (tipoDeBusca == 3) {
			catSelected = null;
			marcaSelected = null;
			buscarVeicDispo();
		}
	}

	public void buscarPelaCNH() {
		clients = serviceCli.buscarPelaCNH(inputSearchCli);

		if (clients.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void buscarPeloNome() {
		clients = serviceCli.buscarPeloNome(inputSearchCli);
		if (clients.isEmpty()) {
			resultEmpty = true;
		}
	}

	public boolean cliIsValid(Long idCliente) {
		boolean result = true;
		if (serviceLocacao.cliComLocacaoAtiva(idCliente)) {
			restricaoCli = "Cliente com locação em andamento!";
			result = false;
		} else if (serviceCli.cnhVencida(idCliente)) {
			restricaoCli = "CNH vencida há mais de 15 dias!";
			result = false;
		}
		return result;
	}

	public void enableDivCli() {
		step = 0;
		tipoDeBusca = 0;
		showDivCli = true;
		showDivVeic = false;
		showDivConfirmation = false;
		showDivResume = false;
	}

	public void enableDivVeic() {
		step = 1;
		tipoDeBusca = 0;
		showDivVeic = true;
		showDivCli = false;
		showDivConfirmation = false;
	}

	public void enableDivConfirmation() {
		step = 2;
		tipoDeBusca = 0;
		showDivConfirmation = true;
		showDivVeic = false;
		showDivCli = false;
		if (locacao.getDataInicial() == null) {
			locacao.setDataInicial(new Date());
		}
	}

	private void enableDivResume() {
		step = 3;
		tipoDeBusca = 0;
		showDivConfirmation = false;
		showDivResume = true;
	}

	public void buscarPorMarca() {
		veics = serviceVeic.buscarPorMarcaEDisponivel(marcaSelected);
		if (veics.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void buscarPorCategoria() {
		veics = serviceVeic.buscarPorCatEDisponivel(catSelected);
		if (veics.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void buscarVeicDispo() {
		veics = serviceVeic.buscarPorStatus(OpStatus.DISPONIVEL);
		if (veics.isEmpty()) {
			resultEmpty = true;
		}
	}

	public void efetivarLocacao() {

		serviceVeic.alterarStatus(veicSelected, OpStatus.ALUGADO);
		locacao.setCli(cliSelected);
		locacao.setVeicLocado(veicSelected);
		locacao.setFunc(funcSelected);
		locacao = serviceLocacao.efetuarLocacao(locacao);

		if (locacao.getId() != null) {
			System.out.println("Id da locação: " + locacao.getId());
			enableDivResume();
		} else {
			serviceVeic.alterarStatus(veicSelected, OpStatus.DISPONIVEL);
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

	/***************************************
	 * Getters e Setters do Controller
	 ***************************************/

	public byte getStep() {
		return step;
	}

	public byte getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(byte tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public String getInputSearchCli() {
		return inputSearchCli;
	}

	public void setInputSearchCli(String inputSearchCli) {
		this.inputSearchCli = inputSearchCli;
	}

	public boolean isResultEmpty() {
		return resultEmpty;
	}

	public List<Cliente> getClients() {
		return clients;
	}

	public Cliente getCliSelected() {
		return cliSelected;
	}

	public void setCliSelected(Cliente cliSelected) {
		this.cliSelected = cliSelected;
	}

	public boolean isShowDivCli() {
		return showDivCli;
	}

	public boolean isShowDivVeic() {
		return showDivVeic;
	}

	public boolean isShowDivConfirmation() {
		return showDivConfirmation;
	}

	public boolean isShowDivResume() {
		return showDivResume;
	}

	public OpMarcas getMarcaSelected() {
		return marcaSelected;
	}

	public void setMarcaSelected(OpMarcas marcaSelected) {
		this.marcaSelected = marcaSelected;
	}

	public OpCategorias getCatSelected() {
		return catSelected;
	}

	public void setCatSelected(OpCategorias catSelected) {
		this.catSelected = catSelected;
	}

	public List<Veiculo> getVeics() {
		return veics;
	}

	public Veiculo getVeicSelected() {
		return veicSelected;
	}

	public void setVeicSelected(Veiculo veicSelected) {
		this.veicSelected = veicSelected;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public String getRestricaoCli() {
		return restricaoCli;
	}

}
