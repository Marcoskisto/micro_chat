package kisto.backend.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kisto.backend.entity.Autorizacao;
import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.entity.Usuario;
import kisto.backend.repository.ConversaRepository;
import kisto.backend.repository.MensagemRepository;
import kisto.backend.repository.UsuarioRepository;

@Service
public class SecurityServiceImp implements SecurityService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private ConversaRepository conversaRepo;
	
	@Autowired MensagemRepository mensagemRepo;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNickname(username);
        if (usuario == null) {
          throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
        }
        return User.builder().username(username).password(usuario.getSenha())
            .authorities(usuario.getAutorizacoes().stream()
                .map(Autorizacao::getNome).collect(Collectors.toList())
                .toArray(new String[usuario.getAutorizacoes().size()]))
            .build();
    }
    
	@Override
    public boolean isUsuarioInConversa(
    		Authentication authentication, 
    		Long usuarioId,
    		Long conversaId) {
		Usuario usuario = usuarioRepo.findByNickname(authentication.getName());
		Conversa conversa = conversaRepo.findById(conversaId).get();
  	
		if(usuario.getId() == usuarioId && 
				conversa.getUsuarios().contains(usuario)
				) {
			return true;
		}
		return false;
	}
	
	public boolean isMensagemFromUsuario(Authentication authentication, Long mensagemId) {
		Mensagem mensagem = mensagemRepo.findById(mensagemId).get();
		
		if(mensagem.getRemetenteNome() == authentication.getName()) {
			return true;
		}
		return false;
	}
}
