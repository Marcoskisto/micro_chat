package kisto.backend.service;

import java.util.List;
import java.util.Set;

import kisto.backend.dto.AutorizacaoDto;
import kisto.backend.entity.Autorizacao;
import kisto.backend.entity.Usuario;

public interface SecurityService {
	
	public List<Usuario> recuperarTodosUsuarios();
	
	public Usuario cadastrarUsuario(
			String nickname, String email, String senha, 
			List<String> autorizacoesNomes);
	
	public Usuario atribuirAutorizacao(Long usuarioId, String nomeAutorizacao);
	
	public Usuario revogarAutorizacao(Long usuarioId, Long autorizacaoId);
	
	public Autorizacao cadastrarAutorizacao(String nome);
	
	public Usuario getUsuario(Long id);

}
