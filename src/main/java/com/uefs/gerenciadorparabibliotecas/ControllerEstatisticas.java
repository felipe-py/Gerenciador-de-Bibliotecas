package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controlador responsável pela exibição de estatísticas sobre empréstimos de livros e usuários.
 * Permite visualizar o número de livros emprestados, atrasados, reservados e informações sobre os usuários.
 */
public class ControllerEstatisticas implements Initializable {
    @FXML
    private Button btnbuscar;
    @FXML
    private TextField campoDeBusca;
    @FXML
    private Label numeroDeLivrosEmprestados;
    @FXML
    private Label numeroDeLivrosAtrasados;
    @FXML
    private Label numeroDeLivrosReservados;
    @FXML
    private Label maisPopular;
    @FXML
    private Label segundoMaisPopular;
    @FXML
    private Label terceiroMaisPopular;
    @FXML
    private Label livroMaisEmprestado;
    @FXML
    private Label segundoLivroMaisEmprestado;
    @FXML
    private Label terceiroLivroMaisEmprestado;
    @FXML
    private Label nomeDoUsuario;
    @FXML
    private Label labelErrorPesquisaUsuarios;


    /**
     * Verifica se o campo de busca está vazio ou contém caracteres inválidos.
     * Exibe mensagens de erro correspondentes, se aplicável.
     *
     * @return true se o campo de busca contiver informações válidas, false caso contrário.
     */

    private Boolean verificaCampoDeTexto() {
        if (this.campoDeBusca.getText().isEmpty()) {
            this.labelErrorPesquisaUsuarios.setText("Informação vazia");
            return false;
        }
        if (!(this.campoDeBusca.getText().matches("^[0-9\\s-]*$"))) {
            this.labelErrorPesquisaUsuarios.setText(this.campoDeBusca.getText() + " inválido");
            return false;
        }
        return true;
    }

    /**
     * Executa a ação de buscar informações de um usuário com base no ID fornecido.
     * Exibe informações sobre os empréstimos do usuário, incluindo os três últimos IDs de empréstimos.
     * Lança uma exceção LeitorException se ocorrer um erro durante a busca do usuário.
     *
     * @param event O evento de clique do mouse.
     * @throws IOException se ocorrer um erro de entrada e saída.
     */

    @FXML
    public void clicarEmBuscar (MouseEvent event) throws IOException {
        try {
            if (verificaCampoDeTexto()){
                //System.out.println(MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.campoDeBusca.getText())));
                Leitor leitor = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.campoDeBusca.getText()));
                //System.out.println((String.valueOf(leitor.getEmprestimos().get(0))));

                livroMaisEmprestado.setText("");
                segundoLivroMaisEmprestado.setText("");
                terceiroLivroMaisEmprestado.setText("");
                nomeDoUsuario.setText(leitor.getNome());
                try {
                    livroMaisEmprestado.setText("1: ID = " + String.valueOf(leitor.getEmprestimos().get(leitor.getEmprestimos().size()-1).getEmprestimoID()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    segundoLivroMaisEmprestado.setText("2: ID = " + String.valueOf(leitor.getEmprestimos().get(leitor.getEmprestimos().size()-2).getEmprestimoID()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    terceiroLivroMaisEmprestado.setText("3: ID = " + String.valueOf(leitor.getEmprestimos().get(leitor.getEmprestimos().size()-3).getEmprestimoID()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (LeitorException e) {
            this.labelErrorPesquisaUsuarios.setText(e.getMessage());
        }
    }

    /**
     * Gera e exibe estatísticas sobre empréstimos de livros.
     * Define os valores das etiquetas correspondentes com base nas estatísticas obtidas do DAO.
     */

    @FXML
    public void gerarEstatisticas () {
        numeroDeLivrosEmprestados.setText(String.valueOf(MasterDAO.getLivroDAO().nLivrosEmprestados()));
        //System.out.println(MasterDAO.getLivroDAO().nLivrosEmprestados());
        //System.out.println(MasterDAO.getLivroDAO().nLivrosReservados());
        // Sem uma função para retornar o numero de Atrasados
        numeroDeLivrosAtrasados.setText(String.valueOf(MasterDAO.getEmprestimoDAO().nLivrosatrasados(LocalDate.now())));
        numeroDeLivrosReservados.setText(String.valueOf(MasterDAO.getLivroDAO().nLivrosReservados()));

        int soma = 1;
        for (Map.Entry<Integer, List<String>> entry : MasterDAO.getLivroDAO().livrosPopulares(MasterDAO.getLivroDAO().agruparLivrosPorISBN()).entrySet()) {
            if (soma == 1) {
                maisPopular.setText(soma + "º: Número de empréstimos => " + entry.getKey() + " || Livro: " + entry.getValue());
            } else if (soma == 2) {
                segundoMaisPopular.setText(soma + "º: Número de empréstimos => " + entry.getKey() + " || Livro: " + entry.getValue());
            } else if (soma == 3) {
                terceiroMaisPopular.setText(soma + "º: Número de empréstimos => " + entry.getKey() + " || Livro: " + entry.getValue());
            }
            //livrosMaisPopulares.setText(soma + "º: Número de empréstimos => " + entry.getKey() + " || Livro: " + entry.getValue());
            soma++;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gerarEstatisticas();
    }
}
