package com.uefs.gerenciadorparabibliotecas;

import java.net.URL;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

/**
 * Controlador responsável pela funcionalidade relacionada aos livros.
 * Permite buscar, editar, reservar, emprestar e registrar livros.
 */

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


    /**
     * Executa a ação de busca quando o botão de busca é clicado.
     * Limpa a ListView e adiciona os resultados da busca à ListView.
     * Os resultados são obtidos chamando a função de busca e passando o texto digitado no TextField.
     *
     * @param event O evento de clique do mouse.
     * @throws IOException se ocorrer um erro de entrada e saída.
     */

    @FXML
    void clicarEmBuscar(MouseEvent event) throws IOException {
        lv.getItems().clear();
        lv.getItems().addAll(buscar(tf.getText(),palavras));
    }

    /**
     * Realiza uma busca na lista de palavras com base nas palavras-chave fornecidas.
     * Abre uma nova janela ao invés de realizar a busca diretamente.
     *
     * @param palavrasChave   As palavras-chave a serem usadas na busca.
     * @param listaDePalavras A lista de palavras na qual realizar a busca.
     * @return Uma lista de resultados da busca.
     * @throws IOException se ocorrer um erro de entrada e saída ao abrir a nova janela.
     */

    private List<String> buscar (String palavrasChave, List<String> listaDePalavras) throws IOException {
        /*
        List<String> arrayDePalavras = Arrays.asList(palavrasChave.trim().split(" "));

        return listaDePalavras.stream().filter(input -> {
            return arrayDePalavras.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
         */
        Stage stageRegistro = new Stage();
        FXMLLoader pagina = new FXMLLoader();

        Pane painelLogin = pagina.load(getClass().getResource("viewPesquisaLivros.fxml").openStream());
        stageRegistro.setScene(new Scene (painelLogin, 600, 400));
        stageRegistro.show();
        return listaDePalavras;
    }

    public void clicarEmEditar (MouseEvent event) throws IOException {
        //System.out.println("Editar um livro");
        //Abrir uma janela para editar um registro, e um botão para remover o dito livro.
    }

    public void clicarEmReservar (MouseEvent event) throws IOException {
        //System.out.println("Reservar um livro");
    }

    public void clicarEmEmprestar (MouseEvent event) throws IOException {
        //System.out.println("Emprestar um livro");
    }

    @FXML
    public void clicarEmRegistrar (MouseEvent event) throws IOException {
        //System.out.println("Registrar um Livro");

        Stage stageRegistro = new Stage();
        FXMLLoader pagina = new FXMLLoader();

        Pane painelRegistro = pagina.load(getClass().getResource("viewRegistroLivros.fxml").openStream());
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

    /*
    private void loadPage (String panel) {
        Parent raiz = null;

        try {
            raiz = FXMLLoader.load(getClass().getResource(panel + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(raiz);

    }

     */

}