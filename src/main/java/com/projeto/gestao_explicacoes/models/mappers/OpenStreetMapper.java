package com.projeto.gestao_explicacoes.models.mappers;

import com.projeto.gestao_explicacoes.models.Universidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    /**
     * Completa os campos do objeto Universidade, recebidos por pesquisa web.
     *
     * @param universidade previamente criada.
     */
    public void completaUniversidade(@NonNull Universidade universidade) {
        universidade.setLatitude(this.lat);
        universidade.setLongitude(this.lon);
        universidade.setEndereco(this.display_name);
    }

}
