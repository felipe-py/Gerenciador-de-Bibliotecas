module com.uefs.gerenciadorparabibliotecas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.uefs.gerenciadorparabibliotecas.dao.livro;
    opens com.uefs.gerenciadorparabibliotecas.dao.leitor;
    opens com.uefs.gerenciadorparabibliotecas.dao.emprestimo;
    opens com.uefs.gerenciadorparabibliotecas.dao.reserva;
    opens com.uefs.gerenciadorparabibliotecas.dao.funcionario;
    opens com.uefs.gerenciadorparabibliotecas.model;

    opens com.uefs.gerenciadorparabibliotecas to javafx.fxml;
    exports com.uefs.gerenciadorparabibliotecas;
}