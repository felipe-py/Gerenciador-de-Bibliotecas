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

/**
 * Controlador para a interface de edição de livros.
 */
public class ControllerEditarLivro {


    @FXML
    private Label LabelLivroEncontrado;

    @FXML
    private Label LabelLivroEncontrado2;


    @FXML
    private Button buttonAnoDePublicacao;

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonAutor;

    @FXML
    private Button buttonCategoria;

    @FXML
    private Button buttonEditora;

    @FXML
    private Button buttonISBN;

    @FXML
    private Button buttonLocalizacao;

    @FXML
    private Button buttonTitulo;

    @FXML
    private Label labelError;

    @FXML
    private TextField labelNovaInformacao;

    @FXML
    private TextField textFieldIDLivro;

    @FXML
    private Button buttonExcluir;

    /**
     * Verifica se o texto inserido no campo de ID do livro é válido.
     *
     * @throws LivroException se o texto não for um número ou estiver vazio.
     */
    private void verificaTexto() throws LivroException {
        if (!(this.textFieldIDLivro.getText().matches("^[0-9\\s-]*$")) || this.textFieldIDLivro.getText().isEmpty()) {
            throw new LivroException(LivroException.INVALID_INFO, this.textFieldIDLivro.getText());
        }
    }

    /**
     * Verifica se o campo de texto para nova informação está vazio.
     *
     * @throws LivroException se o campo estiver vazio.
     */
    private void verificaTexto2() throws LivroException {
        if (this.labelNovaInformacao.getText().isEmpty()) {
            throw new LivroException(LivroException.EMPITY_INFO, this.textFieldIDLivro.getText());
        }
    }

    /**
     * Verifica se o texto inserido no campo de nova informação é válido.
     *
     * @throws LivroException se o texto contiver caracteres inválidos.
     */
    private void verificaTexto3() throws LivroException {
        if (!(this.labelNovaInformacao.getText().matches("^[a-zA-Z\\s]*$"))) {
            throw new LivroException(LivroException.INVALID_INFO, this.labelError.getText());
        }
    }

    /**
     * Limpa os campos de texto e mensagens de erro.
     */
    private void limparCampos() {
        this.labelError.setText("");
        this.labelNovaInformacao.setText("");
    }

    /**
     * Executa a ação de exclusão de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonExcluirclicked(ActionEvent event) {
        //System.out.println(MasterDAO.getLivroDAO().getLivros());
        try {
            verificaTexto();
            if (MasterDAO.getLivroDAO().getLivros().contains(MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText())))) {
                Livro livroEncontrado = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));

                this.LabelLivroEncontrado.setText("ID: " + livroEncontrado.getLivroID() + " / Titulo: " + livroEncontrado.getTitulo() + " / Autor: " + livroEncontrado.getautor() + " / Editora: " + livroEncontrado.getEditora());
                this.LabelLivroEncontrado2.setText("Ano de Publicação: " + livroEncontrado.getAnoDePublicacao() + " / Localização: " + livroEncontrado.getLocalizacao() + " / Categoria: " + livroEncontrado.getCategoria());
                this.labelError.setText("O livro " + livroEncontrado.getTitulo() + " foi excluído com sucesso.");

                MasterDAO.getLivroDAO().deletar(livroEncontrado);
            } else {
                this.labelError.setText("ID inválido");
            }
        } catch (LivroException e) {
            labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do ano de publicação de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonAnoDePublicacaoclicked(ActionEvent event) {
        try {
            verificaTexto();
            verificaTexto2();
            if (!(this.labelNovaInformacao.getText().matches("^[0-9\\s-]*$"))) {
                this.labelError.setText("Somente números");
            } else {
                Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
                novo.setAnoDePublicacao(this.labelNovaInformacao.getText());
                MasterDAO.getLivroDAO().atualizar(novo);
                limparCampos();
            }
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do autor de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonAutorclicked(ActionEvent event) {
        try {
            verificaTexto2();
            verificaTexto3();
            Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
            novo.setAutor(this.labelNovaInformacao.getText());
            MasterDAO.getLivroDAO().atualizar(novo);
            limparCampos();
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de buscar um livro pelo ID.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonBuscarLivroclicked(ActionEvent event) {
        //System.out.println(MasterDAO.getLivroDAO().getLivros());
        try {
            verificaTexto();
            if (MasterDAO.getLivroDAO().getLivros().contains(MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText())))) {
                Livro livroEncontrado = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
                this.LabelLivroEncontrado.setText("ID: " + livroEncontrado.getLivroID() + " / Titulo: " + livroEncontrado.getTitulo() + " / Autor: " + livroEncontrado.getautor() + " / Editora: " + livroEncontrado.getEditora());
                this.LabelLivroEncontrado2.setText("Ano de Publicação: " + livroEncontrado.getAnoDePublicacao() + " / Localização: " + livroEncontrado.getLocalizacao() + " / Categoria: " + livroEncontrado.getCategoria());
                this.labelError.setText("");
            } else {
                this.labelError.setText("ID inválido");
            }
        } catch (LivroException e) {
            labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização da categoria de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonCategoriaclicked(ActionEvent event) {
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

        try{
            verificaTexto();
            verificaTexto2();
            for (Map.Entry<String, CategoriaLivro> entrada : categorias.entrySet()) {
                String chave = entrada.getKey();
                CategoriaLivro valor = entrada.getValue();
                if (this.labelNovaInformacao.getText().equalsIgnoreCase(chave)) {
                    Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
                    novo.setCategoria(valor);
                    MasterDAO.getLivroDAO().atualizar(novo);
                    limparCampos();
                }
            }
            //System.out.println(MasterDAO.getLivroDAO().getLivros());
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização da editora de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonEditoraclicked(ActionEvent event) {
        try {
            verificaTexto();
            verificaTexto2();
            Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
            novo.setEditora(this.labelNovaInformacao.getText());
            MasterDAO.getLivroDAO().atualizar(novo);
            limparCampos();
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do ISBN de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonISBNclicked(ActionEvent event) {
        try {
            verificaTexto();
            verificaTexto2();
            if(!(this.labelNovaInformacao.getText().matches("^[0-9\\s-]*$"))){
                this.labelError.setText("Somente números");
            } else {
                Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
                novo.setIsbn(this.labelNovaInformacao.getText());
                MasterDAO.getLivroDAO().atualizar(novo);
                limparCampos();
            }
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização da localização de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonLocalizacaoclicked(ActionEvent event) {
        HashMap<String, LocalizacaoLivro> localizacoes = new HashMap<>();
        localizacoes.put("a", LocalizacaoLivro.alaA);
        localizacoes.put("b", LocalizacaoLivro.alaB);
        localizacoes.put("c", LocalizacaoLivro.alaC);
        localizacoes.put("d", LocalizacaoLivro.alaD);
        localizacoes.put("e", LocalizacaoLivro.alaE);
        localizacoes.put("f", LocalizacaoLivro.alaF);
        localizacoes.put("g", LocalizacaoLivro.alaG);

        try {
            verificaTexto();
            verificaTexto2();
            for (Map.Entry<String, LocalizacaoLivro> entrada : localizacoes.entrySet()) {
                String chave = entrada.getKey();
                LocalizacaoLivro valor = entrada.getValue();
                if (this.labelNovaInformacao.getText().equalsIgnoreCase(chave)) {
                    Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
                    novo.setLocalizacao(valor);
                    MasterDAO.getLivroDAO().atualizar(novo);
                    limparCampos();
                }
            }
            //System.out.println(MasterDAO.getLivroDAO().getLivros());
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do título de um livro.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonTituloclicked(ActionEvent event) {
        try {
            verificaTexto();
            verificaTexto2();
            Livro novo = MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.textFieldIDLivro.getText()));
            novo.setTitulo(this.labelNovaInformacao.getText());
            MasterDAO.getLivroDAO().atualizar(novo);
            limparCampos();
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }
}
