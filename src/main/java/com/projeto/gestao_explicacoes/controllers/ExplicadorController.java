package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.DTO.ExplicadorDTO;
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
     * @return string em formato json com os explicadores
     */
    @GetMapping(value = "/{universidade}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getExplicadoresUniversidade(@PathVariable("universidade") String nomeUniversidade, @RequestParam Map<String, String> parametros) {
        this.logger.info("Recebido um pedido GET em getExplicadoresUniversidade()");


        return ResponseEntity.ok(this.explicadorService.procuraExplicadoresUniversidade(parametros, nomeUniversidade));
    }

    /**
     * Cria um explicador numa universidade.
     *
     * @param explicadorDTO dto do explicador passado por POST, no payload
     * @param nomeUniversidade sigla da universidade
     * @return dto do explicador, se criado
     */
    @PostMapping(value = "/{universidade}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> createExplicadorUniversidade(@RequestBody ExplicadorDTO explicadorDTO, @PathVariable("universidade") String nomeUniversidade){
        this.logger.info("Recebido um pedido POST em createExplicadorUniversidade()");

        Optional<ExplicadorDTO> criadoExplicador = this.explicadorService.criarExplicadorUniversidade(explicadorDTO, nomeUniversidade);

        if(criadoExplicador.isPresent()){
            return ResponseEntity.ok(criadoExplicador.get());
        }

        throw new FalhaCriarException("O explicador com o nome: " + explicadorDTO.getNome() + " ja existe!");
    }

    /**
     * Modifica um explicador em todas as faculdades que esteja presente.
     *
     * @param explicadorDTO dto com os dados do explicador
     * @return dto com o explicador modificado
     */
    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> putExplicadorTodas(@RequestBody ExplicadorDTO explicadorDTO){
        this.logger.info("Recebido um pedido PUT em putExplicadorTodas()");

        Optional<ExplicadorDTO> explicadorModificado = this.explicadorService.modificaExplicadorTodas(explicadorDTO);

        if(explicadorModificado.isPresent()){
            return ResponseEntity.ok(explicadorModificado.get());
        }

        throw new FalhaCriarException("Falha ao modificar as disponibilidades do explicador nas Universidades!");
    }

    /**
     * Modifica um explicador de uma faculdade.
     *
     * @param explicadorDTO dto com os dados do explicador
     * @param nomeUniversidade sigla da universidade
     * @return dto com o explicador modificado
     */
    @PutMapping(value = "/{universidade}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> putExplicadorUniversidade(@RequestBody ExplicadorDTO explicadorDTO, @PathVariable("universidade") String nomeUniversidade){
        this.logger.info("Recebido um pedido PUT em createExplicadorUniversidade()");

        Optional<ExplicadorDTO> explicadorModificado = this.explicadorService.modificaExplicadorUniversidade(explicadorDTO, nomeUniversidade);

        if(explicadorModificado.isPresent()){
            return ResponseEntity.ok(explicadorModificado.get());
        }

        throw new FalhaCriarException("Falha ao modificar as disponibilidades do explicador!");
    }

    /**
     * Associa uma cadeira a um explicador, de uma faculdade.
     *
     * @param explicadorDTO dto com os dados do explicador
     * @param nomeUniversidade sigla da universidade
     * @param nomeCadeira nome da cadeira
     * @return dto com o explicador modificado
     */
    @PutMapping(value = "/{universidade}/{cadeira}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExplicadorDTO> putExplicadorUniversidadeCadeira(@RequestBody ExplicadorDTO explicadorDTO, @PathVariable("universidade") String nomeUniversidade, @PathVariable("cadeira") String nomeCadeira){
        this.logger.info("Recebido um pedido PUT em putExplicadorUniversidadeCadeira()");

        Optional<ExplicadorDTO> explicadorModificado = this.explicadorService.modificaExplicadorUniversidadeCadeira(explicadorDTO, nomeUniversidade, nomeCadeira);

        if(explicadorModificado.isPresent()){
            return ResponseEntity.ok(explicadorModificado.get());
        }

        throw new FalhaCriarException("Falha ao associar a cadeira do explicador!!");
    }
}
