package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.AtendimentoDTO;
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

import java.util.Optional;

@Service
@Profile(value = "db")
public class AtendimentoServiceDB implements AtendimentoService {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private UniversidadeRepo universidadeRepo;

    @Autowired
    public AtendimentoServiceDB(UniversidadeRepo universidadeRepo) {
        this.universidadeRepo = universidadeRepo;
    }

    /**
     * Cria um atendimento numa determinada faculdade.
     *
     * @param atendimentoDTO dto com os dados do atendimento
     * @param nomeUniversidade string com a sigla da universidade
     * @return dto do atendimento criado
     */
    @Override
    public Optional<AtendimentoDTO> criarAtendimentoUniversidade(AtendimentoDTO atendimentoDTO, String nomeUniversidade) {
        this.logger.info("No método: AtendimentoServiceDB -> criarAtendimentoUniversidade()");

        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a universidade indicada!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/atendimento");
        String fullUrl = sb.toString();

        try {
            ResponseEntity<AtendimentoDTO> auxAtendimentoDTO = WebService.byPost(atendimentoDTO, fullUrl, AtendimentoDTO.class);
            System.out.println(auxAtendimentoDTO.getBody());
            this.logger.info("Atendimento criado!!");
            return Optional.ofNullable(auxAtendimentoDTO.getBody());
        } catch (HttpClientErrorException exc) {
            this.logger.info("Erro ao criar o atendimento!!");
            throw new FalhaCriarException("Erro ao criar o atendimento!!");
        }

    }
}
