[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/RetlavSource/ESOF_Project_2?color=green&include_prereleases&label=ultima%20vers%C3%A3o&style=plastic)](https://github.com/RetlavSource/ESOF_Project_2/releases)

[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/RetlavSource/ESOF_Projecto?color=green&include_prereleases&label=%C3%BAltima%20vers%C3%A3o%20do%20ESOF_Poject&style=plastic)](https://github.com/RetlavSource/ESOF_Project/releases)

# Web Service para o Projeto Prático de Engenharia de Software

Este é o completento do projeto prático [*`ESOF_Project`*](https://github.com/RetlavSource/ESOF_Project) para a disciplina de  ***Engenharia de Software*** na ***Universidade Fernando Pessoa***, representando um Web Service (Faculdades) que comunica com *`ESOF_Project`*.

## Endpoints utilizados
*   **GET**
    *   /explicador/{universidade} -- *`lista todos os explicadores da {universidade}`*
    *   /explicador/{universidade}/{nome} -- *`lista o explicador com o {nome} da {universidade}`*
    *   /universidade -- *`lista uma universidade`*

*   **POST**
    *   /explicador/{universidade} -- *`cria um explicador na {universidade} (info do explicador no payload)`*
    *   /universidade -- *`cria uma universidade`*
    *   /atendimento/{universidade} -- *`cria um atendimento na {universidade} (info do atendimento no payload)`*

*   **PUT**
    *   /explicador -- *`define a disponibilidade do explicador (itera pelas faculdades à procura)(info do explicador no payload)`*
    *   /explicador/{universidade}/{cadeira} -- *`atribui uma cadeira a um explicador da {universidade} (info do explicador no payload)`*
    *   /explicador/{universidade} -- *`modifica o explicador da {universidade} (info do explicador no payload)`*
    
*   **DELETE**
    *   /explicador/{universidade} -- *`remove o explicador da {universidade} (info do explicador no payload)`*

## Documentação de referência
Documentação utilizada na realização do projeto:
*   [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
*   [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
*   [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
*   [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
*   [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#using-boot-devtools)
*   [Thymeleaf](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

## Guias de utilização
Guias de utilização de algumas ferramentas utilizadas:

*   [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
*   [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
*   [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
*   [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
*   [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
*   [TODOS os guias](https://spring.io/guides/)