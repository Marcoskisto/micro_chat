package kisto.backend.dto;

import java.util.Set;

import kisto.backend.enums.ConversaTipo;

public class ConversaDto {
	private Long id;
	private String assunto;
	private ConversaTipo tipo;
	private Set<MensagemDto> mensagems;
	private Set<UsuarioDto> usuarios;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public ConversaTipo getTipo() {
		return tipo;
	}
	public void setTipo(ConversaTipo tipo) {
		this.tipo = tipo;
	}
	public Set<MensagemDto> getMensagems() {
		return mensagems;
	}
	public void setMensagems(Set<MensagemDto> mensagems) {
		this.mensagems = mensagems;
	}
	public Set<UsuarioDto> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<UsuarioDto> usuarios) {
		this.usuarios = usuarios;
	}
	
}

