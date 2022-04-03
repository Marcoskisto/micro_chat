package kisto.backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import kisto.backend.entity.Conversa;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
	public Set<Conversa> findByUsuariosNickname(String nome);

	public Conversa findByAssunto(String assunto);

	public Conversa findByMensagemsId(Long mensagemId);
	
}
