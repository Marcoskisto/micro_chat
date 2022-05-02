package kisto.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import kisto.backend.dto.UsuarioDto;
import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.entity.Usuario;
import kisto.backend.enums.ConversaTipo;
import kisto.backend.exceptions.UsuarioForaDaconversaException;
import kisto.backend.repository.ConversaRepository;
import kisto.backend.repository.MensagemRepository;
import kisto.backend.repository.UsuarioRepository;



//@PreAuthorize("isAuthenticated()")
@Service("chatService")
public class ChatServiceImp implements ChatService {
	
	@Autowired
	ConversaRepository conversaRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	MensagemRepository mensagemRepo;
	
	@Override
	public Conversa criarConversa(String assunto, ConversaTipo tipo, Set<UsuarioDto> usuariosInclusao) {

			Conversa conversa = new Conversa();
			conversa.setAssunto(assunto);
			conversa.setTipo(tipo);
			
			HashSet<Usuario> listaDeUsuarios = new HashSet<Usuario>();
			for(UsuarioDto usuarioInclusao: usuariosInclusao) {
				Usuario usuario = usuarioRepo.findById(usuarioInclusao.getId()).get();
				listaDeUsuarios.add(usuario);				
			}
			conversa.setUsuarios(listaDeUsuarios);
			
			conversaRepo.save(conversa);
			return conversa;
	}

	@Override
	public Mensagem enviarMensagem(Long usuarioId, Long conversaId, String texto) throws UsuarioForaDaconversaException {
		Usuario remetente = usuarioRepo.findById(usuarioId).get();
		Conversa conversa = conversaRepo.findById(conversaId).get();
		
		if (!conversa.getUsuarios().contains(remetente)) {
			throw new UsuarioForaDaconversaException();
		}
				
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
	
	@Override
	public Conversa atualizaAssuntoDaConversa(Long id, String assunto) {
		Conversa conversa = conversaRepo.findById(id).get();
		conversa.setAssunto(assunto);
		return conversaRepo.save(conversa);
	}

	@Override
	public Conversa adicionarUsuariosNaConversa(Long conversaId, Set<UsuarioDto> usuariosEmAdicao) {
		Conversa conversa = conversaRepo.findById(conversaId).get();
		Set<Usuario> listaDeUsuarios = conversa.getUsuarios();
		for(UsuarioDto usuarioDto: usuariosEmAdicao) {
			Usuario usuario = usuarioRepo.findById(usuarioDto.getId()).get();
			listaDeUsuarios.add(usuario);
		}
		conversa.setUsuarios(listaDeUsuarios);			
		return conversaRepo.save(conversa);
	}
	
	@Override
	public Conversa removeUsuarioDaConversa(Long conversaId, Long usuarioId) {
		Conversa conversa = conversaRepo.findById(conversaId).get();
		Usuario usuario = usuarioRepo.findById(usuarioId).get();
		Set<Usuario> usuarios = conversa.getUsuarios();
		usuarios.remove(usuario);
		conversa.setUsuarios(usuarios);
		return conversaRepo.save(conversa);
	}
	
	@Override
	public Set<Conversa> getConversasDeUsuarioPorTipo(ConversaTipo conversaTipo, Long usuarioId) {
		return conversaRepo.findByTipoAndUsuariosId(conversaTipo, usuarioId);
	}

}
