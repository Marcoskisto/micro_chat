package kisto.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="msg_mensagem")
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "msg_id")
	private Long id;
	
	@Column(name = "msg_texto", length = 100, nullable = false)
	private String texto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "msg_remetente_id")
	private Usuario remetente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "msg_conversa_id")
	private Conversa conversa;

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
	}

	public Conversa getConversa() {
		return this.conversa;
	}

	public void setTexto(String novoTexto) {
		this.texto = texto;
		
	}

}
