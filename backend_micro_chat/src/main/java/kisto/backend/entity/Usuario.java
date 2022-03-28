package kisto.backend.entity;

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


@Entity
@Table(name = "usr_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id")
	private Long id;
	
	@Column(name = "usr_nickname", unique = true, length = 20, nullable = false)
	private String nickname;
	
	@Column(name = "usr_email", unique = true, length = 40)
	private String email;
	
	@Column(name = "usr_senha", unique = true, length = 10)
	private String senha;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aua_autorizacao_usuario",
		joinColumns = { @JoinColumn(name = "aua_usr_id") },
		inverseJoinColumns = { @JoinColumn(name = "aua_aut_id") })
	private Set<Autorizacao> autorizacoes;	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ucu_conversa_usuario",
		joinColumns = { @JoinColumn(name = "ucu_usr_id") },
		inverseJoinColumns = { @JoinColumn(name = "ucu_cnv_id") })
	private Set<Conversa> conversas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "remetente")
	private Set<Mensagem> mensagems;

}
