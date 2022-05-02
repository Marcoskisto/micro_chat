package kisto.backend.service;

import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import kisto.backend.dto.UsuarioDto;
import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.entity.Usuario;
import kisto.backend.enums.ConversaTipo;
import kisto.backend.exceptions.UsuarioForaDaconversaException;

@Service("chatService")
public interface ChatService {
	
	public Conversa criarConversa(String assunto, ConversaTipo tipo, Set<UsuarioDto> usuariosInclusao);

	public Conversa atualizaAssuntoDaConversa(Long id, String assunto);
	
	public Set<Conversa> getConversasDeUsuario(Long usuarioId);
	
	public Set<Usuario> getUsuariosDeConversa(Long conversaId);

	public Mensagem enviarMensagem(Long usuarioId, Long conversaId, String texto) throws UsuarioForaDaconversaException;
		
	public void exlcuirMensagem(Long mensagemId);
	
	public Conversa corrigirMensagem(Long mensagemId, String novoTexto);
	
	public List<Mensagem> buscaMensagensDeConversa(Long conversaId);

	public Conversa adicionarUsuariosNaConversa(Long conversaId, Set<UsuarioDto> usuariosEmAdicao);

	public Set<Conversa> getConversasDeUsuarioPorTipo(ConversaTipo conversaTipo, Long usuarioId);

	public Conversa removeUsuarioDaConversa(Long conversaId, Long usuarioId);
}
