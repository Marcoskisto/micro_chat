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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import utils.TipoDeConversa;

@Entity
@Table(name = "cnv_conversa")
public class Conversa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cnv_id")
	private Long id;
	
	@Column(name = "cnv_assunto", unique = true, length = 20, nullable = false)
	private String assunto;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cnv_tipo")
	private TipoDeConversa tipo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ucu_conversa_usuario",
		joinColumns = { @JoinColumn(name = "ucu_cnv_id") },
		inverseJoinColumns = { @JoinColumn(name = "ucu_usr_id") })
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
