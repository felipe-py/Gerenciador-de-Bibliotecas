package com.uefs.gerenciadorparabibliotecas;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerEstatisticas implements Initializable {
    @FXML
    private Button btnbuscar;

    @FXML
    public void clicarEmBuscar (MouseEvent event) throws IOException {
        System.out.println("Buscar o histórico de um usuário");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }
}
