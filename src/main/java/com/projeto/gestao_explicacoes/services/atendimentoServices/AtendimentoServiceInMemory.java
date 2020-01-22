package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.DTO.AtendimentoDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile(value = "inmemory")
public class AtendimentoServiceInMemory implements AtendimentoService {


    @Override
    public Optional<AtendimentoDTO> criarAtendimentoUniversidade(AtendimentoDTO atendimentoDTO, String nomeUniversidade) {
        return Optional.empty();
    }
}
