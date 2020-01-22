package com.projeto.gestao_explicacoes.services.universidadeServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.UniversidadeDTO;
import com.projeto.gestao_explicacoes.models.Universidade;
import com.projeto.gestao_explicacoes.models.builders.WebService;
import com.projeto.gestao_explicacoes.models.mappers.OpenStreetMapper;
import com.projeto.gestao_explicacoes.repositories.UniversidadeRepo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        this.logger.info("No método: UniversidadeServiceDB -> findAll()");

        Set<UniversidadeDTO> universidades = new HashSet<>();

        for (Universidade universidade : this.universidadeRepo.findAll()) {
            universidades.add(universidade.copyToUniversidadeDTO());
        }

        return universidades;
    }

    /**
     * Cria uma universidade e preenche os seus dados via web api.
     *
     * @param universidadeDTO dados da universidade a criar
     * @return dto da universidade criada
     */
    @Override
    public Optional<UniversidadeDTO> criarUniversidade(UniversidadeDTO universidadeDTO) {
        this.logger.info("No método: UniversidadeServiceDB -> criarUniversidade()");

        if ( universidadeDTO.getNome() == null || universidadeDTO.getSigla() == null || universidadeDTO.getUrl() == null ) {
            throw new FalhaCriarException("Os campos \"nome\", \"sigla\" e \"nome\", têm que ser preenchidos!!");
        }

        if ( universidadeDTO.getNome().isBlank() || universidadeDTO.getSigla().isBlank() || universidadeDTO.getUrl().isBlank() ) {
            throw new FalhaCriarException("Os campos \"nome\", \"sigla\" e \"nome\", têm que ser preenchidos!!");
        }

        if (universidadeDTO.getTipo() != null) {
            if (universidadeDTO.getTipo().isBlank()) {
                universidadeDTO.setTipo("university");
            }
        } else {
            universidadeDTO.setTipo("university");
        }

        Optional<Universidade> checkUniversidade = this.universidadeRepo.findByNome(universidadeDTO.getNome());
        if (checkUniversidade.isPresent()) {
            return Optional.empty();
        }

        Universidade universidade = new Universidade(universidadeDTO.getNome(), universidadeDTO.getSigla(), universidadeDTO.getUrl());

        this.getDadosUniversidade(universidade, universidadeDTO.getTipo());
        this.logger.info("A gravar universidade!");
        this.universidadeRepo.save(universidade);

        return Optional.of(universidade.copyToUniversidadeDTO());
    }

    /**
     * Método que pesquisa informação sobre a faculdade e completa os seus parâmetros.
     *
     * @param universidade previamente criada
     * @param tipo tipo da informação pesquisada
     */
    private void getDadosUniversidade(@NotNull Universidade universidade, String tipo) {
        this.logger.info("No método: UniversidadeServiceDB -> getDadosUniversidade()");

        String openMapsURL = "https://nominatim.openstreetmap.org/search.php?q=";
        String stringFormat = "&format=json";

        StringBuilder sb = new StringBuilder();
        sb.append(openMapsURL).append(universidade.getNome()).append(stringFormat);
        ResponseEntity<String> universidadeMap = WebService.byGet(sb.toString(), String.class);
        if (universidadeMap.getBody() == null) {
            this.logger.info("Resultado dos dados sobre a faculdade sem sucesso!!");
            return;
        }

        ObjectMapper universidadeObjectMapper = new ObjectMapper();
        universidadeObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            List<OpenStreetMapper> universidadeObjects = universidadeObjectMapper.readValue(universidadeMap.getBody() , new TypeReference<List<OpenStreetMapper>>() {});
            for (OpenStreetMapper openStreetMapper : universidadeObjects) {
                if (openStreetMapper.getType().equals(tipo)) {
                    openStreetMapper.completaUniversidade(universidade);
                    this.logger.info("Dados sobre a faculdade adquiridos com sucesso!!");
                    break;
                }
            }
            this.logger.info("Não encontrados dados sobre a faculdade!!");
        } catch (JsonProcessingException e) {
            this.logger.info("Erro JSON: Resultado dos dados sobre a faculdade sem sucesso!!");
            e.printStackTrace();
        }

    }
}
