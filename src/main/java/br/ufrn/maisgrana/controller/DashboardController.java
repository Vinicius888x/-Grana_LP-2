package br.ufrn.maisgrana.controller;

import br.ufrn.maisgrana.model.TipoTransacao;
import br.ufrn.maisgrana.model.Transacao;
import br.ufrn.maisgrana.service.TransacaoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class DashboardController {

    @FXML private ListView<Transacao> listaTransacoes;
    @FXML private Label labelBalanco; // Você precisará adicionar fx:id="labelBalanco" no FXML depois
    
    private TransacaoService service = new TransacaoService();

    @FXML
    public void initialize() {
        atualizarTela();
    }

    private void atualizarTela() {
        // Carrega dados do JSON
        listaTransacoes.setItems(FXCollections.observableArrayList(service.listar()));
        
        // Atualiza o texto do saldo
        double saldo = service.calcularBalanco();
        if(labelBalanco != null) {
            labelBalanco.setText("R$ " + String.format("%.2f", saldo));
        }
    }
    
    @FXML
    public void onAdicionarClick() {
        // Cria uma janelinha rápida para pedir dados (Dialog)
        Dialog<Transacao> dialog = new Dialog<>();
        dialog.setTitle("Nova Transação");
        dialog.setHeaderText("Adicione uma Receita ou Despesa");

        ButtonType botaoSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(botaoSalvar, ButtonType.CANCEL);

        // Campos do formulário
        TextField txtDescricao = new TextField();
        txtDescricao.setPromptText("Descrição (ex: Lanche)");
        TextField txtValor = new TextField();
        txtValor.setPromptText("Valor (ex: 10.50)");
        ComboBox<TipoTransacao> comboTipo = new ComboBox<>();
        comboTipo.getItems().setAll(TipoTransacao.values());
        comboTipo.setValue(TipoTransacao.DESPESA);

        VBox box = new VBox(10);
        box.getChildren().addAll(new Label("Descrição:"), txtDescricao, new Label("Valor:"), txtValor, new Label("Tipo:"), comboTipo);
        dialog.getDialogPane().setContent(box);

        // Converte o resultado quando clica em Salvar
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == botaoSalvar) {
                try {
                    String desc = txtDescricao.getText();
                    double val = Double.parseDouble(txtValor.getText().replace(",", "."));
                    return new Transacao(desc, val, comboTipo.getValue(), "Geral");
                } catch (NumberFormatException e) {
                    return null; // Ignora se valor for inválido
                }
            }
            return null;
        });

        Optional<Transacao> resultado = dialog.showAndWait();
        resultado.ifPresent(transacao -> {
            service.salvar(transacao); // Salva no JSON
            atualizarTela(); // Atualiza a lista e o saldo
        });
    }
}