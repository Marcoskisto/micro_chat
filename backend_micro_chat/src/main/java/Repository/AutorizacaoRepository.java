package Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kisto.backend.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {
public Autorizacao findByNome(String nome);
}
