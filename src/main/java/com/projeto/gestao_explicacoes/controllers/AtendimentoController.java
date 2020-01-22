package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.AtendimentoDTO;
import com.projeto.gestao_explicacoes.services.atendimentoServices.AtendimentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    /**
     * Cria um atendimento numa determinada faculdade.
     *
     * @param atendimentoDTO dto que recebe os dados do atendimento
     * @param nomeUniversidade string com a sigla da universidade
     * @return dto do atendimento criado
     */
    @PostMapping(value = "/{universidade}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtendimentoDTO> createAtendimentoUniversidade(@RequestBody AtendimentoDTO atendimentoDTO, @PathVariable("universidade") String nomeUniversidade) {
        this.logger.info("Recebido um pedido POST em createAtendimentoUniversidade()");

        Optional<AtendimentoDTO> criadoAtendimento = this.atendimentoService.criarAtendimentoUniversidade(atendimentoDTO, nomeUniversidade);

        if (criadoAtendimento.isPresent()) {
            return ResponseEntity.ok(criadoAtendimento.get());
        }

        throw new FalhaCriarException("O atendimento entre o explicador " + atendimentoDTO.getNomeExplicador() + " e o aluno " + atendimentoDTO.getNomeAluno() + " na data " + atendimentoDTO.getData() + " nao foi criado com sucesso!");
    }
}
