package com.projeto.gestao_explicacoes.services.universidadeServices;

import com.projeto.gestao_explicacoes.models.Universidade;

import java.util.Set;

public interface UniversidadeService {

    Set<Universidade> findAll();

}