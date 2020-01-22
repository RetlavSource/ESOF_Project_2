package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Curso extends BaseModel{

  private String nome;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private Faculdade faculdade; // adicionado em "Faculdade"

  @ToString.Exclude
  @JsonIgnore
  private Set<Cadeira> cadeiras = new HashSet<>();

  @ToString.Exclude
  @JsonIgnore
  private Set<Aluno>  alunos = new HashSet<>();

  // ****** METHODS ******

  public Curso(String nome) {
    this.nome = nome;
  }

  public void addCadeira(Cadeira cadeira){
    this.cadeiras.add(cadeira);
    cadeira.setCurso(this);
  }

  public void removeCadeira(Cadeira cadeira) {
    this.cadeiras.remove(cadeira);
    cadeira.setCurso(null);
  }

  public void addAluno(Aluno aluno){
    this.alunos.add(aluno);
    aluno.setCurso(this);
  }

  public void removeAluno(Aluno aluno){
    this.alunos.remove(aluno);
    aluno.setCurso(null);
  }

  @ToString.Include
  @JsonProperty // mais genérico, mas pode ser utilizado também @JsonGetter
  public ArrayList<String> cadeiras() {
    ArrayList<String> nomeCadeiras = new ArrayList<>();

    for (Cadeira cadeira : this.cadeiras) {
      nomeCadeiras.add(cadeira.getNome());
    }

    return nomeCadeiras;
  }

}