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

public class ControllerEmprestimos implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnemprestar;
    @FXML
    private Button btndevolver;
    @FXML
    private Button btnrenovar;
    public void initialize (URL url, ResourceBundle rb) {
        //
    }

    @FXML
    public void clicarEmEmprestar (MouseEvent event) throws IOException {
        System.out.println("Emprestar um Livro");
    }

    @FXML
    public void clicarEmDevolver (MouseEvent event) throws IOException {
        System.out.println("Devolver um Livro");
    }

    @FXML
    public void clicarEmRenovar (MouseEvent event) throws IOException {
        System.out.println("Renovar um emprestimo");
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