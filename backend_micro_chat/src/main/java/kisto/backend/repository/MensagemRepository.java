package kisto.backend.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import kisto.backend.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	public Set<Mensagem> findByConversaAssunto(String assunto);
}
