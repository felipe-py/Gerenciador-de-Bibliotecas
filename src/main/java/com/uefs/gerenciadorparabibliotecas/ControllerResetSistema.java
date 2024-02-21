package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ControllerResetSistema {

    @FXML
    private CheckBox boxRemoveEmprestimos;

    @FXML
    private CheckBox boxRemoveFuncionarios;

    @FXML
    private CheckBox boxRemoveLeitores;

    @FXML
    private CheckBox boxRemoveLivros;

    @FXML
    private CheckBox boxRemoveReservas;

    @FXML
    private CheckBox boxRemoveTudo;

    @FXML
    private Button buttonRemover;

    @FXML
    private Label labelError;

    @FXML
    void onBoxRemoveEmprestimosClicked(ActionEvent event) {
    }

    @FXML
    void onBoxRemoveFuncionariosClicked(ActionEvent event) {
    }

    @FXML
    void onBoxRemoveLeitoresClicked(ActionEvent event) {
    }

    @FXML
    void onBoxRemoveLivrosClicked(ActionEvent event) {
    }

    @FXML
    void onBoxRemoveReservasClicked(ActionEvent event) {
    }

    @FXML
    void onBoxRemoveTudoClicked(ActionEvent event) {
    }

    private void limparSelecao(){
        this.boxRemoveEmprestimos.setSelected(false);
        this.boxRemoveFuncionarios.setSelected(false);
        this.boxRemoveLeitores.setSelected(false);
        this.boxRemoveReservas.setSelected(false);
        this.boxRemoveLivros.setSelected(false);
        this.boxRemoveTudo.setSelected(false);
    }

    @FXML
    void onButtonRemoverclicked(ActionEvent event) {
        if (boxRemoveLeitores.isSelected()) {
            MasterDAO.getLeitorDAO().resetar();
        }
        if (boxRemoveEmprestimos.isSelected()) {
            MasterDAO.getEmprestimoDAO().resetar();
        }
        if (boxRemoveFuncionarios.isSelected()) {
            MasterDAO.getFuncionarioDAO().resetar();
        }
        if (boxRemoveReservas.isSelected()) {
            MasterDAO.getReservaDAO().resetar();
        }
        if (boxRemoveLivros.isSelected()) {
            MasterDAO.getLivroDAO().resetar();
        }
        if (boxRemoveTudo.isSelected()) {
            MasterDAO.getLeitorDAO().resetar();
            MasterDAO.getEmprestimoDAO().resetar();
            MasterDAO.getFuncionarioDAO().resetar();
            MasterDAO.getReservaDAO().resetar();
            MasterDAO.getLivroDAO().resetar();
        }
        if (!boxRemoveLeitores.isSelected() && !boxRemoveEmprestimos.isSelected() && !boxRemoveFuncionarios.isSelected() && !boxRemoveReservas.isSelected() && !boxRemoveLivros.isSelected() && !boxRemoveTudo.isSelected()) {
            labelError.setText("Selecione ao menos um campo");
        } else {
            labelError.setText("Remoção feita com sucesso");
            limparSelecao();
        }
    }
}
