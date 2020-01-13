package com.projeto.gestao_explicacoes.services.explicadorServices;

import com.projeto.gestao_explicacoes.exceptions.FalhaCriarException;
import com.projeto.gestao_explicacoes.models.Explicador;
import com.projeto.gestao_explicacoes.models.Universidade;
import com.projeto.gestao_explicacoes.models.builders.WebService;
import com.projeto.gestao_explicacoes.repositories.ExplicadorRepo;
import com.projeto.gestao_explicacoes.repositories.UniversidadeRepo;
import com.projeto.gestao_explicacoes.services.explicadorServices.filters.FilterObjectExplicador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Set<Explicador> findAll() {
        Set<Explicador> explicadores = new HashSet<>();
        for (Explicador explicador : this.explicadorRepo.findAll()) {
            explicadores.add(explicador);
        }
        return explicadores;
    }

    @Override
    public Optional<Explicador> criarExplicador(Explicador explicador) {

        if(this.explicadorRepo.findByNumero(explicador.getNumero()).isPresent()){

            return Optional.empty();
        }

        Explicador explicadorCriado = this.explicadorRepo.save(explicador);

        return Optional.of(explicadorCriado);
    }

    @Override
    public Set findExplicadorByUniversidade(String nomeUniversidade) {
        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a faculdade pedida!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador");
        String fullUrl = sb.toString();

        ResponseEntity<Set> auxSetExplicadores = WebService.byGet(fullUrl, Set.class);

        return auxSetExplicadores.getBody();
    }

    @Override
    public Set procuraExplicadorDisponivelByUniversidade(FilterObjectExplicador filterObjectExplicador, String nomeUniversidade) {
        Optional<Universidade> universidade = this.universidadeRepo.findBySigla(nomeUniversidade);
        if (universidade.isEmpty()) {
            throw new FalhaCriarException("Não existe a faculdade pedida!!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(universidade.get().getUrl()).append("/explicador/procura").append("?");
        sb.append("cadeira=").append(filterObjectExplicador.getNomeCadeira());
        sb.append("&");
        sb.append("idioma=").append(filterObjectExplicador.getNomeIdioma());
        sb.append("&");
        sb.append("dia=").append(filterObjectExplicador.getDiaSemana());
        sb.append("&");
        sb.append("inicio=").append(filterObjectExplicador.getHoraInicio());
        sb.append("&");
        sb.append("fim=").append(filterObjectExplicador.getHoraInicio());

        String fullUrl = sb.toString();
        ResponseEntity<Set> auxSetExplicadores = WebService.byGet(fullUrl, Set.class);


        return auxSetExplicadores.getBody();
    }

}
