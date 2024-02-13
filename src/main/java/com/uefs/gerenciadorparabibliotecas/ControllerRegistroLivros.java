package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;
import com.uefs.gerenciadorparabibliotecas.model.LocalizacaoLivro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerRegistroLivros {

    @FXML
    private Button buttonRegistrarLivro;

    @FXML
    private TextField textFieldISBN;

    @FXML
    private TextField textFieldanodepublicacao;

    @FXML
    private TextField textFieldautor;

    @FXML
    private TextField textFieldcategoria;

    @FXML
    private TextField textFieldeditora;

    @FXML
    private TextField textFieldlocalizacao;

    @FXML
    private TextField textFieldtitulo;

    @FXML
    private Label labelErrorRegistroLivros;

    void limparPaginaRegistroLivros(){
        textFieldanodepublicacao.clear();
        textFieldautor.clear();
        textFieldcategoria.clear();
        textFieldeditora.clear();
        textFieldtitulo.clear();
        textFieldISBN.clear();
        textFieldlocalizacao.clear();
        labelErrorRegistroLivros.setText("");
    }

    @FXML
    void onButtonRegistrarLivroClicked(ActionEvent event) throws LivroException {
        if (textFieldanodepublicacao.getText().equals("") || textFieldautor.getText().equals("") || textFieldcategoria.getText().equals("")
                || textFieldeditora.getText().equals("") || textFieldtitulo.getText().equals("") || textFieldISBN.getText().equals("")
                || textFieldlocalizacao.getText().equals("")){
            labelErrorRegistroLivros.setText("Prencha todos os campos");
        } else {
            Livro teste = MasterDAO.getLivroDAO().criar(new Livro(textFieldtitulo.getText(), textFieldautor.getText(), textFieldeditora.getText()
            , textFieldISBN.getText(), textFieldanodepublicacao.getText(), CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
            limparPaginaRegistroLivros();
            System.out.println(MasterDAO.getLivroDAO().getLivros());
        }
    }
}
