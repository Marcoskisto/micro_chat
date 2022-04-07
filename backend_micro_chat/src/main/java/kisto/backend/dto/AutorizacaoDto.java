package kisto.backend.dto;

import java.util.Set;

public class AutorizacaoDto {
	private Long id;
	private String nome;
	private Set<UsuarioDto> usuarios;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<UsuarioDto> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<UsuarioDto> usuarios) {
		this.usuarios = usuarios;
	}
}




