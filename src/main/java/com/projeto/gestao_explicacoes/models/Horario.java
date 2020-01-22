package com.projeto.gestao_explicacoes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario extends BaseModel{

  private DayOfWeek diaSemana;
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime horaInicio;
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime horaFim;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private Explicador explicador; // adicionado em "Explicador"

  // ****** METHODS ******

  public Horario(DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFim) {
    this.diaSemana = diaSemana;
    this.horaInicio = horaInicio;
    this.horaFim = horaFim;
  }

}