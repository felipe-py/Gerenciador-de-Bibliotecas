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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLivros implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnregistrar;
    @FXML
    private Button btnreservar;
    @FXML
    private Button btnbuscar;
    @FXML
    private Button btnremover;
    @FXML
    private Button btneditar;
    @FXML
    private Button btnemprestar;

    public void initialize (URL url, ResourceBundle rb) {
        //
    }

    @FXML
    public void clicarEmRegistrar (MouseEvent event) throws IOException {
        System.out.println("Registrar um Livro");

        Stage stageRegistro = new Stage();
        FXMLLoader pagina = new FXMLLoader();

        Pane painelRegistro = pagina.load(getClass().getResource("RegistroLivros-view.fxml").openStream());
        stageRegistro.setScene(new Scene (painelRegistro, 600, 400));
        stageRegistro.show();
    }

    @FXML
    public void clicarEmEmprestar (MouseEvent event) throws IOException {
        System.out.println("Emprestar um Livro");
    }

    @FXML
    public void clicarEmReservar (MouseEvent event) throws IOException {
        System.out.println("Reservar um Livro");
    }

    @FXML
    public void clicarEmBuscar (MouseEvent event) throws IOException {
        System.out.println("Buscar um Livro");
    }

    @FXML
    public void clicarEmRemover (MouseEvent event) throws IOException {
        System.out.println("Remover um livro");
    }

    @FXML
    public void clicarEmEditar (MouseEvent event) throws IOException {
        System.out.println("Editar um livro");
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