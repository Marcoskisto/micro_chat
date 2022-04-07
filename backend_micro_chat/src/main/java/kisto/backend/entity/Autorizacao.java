package kisto.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.controller.View;

@Entity
@Table(name="aut_autorizacao")
public class Autorizacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="aut_id")
	private Long id;
	
	@Column(name = "aut_nome", unique = true, length = 20, nullable = false)
	@JsonView({View.UsuarioDados.class})
	private String nome;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "autorizacoes")
	private Set<Usuario> usuarios;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
