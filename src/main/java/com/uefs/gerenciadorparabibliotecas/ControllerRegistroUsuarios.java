package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Administrador;
import com.uefs.gerenciadorparabibliotecas.model.Bibliotecario;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador responsável pela lógica de registro de usuários na interface gráfica.
 * Este controlador gerencia as ações relacionadas ao registro de novos usuários (Administradores, Bibliotecários e Leitores) no sistema.
 */
public class ControllerRegistroUsuarios {

    @FXML
    private TextField TextFieldEnderecoUsuario;

    @FXML
    private TextField TextFieldIDUsuario;

    @FXML
    private TextField TextFieldNomeUsuario;

    @FXML
    private TextField TextFieldSenhaUsuario;

    @FXML
    private TextField TextFieldTelefoneUsuario;

    @FXML
    private Button buttonAdministrador;

    @FXML
    private Button buttonBibliotecario;

    @FXML
    private Button buttonRegistrarUsuario;

    @FXML
    private Label labelerrorUsuario;

    /**
     * Limpa todos os campos de entrada e a mensagem de erro na interface gráfica.
     * Este método é chamado para limpar a página de registro de usuários após o registro bem-sucedido ou para resetar os campos após um erro.
     */
    private void limparCamposRegistro(){
        this.labelerrorUsuario.setText("");
        this.TextFieldIDUsuario.clear();
        this.TextFieldNomeUsuario.clear();
        this.TextFieldEnderecoUsuario.clear();
        this.TextFieldSenhaUsuario.clear();
        this.TextFieldTelefoneUsuario.clear();
    }

    /**
     * Verifica se o campo de ID do usuário contém apenas números e não está vazio.
     * Este método é utilizado para garantir que o ID inserido seja válido antes de criar um novo Administrador ou Bibliotecário.
     * @throws FuncionarioException Se o ID inserido não for válido (não numérico ou vazio).
     */
    private void verificaTextoIDFuncionarios() throws FuncionarioException {
        if(!(this.TextFieldIDUsuario.getText().matches("^[0-9\\s-]*$")) || this.TextFieldIDUsuario.getText().isEmpty()){
            throw new FuncionarioException(FuncionarioException.INVALID_INFO, this.TextFieldIDUsuario.getText());
        }
    }

    /**
     * Verifica se o campo de ID do usuário contém apenas números e não está vazio.
     * Este método é utilizado para garantir que o ID inserido seja válido antes de criar um novo Leitor.
     * @throws LeitorException Se o ID inserido não for válido (não numérico ou vazio).
     */
    private void verificaTextoIDLeitor() throws LeitorException {
        if(!(this.TextFieldIDUsuario.getText().matches("^[0-9\\s-]*$")) || this.TextFieldIDUsuario.getText().isEmpty()){
            throw new LeitorException(LeitorException.INVALID_INFO, this.TextFieldIDUsuario.getText());
        }
    }

    /**
     * Método chamado quando o botão de registro de Administrador é clicado.
     * Ele verifica se o ID inserido é válido e, em seguida, cria um novo Administrador no sistema.
     * @param event O evento de clique do botão.
     * @throws FuncionarioException Se ocorrer um erro ao criar o Administrador.
     * @throws NumberFormatException Se houver um erro de formato ao converter o ID para número.
     */
    @FXML
    void onButtonAdministradorClicked(ActionEvent event) throws FuncionarioException, NumberFormatException {
        try{
            verificaTextoIDFuncionarios();
            MasterDAO.getFuncionarioDAO().criar(new Administrador(TextFieldNomeUsuario.getText(), TextFieldEnderecoUsuario.getText(),
                    TextFieldSenhaUsuario.getText(), TextFieldTelefoneUsuario.getText(), Integer.parseInt(TextFieldIDUsuario.getText())));
            limparCamposRegistro();
        } catch (FuncionarioException e) {
            labelerrorUsuario.setText(e.getMessage());
        }
        //System.out.println(MasterDAO.getFuncionarioDAO().getFuncionarios());
    }

    /**
     * Método chamado quando o botão de registro de Bibliotecário é clicado.
     * Ele verifica se o ID inserido é válido e, em seguida, cria um novo Bibliotecário no sistema.
     * @param event O evento de clique do botão.
     * @throws FuncionarioException Se ocorrer um erro ao criar o Bibliotecário.
     * @throws NumberFormatException Se houver um erro de formato ao converter o ID para número.
     */
    @FXML
    void onButtonBibliotecarioClicked(ActionEvent event) throws FuncionarioException, NumberFormatException{
        try{
            verificaTextoIDFuncionarios();
            MasterDAO.getFuncionarioDAO().criar(new Bibliotecario(TextFieldNomeUsuario.getText(), TextFieldEnderecoUsuario.getText(),
                    TextFieldSenhaUsuario.getText(), TextFieldTelefoneUsuario.getText(), Integer.parseInt(TextFieldIDUsuario.getText())));
            limparCamposRegistro();
        } catch (FuncionarioException e) {
            labelerrorUsuario.setText(e.getMessage());
        }
        //System.out.println(MasterDAO.getFuncionarioDAO().getFuncionarios());
    }

    /**
     * Método chamado quando o botão de registro de Leitor é clicado.
     * Ele verifica se o ID inserido é válido e, em seguida, cria um novo Leitor no sistema.
     * @param event O evento de clique do botão.
     * @throws LeitorException Se ocorrer um erro ao criar o Leitor.
     * @throws NumberFormatException Se houver um erro de formato ao converter o ID para número.
     */
    @FXML
    void onButtonRegistrarUsuarioClicked(ActionEvent event) throws LeitorException, NumberFormatException{
        try{
            verificaTextoIDLeitor();
            MasterDAO.getLeitorDAO().criar(new Leitor(TextFieldNomeUsuario.getText(), TextFieldEnderecoUsuario.getText(),
                    TextFieldSenhaUsuario.getText(), TextFieldTelefoneUsuario.getText(), Integer.parseInt(TextFieldIDUsuario.getText())));
            limparCamposRegistro();
        } catch (LeitorException e) {
            labelerrorUsuario.setText(e.getMessage());
        }
        //System.out.println(MasterDAO.getLeitorDAO().getLeitores());
    }
}
