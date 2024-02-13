package com.uefs.gerenciadorparabibliotecas;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ControllerUsuarios implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnregistrar;
    @FXML
    private Button btnbloquear;

    public void initialize (URL url, ResourceBundle rb) {
        //
    }

    @FXML
    public void clicarEmRegistrar (MouseEvent event) throws IOException {
        System.out.println("Registrar um novo usuario");
        //loadPage("painelLivros");
        //File arq = new File("src/painelLivros.fxml");
        //AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));

        //bp.setCenter(view);
    }

    @FXML
    public void clicarEmBloquear (MouseEvent event) throws IOException {
        System.out.println("Bloquear um usuario");
        //loadPage("painelEmprestimos");
        //loadPage("painelEmprestimos");
        //File arq = new File("src/painelEmprestimos.fxml");
        //AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));


        //bp.setCenter(view);
    }

    private void loadPage (String panel) {
        Parent raiz = null;

        try {
            raiz = FXMLLoader.load(getClass().getResource(panel + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(raiz);



    }

}