package com.projeto.gestao_explicacoes.controllers;

import com.projeto.gestao_explicacoes.services.universidadeServices.UniversidadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/universidade")
public class UniversidadeController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private UniversidadeService universidadeService;

    @Autowired
    public UniversidadeController(UniversidadeService universidadeService) {
        this.universidadeService = universidadeService;
    }

}
