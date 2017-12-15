package br.edu.tglima.locadora.models.locacao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.edu.tglima.locadora.models.pessoa.Cliente;
import br.edu.tglima.locadora.models.pessoa.Funcionario;
import br.edu.tglima.locadora.models.veiculo.Veiculo;

@Table(name = "tb_locacao")

@Entity
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Funcionario func;
	private Cliente cli;
	private Veiculo veicLocado;
	private Date dataInicial;
	private Date dataFinal;
	private Integer kmPercorrida;
	private LocacaoStatus status;
	private Double valorDiaria;
	private Double valorTotal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "O funcionário deve ser informado!")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_funcionario"), name = "id_funcionario", referencedColumnName = "id", nullable = false)
	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}

	@NotNull(message = "O cliente deve ser informado!")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_cliente"), name = "id_cliente", referencedColumnName = "id", nullable = false)
	public Cliente getCli() {
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	@NotNull(message = "O veículo deve ser informado!")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_veiculo"), name = "id_veiculo", referencedColumnName = "id", nullable = false)
	public Veiculo getVeicLocado() {
		return veicLocado;
	}

	public void setVeicLocado(Veiculo veicLocado) {
		this.veicLocado = veicLocado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicial", nullable = false)
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_final")
	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	@Column(name = "km_percorrida", nullable = false, length = 6)
	@Min(value = 0, message = "A menor Km aceita é 0")
	@Max(value = 999999, message = "A maior Km aceita é 999.999")
	public Integer getKmPercorrida() {
		return kmPercorrida;
	}

	public void setKmPercorrida(Integer kmPercorrida) {
		this.kmPercorrida = kmPercorrida;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	public LocacaoStatus getStatus() {
		return status;
	}

	public void setStatus(LocacaoStatus status) {
		this.status = status;
	}

	@Column(name = "valor_diaria", nullable = false, precision = 10, scale = 2)
	@Min(value = 0, message = "O valo da diária não pode ser negativo!")
	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	@Column(name = "valor_total", precision = 10, scale = 2)
	@Min(value = 0, message = "O valo da diária não pode ser negativo!")
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
