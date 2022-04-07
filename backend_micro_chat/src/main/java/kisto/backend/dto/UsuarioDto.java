package kisto.backend.dto;

import java.util.List;
import java.util.Set;

import kisto.backend.entity.Mensagem;

public class UsuarioDto {
	private Long id;
	private String nickname;
	private String email;
	private String senha;
	private List<String> autorizacoes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<String> getAutorizacoes() {
		return autorizacoes;
	}
	public void setAutorizacoes(List<String> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	
}
