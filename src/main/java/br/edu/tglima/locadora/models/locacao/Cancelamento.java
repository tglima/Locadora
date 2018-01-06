package br.edu.tglima.locadora.models.locacao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.edu.tglima.locadora.models.pessoa.Funcionario;

@Table(name = "tb_cancelamento_locacao")

@Entity
public class Cancelamento implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Funcionario gerente;
	private Locacao locacao;
	private String descricao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "O gerente deve ser informado!")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_gerente"), name = "id_funcionario", referencedColumnName = "id", nullable = false)
	public Funcionario getGerente() {
		return gerente;
	}

	public void setGerente(Funcionario gerente) {
		this.gerente = gerente;
	}

	@NotNull(message = "É necessário associar uma locação!")
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_locacao"), name = "id_locacao", referencedColumnName = "id", nullable = false)
	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	@Column
	@Lob
	@NotNull(message = "É obrigátorio informar um descrição sobre o cancelamento.")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
