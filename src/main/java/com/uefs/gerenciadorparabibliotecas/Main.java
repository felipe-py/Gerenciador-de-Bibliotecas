package com.uefs.gerenciadorparabibliotecas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();

        //Usei o file para poder 'passar o caminho' a partir do src, por imagem só pegava o caminho absoluto
        File file = new File("src/icone.png");
        Image icone = new Image(file.toURI().toString());
        stage.getIcons().add(icone);

        Parent raiz = FXMLLoader.load(getClass().getResource("painel.fxml"));
        Scene scene = new Scene(raiz);


        stage.setTitle("Gerenciador");
        stage.setScene(scene);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}