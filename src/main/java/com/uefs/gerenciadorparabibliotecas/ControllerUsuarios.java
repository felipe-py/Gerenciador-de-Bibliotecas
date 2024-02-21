package com.uefs.gerenciadorparabibliotecas;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador responsável pela lógica de interação com a interface de usuário relacionada aos usuários.
 * Este controlador gerencia as ações relacionadas ao registro, bloqueio e login de usuários no sistema.
 */
public class ControllerUsuarios implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button btnregistrar;
    @FXML
    private Button btnbloquear;
    @FXML
    private Button btnlogar;

    public void initialize (URL url, ResourceBundle rb) {
        //
    }

    /**
     * Método chamado quando o botão de registro de usuário é clicado.
     * Ele abre uma nova janela para o registro de usuários.
     * @param event O evento de clique do mouse.
     * @throws IOException Se houver um erro ao carregar a interface gráfica de registro de usuários.
     */
    @FXML
    public void clicarEmRegistrar (MouseEvent event) throws IOException {
        //System.out.println("Registrar um novo usuario");
        //loadPage("painelLivros");
        //File arq = new File("src/painelLivros.fxml");
        //AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));

        //bp.setCenter(view);

        Stage stageRegistro = new Stage();
        FXMLLoader pagina = new FXMLLoader();

        Pane painelLogin = pagina.load(getClass().getResource("viewRegistroUsuarios.fxml").openStream());
        stageRegistro.setScene(new Scene (painelLogin, 600, 400));
        stageRegistro.show();
    }

    /**
     * Método chamado quando o botão de bloqueio de usuário é clicado.
     * Ele abre uma nova janela para a pesquisa e bloqueio de usuários.
     * @param event O evento de clique do mouse.
     * @throws IOException Se houver um erro ao carregar a interface gráfica de pesquisa e bloqueio de usuários.
     */
    @FXML
    public void clicarEmBloquear (MouseEvent event) throws IOException {
        //System.out.println("Bloquear um usuario");
        //loadPage("painelEmprestimos");
        //loadPage("painelEmprestimos");
        //File arq = new File("src/painelEmprestimos.fxml");
        //AnchorPane view = FXMLLoader.load(getClass().getResource(arq.toURI().toString()));


        //bp.setCenter(view);

        Stage stageRegistro = new Stage();
        FXMLLoader pagina = new FXMLLoader();

        Pane painelLogin = pagina.load(getClass().getResource("viewPesquisaUsuarios.fxml").openStream());
        stageRegistro.setScene(new Scene (painelLogin, 600, 400));
        stageRegistro.show();

    }

    /**
     * Método chamado quando o botão de login é clicado.
     * Ele abre uma nova janela para o login de usuários.
     * @param event O evento de clique do mouse.
     * @throws IOException Se houver um erro ao carregar a interface gráfica de login.
     */
    @FXML
    public void clicarEmLogar (MouseEvent event) throws IOException {
        //System.out.println("Logar");

        Stage stageRegistro = new Stage();
        FXMLLoader pagina = new FXMLLoader();

        Pane painelLogin = pagina.load(getClass().getResource("PainelLogin.fxml").openStream());
        stageRegistro.setScene(new Scene (painelLogin, 200, 400));
        stageRegistro.show();
    }

    /*private void loadPage (String panel) {
        Parent raiz = null;

        try {
            raiz = FXMLLoader.load(getClass().getResource(panel + ".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bp.setCenter(raiz);



    }*/

}