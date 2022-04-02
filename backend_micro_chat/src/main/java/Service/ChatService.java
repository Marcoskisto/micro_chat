package Service;

import java.util.Set;

import org.springframework.stereotype.Service;

import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.entity.Usuario;
import utils.TipoDeConversa;

@Service("chatService")
public interface ChatService {
	
	public Conversa criarConversa(String assunto, TipoDeConversa tipo, Set<Long> usuariosId);
	
	public Mensagem enviarMensagem(Long usuarioId, Long conversaId, String texto);
	
	public Set<Conversa> getConversasDeUsuario(Long usuarioId);
		
	public Set<Usuario> getUsuariosDeConversa(Long conversaId);
	
	public Conversa exluirMensagem(Long mensagemId);
	
	public Conversa corrigirMensagem(Long mensagemId, String novoTexto);

	
}
