package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador responsável pela interface de edição de usuários do sistema.
 * Permite buscar, visualizar e editar informações de usuários (funcionários e leitores).
 */
public class ControllerEditarUsuarios {

    @FXML
    private Button ButtonBuscarUsuario;

    @FXML
    private Label LabelUsuarioEncontrado;

    @FXML
    private Label LabelUsuarioEncontrado2;

    @FXML
    private CheckBox boxAdministrador;

    @FXML
    private CheckBox boxBibliotecario;

    @FXML
    private CheckBox boxLeitor;

    @FXML
    private Button buttonEndereco;

    @FXML
    private Button buttonID;

    @FXML
    private Button buttonNome;

    @FXML
    private Button buttonSenha;

    @FXML
    private Button buttonTelefone;

    @FXML
    private Label labelError;

    @FXML
    private TextField labelNovaInformacao;

    @FXML
    private TextField textFieldIDUsuario;

    @FXML
    private Button excluirUsuario;

    /**
     * Verifica se o texto inserido no campo de ID do usuário é válido.
     * Lança uma exceção LeitorException se o texto não consistir apenas em dígitos numéricos ou estiver vazio.
     *
     * @throws LeitorException se o texto não for válido.
     */
    private void verificaTexto() throws LeitorException {
        if(!(this.textFieldIDUsuario.getText().matches("^[0-9\\s-]*$")) || this.textFieldIDUsuario.getText().isEmpty()){
            throw new LeitorException(LeitorException.INVALID_INFO, this.textFieldIDUsuario.getText());
        }
    }

    /**
     * Verifica se o texto inserido no campo de nova informação está vazio.
     * Lança uma exceção LeitorException se o campo estiver vazio.
     *
     * @throws LeitorException se o campo estiver vazio.
     */
    private void verificaTexto2() throws LeitorException {
        if(this.labelNovaInformacao.getText().isEmpty()){
            throw new LeitorException(LeitorException.EMPITY_INFO, this.labelError.getText());
        }
    }

    /**
     * Verifica se o texto inserido no campo de nova informação contém apenas dígitos numéricos.
     * Lança uma exceção LeitorException se o texto não consistir apenas em dígitos numéricos.
     *
     * @throws LeitorException se o texto não for composto apenas por dígitos numéricos.
     */
    private void verificaTexto3() throws LeitorException {
        if(!(this.labelNovaInformacao.getText().matches("^[0-9\\s-]*$"))){
            throw new LeitorException(LeitorException.INVALID_INFO, this.labelError.getText());
        }
    }

    /**
     * Verifica se o texto inserido no campo de nova informação contém apenas letras ou espaços.
     * Lança uma exceção LeitorException se o texto contiver caracteres que não sejam letras ou espaços.
     *
     * @throws LeitorException se o texto contiver caracteres inválidos.
     */
    private void verificaTexto4() throws LeitorException {
        if(!(this.labelNovaInformacao.getText().matches("^[a-zA-Z\\s]*$"))){
            throw new LeitorException(LeitorException.INVALID_INFO, this.labelError.getText());
        }
    }

    /**
     * Verifica a classe do objeto associado ao ID inserido no campo.
     *
     * @return O nome da classe do objeto associado ao ID.
     */
    private String verificaClasse(){
        Class<?> classeDoObjeto = null;
        try {
            classeDoObjeto = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText())).getClass();
        } catch (FuncionarioException e) {
            this.labelError.setText(e.getMessage());
        }
        return classeDoObjeto.getName();
    }

    /**
     * Executa a ação quando a caixa de seleção de administrador é clicada.
     * Desmarca outras caixas de seleção se esta for marcada.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onBoxAdministradorClicked(ActionEvent event) {
        if (boxAdministrador.isSelected()) {
            boxBibliotecario.setSelected(false);
            boxLeitor.setSelected(false);
        }
    }

    /**
     * Executa a ação quando a caixa de seleção de bibliotecário é clicada.
     * Desmarca outras caixas de seleção se esta for marcada.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onBoxBibliotecarioClicked(ActionEvent event) {
        if (boxBibliotecario.isSelected()) {
            boxAdministrador.setSelected(false);
            boxLeitor.setSelected(false);
        }
    }

    /**
     * Executa a ação quando a caixa de seleção de leitor é clicada.
     * Desmarca outras caixas de seleção se esta for marcada.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onBoxLeitorClicked(ActionEvent event) {
        if (boxLeitor.isSelected()) {
            boxAdministrador.setSelected(false);
            boxBibliotecario.setSelected(false);
        }
    }

    /**
     * Limpa todas as caixas de seleção de categoria de usuário.
     */
    private void limparBox(){
        boxBibliotecario.setSelected(false);
        boxLeitor.setSelected(false);
        boxAdministrador.setSelected(false);
    }

    /**
     * Limpa o campo de nova informação e a área de exibição de erros.
     */
    private void limparCampos(){
        this.labelError.setText("");
        this.labelNovaInformacao.setText("");
    }

    /**
     * Verifica se o ID inserido no campo de nova informação já existe no sistema.
     *
     * @return true se o ID for único, false se já existir no sistema.
     */
    private boolean verificaID(){
        try {
            if(MasterDAO.getFuncionarioDAO().getFuncionarios().contains(MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.labelNovaInformacao.getText())))){
                this.labelError.setText("ID existente no sistema");
                return false;
            }
        } catch (FuncionarioException e) {
            this.labelError.setText(e.getMessage());
        }
        return true;
    }

    /**
     * Executa a ação de exclusão de um usuário do sistema.
     * Exibe informações do usuário encontrado e remove-o do sistema.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a exclusão.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonExcluirUsuarioclicked (ActionEvent event) {
        try {
            verificaTexto();
            if(boxAdministrador.isSelected()){
                if(verificaClasse().equals("com.uefs.gerenciadorparabibliotecas.model.Administrador")){
                    Funcionario funcionario = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                    this.LabelUsuarioEncontrado.setText("Nome: " + funcionario.getNome() + " / Endereço: " + funcionario.getEndereco() + " / Telefone: " + funcionario.getNumeroDeTelefone());
                    this.LabelUsuarioEncontrado2.setText("ID: " + funcionario.getUserID() + " / Senha: " + funcionario.getSenha() + " / Cargo: Administrador");
                    labelError.setText("O administrador "+ funcionario.getNome() + " foi removido do sistema");
                    limparBox();

                    MasterDAO.getFuncionarioDAO().deletar(funcionario);

                } else {
                    labelError.setText("ID inválido");
                }
            } else if(boxBibliotecario.isSelected()){
                if(verificaClasse().equals("com.uefs.gerenciadorparabibliotecas.model.Bibliotecario")){
                    Funcionario funcionario = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                    this.LabelUsuarioEncontrado.setText("Nome: " + funcionario.getNome() + " / Endereço: " + funcionario.getEndereco() + " / Telefone: " + funcionario.getNumeroDeTelefone());
                    this.LabelUsuarioEncontrado2.setText("ID: " + funcionario.getUserID() + " / Senha: " + funcionario.getSenha() + " / Cargo: Bibliotecário(a)" );
                    labelError.setText("O bibliotecario "+ funcionario.getNome() + " foi removido do sistema");
                    limparBox();

                    MasterDAO.getFuncionarioDAO().deletar(funcionario);

                } else {
                    labelError.setText("ID inválido");
                }
            } else if(boxLeitor.isSelected()){
                Leitor leitor = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                this.LabelUsuarioEncontrado.setText("Nome: " + leitor.getNome() + " / Endereço: " + leitor.getEndereco() + " / Telefone: " + leitor.getNumeroDeTelefone());
                this.LabelUsuarioEncontrado2.setText("ID: " + leitor.getUserID() + " / Senha: " + leitor.getSenha());
                labelError.setText("O leitor "+ leitor.getNome() + " foi removido do sistema");
                limparBox();

                MasterDAO.getLeitorDAO().deletar(leitor);

            } else {
                labelError.setText("Escolha uma categoria de Usuário");
            }
        } catch (LeitorException | FuncionarioException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de busca de um usuário no sistema.
     * Exibe informações do usuário encontrado.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a busca.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonBuscarUsuarioclicked(ActionEvent event) {
        try {
            verificaTexto();
            if(boxAdministrador.isSelected()){
                if(verificaClasse().equals("com.uefs.gerenciadorparabibliotecas.model.Administrador")){
                    Funcionario funcionario = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                    this.LabelUsuarioEncontrado.setText("Nome: " + funcionario.getNome() + " / Endereço: " + funcionario.getEndereco() + " / Telefone: " + funcionario.getNumeroDeTelefone());
                    this.LabelUsuarioEncontrado2.setText("ID: " + funcionario.getUserID() + " / Senha: " + funcionario.getSenha() + " / Cargo: Administrador");
                    labelError.setText("");
                    limparBox();
                } else {
                    labelError.setText("ID inválido");
                }
            } else if(boxBibliotecario.isSelected()){
                if(verificaClasse().equals("com.uefs.gerenciadorparabibliotecas.model.Bibliotecario")){
                    Funcionario funcionario = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                    this.LabelUsuarioEncontrado.setText("Nome: " + funcionario.getNome() + " / Endereço: " + funcionario.getEndereco() + " / Telefone: " + funcionario.getNumeroDeTelefone());
                    this.LabelUsuarioEncontrado2.setText("ID: " + funcionario.getUserID() + " / Senha: " + funcionario.getSenha() + " / Cargo: Bibliotecário(a)" );
                    labelError.setText("");
                    limparBox();
                } else {
                    labelError.setText("ID inválido");
                }
            } else if(boxLeitor.isSelected()){
                Leitor leitor = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                this.LabelUsuarioEncontrado.setText("Nome: " + leitor.getNome() + " / Endereço: " + leitor.getEndereco() + " / Telefone: " + leitor.getNumeroDeTelefone());
                this.LabelUsuarioEncontrado2.setText("ID: " + leitor.getUserID() + " / Senha: " + leitor.getSenha());
                labelError.setText("");
                limparBox();
            } else {
                labelError.setText("Escolha uma categoria de Usuário");
            }
        } catch (LeitorException | FuncionarioException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do endereço de um usuário.
     * Atualiza o endereço do usuário no sistema.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a atualização.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonEnderecoClicked(ActionEvent event) {
        try {
            verificaTexto2();
            verificaTexto();
            if(boxLeitor.isSelected()){
                Leitor novo = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setEndereco(this.labelNovaInformacao.getText());
                MasterDAO.getLeitorDAO().atualizar(novo);
                limparCampos();
                limparBox();
            } else if(boxBibliotecario.isSelected() || boxAdministrador.isSelected()){
                Funcionario novo = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setEndereco(this.labelNovaInformacao.getText());
                MasterDAO.getFuncionarioDAO().atualizar(novo);
                limparCampos();
                limparBox();
            }
        } catch (FuncionarioException | LeitorException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do ID de um usuário.
     * Atualiza o ID do usuário no sistema.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a atualização.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonIDClicked(ActionEvent event) {
        //System.out.println(MasterDAO.getFuncionarioDAO().getFuncionarios());
        try {
            verificaTexto2();
            verificaTexto();
            verificaTexto3();
            if(boxLeitor.isSelected()){
                Leitor novo = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setUserID(Integer.parseInt(this.labelNovaInformacao.getText()));
                MasterDAO.getLeitorDAO().atualizar(novo);
                limparCampos();
                limparBox();
            } else if(boxBibliotecario.isSelected() || boxAdministrador.isSelected()){
                if (verificaID()){
                    Funcionario novo = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                    novo.setUserID(Integer.parseInt(this.labelNovaInformacao.getText()));
                    MasterDAO.getFuncionarioDAO().atualizar(novo);
                    limparCampos();
                    limparBox();
                }
            }
        } catch (FuncionarioException | LeitorException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do nome de um usuário.
     * Atualiza o nome do usuário no sistema.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a atualização.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonNomeClicked(ActionEvent event) {
        try {
            verificaTexto2();
            verificaTexto();
            verificaTexto4();
            if(boxLeitor.isSelected()){
                Leitor novo = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setNome(this.labelNovaInformacao.getText());
                MasterDAO.getLeitorDAO().atualizar(novo);
                limparCampos();
                limparBox();
            } else if(boxBibliotecario.isSelected() || boxAdministrador.isSelected()){
                Funcionario novo = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setNome(this.labelNovaInformacao.getText());
                MasterDAO.getFuncionarioDAO().atualizar(novo);
                limparCampos();
                limparBox();
            }
        } catch (FuncionarioException | LeitorException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização da senha de um usuário.
     * Atualiza a senha do usuário no sistema.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a atualização.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonSenhaClicked(ActionEvent event) {
        try {
            verificaTexto2();
            verificaTexto();
            if(boxLeitor.isSelected()){
                Leitor novo = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setSenha(this.labelNovaInformacao.getText());
                MasterDAO.getLeitorDAO().atualizar(novo);
                limparCampos();
                limparBox();
            } else if(boxBibliotecario.isSelected() || boxAdministrador.isSelected()){
                Funcionario novo = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setSenha(this.labelNovaInformacao.getText());
                MasterDAO.getFuncionarioDAO().atualizar(novo);
                limparCampos();
                limparBox();
            }
        } catch (FuncionarioException | LeitorException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Executa a ação de atualização do número de telefone de um usuário.
     * Atualiza o número de telefone do usuário no sistema.
     * Lança exceções LeitorException ou FuncionarioException se ocorrerem erros durante a atualização.
     *
     * @param event O evento acionado.
     */
    @FXML
    void onButtonTelefoneClicked(ActionEvent event) {
        //System.out.println(MasterDAO.getLeitorDAO().getLeitores());
        try {
            verificaTexto2();
            verificaTexto();
            verificaTexto3();
            if(boxLeitor.isSelected()){
                Leitor novo = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setNumeroDeTelefone(this.labelNovaInformacao.getText());
                MasterDAO.getLeitorDAO().atualizar(novo);
                limparCampos();
                limparBox();
            } else if(boxBibliotecario.isSelected() || boxAdministrador.isSelected()){
                Funcionario novo = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.textFieldIDUsuario.getText()));
                novo.setNumeroDeTelefone(this.labelNovaInformacao.getText());
                MasterDAO.getFuncionarioDAO().atualizar(novo);
                limparCampos();
                limparBox();
            }
        } catch (FuncionarioException | LeitorException e) {
            this.labelError.setText(e.getMessage());
        }
    }
}
