package com.projeto.gestao_explicacoes.models.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UniversidadeDTO {

    /**
     * Representa o regex a usar na divisão da morada.
     * Alterar, caso o regex seja diferente.
     */
    @JsonIgnore
    private final String regex = ", ";

    private String nome;
    private String sigla;
    private String url;
    private String latitude;
    private String longitude;
    private String endereco;
    private ArrayList<String> direcao = new ArrayList<>();

    public UniversidadeDTO(String nome, String sigla, String latitude, String longitude, String endereco) {
        this();
        this.nome = nome;
        this.sigla = sigla;
        this.latitude = latitude;
        this.longitude = longitude;
        if (endereco != null) {
            String[] token = endereco.split(this.regex);
            for (String string : token) {
                this.direcao.add(string);
            }
        }
    }
}
