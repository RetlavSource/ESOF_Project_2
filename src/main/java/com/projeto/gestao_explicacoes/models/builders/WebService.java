package com.projeto.gestao_explicacoes.models.builders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WebService {

    private static RestTemplate restTemplate = null;
    private static HttpHeaders httpHeaders = null;

    private WebService() { }

    /**
     * Recupera uma representação executando um GET no modelo URI.
     * A resposta é guardada e convertida numa {@link ResponseEntity}.
     *
     * @param url o URL
     * @param responseType o tipo de valor de retorno
     * @param <T> o tipo de objeto utilizado
     * @return o objeto convertido
     */
    public static <T> ResponseEntity<T> byGet(String url, Class<T> responseType) {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate.getForEntity(url, responseType);
    }

    /**
     * Cria um novo recurso, fazendo POST ao objeto fornecido na URL e devolve a representação encontrada na resposta.
     *
     * @param body o payload da entidade a enviar
     * @param url o URL
     * @param responseType o tipo de valor de retorno
     * @param <T> o tipo de objeto utilizado
     * @return o objeto convertido
     */
    public static <T> ResponseEntity<T> byPost(T body, String url, Class<T> responseType) {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }

        HttpEntity<T> payload = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForEntity(url, payload, responseType);

    }

    /**
     * Modifica um novo recurso, fazendo PATCH ao objeto fornecido na URL e devolve o objeto modificado na resposta.
     *
     * @param body o payload da entidade a enviar
     * @param url o  URL
     * @param responseType o tipo de valor de retorno
     * @param <T> o tipo de objeto utilizado
     * @return o objeto convertido
     */
    public static <T> T byPut(T body, String url, Class<T> responseType) {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
        }

        HttpEntity<T> payload = new HttpEntity<>(body, httpHeaders);

        return restTemplate.patchForObject(url, payload, responseType);

    }

    /*
    public static void main(String[] args) {
        ResponseEntity<String> googlePage=WebService.byGet("http://google.com",String.class);

        System.out.println(googlePage.getBody());
    }
    */

}
