package com.projeto.gestao_explicacoes.services.universidadeServices;

import com.projeto.gestao_explicacoes.models.Universidade;
import com.projeto.gestao_explicacoes.repositories.UniversidadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "db")
public class UniversidadeServiceDB implements UniversidadeService {

    private UniversidadeRepo universidadeRepo;

    @Autowired
    public UniversidadeServiceDB(UniversidadeRepo universidadeRepo) {
        this.universidadeRepo = universidadeRepo;
    }

    @Override
    public Set<Universidade> findAll() {
        return null;
    }
}
