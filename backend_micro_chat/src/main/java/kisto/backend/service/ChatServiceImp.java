package kisto.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.entity.Usuario;
import kisto.backend.repository.ConversaRepository;
import kisto.backend.repository.MensagemRepository;
import kisto.backend.repository.UsuarioRepository;
import utils.TipoDeConversa;



@Service("chatService")
public class ChatServiceImp implements ChatService {
	
	@Autowired
	ConversaRepository conversaRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	MensagemRepository mensagemRepo;
	
	@Override
	public Conversa criarConversa(String assunto, TipoDeConversa tipo, Set<Long> usuarios) {

			Conversa conversa = new Conversa();
			conversa.setAssunto(assunto);
			conversaRepo.save(conversa);
			
			HashSet<Usuario> listaDeUsuarios = new HashSet<Usuario>();
			for(Long id: usuarios) {
				Usuario usuario = usuarioRepo.findById(id).get();
				listaDeUsuarios.add(usuario);
			}
						
			conversa.setUsuarios(listaDeUsuarios);
			
			conversaRepo.save(conversa);
			return conversa;
		
	}

	@Override
	public Mensagem enviarMensagem(Long usuarioId, Long conversaId, String texto) {
		Usuario remetente = usuarioRepo.findById(usuarioId).get();
		Conversa conversa = conversaRepo.findById(conversaId).get();
				
		Mensagem mensagem = new Mensagem();
		mensagem.setRemetente(remetente);
		mensagem.setConversa(conversa);
		mensagem.setTexto(texto);
		
		mensagemRepo.save(mensagem);
		
		return mensagem;
	}

	@Override
	public Set<Conversa> getConversasDeUsuario(Long usuarioId) {
		return usuarioRepo.findById(usuarioId).get().getConversas();
	}

	@Override
	public Set<Usuario> getUsuariosDeConversa(Long conversaId) {
		return conversaRepo.findById(conversaId).get().getUsuarios();
	}

	@Override
	public void exlcuirMensagem(Long mensagemId) {		
		Mensagem mensagem = mensagemRepo.findById(mensagemId).get();
		Long conversaId = mensagem.getConversa().getId();
		mensagemRepo.delete(mensagem);
	}

	@Override
	public Conversa corrigirMensagem(Long mensagemId, String novoTexto) {
		Mensagem mensagem = mensagemRepo.findById(mensagemId).get();
		mensagem.setTexto(novoTexto);
		mensagemRepo.save(mensagem);
		return conversaRepo.findByMensagemsId(mensagemId);
	}
	
	@Override
	public List<Mensagem> buscaMensagensDeConversa(Long conversaId) {
		return mensagemRepo.findByConversaId(conversaId);
	}

}
