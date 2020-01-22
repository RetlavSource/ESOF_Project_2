package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
import com.projeto.gestao_explicacoes.models.Universidade;
import com.projeto.gestao_explicacoes.models.builders.WebService;
import com.projeto.gestao_explicacoes.repositories.UniversidadeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;
import java.util.Optional;

@Service
@Profile(value = "db")
public class ExplicadorServiceDB implements ExplicadorService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private UniversidadeRepo universidadeRepo;

    @Autowired
    public ExplicadorServiceDB(UniversidadeRepo universidadeRepo) {
        this.universidadeRepo = universidadeRepo;
    }

    /**
     * Cria um explicador numa universidade.
     *
     * @param explicadorDTO dto com os dados do explicador
     * @param nomeUniversidade string com a sigla da universidade
     * @return dto do explicador se criado
     */
    @Override
    public Optional<ExplicadorDTO> criarExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade) {
        this.logger.info("No método: ExplicadorServiceDB -> criarExplicadorUniversidade()");

        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a universidade indicada!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador");
        String fullUrl = sb.toString();

        try {
            ResponseEntity<ExplicadorDTO> auxExplicadorDTO = WebService.byPost(explicadorDTO, fullUrl, ExplicadorDTO.class);
            System.out.println(auxExplicadorDTO.getBody());
            this.logger.info("Explicador criado!!");
            return Optional.ofNullable(auxExplicadorDTO.getBody());
        } catch (HttpClientErrorException exc) {
            this.logger.info("O explicador já existe!!");
            throw new FalhaCriarException("O explicador já existe!!");
        }

    }

    /**
     * Procura um explicador, numa faculdade, por diversos parâmetros, utilizando filtros.
     *
     * @param parametros a pesquisar
     * @param nomeUniversidade nome da faculdade
     * @return explicadores encontrados
     */
    @Override
    public String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade) {
        this.logger.info("No método: ExplicadorServiceDB -> procuraExplicadoresUniversidade()");

        System.out.println(parametros);
        System.out.println(nomeUniversidade);

        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a universidade indicada!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador").append("?");
        sb.append("cadeira=").append(parametros.get("cadeira"));
        sb.append("&");
        sb.append("idioma=").append(parametros.get("idioma"));
        sb.append("&");
        sb.append("dia=").append(parametros.get("dia"));
        sb.append("&");
        sb.append("inicio=").append(parametros.get("inicio"));
        sb.append("&");
        sb.append("fim=").append(parametros.get("fim"));

        String fullUrl = sb.toString();

        System.out.println(fullUrl);

        ResponseEntity<String> auxSetExplicadores = WebService.byGet(fullUrl, String.class);

        return auxSetExplicadores.getBody();
    }

    /**
     * Modifica um explicador de uma faculdade.
     *
     * @param explicadorDTO dto com as informações do explicador
     * @param nomeUniversidade string com a sigla da universidade
     * @return dto do explicador modificado
     */
    @Override
    public Optional<ExplicadorDTO> modificaExplicadorUniversidade(ExplicadorDTO explicadorDTO, String nomeUniversidade) {
        this.logger.info("No método: ExplicadorServiceDB -> modificaExplicadorUniversidade()");

        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a universidade indicada!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador");
        String fullUrl = sb.toString();

        try {
            ResponseEntity<ExplicadorDTO> auxExplicadorDTO = WebService.byPut(explicadorDTO, fullUrl, ExplicadorDTO.class);
            System.out.println(auxExplicadorDTO.getBody());
            this.logger.info("Explicador modificado!!");
            return Optional.ofNullable(auxExplicadorDTO.getBody());
        } catch (HttpClientErrorException exc) {
            this.logger.info("O explicador não foi modificado!!");
            throw new FalhaCriarException("O explicador não foi modificado!!");
        }

    }

    /**
     * Associa uma cadeira a um explicador, de uma faculdade.
     *
     * @param explicadorDTO dto com os dados do explicador
     * @param nomeUniversidade string com a sigla da universidade
     * @param nomeCadeira nome da cadeira
     * @return dto do explicador modificado
     */
    @Override
    public Optional<ExplicadorDTO> modificaExplicadorUniversidadeCadeira(ExplicadorDTO explicadorDTO, String nomeUniversidade, String nomeCadeira) {
        this.logger.info("No método: ExplicadorServiceDB -> modificaExplicadorUniversidadeCadeira()");

        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a universidade indicada!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador").append("/").append(nomeCadeira);
        String fullUrl = sb.toString();

        try {
            ResponseEntity<ExplicadorDTO> auxExplicadorDTO = WebService.byPut(explicadorDTO, fullUrl, ExplicadorDTO.class);
            System.out.println(auxExplicadorDTO.getBody());
            this.logger.info("Explicador modificado!!");
            return Optional.ofNullable(auxExplicadorDTO.getBody());
        } catch (HttpClientErrorException exc) {
            this.logger.info("O explicador não foi modificado!!");
            throw new FalhaCriarException("O explicador não foi modificado!!");
        }

    }


}
