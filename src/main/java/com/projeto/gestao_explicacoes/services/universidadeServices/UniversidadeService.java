package com.projeto.gestao_explicacoes.services.universidadeServices;

import com.projeto.gestao_explicacoes.models.DTO.UniversidadeDTO;
import com.projeto.gestao_explicacoes.models.Universidade;

import java.util.Optional;
import java.util.Set;

public interface UniversidadeService {

    Set<UniversidadeDTO> findAll();

    Optional<UniversidadeDTO> criarUniversidade(UniversidadeDTO universidadeDTO);
}
