package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;

import java.util.Map;
import java.util.Optional;

public interface ExplicadorService {

    String procuraExplicadoresTodos(Map<String, String> parametros);

    String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade);

    String procuraExplicadorUniversidadeNome(String nomeUniversidade, String nomeExplicador);

    Optional<ExplicadorDTO> criarExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade);

    Optional<ExplicadorDTO> modificaExplicadorTodas(ExplicadorDTO explicadorDTO);

    Optional<ExplicadorDTO> modificaExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade);

    Optional<ExplicadorDTO> modificaExplicadorUniversidadeCadeira(ExplicadorDTO explicadorDTO, String nomeUniversidade, String nomeCadeira);
}
