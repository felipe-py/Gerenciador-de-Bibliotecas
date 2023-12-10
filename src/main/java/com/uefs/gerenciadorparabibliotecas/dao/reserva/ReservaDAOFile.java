package com.uefs.gerenciadorparabibliotecas.dao.reserva;

import com.uefs.gerenciadorparabibliotecas.exeptions.reservaExcetions.ReservaException;
import com.uefs.gerenciadorparabibliotecas.model.Reserva;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD e acesso aos dados das reservas em arquivo
 */
public class ReservaDAOFile implements ReservaDAO{
    List<Reserva> reservas;
    private Integer novoID;

    private String filename;

    /**
     * Contrutor do DAO reserva responsável por receber o caminho até o arquivo de armazenamento,
     * realizar a leitura do arquivo para a lista em memória com o método ler e definição de inicialização
     * do ID para as reservas
     * @param filename caminho até a pásta em que se encontra a base de dados do projeto
     */
    public ReservaDAOFile(String filename) {
        this.filename = filename;
        this.reservas = new ArrayList<>();
        this.reservas = ler();
        if(this.reservas.isEmpty()){
            this.novoID = 0;
        }
        else{
            this.novoID = this.reservas.get(this.reservas.size() - 1).getReservaID() + 1;
        }
    }

    /**
     * Método utilizado para salvar a lista que contém todos as reservas armazenados em memória
     * para o arquivo em disco.
     * @param reservas Lista que possui todos as reservas em memória
     */
    public void salvar(List<Reserva> reservas) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.filename));
            for (Reserva reserva : reservas) {
                file.writeObject(reserva);
            }
            file.close();
        } catch (IOException e) {
        }
    }

    /**
     * Método para leitura do arquivo relacionado as reservas, todos as reservas encontrados no
     * arquivo são colocados em uma lista do tipo Reserva.
     * @return lista contendo todos as reservas encontrados no arquivo
     */
    public List<Reserva> ler() {
        List<Reserva> reservas = new ArrayList<>();
        try {
            Reserva reserva = null;
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(this.filename));
            do {
                reserva = (Reserva) file.readObject();
                if (reserva != null) {
                    reservas.add(reserva);
                }
            } while (reserva != null);
            file.close();
        } catch (IOException | ClassNotFoundException e) {
        }
        return reservas;
    }

    @Override
    public Reserva criar(Reserva reserva) {
        reserva.setReservaID(this.novoID);
        this.novoID++;
        this.reservas.add(reserva);
        reserva.getLivroReservado().setStatusReserva(true);  // Altera o status de reserva do livro
        salvar(this.reservas);
        return reserva;
    }

    @Override
    public void deletar(Reserva reserva) throws ReservaException {
        boolean remove = this.reservas.remove(reserva);
        if (!remove) {
            throw new ReservaException(ReservaException.DELETE, reserva);
        }
        salvar(this.reservas);
    }

    @Override
    public void resetar() {
        this.reservas = new ArrayList<>();
        this.novoID = 0;
        salvar(this.reservas);
    }

    @Override
    public Reserva atualizar(Reserva obj) {
        return null;
    }

    @Override
    public Reserva procurarPorID(Integer id) throws ReservaException{
        for (Reserva reserva : this.reservas) {
            if (reserva.getReservaID() == id) {
                return reserva;
            }
        }
        throw new ReservaException(ReservaException.SEARCH, id);
    }

    /**
     * Retorna todas as reservas cadastradas no sistema
     * @return lista com reservas
     */
    @Override
    public List<Reserva> getReservas() {
        return reservas;
    }
}
