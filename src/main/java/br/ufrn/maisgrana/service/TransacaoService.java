package br.ufrn.maisgrana.service;

import br.ufrn.maisgrana.model.Transacao;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class TransacaoService {
    // Simula onde o arquivo será salvo
    private static final String ARQUIVO_DADOS = "transacoes.json";
    private Gson gson = new Gson();

    public void salvar(Transacao t) {
        System.out.println("Salvando no JSON: " + t);
        // Implementação real será feita no próximo checkpoint
    }

    public List<Transacao> listar() {
        return new ArrayList<>();
    }
}