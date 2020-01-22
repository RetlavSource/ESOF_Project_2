package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.DTO.AtendimentoDTO;

import java.util.Optional;

public interface AtendimentoService {

    Optional<AtendimentoDTO> criarAtendimentoUniversidade(AtendimentoDTO atendimentoDTO, String nomeUniversidade);

}
