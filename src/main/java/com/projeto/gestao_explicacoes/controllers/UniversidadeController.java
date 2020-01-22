package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.UniversidadeDTO;
import com.projeto.gestao_explicacoes.services.universidadeServices.UniversidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/universidade")
public class UniversidadeController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private UniversidadeService universidadeService;

    @Autowired
    public UniversidadeController(UniversidadeService universidadeService) {
        this.universidadeService = universidadeService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UniversidadeDTO>> getAllUniversidades() {
        this.logger.info("Recebido um pedido GET em getAllUniversidades()");

        return ResponseEntity.ok(this.universidadeService.findAll());
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UniversidadeDTO> createUniversidade(@RequestBody UniversidadeDTO universidadeDTO) {
        this.logger.info("Recebido um pedido POST em createUniversidade()");

        Optional<UniversidadeDTO> optUniversidade = this.universidadeService.criarUniversidade(universidadeDTO);

        if (optUniversidade.isPresent()) {
            return ResponseEntity.ok(optUniversidade.get());
        }

        throw new FalhaCriarException("Faculdade com o nome: "+ universidadeDTO.getNome() + " já existe!!");
    }

}
