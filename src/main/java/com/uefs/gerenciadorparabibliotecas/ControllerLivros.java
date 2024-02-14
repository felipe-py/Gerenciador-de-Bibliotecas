package com.uefs.gerenciadorparabibliotecas;

import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

public class ControllerLivros implements Initializable {


    @FXML
    private ListView lv;
    @FXML
    private TextField tf;
    @FXML
    private Button btnbuscar;
    @FXML
    private Button btnnovo;
    @FXML
    private Button btneditar;
    @FXML
    private Button btnreservar;
    @FXML
    private Button btnemprestimo;
    @FXML
    private Label labellivro;
    @FXML
    String livroEscolhido;

    public void initialize (URL url, ResourceBundle rb) {
        lv.getItems().addAll(palavras);
        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                livroEscolhido = (String) lv.getSelectionModel().getSelectedItem();
                labellivro.setText(livroEscolhido);

            }
        })
        ;
    }

    ArrayList<String> palavras = new ArrayList<>(
            //Substituir pelos livros no registro!
            Arrays.asList("livro 1", "livro 2","livro 3")
    );

    @FXML
    void clicarEmBuscar(MouseEvent event) {
        lv.getItems().clear();
        lv.getItems().addAll(buscar(tf.getText(),palavras));
    }

    private List<String> buscar (String palavrasChave, List<String> listaDePalavras) {

        List<String> arrayDePalavras = Arrays.asList(palavrasChave.trim().split(" "));

        return listaDePalavras.stream().filter(input -> {
            return arrayDePalavras.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    public void clicarEmEditar (MouseEvent event) throws IOException {
        System.out.println("Editar um livro");
        //Abrir uma janela para editar um registro, e um botão para remover o dito livro.
    }

    public void clicarEmReservar (MouseEvent event) throws IOException {
        System.out.println("Reservar um livro");
    }

    public void clicarEmEmprestar (MouseEvent event) throws IOException {
        System.out.println("Emprestar um livro");
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
    /*
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
    }*/

    private void loadPage (String panel) {
        /*
        Parent raiz = null;

        try {
            raiz = FXMLLoader.load(getClass().getResource(panel + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(raiz);*/



    }

}