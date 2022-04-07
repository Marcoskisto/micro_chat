package kisto.backend.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.controller.View;


@Entity
@Table(name = "usr_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	@JsonView({View.UsuarioLista.class, View.UsuarioDados.class, 
		View.ConversaLista.class, View.MensagemLeitura.class})
	private Long id;
	
	@Column(name = "usr_nickname", unique = true, length = 20, nullable = false)
	@JsonView({View.UsuarioLista.class, View.UsuarioDados.class, 
		View.ConversaLista.class, View.MensagemLeitura.class})
	private String nickname;
	
	@Column(name = "usr_email", unique = true, length = 40)
	@JsonView({View.UsuarioDados.class})
	private String email;
	
	@Column(name = "usr_senha", unique = true, length = 10)
	private String senha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aua_autorizacao_usuario",
		joinColumns = { @JoinColumn(name = "aua_usr_id") },
		inverseJoinColumns = { @JoinColumn(name = "aua_aut_id") })
	@JsonView({View.UsuarioDados.class})
	private Set<Autorizacao> autorizacoes;	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ucu_conversa_usuario",
		joinColumns = { @JoinColumn(name = "ucu_usr_id") },
		inverseJoinColumns = { @JoinColumn(name = "ucu_cnv_id") })
	@JsonIgnore
	private Set<Conversa> conversas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remetente")
	private Set<Mensagem> mensagems;
	
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setAutorizacoes(HashSet<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}
	
	public Set<Autorizacao> getAutorizacoes() {
		return this.autorizacoes;
	}
	
	public void addAutorizacao(Autorizacao autorizacao) {
		this.autorizacoes.add(autorizacao);
	}

	
	public Long getId() {
		return this.id;
	}
	public String getNickname() {
		return this.nickname;
	}
	public String getEmail() {
		return this.email;
	}
	public Set<Conversa> getConversas() {
		return this.conversas;
	}
	public Set<Mensagem> getMensagems(){
		return this.mensagems;
	}

}
