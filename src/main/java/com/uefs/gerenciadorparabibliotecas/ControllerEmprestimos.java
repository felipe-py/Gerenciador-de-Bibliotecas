package com.uefs.gerenciadorparabibliotecas;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;


public class ControllerEmprestimos implements Initializable {


    ArrayList<String> palavras = new ArrayList<>(
            //Substituir pelos empréstimos no registro!
            Arrays.asList("empréstimo 1", "empréstimo 2","empréstimo 3")
    );


    //@FXML
    //private ListView<String> listaview;

    @FXML
    void clicarEmBusca (MouseEvent event) {
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

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    //@FXML
    //private Button btnemprestar;
    @FXML
    private ListView lv;
    @FXML
    private TextField tf;
    @FXML
    private Button btndevolver;
    @FXML
    private Button btnrenovar;
    @FXML
    private Button btnbuscar;
    @FXML
    private Label labelemprestimo;
    @FXML
    String emprestimoEscolhido;



    public void initialize (URL url, ResourceBundle rb) {
        lv.getItems().addAll(palavras);
        lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

                emprestimoEscolhido = (String) lv.getSelectionModel().getSelectedItem();
                labelemprestimo.setText(emprestimoEscolhido);

            }
        })
        ;
    }

    /*@FXML
    public void clicarEmEmprestar (MouseEvent event) throws IOException {
        System.out.println("Emprestar um Livro");
    }*/

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