package Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import kisto.backend.entity.Conversa;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
	public Set<Conversa> findByUsuarioNickname(String nome);

	public Conversa findByAssunto(String assunto);

	public Conversa findByMensagemId(Long mensagemId);
	
}
