package kisto.backend.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import kisto.backend.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {	
	
	public Usuario findByNickname(String nickname);
	
	public Optional<Usuario> findById(Long id);
	
	public Usuario findByEmail(String email);
	
	public Set<Usuario> findByConversasId(Long conversaId);
	
}
