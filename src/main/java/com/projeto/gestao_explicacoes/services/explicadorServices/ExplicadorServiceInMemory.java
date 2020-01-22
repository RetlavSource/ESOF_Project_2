package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.models.Explicador;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Profile(value = "inmemory")
public class ExplicadorServiceInMemory implements ExplicadorService {

    @Override
    public Optional<Explicador> criarExplicador(Explicador explicador) {

        return Optional.empty();
    }

    @Override
    public String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade) {
        return null;
    }
}
