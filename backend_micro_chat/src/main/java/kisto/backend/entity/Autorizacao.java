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

@Entity
@Table(name="aut_autorizacao")
public class Autorizacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="aut_id")
	private Long id;
	
	@Column(name = "aut_nome", unique = true, length = 20, nullable = false)
	private String nome;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "aua_autorizacao_usuario",
		joinColumns = { @JoinColumn(name = "aua_aut_id") },
		inverseJoinColumns = { @JoinColumn(name = "aua_usr_id") })
	private Set<Usuario> usuarios;	
}
