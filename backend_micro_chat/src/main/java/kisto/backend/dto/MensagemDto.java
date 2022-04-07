package kisto.backend.dto;


public class MensagemDto {
	private Long id;
	private String texto;
	private Long remetenteId;
	private Long conversaId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Long getRemetenteId() {
		return remetenteId;
	}
	public void setRemetenteId(Long remetenteId) {
		this.remetenteId = remetenteId;
	}
	public Long getConversaId() {
		return conversaId;
	}
	public void setConversaId(Long conversaId) {
		this.conversaId = conversaId;
	}
	
}
