package com.projeto.gestao_explicacoes.repositories;

import com.projeto.gestao_explicacoes.models.Universidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversidadeRepo extends CrudRepository<Universidade, Long> {

    Optional<Universidade> findBySigla(String sigla);

    Optional<Universidade> findByNome(String nome);

}
