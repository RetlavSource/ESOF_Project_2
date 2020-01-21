package com.projeto.gestao_explicacoes.services.universidadeServices;

import com.projeto.gestao_explicacoes.models.DTO.UniversidadeDTO;
import com.projeto.gestao_explicacoes.models.Universidade;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "inmemory")
public class UniversidadeServiceInMemory implements UniversidadeService {

    @Override
    public Set<UniversidadeDTO> findAll() {
        return null;
    }
}
