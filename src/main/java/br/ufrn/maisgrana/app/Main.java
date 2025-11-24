package br.ufrn.maisgrana.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carrega o arquivo visual FXML
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/br/ufrn/maisgrana/view/dashboard.fxml"));
        
        Scene scene = new Scene(fxmlLoader.load(), 350, 600);
        stage.setTitle("+Grana");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}