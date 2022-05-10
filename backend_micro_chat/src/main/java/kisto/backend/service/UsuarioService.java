package kisto.backend.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import kisto.backend.dto.AutorizacaoDto;
import kisto.backend.entity.Autorizacao;
import kisto.backend.entity.Usuario;
import kisto.backend.exceptions.UsuarioJaCadastradoException;

public interface UsuarioService {
	
	public List<Usuario> recuperarTodosUsuarios();
	
	public Usuario cadastrarUsuario(
			String nickname, String email, String senha, 
			List<String> autorizacoesNomes) throws UsuarioJaCadastradoException;
	
	public Usuario atribuirAutorizacao(Long usuarioId, String nomeAutorizacao);
	
	public Usuario revogarAutorizacao(Long usuarioId, Long autorizacaoId);
	
	public Autorizacao cadastrarAutorizacao(String nome);
	
	public Usuario getUsuario(Long id);

}
