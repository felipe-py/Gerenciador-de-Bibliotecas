package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.CategoriaLivro;
import com.uefs.gerenciadorparabibliotecas.model.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador responsável pela lógica de pesquisa de livros na interface gráfica.
 * Este controlador gerencia as ações relacionadas à pesquisa de livros por diferentes critérios.
 */
public class ControllerPesquisaLivros {

    @FXML
    private Button buttonAutor;

    @FXML
    private Button buttonCategoria;

    @FXML
    private Button buttonEditora;

    @FXML
    private Button buttonISBN;

    @FXML
    private Button buttonTitulo;

    @FXML
    private Label labelError;

    @FXML
    private TextField textFieldPesquisa;

    @FXML
    private Button buttonID;
    @FXML
    private Label tituloDoLivro1;
    @FXML
    private Label tituloDoLivro2;
    @FXML
    private Label tituloDoLivro3;
    @FXML
    private Label idDoLivro1;
    @FXML
    private Label idDoLivro2;
    @FXML
    private Label idDoLivro3;

    /**
     * Retorna o texto inserido no campo de pesquisa.
     * @return O texto inserido no campo de pesquisa.
     */
    String retornarTextoPesquisa(){
        return textFieldPesquisa.getText();
    }

    /**
     * Limpa o campo de pesquisa e a mensagem de erro na interface gráfica.
     */
    void limparPaginaPrincipal(){
        this.textFieldPesquisa.clear();
        this.labelError.setText("");
    }

    /**
     * Método chamado quando o botão de pesquisa por autor é clicado.
     * Ele busca por livros baseados no autor especificado e exibe até três resultados na interface gráfica.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonAutorClicked(ActionEvent event) throws LivroException {
        try{
            if (retornarTextoPesquisa().equalsIgnoreCase("")){
                this.labelError.setText("Inválido");
            } else {
                List<Livro> livrosEncontrados = MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorAutor(retornarTextoPesquisa()));
                //System.out.println(livrosEncontrados);


                if (livrosEncontrados.size() >= 1) {
                    Livro livro1 = livrosEncontrados.get(0);
                    tituloDoLivro1.setText("Título: "+livrosEncontrados.get(0).getTitulo());
                    idDoLivro1.setText("ID: "+livrosEncontrados.get(0).getLivroID());
                } else {
                    tituloDoLivro1.setText("");
                    idDoLivro1.setText("");
                }

                if (livrosEncontrados.size() >= 2) {
                    Livro livro2 = livrosEncontrados.get(1);
                    tituloDoLivro2.setText("Título: "+livrosEncontrados.get(1).getTitulo());
                    idDoLivro2.setText("ID: "+livrosEncontrados.get(1).getLivroID());
                } else {
                    idDoLivro2.setText("");
                    tituloDoLivro2.setText("");
                }

                if (livrosEncontrados.size() >= 3) {
                    Livro livro3 = livrosEncontrados.get(2);
                    tituloDoLivro3.setText("Título: "+livrosEncontrados.get(2).getTitulo());
                    idDoLivro3.setText("ID: "+livrosEncontrados.get(2).getLivroID());
                } else {
                    idDoLivro3.setText("");
                    tituloDoLivro3.setText("");
                }


                limparPaginaPrincipal();
            }
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de pesquisa por categoria é clicado.
     * Ele busca por livros baseados na categoria especificada e exibe até três resultados na interface gráfica.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonCategoriaClicked(ActionEvent event) throws LivroException {
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
            if (retornarTextoPesquisa().equalsIgnoreCase("")){
                this.labelError.setText("Inválido");
            }
            for (Map.Entry<String, CategoriaLivro> entrada : categorias.entrySet()) {
                String chave = entrada.getKey();
                CategoriaLivro valor = entrada.getValue();
                if (textFieldPesquisa.getText().equalsIgnoreCase(chave)) {
                    List<Livro> livrosEncontrados = MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorCategoria(valor));

                    if (livrosEncontrados.size() >= 1) {
                        Livro livro1 = livrosEncontrados.get(0);
                        tituloDoLivro1.setText("Título: "+livrosEncontrados.get(0).getTitulo());
                        idDoLivro1.setText("ID: "+livrosEncontrados.get(0).getLivroID());
                    } else {
                        tituloDoLivro1.setText("");
                        idDoLivro1.setText("");
                    }

                    if (livrosEncontrados.size() >= 2) {
                        Livro livro2 = livrosEncontrados.get(1);
                        tituloDoLivro2.setText("Título: "+livrosEncontrados.get(1).getTitulo());
                        idDoLivro2.setText("ID: "+livrosEncontrados.get(1).getLivroID());
                    } else {
                        idDoLivro2.setText("");
                        tituloDoLivro2.setText("");
                    }

                    if (livrosEncontrados.size() >= 3) {
                        Livro livro3 = livrosEncontrados.get(2);
                        tituloDoLivro3.setText("Título: "+livrosEncontrados.get(2).getTitulo());
                        idDoLivro3.setText("ID: "+livrosEncontrados.get(2).getLivroID());
                    } else {
                        idDoLivro3.setText("");
                        tituloDoLivro3.setText("");
                    }

                    //System.out.println(livrosEncontrados);
                }
            }
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de pesquisa por ISBN é clicado.
     * Ele busca por livros com o ISBN especificado e exibe até três resultados na interface gráfica.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonISBNClicked(ActionEvent event) {
        try{
            if (retornarTextoPesquisa().equalsIgnoreCase("")){
                this.labelError.setText("Inválido");
            } else {
                List<Livro> livrosEncontrados = MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorISBN(retornarTextoPesquisa()));


                if (livrosEncontrados.size() >= 1) {
                    Livro livro1 = livrosEncontrados.get(0);
                    tituloDoLivro1.setText("Título: "+livrosEncontrados.get(0).getTitulo());
                    idDoLivro1.setText("ID: "+livrosEncontrados.get(0).getLivroID());
                } else {
                    tituloDoLivro1.setText("");
                    idDoLivro1.setText("");
                }

                if (livrosEncontrados.size() >= 2) {
                    Livro livro2 = livrosEncontrados.get(1);
                    tituloDoLivro2.setText("Título: "+livrosEncontrados.get(1).getTitulo());
                    idDoLivro2.setText("ID: "+livrosEncontrados.get(1).getLivroID());
                } else {
                    idDoLivro2.setText("");
                    tituloDoLivro2.setText("");
                }

                if (livrosEncontrados.size() >= 3) {
                    Livro livro3 = livrosEncontrados.get(2);
                    tituloDoLivro3.setText("Título: "+livrosEncontrados.get(2).getTitulo());
                    idDoLivro3.setText("ID: "+livrosEncontrados.get(2).getLivroID());
                } else {
                    idDoLivro3.setText("");
                    tituloDoLivro3.setText("");
                }

                //System.out.println(livrosEncontrados);
                limparPaginaPrincipal();
            }
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de pesquisa por título é clicado.
     * Ele busca por livros com o título especificado e exibe até três resultados na interface gráfica.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonTituloClicked(ActionEvent event) {
        try{
            if (retornarTextoPesquisa().equalsIgnoreCase("")){
                this.labelError.setText("Inválido");
            } else {
                List<Livro> livrosEncontrados = MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorTitulo(retornarTextoPesquisa()));

                if (livrosEncontrados.size() >= 1) {
                    Livro livro1 = livrosEncontrados.get(0);
                    tituloDoLivro1.setText("Título: "+livrosEncontrados.get(0).getTitulo());
                    idDoLivro1.setText("ID: "+livrosEncontrados.get(0).getLivroID());
                } else {
                    tituloDoLivro1.setText("");
                    idDoLivro1.setText("");
                }

                if (livrosEncontrados.size() >= 2) {
                    Livro livro2 = livrosEncontrados.get(1);
                    tituloDoLivro2.setText("Título: "+livrosEncontrados.get(1).getTitulo());
                    idDoLivro2.setText("ID: "+livrosEncontrados.get(1).getLivroID());
                } else {
                    idDoLivro2.setText("");
                    tituloDoLivro2.setText("");
                }

                if (livrosEncontrados.size() >= 3) {
                    Livro livro3 = livrosEncontrados.get(2);
                    tituloDoLivro3.setText("Título: "+livrosEncontrados.get(2).getTitulo());
                    idDoLivro3.setText("ID: "+livrosEncontrados.get(2).getLivroID());
                } else {
                    idDoLivro3.setText("");
                    tituloDoLivro3.setText("");
                }

                //System.out.println(livrosEncontrados);
                limparPaginaPrincipal();
            }
        } catch (LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de pesquisa por ID é clicado.
     * Ele busca por livros com o ID especificado e exibe até três resultados na interface gráfica.
     * @param event O evento de clique do botão.
     * @throws LivroException Se ocorrer um erro ao pesquisar o livro por ID.
     */
    @FXML
    void onButtonIDClicked(ActionEvent event) throws LivroException {
        String texto = retornarTextoPesquisa();
        if (texto.equalsIgnoreCase("")){
            this.labelError.setText("Inválido");
        } else {
            try {
                Integer n = Integer.parseInt(texto);
                List<Livro> livrosEncontrados = Collections.singletonList(MasterDAO.getLivroDAO().procurarPorID(n));

                if (livrosEncontrados.size() >= 1) {
                    Livro livro1 = livrosEncontrados.get(0);
                    tituloDoLivro1.setText("Título: "+livrosEncontrados.get(0).getTitulo());
                    idDoLivro1.setText("ID: "+livrosEncontrados.get(0).getLivroID());
                } else {
                    tituloDoLivro1.setText("");
                    idDoLivro1.setText("");
                }

                if (livrosEncontrados.size() >= 2) {
                    Livro livro2 = livrosEncontrados.get(1);
                    tituloDoLivro2.setText("Título: "+livrosEncontrados.get(1).getTitulo());
                    idDoLivro2.setText("ID: "+livrosEncontrados.get(1).getLivroID());
                } else {
                    idDoLivro2.setText("");
                    tituloDoLivro2.setText("");
                }

                if (livrosEncontrados.size() >= 3) {
                    Livro livro3 = livrosEncontrados.get(2);
                    tituloDoLivro3.setText("Título: "+livrosEncontrados.get(2).getTitulo());
                    idDoLivro3.setText("ID: "+livrosEncontrados.get(2).getLivroID());
                } else {
                    idDoLivro3.setText("");
                    tituloDoLivro3.setText("");
                }

                //System.out.println(livrosEncontrados);
                limparPaginaPrincipal();
            } catch (NumberFormatException e) {
                this.labelError.setText("ID - Somente números");
            }
        }
    }
}
