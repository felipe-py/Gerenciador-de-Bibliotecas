package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.Emprestimo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controlador responsável pela interface de gerenciamento de empréstimos do sistema.
 * Permite criar, buscar, encerrar, renovar e excluir empréstimos.
 */
public class ControllerEmprestimos {

    @FXML
    private TextField IDLivroLabell;

    @FXML
    private TextField IDUsuarioLabel;

    @FXML
    private TextField IDemprestimoTextField;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonEncerrar;

    @FXML
    private Button buttonRenovar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Button buttonVerificar;

    @FXML
    private Label labelEmprestimo1;

    @FXML
    private Label labelEmprestimo2;

    @FXML
    private Label labelEmprestimo3;

    @FXML
    private Label labelError;

    @FXML
    private DatePicker dataAbertura;

    @FXML
    private DatePicker dataEntrega;

    /**
     * Verifica se os IDs do livro e do usuário inseridos nos campos são válidos.
     * Lança exceções LivroException ou LeitorException se os IDs não forem válidos ou estiverem vazios.
     *
     * @throws LivroException se o ID do livro não for válido.
     * @throws LeitorException se o ID do usuário não for válido.
     */
    private void verificacao() throws LivroException, LeitorException {
        if(!(this.IDLivroLabell.getText().matches("^[0-9\\s-]*$")) || this.IDLivroLabell.getText().isEmpty()){
            throw new LivroException(LivroException.INVALID_INFO, this.IDLivroLabell.getText());
        }
        if(!(this.IDUsuarioLabel.getText().matches("^[0-9\\s-]*$")) || this.IDUsuarioLabel.getText().isEmpty()){
            throw new LeitorException(LeitorException.INVALID_INFO, this.IDUsuarioLabel.getText());
        }
    }

    /**
     * Verifica se o campo de ID de empréstimo está vazio.
     * Lança uma exceção EmprestimoException se o campo estiver vazio.
     *
     * @throws EmprestimoException se o campo estiver vazio.
     */
    private void verificacao2() throws EmprestimoException {
        if(this.IDemprestimoTextField.getText().isEmpty()){
            throw new EmprestimoException(EmprestimoException.SEARCH, Integer.valueOf(this.IDemprestimoTextField.getText()));
        }
    }

    /**
     * Limpa os campos de entrada de texto e as áreas de exibição de erro e de informações de empréstimo.
     */
    private void limparCampos(){
        this.labelError.setText("");
        this.IDUsuarioLabel.setText("");
        this.IDLivroLabell.setText("");
        dataEntrega.setValue(null);
        dataAbertura.setValue(null);
    }

    /**
     * Verifica se o empréstimo está ativo ou finalizado e retorna a data de devolução final, se houver.
     *
     * @param emprestimo O empréstimo a ser verificado.
     * @return Uma string indicando se o empréstimo está ativo ou a data de devolução final.
     */
    private String verificaData(Emprestimo emprestimo){
        if(emprestimo.getDataDevolvidoFinal() == null){
            return "Ainda ativo";
        } else {
            return emprestimo.getDataDevolvidoFinal().toString();
        }
    }

    /**
     * Executa a ação de encerrar um empréstimo.
     * Finaliza o empréstimo, define a data de devolução final como a data atual e exibe informações do empréstimo encerrado.
     * Lança uma exceção EmprestimoException se ocorrer um erro durante o encerramento do empréstimo.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonEncerrarClicked(ActionEvent event) {
        try {
            verificacao2();
            Emprestimo emprestimoEncerrar = MasterDAO.getEmprestimoDAO().procurarPorID(Integer.parseInt(this.IDemprestimoTextField.getText()));
            emprestimoEncerrar.finalizarEmprestimo(emprestimoEncerrar, LocalDate.now());
            this.labelEmprestimo1.setText("Empréstimo encerrado => ID: " + emprestimoEncerrar.getEmprestimoID() + " / Usuário: " + emprestimoEncerrar.getMutuario().getNome()
                    + " / Livro: " + emprestimoEncerrar.getLivroEmprestado().getTitulo());
            this.labelEmprestimo2.setText("Ativo = " + emprestimoEncerrar.getNaoDevolvido());
            this.labelEmprestimo3.setText("");
            limparCampos();
        } catch (EmprestimoException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de renovar um empréstimo.
     * Estende a data de devolução do empréstimo e exibe informações do empréstimo renovado.
     * Lança uma exceção EmprestimoException se ocorrer um erro durante a renovação do empréstimo.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonRenovarClicked(ActionEvent event) {
        try {
            verificacao2();
            Emprestimo emprestimoRenovar = MasterDAO.getEmprestimoDAO().procurarPorID(Integer.parseInt(this.IDemprestimoTextField.getText()));
            emprestimoRenovar.estenderEmprestimo(emprestimoRenovar);
            this.labelEmprestimo1.setText("Empréstimo renovado => ID: " + emprestimoRenovar.getEmprestimoID() + " / Usuário: " + emprestimoRenovar.getMutuario().getNome()
                    + " / Livro: " + emprestimoRenovar.getLivroEmprestado().getTitulo());
            this.labelEmprestimo2.setText("Nova data entrega = " + emprestimoRenovar.getdataDevolucaoEsperada());
            this.labelEmprestimo3.setText("");
            limparCampos();
        } catch (EmprestimoException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de salvar um novo empréstimo no sistema.
     * Cria um novo empréstimo com as informações fornecidas, salva no sistema e exibe informações do empréstimo criado.
     * Lança exceções EmprestimoException, LivroException ou LeitorException se ocorrerem erros durante a criação do empréstimo.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonSalvarClicked(ActionEvent event) {
        LocalDate dataAberturaV = dataAbertura.getValue();
        LocalDate dataEntregaV = dataEntrega.getValue();

        try {
            verificacao();
            if(dataAbertura.getValue() == null || dataEntrega.getValue() == null){
                this.labelError.setText("Preencha as datas");
            } else {
                Emprestimo novo = MasterDAO.getEmprestimoDAO().criar(new Emprestimo(dataAberturaV, dataEntregaV, MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.IDLivroLabell.getText())),
                        MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.IDUsuarioLabel.getText()))));
                this.labelEmprestimo1.setText("ID: " + novo.getEmprestimoID() + " / ID Usuário: " + novo.getMutuario().getUserID() + " / ID Livro: " + novo.getLivroEmprestado().getLivroID());
                this.labelEmprestimo2.setText("Nome Usuário: " + novo.getMutuario().getNome() + " / Título Livro: " + novo.getLivroEmprestado().getTitulo());
                this.labelEmprestimo3.setText("Data Abertura: " + novo.getDataEmprestimo() + " / Data Devolução: " + novo.getdataDevolucaoEsperada());
                limparCampos();
            }
        } catch (EmprestimoException | LivroException | LeitorException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de verificar um empréstimo no sistema.
     * Exibe informações do empréstimo buscado com base no ID fornecido.
     * Lança uma exceção EmprestimoException se ocorrer um erro durante a busca do empréstimo.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonVerificarClicked(ActionEvent event) {
        try {
            verificacao2();
            Emprestimo buscado = MasterDAO.getEmprestimoDAO().procurarPorID(Integer.parseInt(this.IDemprestimoTextField.getText()));
            this.labelEmprestimo1.setText("ID: " + buscado.getEmprestimoID() + " / ID Usuário: " + buscado.getMutuario().getUserID() + " / ID Livro: " + buscado.getLivroEmprestado().getLivroID());
            this.labelEmprestimo2.setText("Nome Usuário: " + buscado.getMutuario().getNome() + " / Título Livro: " + buscado.getLivroEmprestado().getTitulo() + " / Empréstimo Ativo = " + buscado.getNaoDevolvido());
            this.labelEmprestimo3.setText("Data Abertura: " + buscado.getDataEmprestimo() + " / Data Entrega Esperada: " + buscado.getdataDevolucaoEsperada() +" / Data Entrega Final: " + verificaData(buscado));
            limparCampos();
            //System.out.println(buscado.getDataDevolvidoFinal());
        } catch (EmprestimoException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de deletar um empréstimo do sistema.
     * Remove o empréstimo com o ID fornecido do sistema e exibe uma mensagem de confirmação.
     * Lança uma exceção EmprestimoException se ocorrer um erro durante a exclusão do empréstimo.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonDeletarClicked(ActionEvent event) {
        try {
            verificacao2();
            MasterDAO.getEmprestimoDAO().deletar(MasterDAO.getEmprestimoDAO().procurarPorID(Integer.parseInt(this.IDemprestimoTextField.getText())));
            limparCampos();
            this.labelEmprestimo1.setText("Empréstimo removido do sistema");
            this.labelEmprestimo2.setText("");
            this.labelEmprestimo3.setText("");
        } catch (EmprestimoException e) {
            this.labelError.setText(e.getMessage());
        }
    }
}
