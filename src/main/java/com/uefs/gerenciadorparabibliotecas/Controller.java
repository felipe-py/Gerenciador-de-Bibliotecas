package com.uefs.gerenciadorparabibliotecas;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label mensagemBoasVindas;

    @FXML
    private Button btnRegistrarLivro;
    @FXML
    private Button btnEditarLivro;
    //@FXML
    //private Button btnRemoverLivro;
    @FXML
    private Button btnPesquisarLivro;
    @FXML
    private Button btnEmprestar;
    //@FXML
    //private Button btnDevolver;
    @FXML
    private Button btnReservar;
    //@FXML
    //private Button btnRenovarEmprestimo;
    @FXML
    private Button btnCadastrarUsuario;
    //@FXML
    //private Button btnBloquearUsuario;
    @FXML
    private Button btnEditarUsuario;
    @FXML
    private Button btnEstatisticas;


    public void initialize (URL url, ResourceBundle rb) {
        //
        //mandarMensagem("Lucas");
    }

    public void mandarMensagem (String nome) {
        //Mensagem para confirmar qual usuario esta logado no terminal
        //System.out.println(nome + " esta logado");
        mensagemBoasVindas.setText("Seja Bem-vindo(a) " + nome);

    }

    public void clicarEmRegistrarLivro (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewRegistroLivros.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Registrar um livro");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerRegistroLivros controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();

    }

    public void clicarEmPesquisarLivro (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewPesquisaLivros.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Pesquisar livros");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerPesquisaLivros controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();

    }


    public void clicarEmEditarLivro (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewEditarLivros.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Editar um livro");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerEditarLivro controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();

    }

    public void clicarEmEmprestar (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewEmprestimos.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Emprestimo");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerEmprestimos controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();

    }


    public void clicarReservar (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewReservas.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Reservar um livro");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerReserva controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);

        stageSecundario.show();

    }
    public void clicarCadastrarUsuario (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewRegistroUsuarios.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Cadastro de Novo Usuario");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerRegistroUsuarios controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();
    }
    public void clicarEmEditarUsuario (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewEditarUsuarios.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Editar usuário");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerEditarUsuarios controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();

    }

    public void clicarEmEstatisticas (MouseEvent event) throws IOException {

        Stage stageSecundario = new Stage();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("viewEstatisticas.fxml"));
        Parent root1 = loader1.load();

        Scene scene1 = new Scene(root1);
        //scene.getStylesheets().add("/styles/Styles.css");

        stageSecundario.setTitle("Estatísticas");
        File file = new File("src/main/java/com/uefs/gerenciadorparabibliotecas/icone.png");
        Image icone = new Image(file.toURI().toString());
        stageSecundario.getIcons().add(icone);
        stageSecundario.setScene(scene1);
        ControllerEstatisticas controllerRegistro = loader1.getController();

        stageSecundario.setScene(scene1);
        //stage.setResizable(false);
        //stage.setWidth(800);
        //stage.setHeight(600);
        //controller.desativarBotaoUsuarios();

        stageSecundario.show();
    }
    public void desativarRegistrarLivro () {
        btnRegistrarLivro.setDisable(true);
    }
    public void desativarEditarLivro () {
        btnEditarLivro.setDisable(true);
    }
    public void desativarPesquisarLivro () {
        btnPesquisarLivro.setDisable(true);
    }
    public void desativarEmprestar () {
        btnEmprestar.setDisable(true);
    }
    public void desativarReservar () {
        btnReservar.setDisable(true);
    }
    public void desativarCadastrarUsuario () {
        btnCadastrarUsuario.setDisable(true);
    }
    public void desativarEditarUsuario () {
        btnEditarUsuario.setDisable(true);
    }
    public void desativarEstatisticas () {
        btnEstatisticas.setDisable(true);
    }

    public void loadPage (String panel) {
        Parent raiz = null;

        try {
            raiz = FXMLLoader.load(getClass().getResource(panel + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(raiz);
    }
}