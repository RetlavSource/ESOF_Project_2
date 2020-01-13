package com.projeto.gestao_explicacoes;

import com.projeto.gestao_explicacoes.models.*;
import com.projeto.gestao_explicacoes.models.builders.AtendimentoBuilder;
import com.projeto.gestao_explicacoes.models.builders.ExplicadorBuilder;
import com.projeto.gestao_explicacoes.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private FaculdadeRepo faculdadeRepo;
    private CursoRepo cursoRepo;
    private CadeiraRepo cadeiraRepo;
    private AlunoRepo alunoRepo;
    private ExplicadorRepo explicadorRepo;
    private IdiomaRepo idiomaRepo;
    private HorarioRepo horarioRepo;
    private AtendimentoRepo atendimentoRepo;

    @Autowired
    public Bootstrap(FaculdadeRepo faculdadeRepo, CursoRepo cursoRepo, CadeiraRepo cadeiraRepo, AlunoRepo alunoRepo, ExplicadorRepo explicadorRepo, IdiomaRepo idiomaRepo, HorarioRepo horarioRepo, AtendimentoRepo atendimentoRepo) {
        this.faculdadeRepo = faculdadeRepo;
        this.cursoRepo = cursoRepo;
        this.cadeiraRepo = cadeiraRepo;
        this.alunoRepo = alunoRepo;
        this.explicadorRepo = explicadorRepo;
        this.idiomaRepo = idiomaRepo;
        this.horarioRepo = horarioRepo;
        this.atendimentoRepo = atendimentoRepo;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Startup");

        //allTestes();
        //exemploEntradas();
        //testStringBuilder();

        //novosDados();     // server.port=8081
        //novosDadosWS11();   // server.port=8082
        //novosDadosWS12(); // server.port=8083
    }

    private void allTestes() {

        // Testes rápidos:


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

    private void novosDados(){

        Faculdade faculdade1 = new Faculdade("Ciencias e Tecnologia");
        Faculdade faculdade2 = new Faculdade("Ciencias Humanas e Sociais");
        Faculdade faculdade3 = new Faculdade("Ciencias da Saude");


        Curso engInformatica = new Curso("Engenharia Informatica");
        faculdade1.addCurso(engInformatica);

        Curso engCivil = new Curso("Engenharia Civil");
        faculdade1.addCurso(engCivil);

        Curso psicologia = new Curso("Psicologia");
        faculdade2.addCurso(psicologia);

        Curso fisioterapia = new Curso("Fisioterapia");
        faculdade3.addCurso(fisioterapia);


        Cadeira alg1 = new Cadeira("Algoritmos e estruturas de Dados I", "ALG1");
        engInformatica.addCadeira(alg1);

        Cadeira alg2 = new Cadeira("Algoritmos e estruturas de Dados II", "ALG2");
        engInformatica.addCadeira(alg2);

        Cadeira lp1 = new Cadeira("Linguagens de Programacao I", "LP1");
        engInformatica.addCadeira(lp1);

        Cadeira lp2 = new Cadeira("Linguagens de Programacao II", "LP2");
        engInformatica.addCadeira(lp2);

        Cadeira so = new Cadeira("Sistemas Operativos", "SO");
        engInformatica.addCadeira(so);

        Cadeira esof = new Cadeira("Engenharia de Software", "ESOF");
        engInformatica.addCadeira(esof);

        Cadeira fisica = new Cadeira("Fisica", "FIS");
        engCivil.addCadeira(fisica);

        Cadeira materiais = new Cadeira("Materiais de Construcao", "MAT");
        engCivil.addCadeira(materiais);

        Cadeira resistencia = new Cadeira("Resistencia de Materiais", "RES");
        engCivil.addCadeira(resistencia);

        Cadeira psocial = new Cadeira("Psicologia Social", "PSO");
        psicologia.addCadeira(psocial);

        Cadeira neuro = new Cadeira("Neuropsicologia", "NPS");
        psicologia.addCadeira(neuro);

        Cadeira juridica = new Cadeira("Psicologia Juridica", "PSJ");
        psicologia.addCadeira(juridica);

        Cadeira fisiologia = new Cadeira("Anatomofisiologia", "AFI");
        fisioterapia.addCadeira(fisiologia);

        Cadeira bioquimica = new Cadeira("Bioquimica Fisiologica", "BFI");
        fisioterapia.addCadeira(bioquimica);

        Cadeira motricidade = new Cadeira("Motricidade Humana", "MHU");
        fisioterapia.addCadeira(motricidade);

        Aluno valter = new Aluno("Valter Cardoso", 31062);
        engInformatica.addAluno(valter);

        Aluno gustavo = new Aluno("Gustavo Teixeira", 21736);
        engInformatica.addAluno(gustavo);

        Aluno manuel = new Aluno("Manuel Antonio", 13975);
        engCivil.addAluno(manuel);

        Aluno maria = new Aluno("Maria Aparecida", 33971);
        psicologia.addAluno(maria);

        Aluno jose = new Aluno("Jose Manuel", 25344);
        fisioterapia.addAluno(jose);

        Idioma portugues = new Idioma("Portugues", "PT");
        Idioma portuguesBr = new Idioma("Portugues-BR", "PT-BR");
        Idioma espanhol = new Idioma("Espanhol", "ES");
        Idioma inglesUk = new Idioma("Ingles-UK", "EN-UK");
        Idioma coreano = new Idioma("Coreano", "KOR");

        Explicador alessandro = new ExplicadorBuilder().setNome("Alessandro Moreira").setNumero(21064)
                .addCadeira(so)
                .addCadeira(esof)
                .addCadeira(lp2)
                .addIdioma(portuguesBr)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(11, 0),
                        LocalTime.of(12, 0)))
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(13, 0),
                        LocalTime.of(17, 0)))
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(9, 0),
                        LocalTime.of(12, 0)))
                .addHorario(new Horario(DayOfWeek.FRIDAY,
                        LocalTime.of(10, 0),
                        LocalTime.of(16, 0)))
                .build();



        Explicador feliz = new ExplicadorBuilder().setNome("Feliz Gouveia").setNumero(11211)
                .addCadeira(esof)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(17, 0),
                        LocalTime.of(20, 0)))
                .addHorario(new Horario(DayOfWeek.TUESDAY,
                        LocalTime.of(13, 0),
                        LocalTime.of(15, 0)))
                .build();

        Explicador jobs = new ExplicadorBuilder().setNome("Steve Jobs").setNumero(1333)
                .addCadeira(lp1)
                .addCadeira(lp2)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.THURSDAY,
                        LocalTime.of(8, 0),
                        LocalTime.of(11, 0)))
                .build();

        Explicador bill = new ExplicadorBuilder().setNome("Bill Gates").setNumero(10567)
                .addCadeira(alg1)
                .addCadeira(alg2)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.FRIDAY,
                        LocalTime.of(12, 0),
                        LocalTime.of(14, 0)))
                .build();

        Explicador edgar = new ExplicadorBuilder().setNome("Edgar Cardoso").setNumero(11767)
                .addCadeira(resistencia)
                .addCadeira(materiais)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addIdioma(espanhol).build();

        Explicador freud = new ExplicadorBuilder().setNome("Sigmund Freud").setNumero(456)
                .addCadeira(neuro)
                .addCadeira(psocial)
                .addIdioma(inglesUk).build();

        Explicador kim = new ExplicadorBuilder().setNome("Kim Jang").setNumero(12909)
                .addCadeira(motricidade)
                .addCadeira(fisiologia)
                .addIdioma(inglesUk)
                .addIdioma(coreano).build();

        Atendimento valAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2019-12-30T13:00"))
                .setAluno(valter)
                .setCadeira(esof)
                .setIdioma(portugues)
                .setExplicador(alessandro)
                .build();

        Atendimento gustAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2020-01-01T11:00"))
                .setAluno(gustavo)
                .setCadeira(so)
                .setIdioma(portuguesBr)
                .setExplicador(alessandro)
                .build();


        this.faculdadeRepo.save(faculdade1);
        this.faculdadeRepo.save(faculdade2);
        this.faculdadeRepo.save(faculdade3);

        System.out.println(this.faculdadeRepo.count() + " " + this.faculdadeRepo.findAll());
        System.out.println(this.cursoRepo.count() + " " + this.cursoRepo.findAll());
        System.out.println(this.cadeiraRepo.count() + " " + this.cadeiraRepo.findAll());
        System.out.println(this.alunoRepo.count() + " " + this.alunoRepo.findAll());
        System.out.println(this.explicadorRepo.count() + " " + this.explicadorRepo.findAll());
        System.out.println(this.idiomaRepo.count() + " " + this.idiomaRepo.findAll());
        System.out.println(this.horarioRepo.count() + " " + this.horarioRepo.findAll());
        System.out.println(this.atendimentoRepo.count() + " " + this.atendimentoRepo.findAll());

    }

    private void novosDadosWS11() {
        Faculdade faculdade1 = new Faculdade(" WS11 Ciencias e Tecnologia");
        Faculdade faculdade2 = new Faculdade("WS11 Ciencias Humanas e Sociais");
        Faculdade faculdade3 = new Faculdade("WS11 Ciencias da Saude");


        Curso engInformatica = new Curso("WS11 Engenharia Informatica");
        faculdade1.addCurso(engInformatica);

        Curso engCivil = new Curso("WS11 Engenharia Civil");
        faculdade1.addCurso(engCivil);

        Curso psicologia = new Curso("WS11 Psicologia");
        faculdade2.addCurso(psicologia);

        Curso fisioterapia = new Curso("WS11 Fisioterapia");
        faculdade3.addCurso(fisioterapia);


        Cadeira alg1 = new Cadeira("WS11 Algoritmos e estruturas de Dados I", "WS11-ALG1");
        engInformatica.addCadeira(alg1);

        Cadeira alg2 = new Cadeira("WS11 Algoritmos e estruturas de Dados II", "WS11-ALG2");
        engInformatica.addCadeira(alg2);

        Cadeira lp1 = new Cadeira("WS11 Linguagens de Programacao I", "WS11-LP1");
        engInformatica.addCadeira(lp1);

        Cadeira lp2 = new Cadeira("WS11 Linguagens de Programacao II", "WS11-LP2");
        engInformatica.addCadeira(lp2);

        Cadeira so = new Cadeira("WS11 Sistemas Operativos", "WS11-SO");
        engInformatica.addCadeira(so);

        Cadeira esof = new Cadeira("WS11 Engenharia de Software", "WS11-ESOF");
        engInformatica.addCadeira(esof);

        Cadeira fisica = new Cadeira("WS11 Fisica", "WS11-FIS");
        engCivil.addCadeira(fisica);

        Cadeira materiais = new Cadeira("WS11 Materiais de Construcao", "WS11-MAT");
        engCivil.addCadeira(materiais);

        Cadeira resistencia = new Cadeira("WS11 Resistencia de Materiais", "WS11-RES");
        engCivil.addCadeira(resistencia);

        Cadeira psocial = new Cadeira("WS11 Psicologia Social", "WS11-PSO");
        psicologia.addCadeira(psocial);

        Cadeira neuro = new Cadeira("WS11 Neuropsicologia", "WS11-NPS");
        psicologia.addCadeira(neuro);

        Cadeira juridica = new Cadeira("WS11 Psicologia Juridica", "WS11-PSJ");
        psicologia.addCadeira(juridica);

        Cadeira fisiologia = new Cadeira("WS11 Anatomofisiologia", "WS11-AFI");
        fisioterapia.addCadeira(fisiologia);

        Cadeira bioquimica = new Cadeira("WS11 Bioquimica Fisiologica", "WS11-BFI");
        fisioterapia.addCadeira(bioquimica);

        Cadeira motricidade = new Cadeira("WS11 Motricidade Humana", "WS11-MHU");
        fisioterapia.addCadeira(motricidade);

        Aluno valter = new Aluno("Valter WS11", 3106);
        engInformatica.addAluno(valter);

        Aluno gustavo = new Aluno("Gustavo WS11", 2173);
        engInformatica.addAluno(gustavo);

        Aluno manuel = new Aluno("Manuel WS11", 1397);
        engCivil.addAluno(manuel);

        Aluno maria = new Aluno("Maria WS11", 3397);
        psicologia.addAluno(maria);

        Aluno jose = new Aluno("Jose WS11", 2534);
        fisioterapia.addAluno(jose);

        Idioma portugues = new Idioma("Portugues", "PT");
        Idioma portuguesBr = new Idioma("Portugues-BR", "PT-BR");
        Idioma espanhol = new Idioma("Espanhol", "ES");
        Idioma inglesUk = new Idioma("Ingles-UK", "EN-UK");
        Idioma coreano = new Idioma("Coreano", "KOR");

        Explicador alessandro = new ExplicadorBuilder().setNome("Alessandro WS11").setNumero(2106)
                .addCadeira(so)
                .addCadeira(esof)
                .addCadeira(lp2)
                .addIdioma(portuguesBr)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.THURSDAY,
                        LocalTime.of(12, 0),
                        LocalTime.of(13, 0)))
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(17, 0),
                        LocalTime.of(19, 0)))
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(11, 0),
                        LocalTime.of(14, 0)))
                .addHorario(new Horario(DayOfWeek.FRIDAY,
                        LocalTime.of(12, 0),
                        LocalTime.of(13, 0)))
                .build();



        Explicador feliz = new ExplicadorBuilder().setNome("Feliz WS11").setNumero(1121)
                .addCadeira(esof)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(11, 0),
                        LocalTime.of(13, 0)))
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(17, 0),
                        LocalTime.of(19, 0)))
                .build();

        Explicador jobs = new ExplicadorBuilder().setNome("Steve WS11").setNumero(133)
                .addCadeira(lp1)
                .addCadeira(lp2)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(9, 0),
                        LocalTime.of(18, 0)))
                .build();

        Explicador bill = new ExplicadorBuilder().setNome("Bill WS11").setNumero(1056)
                .addCadeira(alg1)
                .addCadeira(alg2)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.TUESDAY,
                        LocalTime.of(9, 0),
                        LocalTime.of(13, 0)))
                .build();

        Explicador edgar = new ExplicadorBuilder().setNome("Edgar WS11").setNumero(1176)
                .addCadeira(resistencia)
                .addCadeira(materiais)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addIdioma(espanhol).build();

        Explicador freud = new ExplicadorBuilder().setNome("Sigmund WS11").setNumero(45)
                .addCadeira(neuro)
                .addCadeira(psocial)
                .addIdioma(inglesUk).build();

        Explicador kim = new ExplicadorBuilder().setNome("Kim WS11").setNumero(1290)
                .addCadeira(motricidade)
                .addCadeira(fisiologia)
                .addIdioma(inglesUk)
                .addIdioma(coreano).build();

        Atendimento valAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2020-01-16T12:00"))
                .setAluno(valter)
                .setCadeira(esof)
                .setIdioma(portugues)
                .setExplicador(alessandro)
                .build();

        Atendimento gustAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2020-01-20T13:00"))
                .setAluno(gustavo)
                .setCadeira(so)
                .setIdioma(portuguesBr)
                .setExplicador(alessandro)
                .build();


        this.faculdadeRepo.save(faculdade1);
        this.faculdadeRepo.save(faculdade2);
        this.faculdadeRepo.save(faculdade3);

        System.out.println(this.faculdadeRepo.count() + " " + this.faculdadeRepo.findAll());
        System.out.println(this.cursoRepo.count() + " " + this.cursoRepo.findAll());
        System.out.println(this.cadeiraRepo.count() + " " + this.cadeiraRepo.findAll());
        System.out.println(this.alunoRepo.count() + " " + this.alunoRepo.findAll());
        System.out.println(this.explicadorRepo.count() + " " + this.explicadorRepo.findAll());
        System.out.println(this.idiomaRepo.count() + " " + this.idiomaRepo.findAll());
        System.out.println(this.horarioRepo.count() + " " + this.horarioRepo.findAll());
        System.out.println(this.atendimentoRepo.count() + " " + this.atendimentoRepo.findAll());
    }

    public void novosDadosWS12() {
        Faculdade faculdade1 = new Faculdade(" WS12 Ciencias e Tecnologia");
        Faculdade faculdade2 = new Faculdade("WS12 Ciencias Humanas e Sociais");
        Faculdade faculdade3 = new Faculdade("WS12 Ciencias da Saude");


        Curso engInformatica = new Curso("WS12 Engenharia Informatica");
        faculdade1.addCurso(engInformatica);

        Curso engCivil = new Curso("WS12 Engenharia Civil");
        faculdade1.addCurso(engCivil);

        Curso psicologia = new Curso("WS12 Psicologia");
        faculdade2.addCurso(psicologia);

        Curso fisioterapia = new Curso("WS12 Fisioterapia");
        faculdade3.addCurso(fisioterapia);


        Cadeira alg1 = new Cadeira("WS12 Algoritmos e estruturas de Dados I", "WS12-ALG1");
        engInformatica.addCadeira(alg1);

        Cadeira alg2 = new Cadeira("WS12 Algoritmos e estruturas de Dados II", "WS12-ALG2");
        engInformatica.addCadeira(alg2);

        Cadeira lp1 = new Cadeira("WS12 Linguagens de Programacao I", "WS12-LP1");
        engInformatica.addCadeira(lp1);

        Cadeira lp2 = new Cadeira("WS12 Linguagens de Programacao II", "WS12-LP2");
        engInformatica.addCadeira(lp2);

        Cadeira so = new Cadeira("WS12 Sistemas Operativos", "WS12-SO");
        engInformatica.addCadeira(so);

        Cadeira esof = new Cadeira("WS12 Engenharia de Software", "WS12-ESOF");
        engInformatica.addCadeira(esof);

        Cadeira fisica = new Cadeira("WS12 Fisica", "WS12-FIS");
        engCivil.addCadeira(fisica);

        Cadeira materiais = new Cadeira("WS12 Materiais de Construcao", "WS12-MAT");
        engCivil.addCadeira(materiais);

        Cadeira resistencia = new Cadeira("WS12 Resistencia de Materiais", "WS12-RES");
        engCivil.addCadeira(resistencia);

        Cadeira psocial = new Cadeira("WS12 Psicologia Social", "WS12-PSO");
        psicologia.addCadeira(psocial);

        Cadeira neuro = new Cadeira("WS12 Neuropsicologia", "WS12-NPS");
        psicologia.addCadeira(neuro);

        Cadeira juridica = new Cadeira("WS12 Psicologia Juridica", "WS12-PSJ");
        psicologia.addCadeira(juridica);

        Cadeira fisiologia = new Cadeira("WS12 Anatomofisiologia", "WS12-AFI");
        fisioterapia.addCadeira(fisiologia);

        Cadeira bioquimica = new Cadeira("WS12 Bioquimica Fisiologica", "WS12-BFI");
        fisioterapia.addCadeira(bioquimica);

        Cadeira motricidade = new Cadeira("WS12 Motricidade Humana", "WS12-MHU");
        fisioterapia.addCadeira(motricidade);

        Aluno valter = new Aluno("Valter WS12", 3106);
        engInformatica.addAluno(valter);

        Aluno gustavo = new Aluno("Gustavo WS12", 2173);
        engInformatica.addAluno(gustavo);

        Aluno manuel = new Aluno("Manuel WS12", 1397);
        engCivil.addAluno(manuel);

        Aluno maria = new Aluno("Maria WS12", 3397);
        psicologia.addAluno(maria);

        Aluno jose = new Aluno("Jose WS12", 2534);
        fisioterapia.addAluno(jose);

        Idioma portugues = new Idioma("Portugues", "PT");
        Idioma portuguesBr = new Idioma("Portugues-BR", "PT-BR");
        Idioma espanhol = new Idioma("Espanhol", "ES");
        Idioma inglesUk = new Idioma("Ingles-UK", "EN-UK");
        Idioma coreano = new Idioma("Coreano", "KOR");

        Explicador alessandro = new ExplicadorBuilder().setNome("Alessandro WS12").setNumero(2106)
                .addCadeira(so)
                .addCadeira(esof)
                .addCadeira(lp2)
                .addIdioma(portuguesBr)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.FRIDAY,
                        LocalTime.of(12, 0),
                        LocalTime.of(13, 0)))
                .addHorario(new Horario(DayOfWeek.THURSDAY,
                        LocalTime.of(17, 0),
                        LocalTime.of(19, 0)))
                .addHorario(new Horario(DayOfWeek.MONDAY,
                        LocalTime.of(11, 0),
                        LocalTime.of(14, 0)))
                .addHorario(new Horario(DayOfWeek.WEDNESDAY,
                        LocalTime.of(12, 0),
                        LocalTime.of(13, 0)))
                .build();



        Explicador feliz = new ExplicadorBuilder().setNome("Feliz WS12").setNumero(1121)
                .addCadeira(esof)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.THURSDAY,
                        LocalTime.of(11, 0),
                        LocalTime.of(13, 0)))
                .addHorario(new Horario(DayOfWeek.THURSDAY,
                        LocalTime.of(17, 0),
                        LocalTime.of(19, 0)))
                .build();

        Explicador jobs = new ExplicadorBuilder().setNome("Steve WS12").setNumero(133)
                .addCadeira(lp1)
                .addCadeira(lp2)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.TUESDAY,
                        LocalTime.of(9, 0),
                        LocalTime.of(18, 0)))
                .build();

        Explicador bill = new ExplicadorBuilder().setNome("Bill WS12").setNumero(1056)
                .addCadeira(alg1)
                .addCadeira(alg2)
                .addIdioma(inglesUk)
                .addHorario(new Horario(DayOfWeek.FRIDAY,
                        LocalTime.of(10, 0),
                        LocalTime.of(17, 0)))
                .build();

        Explicador edgar = new ExplicadorBuilder().setNome("Edgar WS12").setNumero(1176)
                .addCadeira(resistencia)
                .addCadeira(materiais)
                .addIdioma(portugues)
                .addIdioma(inglesUk)
                .addIdioma(espanhol).build();

        Explicador freud = new ExplicadorBuilder().setNome("Sigmund WS12").setNumero(45)
                .addCadeira(neuro)
                .addCadeira(psocial)
                .addIdioma(inglesUk).build();

        Explicador kim = new ExplicadorBuilder().setNome("Kim WS12").setNumero(1290)
                .addCadeira(motricidade)
                .addCadeira(fisiologia)
                .addIdioma(inglesUk)
                .addIdioma(coreano).build();

        Atendimento valAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2020-01-23T17:00"))
                .setAluno(valter)
                .setCadeira(esof)
                .setIdioma(portugues)
                .setExplicador(alessandro)
                .build();

        Atendimento gustAtendimento = new AtendimentoBuilder()
                .setData(LocalDateTime.parse("2020-01-27T11:00"))
                .setAluno(gustavo)
                .setCadeira(so)
                .setIdioma(portuguesBr)
                .setExplicador(alessandro)
                .build();


        this.faculdadeRepo.save(faculdade1);
        this.faculdadeRepo.save(faculdade2);
        this.faculdadeRepo.save(faculdade3);

        System.out.println(this.faculdadeRepo.count() + " " + this.faculdadeRepo.findAll());
        System.out.println(this.cursoRepo.count() + " " + this.cursoRepo.findAll());
        System.out.println(this.cadeiraRepo.count() + " " + this.cadeiraRepo.findAll());
        System.out.println(this.alunoRepo.count() + " " + this.alunoRepo.findAll());
        System.out.println(this.explicadorRepo.count() + " " + this.explicadorRepo.findAll());
        System.out.println(this.idiomaRepo.count() + " " + this.idiomaRepo.findAll());
        System.out.println(this.horarioRepo.count() + " " + this.horarioRepo.findAll());
        System.out.println(this.atendimentoRepo.count() + " " + this.atendimentoRepo.findAll());
    }

}
