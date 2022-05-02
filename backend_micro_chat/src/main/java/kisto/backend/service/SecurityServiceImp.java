package kisto.backend.service;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kisto.backend.entity.Autorizacao;
import kisto.backend.entity.Usuario;
import kisto.backend.repository.AutorizacaoRepository;
import kisto.backend.repository.UsuarioRepository;
import kisto.backend.exceptions.UsuarioJaCadastradoException;;


@Service("usuarioService")
public class SecurityServiceImp implements SecurityService {
	@Autowired
	private AutorizacaoRepository autorizacaoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
		
	@Override
	public Autorizacao cadastrarAutorizacao(String nome) {
		Autorizacao autorizacao = autorizacaoRepo.findByNome(nome);
		if (autorizacao == null) {
			autorizacao = new Autorizacao();
			autorizacao.setNome(nome);				
			autorizacaoRepo.save(autorizacao);
		}
		return autorizacao;
	}
	
	@Override
	@Transactional
	public Usuario cadastrarUsuario(
			String nickname, String email, 
			String senha, List<String> autorizacoesNomes) throws UsuarioJaCadastradoException {

		if(usuarioRepo.findByNickname(nickname) != null) {
			throw new UsuarioJaCadastradoException();
		}
		Usuario usuario = new Usuario();
		usuario.setNickname(nickname);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		
		for(String nome: autorizacoesNomes) {
			Autorizacao autorizacao = this.cadastrarAutorizacao(nome);			
			usuario.addAutorizacao(autorizacao);
		}
		usuarioRepo.save(usuario);
		return usuario;
	}

	@PreAuthorize("isAuthenticated")
	@Override
	@Transactional
	public Usuario atribuirAutorizacao(Long usuarioId, String nomeAutorizacao) {
		Usuario usuario = usuarioRepo.findById(usuarioId).get();

		Autorizacao autorizacao = this.cadastrarAutorizacao(nomeAutorizacao);
		usuario.addAutorizacao(autorizacao);
		usuarioRepo.save(usuario);
		return usuario;
	}

	@PreAuthorize("isAuthenticated")
	@Override
	public Usuario revogarAutorizacao(Long usuarioId, Long autorizacaoId) {
		Usuario usuario = usuarioRepo.findById(usuarioId).get();
		Autorizacao autorizacao = autorizacaoRepo.findById(autorizacaoId).get();
		usuario.getAutorizacoes().remove(autorizacao);
		
		return usuario;
	}

	@PreAuthorize("isAuthenticated")
	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		return usuarioRepo.findAll();
	}

	@PreAuthorize("isAuthenticated")
	@Override
	public Usuario getUsuario(Long id) {
		return usuarioRepo.findById(id).get();
	}
}
