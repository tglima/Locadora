package br.edu.tglima.locadora.models.veiculo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Table(name = "tb_veiculo", 
		uniqueConstraints=@UniqueConstraint(columnNames = "placa", name = "placa_uk"))
@Entity
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCadastro;
	private String placa;
	private OpMarcas marca;
	private String modelo;
	private Integer ano;
	private OpCategorias categoria;
	private OpCores cor;
	private OpCombustiveis combustivel;
	private Integer kmInicial;
	private Integer kmAtual;
	private VeicStatus status;
	private Double valorDiaria;

	@Id
	@SequenceGenerator(name = "seq_veic", sequenceName = "seq_veic", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veic")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_cadastro", nullable=false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	
	@Column(unique=true, nullable=false, length=8)
	@NotEmpty
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public OpMarcas getMarca() {
		return marca;
	}

	public void setMarca(OpMarcas marca) {
		this.marca = marca;
	}

	@Column(nullable = false, length = 20)
	@NotEmpty
	@Size(min=2, max=20, message="O modelo do veículo deve conter entre 2 e 20 caracteres")
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(name= "ano_fabricacao", nullable = false, length = 4)
	@Min(value=2010, message="O ano de fabricação do veículo deve ser igual ou superior ao ano de 2010")
	@Max(value=2018, message="O ano de fabricação do veículo não pode ser superior ao ano de 2018")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public OpCategorias getCategoria() {
		return categoria;
	}

	public void setCategoria(OpCategorias categoria) {
		this.categoria = categoria;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public OpCores getCor() {
		return cor;
	}

	public void setCor(OpCores cor) {
		this.cor = cor;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public OpCombustiveis getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(OpCombustiveis combustivel) {
		this.combustivel = combustivel;
	}

	@Column(name="km_inicial", nullable = false, length = 6)
	@Min(value = 0, message = "A menor Km aceita é 0")
	@Max(value = 999999, message = "A maior Km aceita é 999.999")
	public Integer getKmInicial() {
		return kmInicial;
	}

	public void setKmInicial(Integer kmInicial) {
		this.kmInicial = kmInicial;
	}

	@Column(name="km_atual", nullable = false, length = 6)
	@Max(value=999999, message = "A maior Km aceita é 999.999")
	@Min(value=0, message="A menor Km aceita é 0")
	public Integer getKmAtual() {
		return kmAtual;
	}

	public void setKmAtual(Integer kmAtual) {
		this.kmAtual = kmAtual;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public VeicStatus getStatus() {
		return status;
	}

	public void setStatus(VeicStatus status) {
		this.status = status;
	}

	@Column(name="valor_diaria", nullable = false, precision = 10, scale = 2)
	@Min(value=15, message="O valor mínimo da diária não pode ser inferior a R$ 15,00")
	@Max(value=9999, message="O valor máximo da diária deve ser inferior a R$ 9.999,00")
	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

}
