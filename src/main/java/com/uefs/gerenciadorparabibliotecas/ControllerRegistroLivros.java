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

import java.util.HashMap;
import java.util.Map;

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

    private CategoriaLivro categoriaRegistrada(){
        HashMap<String, CategoriaLivro> categorias = new HashMap<>();
        categorias.put("ficcao", CategoriaLivro.FICCAO);
        categorias.put("didatico", CategoriaLivro.DIDATICO);
        categorias.put("romance", CategoriaLivro.ROMANCE);
        categorias.put("misterio", CategoriaLivro.MISTERIO);
        categorias.put("fantasia", CategoriaLivro.FANTASIA);
        categorias.put("biografia", CategoriaLivro.BIOGRAFIA);
        categorias.put("historia", CategoriaLivro.HISTORIA);
        categorias.put("autoajuda", CategoriaLivro.AUTOAJUDA);
        categorias.put("poesia", CategoriaLivro.POESIA);
        categorias.put("outra", CategoriaLivro.OUTRA);

        for (Map.Entry<String, CategoriaLivro> entrada : categorias.entrySet()) {
            String chave = entrada.getKey();
            CategoriaLivro valor = entrada.getValue();
            if (textFieldcategoria.getText().equalsIgnoreCase(chave)) {
                return valor;
            }
        }
        return CategoriaLivro.OUTRA;
    }

    private LocalizacaoLivro localizacaoRegistrada(){
        HashMap<String, LocalizacaoLivro> localizacoes = new HashMap<>();
        localizacoes.put("a", LocalizacaoLivro.alaA);
        localizacoes.put("b", LocalizacaoLivro.alaB);
        localizacoes.put("c", LocalizacaoLivro.alaC);
        localizacoes.put("d", LocalizacaoLivro.alaD);
        localizacoes.put("e", LocalizacaoLivro.alaE);
        localizacoes.put("f", LocalizacaoLivro.alaF);
        localizacoes.put("g", LocalizacaoLivro.alaG);

        for (Map.Entry<String, LocalizacaoLivro> entrada : localizacoes.entrySet()) {
            String chave = entrada.getKey();
            LocalizacaoLivro valor = entrada.getValue();
            if (textFieldlocalizacao.getText().equalsIgnoreCase(chave)) {
                return valor;
            }
        }
        return null;
    }

    @FXML
    void onButtonRegistrarLivroClicked(ActionEvent event) throws LivroException {
        try{
            Livro teste = MasterDAO.getLivroDAO().criar(new Livro(textFieldtitulo.getText(), textFieldautor.getText(), textFieldeditora.getText()
                    , textFieldISBN.getText(), textFieldanodepublicacao.getText(), categoriaRegistrada(), localizacaoRegistrada()));
            limparPaginaRegistroLivros();
            for (Livro livro : MasterDAO.getLivroDAO().getLivros()){
                System.out.println(livro);
            }
        } catch (LivroException e){
            labelErrorRegistroLivros.setText(e.getMessage());
        }
    }
}
