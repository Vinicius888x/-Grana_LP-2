package br.ufrn.maisgrana.controller;

import br.ufrn.maisgrana.model.Transacao;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class DashboardController {

    @FXML
    private ListView<Transacao> listaTransacoes;

    public void initialize() {
        // Este método roda assim que a tela abre.
        // Aqui vamos carregar os dados do JSON futuramente.
        System.out.println("Tela de Dashboard iniciada!");
    }
    
    @FXML
    public void onAdicionarClick() {
        System.out.println("Botão adicionar clicado");
        // Lógica para abrir a janela de cadastro
    }
}