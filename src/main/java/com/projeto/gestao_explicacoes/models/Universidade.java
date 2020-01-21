package com.projeto.gestao_explicacoes.models;

import com.projeto.gestao_explicacoes.models.DTO.UniversidadeDTO;
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

    public Universidade(String nome, String sigla, String url) {
        this.nome = nome;
        this.sigla = sigla;
        this.url = url;
    }

    public UniversidadeDTO copyToUniversidadeDTO() {
        return new UniversidadeDTO(this.nome, this.sigla, this.latitude, this.longitude, this.endereco);
    }

}
