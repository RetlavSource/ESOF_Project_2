package com.projeto.gestao_explicacoes.models.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenStreetMapper {

    private Long place_id;
    private Long osm_id;
    private String lat;
    private String lon;
    private String display_name;
    private String type;

}
