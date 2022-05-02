package kisto.backend.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.dto.ConversaDto;
import kisto.backend.dto.UsuarioDto;
import kisto.backend.entity.Conversa;
import kisto.backend.enums.ConversaTipo;
import kisto.backend.service.ChatServiceImp;

@RestController
@RequestMapping(value = "/conversa/")
@CrossOrigin
public class ConversaController {
	
	@Autowired
	public ChatServiceImp chatService;
	
	@GetMapping(value = "listar_por_usuario/{usuarioId}")
	@JsonView(View.ConversaLista.class)
	public Set<Conversa> listarConversasDeUsuario(
			@PathVariable("usuarioId") Long usuarioId) {
		
		return chatService.getConversasDeUsuario(usuarioId);
	}
	
	@GetMapping(value = "listar_por_tipo/{tipo}/usuario/{usuarioId}")
	@JsonView(View.ConversaLista.class)
	public Set<Conversa> listarConversasDeUsuarioPorTipo(
			@PathVariable("tipo") String tipo,
			@PathVariable("usuarioId") Long usuarioId){
				ConversaTipo conversaTipo = ConversaTipo.valueOf(tipo);
				return chatService.getConversasDeUsuarioPorTipo(conversaTipo, usuarioId);
	}
	
	@PostMapping(value = "criar")
	@JsonView({View.ConversaUsuarioCompleta.class})
	public Conversa criaNovaConversa(@RequestBody ConversaDto conversa) {
		
		return chatService.criarConversa(
				conversa.getAssunto(), 
				conversa.getTipo(), 
				conversa.getUsuarios()
			);
	}
	
	@PutMapping(value = "assunto/{conversaId}")
	public Conversa atualizaAssuntoDaConversa(
			@PathVariable("conversaId") Long conversaId, @RequestBody ConversaDto conversa) {
		
		return chatService.atualizaAssuntoDaConversa(
				conversaId,
				conversa.getAssunto()
				);
	}
	
	@PutMapping(value = "adicionarUsuarios/{conversaId}")
	@JsonView(View.ConversaUsuarioCompleta.class)
	public Conversa adicionarUsuarios(
			@RequestBody ConversaDto conversa) {
		
		return chatService.adicionarUsuariosNaConversa(conversa.getId(), conversa.getUsuarios());	
	}
	
	@PutMapping(value = "removerUsuario/{conversaId}")
	@JsonView(View.ConversaUsuarios.class)
	public Conversa removerUsuarioDaConversa(
			@PathVariable("conversaId") Long conversaId, @RequestBody UsuarioDto usuario) {
		
		return chatService.removeUsuarioDaConversa(conversaId, usuario.getId());
	}	
}
