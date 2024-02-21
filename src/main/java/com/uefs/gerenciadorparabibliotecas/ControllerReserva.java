package com.uefs.gerenciadorparabibliotecas;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controlador responsável pela lógica de reserva de livros na interface gráfica.
 * Este controlador gerencia as ações relacionadas à criação, busca e exclusão de reservas de livros no sistema.
 */
public class ControllerReserva {

    @FXML
    private TextField IDLivroLabell;

    @FXML
    private TextField IDUsuarioLabel;

    @FXML
    private TextField IDreservaTextField;

    @FXML
    private Button buttonDeletar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private Button buttonVerificar;

    @FXML
    private Label labelReserva1;

    @FXML
    private Label labelReserva2;

    @FXML
    private Label labelError;

    /**
     * Verifica se os campos de entrada contêm apenas números e não estão vazios.
     * Este método é utilizado para garantir que os IDs do livro e do usuário inseridos sejam válidos antes de criar uma reserva.
     * @throws LivroException Se o ID do livro inserido não for válido (não numérico ou vazio).
     * @throws LeitorException Se o ID do usuário inserido não for válido (não numérico ou vazio).
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
     * Verifica se o campo de ID da reserva não está vazio.
     * Este método é utilizado para garantir que o ID da reserva inserido seja válido antes de realizar a busca ou exclusão.
     * @throws ReservaException Se o ID da reserva inserido estiver vazio.
     */
    private void verificacao2() throws ReservaException {
        if (this.IDreservaTextField.getText().isEmpty()) {
            throw new ReservaException(ReservaException.SEARCH, Integer.valueOf(this.IDreservaTextField.getText()));
        }
    }

    /**
     * Limpa todos os campos de entrada e a mensagem de erro na interface gráfica.
     * Este método é chamado para limpar os campos após uma operação bem-sucedida ou para resetar os campos após um erro.
     */
    private void limparCampos(){
        this.labelError.setText("");
        this.IDreservaTextField.setText("");
        this.IDLivroLabell.setText("");
        this.IDUsuarioLabel.setText("");
    }

    /**
     * Método chamado quando o botão de exclusão de reserva é clicado.
     * Ele verifica se o ID da reserva inserido é válido e, em seguida, exclui a reserva correspondente.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonDeletarClicked(ActionEvent event) {
        try {
            verificacao2();
            Reserva reservaDeletada = MasterDAO.getReservaDAO().procurarPorID(Integer.parseInt(this.IDreservaTextField.getText()));
            MasterDAO.getReservaDAO().deletar(MasterDAO.getReservaDAO().procurarPorID(Integer.parseInt(this.IDreservaTextField.getText())));
            limparCampos();
            this.labelReserva1.setText("Reserva Deletada => ID: " + reservaDeletada.getReservaID() + " / Usuário: " + reservaDeletada.getLeitorReservador().getNome() + " / Título Livro: " + reservaDeletada.getLivroReservado().getTitulo());
            this.labelReserva2.setText("");
        } catch (ReservaException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de salvamento de reserva é clicado.
     * Ele verifica se os IDs do livro e do usuário inseridos são válidos e, em seguida, cria uma nova reserva no sistema.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonSalvarClicked(ActionEvent event) {
        try {
            verificacao();
            Reserva reservaCriada = MasterDAO.getReservaDAO().criar(new Reserva(MasterDAO.getLeitorDAO().procurarPorID(Integer.parseInt(this.IDUsuarioLabel.getText())),
                    MasterDAO.getLivroDAO().procurarPorID(Integer.parseInt(this.IDLivroLabell.getText()))));
            this.labelReserva1.setText("Reserva Salva => ID: " + reservaCriada.getReservaID() + " / Usuário: " + reservaCriada.getLeitorReservador().getNome() + " / Título Livro: " + reservaCriada.getLivroReservado().getTitulo());
            this.labelReserva2.setText("ID Usuário: " + reservaCriada.getLeitorReservador().getUserID() + " / ID Livro:" + reservaCriada.getLivroReservado().getLivroID());
            limparCampos();
        } catch (LeitorException | LivroException e) {
            this.labelError.setText(e.getMessage());
        }
    }

    /**
     * Método chamado quando o botão de verificação de reserva é clicado.
     * Ele verifica se o ID da reserva inserido é válido e, em seguida, busca e exibe informações sobre a reserva correspondente.
     * @param event O evento de clique do botão.
     */
    @FXML
    void onButtonVerificarClicked(ActionEvent event) {
        try {
            verificacao2();
            Reserva reservaBuscada = MasterDAO.getReservaDAO().procurarPorID(Integer.parseInt(this.IDreservaTextField.getText()));
            this.labelReserva1.setText("Reserva Deletada => ID: " + reservaBuscada.getReservaID() + " / Usuário: " + reservaBuscada.getLeitorReservador().getNome() + " / Título Livro: " + reservaBuscada.getLivroReservado().getTitulo());
            this.labelReserva2.setText("ID Usuário: " + reservaBuscada.getLeitorReservador().getUserID() + " / ID Livro:" + reservaBuscada.getLivroReservado().getLivroID());
            limparCampos();
        } catch (ReservaException e) {
            this.labelError.setText(e.getMessage());
        }
    }
}
