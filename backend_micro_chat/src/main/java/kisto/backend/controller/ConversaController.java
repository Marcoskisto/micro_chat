package kisto.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.dto.MensagemDto;
import kisto.backend.entity.Conversa;
import kisto.backend.entity.Mensagem;
import kisto.backend.service.ChatServiceImp;

@RestController
@RequestMapping(value = "/")
@CrossOrigin
public class ConversaController {
	
	@Autowired
	public ChatServiceImp chatService;
	
	@GetMapping(value = "conversa/por_usuario/{usuarioId}")
	@JsonView(View.ConversaLista.class)
	public Set<Conversa> listarConversasDeUsuario(
			@PathVariable("usuarioId") Long usuarioId) {
		
		return chatService.getConversasDeUsuario(usuarioId);
	}
	
	@GetMapping(value = "mensagem/por_conversa/{conversaId}")
	@JsonView(View.MensagemLeitura.class)
	public List<Mensagem> listarMensagemsDeConversa(
			@PathVariable("conversaId") Long conversaId){
		return chatService.buscaMensagensDeConversa(conversaId);
		
	}
	
	@PostMapping(value = "mensagem/enviar")
	@JsonView(View.MensagemLeitura.class)
	public Mensagem enviarMensagem(@RequestBody MensagemDto mensagem) {
		
		return chatService.enviarMensagem(
				mensagem.getRemetenteId(),
				mensagem.getConversaId(),
				mensagem.getTexto()
				);
	}

}
