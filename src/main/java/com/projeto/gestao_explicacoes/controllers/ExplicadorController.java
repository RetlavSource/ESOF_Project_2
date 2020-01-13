package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.services.explicadorServices.ExplicadorService;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterObjectExplicador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/explicador")
public class ExplicadorController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private ExplicadorService explicadorService;

    @Autowired
    public ExplicadorController(ExplicadorService explicadorService) {
        this.explicadorService = explicadorService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Explicador>> getAllExplicadores() {
        this.logger.info("Recebido um pedido GET");

        return ResponseEntity.ok(this.explicadorService.findAll());
    }

    @GetMapping(value = "/{universidade}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Explicador>> getExplicadoresUniversidade(@PathVariable("universidade") String nomeUniversidade) {
        this.logger.info("Recebido um pedido GET para /{universidade}");

        return ResponseEntity.ok(this.explicadorService.findExplicadorByUniversidade(nomeUniversidade));
    }

    @GetMapping(value = "/{universidade}/procura", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Explicador>> procuraDisponibilidadeExplicadoresUniversidade(@PathVariable("universidade") String nomeUniversidade, @RequestParam Map<String, String> parametros) {
        this.logger.info("Recebido um pedido GET para /{universidade}/procura");

        String nomeCadeira = parametros.get("cadeira");
        String nomeIdioma = parametros.get("idioma");
        String diaSemana = parametros.get("dia");
        String horaInicio = parametros.get("inicio");
        String horaFim = parametros.get("fim");
        if (nomeIdioma != null) {
            nomeIdioma = nomeIdioma.toUpperCase();
        }
        DayOfWeek dia = null;
        if (diaSemana != null) {
            dia = DayOfWeek.valueOf(diaSemana.toUpperCase());
        }
        LocalTime timeInit = null;
        LocalTime timeEnd = null;
        if (horaInicio != null) {
            timeInit = LocalTime.parse(horaInicio);
        }
        if (horaFim != null) {
            timeEnd = LocalTime.parse(horaFim);
        }

        FilterObjectExplicador filterObjectExplicador = new FilterObjectExplicador(nomeCadeira, nomeIdioma, dia, timeInit, timeEnd);

        return ResponseEntity.ok(this.explicadorService.procuraExplicadorDisponivelByUniversidade(filterObjectExplicador, nomeUniversidade));
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
