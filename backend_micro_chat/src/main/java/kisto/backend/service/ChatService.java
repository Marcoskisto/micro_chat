package kisto.backend.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.entity.Usuario;
import kisto.backend.exceptions.UsuarioForaDaconversaException;
import utils.TipoDeConversa;

@Service("chatService")
public interface ChatService {
	
	public Conversa criarConversa(String assunto, TipoDeConversa tipo, Set<Long> usuariosId);
	
	public Mensagem enviarMensagem(Long usuarioId, Long conversaId, String texto) throws UsuarioForaDaconversaException;
	
	public Set<Conversa> getConversasDeUsuario(Long usuarioId);
		
	public Set<Usuario> getUsuariosDeConversa(Long conversaId);
	
	public void exlcuirMensagem(Long mensagemId);
	
	public Conversa corrigirMensagem(Long mensagemId, String novoTexto);

	public List<Mensagem> buscaMensagensDeConversa(Long conversaId);

	public Conversa atualizaAssuntoDaConversa(Long id, String assunto);

	public Conversa adicionaUsuarioNaConversa(Long conversaId, Long usuarioId);

	
}
