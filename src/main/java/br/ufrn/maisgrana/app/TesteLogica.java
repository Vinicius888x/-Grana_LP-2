package br.ufrn.maisgrana.app;

import br.ufrn.maisgrana.service.TransacaoService;
import br.ufrn.maisgrana.model.Transacao;
import br.ufrn.maisgrana.model.TipoTransacao;

public class TesteLogica {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTE NO CODESPACES ---");
        
        // 1. Instancia o Serviço
        TransacaoService service = new TransacaoService();
        
        // 2. Cria uma Transação de Teste
        System.out.println("Tentando salvar uma transação...");
        Transacao t = new Transacao("Teste Codespace", 50.00, TipoTransacao.RECEITA, "Teste");
        
        // 3. Salva
        service.salvar(t);
        System.out.println("Sucesso! Transação enviada para o JSON.");
        
        // 4. Lê o Balanço para ver se gravou
        double saldo = service.calcularBalanco();
        System.out.println("Balanço Atual calculado pelo sistema: R$ " + saldo);
        
        System.out.println("--- FIM DO TESTE ---");
    }
}