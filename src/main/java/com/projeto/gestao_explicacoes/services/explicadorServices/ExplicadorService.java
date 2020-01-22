package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;

import java.util.Map;
import java.util.Optional;

public interface ExplicadorService {

    Optional<ExplicadorDTO> criarExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade);

    String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade);

    Optional<ExplicadorDTO> modificaExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade);
}
