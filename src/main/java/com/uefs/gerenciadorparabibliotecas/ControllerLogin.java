package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Funcionario;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador responsável pela lógica de interação com a interface de login.
 * Este controlador gerencia os eventos de clique nos botões de login e as operações relacionadas.
 */
public class ControllerLogin implements Initializable {
    @FXML
    public Button btnentrarcomoUsuario;
    @FXML
    public Button btnentrarcomoBibliotecario;
    @FXML
    public Button btnentrarcomoAdministrador;
    @FXML
    public TextField usuario;
    @FXML
    public PasswordField senha;
    @FXML
    public Label mensagem;

    private Parent rootMain;

    //public int idLogado;


    /**
     * Método chamado quando o botão de login como usuário é clicado.
     * Ele verifica se o campo de texto do usuário está preenchido e se a senha corresponde ao usuário.
     * Se as condições forem atendidas, abre o painel principal para o usuário.
     * Se não, exibe uma mensagem de erro.
     * @param event O evento de clique do mouse.
     */
    public void clicarEmLogarComoUsuario (MouseEvent event) {
        if (!verificaCampoDeTexto()) {
            usuario.setText("");
            senha.setText("");
        };

        try {
            if (verificaCampoDeTexto()){
                Class<?> classeDoObjeto = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.usuario.getText())).getClass();
                String nomeDaClasse = classeDoObjeto.getName();

                if (nomeDaClasse.equals("com.uefs.gerenciadorparabibliotecas.model.Leitor")) {
                    //System.out.println(MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.usuario.getText())));
                    if (MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.usuario.getText())).getSenha().equals(senha.getText())) {
                        //System.out.println("Login aceito");
                        mensagem.setText("Login aceito");

                        Leitor leitor = MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.usuario.getText()));

                        Stage stageDoPainel = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewPainelPrincipal.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);
                        //scene.getStylesheets().add("/styles/Styles.css");

                        stageDoPainel.setTitle("Gerenciador");
                        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
                        Image icone = new Image(file.toURI().toString());
                        stageDoPainel.getIcons().add(icone);
                        stageDoPainel.setScene(scene);
                        Controller controller = loader.getController();
                        controller.mandarMensagem(leitor.getNome());

                        stageDoPainel.setScene(scene);
                        //stage.setResizable(false);
                        //stage.setWidth(800);
                        //stage.setHeight(600);
                        controller.desativarRegistrarLivro();
                        controller.desativarEditarLivro();
                        //controller.desativarPesquisarLivro();
                        controller.desativarEmprestar();
                        //controller.desativarReservar();
                        controller.desativarCadastrarUsuario();
                        controller.desativarEditarUsuario();
                        controller.desativarEstatisticas();

                        stageDoPainel.show();



                        Stage stageDoLogin = (Stage) btnentrarcomoAdministrador.getScene().getWindow();
                        stageDoLogin.close();

                    } else {
                        //System.out.println("Login recusado");
                        mensagem.setText("Login recusado");
                    }
                    //this.mensagem.setText("");
                } else {
                    this.mensagem.setText("ID não encontrado");
                }
            }
        } catch (LeitorException | IOException e) {
            this.mensagem.setText(e.getMessage());
        }
        //autoridade = 0;

    }

    /**
     * Método chamado quando o botão de login como bibliotecário é clicado.
     * Ele verifica se o campo de texto do usuário está preenchido e se a senha corresponde ao bibliotecário.
     * Se as condições forem atendidas, abre o painel principal para o bibliotecário.
     * Se não, exibe uma mensagem de erro.
     * @param event O evento de clique do mouse.
     */
    public void clicarEmLogarComoBibliotecario (MouseEvent event) {
        if (!verificaCampoDeTexto()) {
            usuario.setText("");
            senha.setText("");
        };

        try {
            if (verificaCampoDeTexto()){
                Class<?> classeDoObjeto = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText())).getClass();
                String nomeDaClasse = classeDoObjeto.getName();

                if (nomeDaClasse.equals("com.uefs.gerenciadorparabibliotecas.model.Bibliotecario")) {
                    //System.out.println(MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText())));
                    if (MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText())).getSenha().equals(senha.getText())) {
                        //System.out.println("Login aceito");
                        mensagem.setText("Login aceito");

                        Funcionario bibliotecario = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText()));

                        Stage stageDoPainel = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewPainelPrincipal.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);
                        //scene.getStylesheets().add("/styles/Styles.css");

                        stageDoPainel.setTitle("Gerenciador");
                        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
                        Image icone = new Image(file.toURI().toString());
                        stageDoPainel.getIcons().add(icone);
                        stageDoPainel.setScene(scene);
                        Controller controller = loader.getController();
                        controller.mandarMensagem(bibliotecario.getNome());

                        stageDoPainel.setScene(scene);
                        //stage.setResizable(false);
                        //stage.setWidth(800);
                        //stage.setHeight(600);
                        //controller.desativarRegistrarLivro();
                        controller.desativarEditarLivro();
                        //controller.desativarPesquisarLivro();
                        //controller.desativarEmprestar();
                        controller.desativarReservar();
                        controller.desativarCadastrarUsuario();
                        controller.desativarEditarUsuario();
                        controller.desativarEstatisticas();

                        stageDoPainel.show();



                        Stage stageDoLogin = (Stage) btnentrarcomoAdministrador.getScene().getWindow();
                        stageDoLogin.close();

                    } else {
                        //System.out.println("Login recusado");
                        mensagem.setText("Login recusado");
                    }
                    //this.mensagem.setText("");
                } else {
                    this.mensagem.setText("ID não encontrado");
                }
            }
        } catch (IOException | FuncionarioException e) {
            this.mensagem.setText(e.getMessage());
        }
        //autoridade = 0;

    }

    /**
     * Método chamado quando o botão de login como administrador é clicado.
     * Ele verifica se o campo de texto do usuário está preenchido e se a senha corresponde ao administrador.
     * Se as condições forem atendidas, abre o painel principal para o administrador.
     * Se não, exibe uma mensagem de erro.
     * @param event O evento de clique do mouse.
     */
    public void clicarEmLogarComoAdministrador (MouseEvent event) {
        if (!verificaCampoDeTexto()) {
            usuario.setText("");
            senha.setText("");
        };

        try {
            if (verificaCampoDeTexto()){
                Class<?> classeDoObjeto = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText())).getClass();
                String nomeDaClasse = classeDoObjeto.getName();

                if (nomeDaClasse.equals("com.uefs.gerenciadorparabibliotecas.model.Administrador")) {
                    //System.out.println(MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText())));
                    if (MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText())).getSenha().equals(senha.getText())) {
                        //System.out.println("Login aceito");
                        mensagem.setText("Login aceito");

                        Funcionario administrador = MasterDAO.getFuncionarioDAO().procurarPorID(Integer.parseInt(this.usuario.getText()));

                        Stage stageDoPainel = new Stage();

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewPainelPrincipal.fxml"));
                        Parent root = loader.load();

                        Scene scene = new Scene(root);
                        //scene.getStylesheets().add("/styles/Styles.css");

                        stageDoPainel.setTitle("Gerenciador");
                        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
                        Image icone = new Image(file.toURI().toString());
                        stageDoPainel.getIcons().add(icone);
                        stageDoPainel.setScene(scene);
                        Controller controller = loader.getController();
                        controller.mandarMensagem(administrador.getNome());

                        stageDoPainel.setScene(scene);
                        //stage.setResizable(false);
                        //stage.setWidth(800);
                        //stage.setHeight(600);
                        //controller.desativarRegistrarLivro();
                        //controller.desativarEditarLivro();
                        //controller.desativarPesquisarLivro();
                        //controller.desativarEmprestar();
                        //controller.desativarReservar();
                        //controller.desativarCadastrarUsuario();
                        //controller.desativarEditarUsuario();
                        //controller.desativarEstatisticas();

                        stageDoPainel.show();



                        Stage stageDoLogin = (Stage) btnentrarcomoAdministrador.getScene().getWindow();
                        stageDoLogin.close();

                    } else {
                        //System.out.println("Login recusado");
                        mensagem.setText("Login recusado");
                    }
                    //this.mensagem.setText("");
                } else {
                    this.mensagem.setText("ID não encontrado");
                }
            }
        } catch (IOException | FuncionarioException e) {
            this.mensagem.setText(e.getMessage());
        }
        //autoridade = 0;

    }

    /**
     * Verifica se o campo de texto do usuário e a senha estão preenchidos.
     * Se algum dos campos estiver vazio, exibe uma mensagem de erro.
     * @return true se os campos estiverem preenchidos, false caso contrário.
     */
    private Boolean verificaCampoDeTexto(){
        if (this.usuario.getText().isEmpty() || this.senha.getText().isEmpty()){
            this.mensagem.setText("Preencha todos os campos");
            return false;
       // } if (!(this.usuario.getText().matches("^[0-9]*$")) || !(this.senha.getText().matches("^[0-9\\s-]*$"))){
       //     this.mensagem.setText(this.mensagem.getText() + " Caractere proibido inserido");
       //     return false;
        }
        return true;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }
}
