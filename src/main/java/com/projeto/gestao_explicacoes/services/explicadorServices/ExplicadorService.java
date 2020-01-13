package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterObjectExplicador;

import java.util.Optional;
import java.util.Set;

public interface ExplicadorService {

    Set<Explicador> findAll();

    Optional<Explicador> criarExplicador(Explicador explicador);

    Set<Explicador> findExplicadorByUniversidade(String nomeUniversidade);

    Set<Explicador> procuraExplicadorDisponivelByUniversidade(FilterObjectExplicador filterObjectExplicador, String nomeUniversidade);
}
