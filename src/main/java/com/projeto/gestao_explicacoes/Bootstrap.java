package com.projeto.gestao_explicacoes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.gestao_explicacoes.models.*;
import com.projeto.gestao_explicacoes.models.builders.AtendimentoBuilder;
import com.projeto.gestao_explicacoes.models.builders.ExplicadorBuilder;
import com.projeto.gestao_explicacoes.models.builders.WebService;
import com.projeto.gestao_explicacoes.models.mappers.OpenStreetMapper;
import com.projeto.gestao_explicacoes.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UniversidadeRepo universidadeRepo;

    @Autowired
    public Bootstrap(UniversidadeRepo universidadeRepo) {
        this.universidadeRepo = universidadeRepo;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

//        try {
//            allTestes();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        //exemploEntradas();
        //testStringBuilder();

        dadosWS2();
    }

    private void allTestes() throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        //ResponseEntity<List> openMaps = restTemplate.getForEntity("https://nominatim.openstreetmap.org/search.php?q=universidade+fernando+pessoa&format=json", List.class);
        ResponseEntity<List> openMaps = WebService.byGet("https://nominatim.openstreetmap.org/search.php?q=universidade+fernando+pessoa&format=json", List.class);

        System.out.println(openMaps);
        System.out.println(openMaps.getBody());
        System.out.println("\n");



        //ResponseEntity<String> openMaps2 = restTemplate.getForEntity("https://nominatim.openstreetmap.org/search.php?q=universidade+fernando+pessoa&format=json", String.class);
        ResponseEntity<String> openMaps2 = WebService.byGet("https://nominatim.openstreetmap.org/search.php?q=universidade+fernando+pessoa&format=json", String.class);

        System.out.println(openMaps2);
        System.out.println(openMaps2.getBody());
        System.out.println("\n");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<OpenStreetMapper> openObjects = objectMapper.readValue(openMaps2.getBody() , new TypeReference<List<OpenStreetMapper>>() {});
        for (OpenStreetMapper openStreetMapper : openObjects) {
            if (openStreetMapper.getType().equals("university")) {
                System.out.println("Got the right one ->");
                System.out .println(openStreetMapper);
                break;
            }
        }

    }

    private void exemploEntradas(){
        // Dados para introdução de Datas

        LocalDateTime dateAndTime = null;
        dateAndTime = LocalDateTime.parse("2018-11-11 23:23", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println(dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println(dateAndTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        System.out.println(LocalDateTime.parse("2019-12-21T12:12").format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.of(2012, Month.SEPTEMBER, 21));
        System.out.println(LocalTime.now());
        System.out.println(LocalTime.of(20, 36, 56));
        System.out.println(LocalDateTime.now());
        System.out.println(DayOfWeek.MONDAY);

        // Para introdução de Idiomas (Idioma em UpperCase)
        String idioma = "português";
        System.out.println(idioma.toUpperCase());
    }

    private void testStringBuilder() {

        Map<String,String> mapping=new HashMap<>();
        mapping.put("UFP","http://localhost:8080");
        mapping.put("UP","http://localhost:8081");



        String uni="UFP";
        StringBuilder sb=new StringBuilder();

        sb.append(mapping.get(uni)).append("/explicador");

        System.out.println(sb.toString());

    }

    private void dadosWS2() {

        Universidade ufp = new Universidade("Universidade Fernando Pessoa", "ufp", "http://127.0.0.1:8081");
        this.webAccessCompletaUniversidade(ufp, "university");

        Universidade ws11 = new Universidade("Faculdade de Arquitetura da Universidade do Porto", "ws11", "http://127.0.0.1:8082");
        this.webAccessCompletaUniversidade(ws11, "university");

        Universidade ws12 = new Universidade("Faculdade de Engenharia da Universidade do Porto", "ws12", "http://127.0.0.1:8083");
        this.webAccessCompletaUniversidade(ws12, "university");

        this.universidadeRepo.save(ufp);
        this.universidadeRepo.save(ws11);
        this.universidadeRepo.save(ws12);

    }

    private void webAccessCompletaUniversidade(Universidade universidade, String tipo) {

        String openMapsURL = "https://nominatim.openstreetmap.org/search.php?q=";
        String stringFormat = "&format=json";

        StringBuilder sb = new StringBuilder();
        sb.append(openMapsURL).append(universidade.getNome()).append(stringFormat);
        ResponseEntity<String> universidadeMap = WebService.byGet(sb.toString(), String.class);
        if (universidadeMap.getBody() == null) {
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
        } catch (JsonProcessingException e) {
            this.logger.info("Resultado dos dados sobre a faculdade sem sucesso!!");
            e.printStackTrace();
        }

    }

}
