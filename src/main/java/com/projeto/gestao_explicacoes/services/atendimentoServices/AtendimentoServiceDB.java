package com.projeto.gestao_explicacoes.services.atendimentoServices;

import com.projeto.gestao_explicacoes.models.Atendimento;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile(value = "db")
public class AtendimentoServiceDB implements AtendimentoService {

    @Override
    public Set<Atendimento> findAll() {
        return null;
    }
}
