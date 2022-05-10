package kisto.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import kisto.backend.dto.UsuarioDto;
import kisto.backend.entity.Usuario;
import kisto.backend.exceptions.UsuarioJaCadastradoException;
import kisto.backend.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario/")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	public UsuarioService usuarioService;
	
	@GetMapping(value = "lista")
	@JsonView(View.UsuarioLista.class)
	public List<Usuario> recuperarTodosUsuarios() {
		return usuarioService.recuperarTodosUsuarios();
	}
	
	@GetMapping(value = "{id}")
	@JsonView(View.UsuarioDados.class)
	public Usuario recuperaDadosDeUsuario(
			@PathVariable("id") Long id) {
		return usuarioService.getUsuario(id);
	}
	
	
	@PostMapping(value = "cadastrar")
	@JsonView(View.UsuarioDados.class)
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDto usuario){
		
		try {
			Usuario usuarioCadastrado = usuarioService.cadastrarUsuario(
					usuario.getNickname(),
					usuario.getEmail(), 
					usuario.getSenha(), 
					usuario.getAutorizacoes()
					);
			
			return new ResponseEntity<Usuario>(usuarioCadastrado, HttpStatus.OK);
			
		} catch (UsuarioJaCadastradoException e) {
			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}
	}
}
