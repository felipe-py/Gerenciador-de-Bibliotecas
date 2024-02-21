package com.uefs.gerenciadorparabibliotecas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controlador responsável pela exibição dos livros encontrados em uma busca.
 * Este controlador configura as colunas e a tabela para exibir informações sobre os livros encontrados.
 */

public class ControllerLivrosEncontrados {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> TableColumnAnodePublicacaoCol;

    @FXML
    private TableColumn<?, ?> TableColumnAutorCol;

    @FXML
    private TableColumn<?, ?> TableColumnCategoriaCol;

    @FXML
    private TableColumn<?, ?> TableColumnEditoraCol;

    @FXML
    private TableColumn<?, ?> TableColumnISBNCol;

    @FXML
    private TableColumn<?, ?> TableColumnLocalizacaoCol;

    @FXML
    private TableColumn<?, ?> TableColumnTituloCol;

    @FXML
    private TableView<?> TableViewLivrosEncontrados;

    /**
     * Método chamado automaticamente após o carregamento do arquivo FXML.
     * Inicializa as colunas da tabela com seus respectivos nomes e adiciona essas colunas à tabela.
     */
    @FXML
    void initialize() {
        TableColumn TituloCol = new TableColumn("Título");
        TableColumn AutorCol = new TableColumn("Autor");
        TableColumn AnoDePublicacaoCol = new TableColumn("Ano de Publicação");
        TableColumn EditoraCol = new TableColumn("Editora");
        TableColumn ISBNCol = new TableColumn("ISBN");
        TableColumn CategoriaCol = new TableColumn("Categoria");
        TableColumn LocalizacaoCol = new TableColumn("Localização");

        this.TableViewLivrosEncontrados.getColumns().addAll(TituloCol, AutorCol, AnoDePublicacaoCol, EditoraCol, ISBNCol, CategoriaCol, LocalizacaoCol);
    }

}

