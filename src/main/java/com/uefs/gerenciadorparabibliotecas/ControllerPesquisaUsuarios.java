package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador responsável pela lógica de pesquisa de usuários na interface gráfica.
 * Este controlador gerencia as ações relacionadas à pesquisa de usuários por diferentes categorias.
 */
public class ControllerPesquisaUsuarios {

    @FXML
    private Button buttonBuscarAdministrador;

    @FXML
    private Button buttonBuscarBibliotecario;

    @FXML
    private Button buttonBuscarLeitor;

    @FXML
    private TextField textFieldBuscaUsuario;

    @FXML
    private Label labelErrorPesquisaUsuarios;

    /**
     * Verifica se o campo de texto de pesquisa de usuário está vazio e se contém apenas números.
     * @return true se o campo não estiver vazio e contiver apenas números, false caso contrário.
     */
    private Boolean verificaCampoDeTexto(){
        if (this.textFieldBuscaUsuario.getText().isEmpty()){
            this.labelErrorPesquisaUsuarios.setText("Informação vazia");
            return false;
        } if (!(this.textFieldBuscaUsuario.getText().matches("^[0-9\\s-]*$"))){
            this.labelErrorPesquisaUsuarios.setText(this.textFieldBuscaUsuario.getText() + " inválido");
            return false;
        }
        return true;
    }

    /**
     * Método chamado quando o botão de pesquisa por administrador é clicado.
     * Ele verifica se o ID inserido corresponde a um administrador e exibe mensagens de erro ou sucesso na interface gráfica.
     * @param event O evento de clique do botão.
     * @throws FuncionarioException Se ocorrer um erro ao pesquisar o administrador.
     */
    @FXML
    void onButtoBuscarAdministradorClicked(ActionEvent event) throws FuncionarioException {
        try {
            if (verificaCampoDeTexto()){
                Class<?> classeDoObjeto = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldBuscaUsuario.getText())).getClass();
                String nomeDaClasse = classeDoObjeto.getName();

                if (nomeDaClasse.equals("com.uefs.gerenciadorparabibliotecas.model.Administrador")) {
                    //System.out.println(MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldBuscaUsuario.getText())));
                    this.labelErrorPesquisaUsuarios.setText("");
                } else {
                    this.labelErrorPesquisaUsuarios.setText("ID não corresponde a categoria escolhida");
                }
            }
        } catch (FuncionarioException e) {
            this.labelErrorPesquisaUsuarios.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de pesquisa por bibliotecário é clicado.
     * Ele verifica se o ID inserido corresponde a um bibliotecário e exibe mensagens de erro ou sucesso na interface gráfica.
     * @param event O evento de clique do botão.
     * @throws FuncionarioException Se ocorrer um erro ao pesquisar o bibliotecário.
     */
    @FXML
    void onButtoBuscarBibliotecarioClicked(ActionEvent event) throws FuncionarioException{
        try {
            if (verificaCampoDeTexto()){
                Class<?> classeDoObjeto = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldBuscaUsuario.getText())).getClass();
                String nomeDaClasse = classeDoObjeto.getName();

                if (nomeDaClasse.equals("com.uefs.gerenciadorparabibliotecas.model.Bibliotecario")) {
                    //System.out.println(MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldBuscaUsuario.getText())));
                    this.labelErrorPesquisaUsuarios.setText("");
                } else {
                    this.labelErrorPesquisaUsuarios.setText("ID não corresponde a categoria escolhida");
                }
            }
        } catch (FuncionarioException e) {
            this.labelErrorPesquisaUsuarios.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de pesquisa por leitor é clicado.
     * Ele busca por um leitor com o ID especificado e exibe informações na interface gráfica.
     * @param event O evento de clique do botão.
     * @throws LeitorException Se ocorrer um erro ao pesquisar o leitor.
     */
    @FXML
    void onButtoBuscarLeitorClicked(ActionEvent event) throws LeitorException{
        try {
            if (verificaCampoDeTexto()){
                System.out.println(MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldBuscaUsuario.getText())));
            }
        } catch (LeitorException e) {
            this.labelErrorPesquisaUsuarios.setText(e.getMessage());
        }
    }
}
