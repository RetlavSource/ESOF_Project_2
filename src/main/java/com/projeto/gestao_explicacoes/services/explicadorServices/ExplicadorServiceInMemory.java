package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Profile(value = "inmemory")
public class ExplicadorServiceInMemory implements ExplicadorService {

    @Override
    public Optional<ExplicadorDTO> criarExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade) {
        return Optional.empty();
    }

    @Override
    public String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade) {
        return null;
    }
}
