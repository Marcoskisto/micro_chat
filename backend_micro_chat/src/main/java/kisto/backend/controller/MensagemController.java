package kisto.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.dto.MensagemDto;
import kisto.backend.entity.Mensagem;
import kisto.backend.exceptions.UsuarioForaDaconversaException;
import kisto.backend.service.ChatServiceImp;

@RestController
@RequestMapping(value = "/mensagem/")
@CrossOrigin
public class MensagemController {
	
	@Autowired
	public ChatServiceImp chatService;
	
	@GetMapping(value = "listar_por_conversa/{conversaId}")
	@JsonView(View.MensagemLeitura.class)
	public List<Mensagem> listarMensagemsDeConversa(
			@PathVariable("conversaId") Long conversaId){
		return chatService.buscaMensagensDeConversa(conversaId);
		
	}
	
	@PostMapping(value = "enviar")
	@JsonView(View.MensagemLeitura.class)
	public ResponseEntity<Mensagem> enviarMensagem(@RequestBody MensagemDto mensagem) {
		
		try {
			Mensagem msgEnviada = chatService.enviarMensagem(
					mensagem.getRemetenteId(),
					mensagem.getConversaId(),
					mensagem.getTexto()
					);
			return new ResponseEntity<Mensagem>(msgEnviada, HttpStatus.OK);
		} 
		catch (UsuarioForaDaconversaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "excluir/{mensagemId}")
	public ResponseEntity<String> excluirMensagem(@PathVariable("mensagemId") Long mensagemId) {
		try {
			chatService.exlcuirMensagem(mensagemId);
			return new ResponseEntity<>("Mensagem excluída", HttpStatus.OK);
		}
		catch (java.util.NoSuchElementException e) {
			return new ResponseEntity<>("Mensagem inexistente",HttpStatus.OK);
		}
	}
}
