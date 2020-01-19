package com.projeto.gestao_explicacoes.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Universidade extends BaseModel {

    private String nome;
    private String sigla;
    private String url;
    private String latitude;
    private String longitude;
    private String endereco;

    public Universidade(String sigla, String url) {
        this.sigla = sigla;
        this.url = url;
    }

}
