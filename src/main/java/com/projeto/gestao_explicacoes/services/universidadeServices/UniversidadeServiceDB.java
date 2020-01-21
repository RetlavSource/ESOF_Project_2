package com.projeto.gestao_explicacoes.services.universidadeServices;

import com.projeto.gestao_explicacoes.models.DTO.UniversidadeDTO;
import com.projeto.gestao_explicacoes.models.Universidade;
import com.projeto.gestao_explicacoes.repositories.UniversidadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile(value = "db")
public class UniversidadeServiceDB implements UniversidadeService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private UniversidadeRepo universidadeRepo;

    @Autowired
    public UniversidadeServiceDB(UniversidadeRepo universidadeRepo) {
        this.universidadeRepo = universidadeRepo;
    }

    @Override
    public Set<UniversidadeDTO> findAll() {
        this.logger.info("No método: UniversidadeServiceDB -> findAll");

        Set<UniversidadeDTO> universidades = new HashSet<>();

        for (Universidade universidade : this.universidadeRepo.findAll()) {
            universidades.add(universidade.copyToUniversidadeDTO());
        }

        return universidades;
    }
}
