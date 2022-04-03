package kisto.backend.service;

import kisto.backend.entity.Autorizacao;
import kisto.backend.entity.Usuario;

public interface UsuarioService {
	public Usuario cadastrarUsuario(
			String nickname, String email, String senha, String nomeAutorizacao);
	
	public Usuario atribuirAutorizacao(Long usuarioId, String nomeAutorizacao);
	
	public Usuario revogarAutorizacao(Long usuarioId, Long autorizacaoId);
	
	public Autorizacao cadastrarAutorizacao(String nome);

}
