package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.ExplicadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private ExplicadorService explicadorService;

    @Autowired
    public ExplicadorController(ExplicadorService explicadorService) {
        this.explicadorService = explicadorService;
    }

    /**
     * Pesquisa os explicadores numa determinada universidade por parâmetros.
     *
     * @param nomeUniversidade sigla da universidade
     * @param parametros capturados no url
     * @return
     */
    @GetMapping(value = "/{universidade}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getExplicadoresUniversidade(@PathVariable("universidade") String nomeUniversidade, @RequestParam Map<String, String> parametros) {
        this.logger.info("Recebido um pedido GET em getExplicadoresUniversidade()");


        return ResponseEntity.ok(this.explicadorService.procuraExplicadoresUniversidade(parametros, nomeUniversidade));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explicador> createExplicador(@RequestBody Explicador explicador){

        this.logger.info("Recebido um pedido POST");

        Optional<Explicador> criadoExplicador = this.explicadorService.criarExplicador(explicador);

        if(criadoExplicador.isPresent()){

            return ResponseEntity.ok(criadoExplicador.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + explicador.getNome() + " ja existe!");
    }
}
