package br.edu.tglima.locadora.models.pessoa;

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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "tb_cliente", uniqueConstraints = @UniqueConstraint(columnNames = "habilitacao", name = "habilitacao_uk"))

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataCadastro;
	private String nome;
	private String sobrenome;
	private Date dataNasc;
	private OpGeneros genero;
	private String telefone;
	private String habilitacao;
	private Date validadeHab;

	@Id
	@SequenceGenerator(name = "seq_cli", sequenceName = "seq_cli", initialValue = 100, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cli")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Column(nullable = false, length = 20)
	@Size(min = 2, max = 20, message = "O nome do cliente deve conter entre 2 e 20 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(nullable = false, length = 50)
	@Size(min = 2, max = 50, message = "O sobrenome do cliente, deve conter entre 2 e 50 caracteres.")
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false)
	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 1)
	public OpGeneros getGenero() {
		return genero;
	}

	public void setGenero(OpGeneros genero) {
		this.genero = genero;
	}

	@Column(nullable = false, length = 15)
	@Size(min = 14, max = 15, message = "O número de telefone deve conter entre 10 e 11 digitos.")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@NotEmpty
	@Column(unique = true, nullable = false)
	@Size(min = 11, message = "O Nº de Registro da CNH deve conter 11 digitos!")
	public String getHabilitacao() {
		return habilitacao;
	}

	public void setHabilitacao(String habilitacao) {
		this.habilitacao = habilitacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "validade_habilitacao", nullable = false)
	public Date getValidadeHab() {
		return validadeHab;
	}

	public void setValidadeHab(Date validadeHab) {
		this.validadeHab = validadeHab;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
