package com.uefs.gerenciadorparabibliotecas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Classe principal que inicia a aplicação JavaFX para o gerenciador de bibliotecas.
 */
public class Main extends Application {

    ControllerPesquisaLivros sc;

    /**
     * Método principal que inicia a aplicação JavaFX.
     *
     * @param primaryStage O palco principal onde a cena será exibida.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega o arquivo FXML para a tela de login
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewLogin.fxml"));
            Parent root = fxmlLoader.load();

            // Configura a cena
            Scene scene = new Scene(root);

            // Carrega o ícone da aplicação
            File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
            Image icone = new Image(file.toURI().toString());
            primaryStage.getIcons().add(icone);

            // Define a cena no palco principal e exibe a aplicação
            primaryStage.setScene(scene);
            primaryStage.show();

            // Obtém o controlador da tela de login
            ControllerLogin controllerLogin = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal para iniciar a aplicação.
     *
     * @param args Os argumentos da linha de comando (não são usados neste exemplo).
     */
    public static void main(String[] args) {
        // Inicia a aplicação JavaFX
        launch(args);
    }
}
