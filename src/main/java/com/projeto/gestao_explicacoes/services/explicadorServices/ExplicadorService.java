package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;

import java.util.Map;
import java.util.Optional;

public interface ExplicadorService {

    Optional<Explicador> criarExplicador(Explicador explicador);

    String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade);
}
