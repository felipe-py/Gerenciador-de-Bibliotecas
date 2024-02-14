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

public class Controller implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnlivros;
    @FXML
    private Button btnemprestimos;
    @FXML
    private Button btnusuarios;
    @FXML
    private Button btnestatisticas;

    public void initialize (URL url, ResourceBundle rb) {
        //
    }

    @FXML
    public void clicarEmLivros (MouseEvent event) throws IOException {
        //System.out.println("livros");
        loadPage("painelLivros");
        File arq = new File("src/painelLivros.fxml");
        AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));

        bp.setCenter(view);
    }

    @FXML
    public void clicarEmEmprestimos (MouseEvent event) throws IOException {
        //System.out.println("emprestimos");
        //loadPage("painelEmprestimos");
        loadPage("painelEmprestimos");
        File arq = new File("src/painelEmprestimos.fxml");
        AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));


        bp.setCenter(view);
    }

    @FXML
    public void clicarEmUsuarios (MouseEvent event) throws IOException {
        //System.out.println("usuarios");
        //loadPage("painelUsuarios");
        loadPage("painelUsuarios");
        File arq = new File("src/painelUsuarios.fxml");
        AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));


        bp.setCenter(view);
    }

    @FXML
    public void clicarEmEstatisticas (MouseEvent event) throws IOException {
        //System.out.println("estatisticas");
        //loadPage("painelEstatisticas");
        loadPage("painelEstatisticas");
        File arq = new File("src/painelEstatisticas.fxml");
        AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));

        bp.setCenter(view);
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