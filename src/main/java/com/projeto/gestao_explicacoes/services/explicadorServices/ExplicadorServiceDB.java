package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Universidade;
import com.projeto.gestao_explicacoes.models.builders.WebService;
import com.projeto.gestao_explicacoes.repositories.ExplicadorRepo;
import com.projeto.gestao_explicacoes.repositories.UniversidadeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Profile(value = "db")
public class ExplicadorServiceDB implements ExplicadorService {

    private ExplicadorRepo explicadorRepo;
    private UniversidadeRepo universidadeRepo;

    @Autowired
    public ExplicadorServiceDB(ExplicadorRepo explicadorRepo, UniversidadeRepo universidadeRepo) {
        this.explicadorRepo = explicadorRepo;
        this.universidadeRepo = universidadeRepo;
    }

    @Override
    public Optional<Explicador> criarExplicador(Explicador explicador) {

        if(this.explicadorRepo.findByNumero(explicador.getNumero()).isPresent()){

            return Optional.empty();
        }

        Explicador explicadorCriado = this.explicadorRepo.save(explicador);

        return Optional.of(explicadorCriado);
    }

    /**
     * Procura um explicador, numa faculdade, por diversos parâmetros, utilizando filtros.
     *
     * @param parametros a pesquisar
     * @param nomeUniversidade nome da faculdade
     * @return explicadores encontrados
     */
    @Override
    public String procuraExplicadoresUniversidade(Map<String, String> parametros, String nomeUniversidade) {

        System.out.println(parametros);
        System.out.println(nomeUniversidade);

        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a faculdade pedida!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador").append("?");
        sb.append("cadeira=").append(parametros.get("cadeira"));
        sb.append("&");
        sb.append("idioma=").append(parametros.get("idioma"));
        sb.append("&");
        sb.append("dia=").append(parametros.get("dia"));
        sb.append("&");
        sb.append("inicio=").append(parametros.get("inicio"));
        sb.append("&");
        sb.append("fim=").append(parametros.get("fim"));

        String fullUrl = sb.toString();

        System.out.println(fullUrl);

        ResponseEntity<String> auxSetExplicadores = WebService.byGet(fullUrl, String.class);

        return auxSetExplicadores.getBody();
    }

}
