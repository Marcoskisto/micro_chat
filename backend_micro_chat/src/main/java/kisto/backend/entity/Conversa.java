package kisto.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.controller.View;
import utils.TipoDeConversa;

@Entity
@Table(name = "cnv_conversa")
public class Conversa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cnv_id")
	@JsonView({View.ConversaLista.class, View.ConversaUsuarios.class})
	private Long id;
	
	@Column(name = "cnv_assunto", unique = true, length = 20, nullable = false)
	@JsonView({View.ConversaLista.class})
	private String assunto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cnv_tipo")
	@JsonView({View.ConversaLista.class})
	private TipoDeConversa tipo;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "conversas")
	@JsonView({View.ConversaUsuarios.class})
	private Set<Usuario> usuarios;	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "conversa")
	private Set<Mensagem> mensagems;

	
	public void setAssunto(String assunto){
		this.assunto = assunto;
	}
	
	public Set<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Long getId() {
		return this.id;
	}
}
