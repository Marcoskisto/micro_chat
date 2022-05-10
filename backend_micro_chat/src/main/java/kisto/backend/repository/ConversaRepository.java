package kisto.backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import kisto.backend.entity.Conversa;
import kisto.backend.enums.ConversaTipo;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
	public Set<Conversa> findByUsuariosNickname(String nome);
	
	public Set<Conversa> findByUsuariosId(Long usuarioId);

	public Conversa findByAssunto(String assunto);

	public Conversa findByMensagemsId(Long mensagemId);
	
	public Set<Conversa> findByTipoAndUsuariosId(ConversaTipo conversaTipo, Long usuarioId);
	
}
