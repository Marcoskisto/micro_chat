package Service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Repository.AutorizacaoRepository;
import Repository.UsuarioRepository;
import kisto.backend.entity.Autorizacao;
import kisto.backend.entity.Usuario;

@Service("usuarioService")
public class UsuarioServiceImp implements UsuarioService {
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
	public Usuario cadastrarUsuario(String nickname, String email, String senha, String nomeAutorizacao) {
		
		Autorizacao autorizacao = this.cadastrarAutorizacao(nomeAutorizacao);
		Usuario usuario = new Usuario();
		usuario.setNickname(nickname);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuario.setAutorizacoes(new HashSet<Autorizacao>());
		usuario.addAutorizacao(autorizacao);
		usuarioRepo.save(usuario);
		
		return usuario;
		
	}

	@Override
	@Transactional
	public Usuario atribuirAutorizacao(Long usuarioId, String nomeAutorizacao) {
		Usuario usuario = usuarioRepo.findById(usuarioId).get();

		Autorizacao autorizacao = this.cadastrarAutorizacao(nomeAutorizacao);
		usuario.addAutorizacao(autorizacao);
		return usuario;
	}

	@Override
	public Usuario revogarAutorizacao(Long usuarioId, Long autorizacaoId) {
		Usuario usuario = usuarioRepo.findById(usuarioId).get();
		Optional<Autorizacao> autorizacao = autorizacaoRepo.findById(autorizacaoId);
		usuario.getAutorizacoes().remove(autorizacao);
		
		return usuario;
	}
	

}
