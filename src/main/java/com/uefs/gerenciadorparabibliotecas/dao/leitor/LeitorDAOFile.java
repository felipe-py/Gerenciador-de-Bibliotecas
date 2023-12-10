package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD e acesso aos dados dos leitores em arquivo
 */

public class LeitorDAOFile implements LeitorDAO{
    private List<Leitor> leitores;
    private String filename;

    /**
     * Contrutor do DAO leitor responsável por receber o caminho até o arquivo de armazenamento e
     * realizar a leitura do arquivo para a lista em memória com o método ler.
     * @param filename caminho até a pásta em que se encontra a base de dados do projeto
     */
    public LeitorDAOFile(String filename) {
        this.filename = filename;
        this.leitores = new ArrayList<>();
        this.leitores = ler();
    }

    /**
     * Método utilizado para salvar a lista que contém todos os leitores armazenados em memória
     * para o arquivo em disco.
     * @param leitores Lista que possui todos os leitores em memória
     */
    public void salvar(List<Leitor> leitores) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(this.filename));
            for (Leitor leitor : leitores) {
                file.writeObject(leitor);
            }
            file.close();
        } catch (IOException e) {
        }
    }

    /**
     * Método para leitura do arquivo relacionado aos leitores, todos os leitores encontrados no
     * arquivo são colocados em uma lista do tipo Leitor.
     * @return lista contendo todos os leitores encontrados no arquivo
     */
    public List<Leitor> ler() {
        List<Leitor> leitores = new ArrayList<>();
        try {
            Leitor leitor = null;
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(this.filename));
            do {
                leitor = (Leitor) file.readObject();
                if (leitor != null) {
                    leitores.add(leitor);
                }
            } while (leitor != null);
            file.close();
        } catch (IOException | ClassNotFoundException e) {
        }
        return leitores;
    }

    /**
     * Retorna todas os leitores cadastradas no sistema
     * @return lista com leitores
     */
    public List<Leitor> getLeitores() {
        return leitores;
    }

    @Override
    public Leitor criar(Leitor leitor){
        this.leitores.add(leitor);
        salvar(this.leitores);
        return leitor;
    }

    @Override
    public void deletar(Leitor leitor) throws LeitorException {
        boolean remove = this.leitores.remove(leitor);
        if (!remove) {
            throw new LeitorException(LeitorException.DELETE, leitor);
        }
        salvar(this.leitores);
    }

    @Override
    public void resetar() {
        this.leitores = new ArrayList<>();
        salvar(this.leitores);
    }

    @Override
    public Leitor procurarPorID(Integer id) throws LeitorException{
        for (Leitor leitor : this.leitores) {
            if (leitor.getUserID().equals(id)) {
                return leitor;
            }
        }
        throw new LeitorException(LeitorException.SEARCH, id);
    }

    @Override
    public Leitor atualizar(Leitor leitor) {
        int leitorID = leitor.getUserID();

        for (int i = 0; i < leitores.size(); i++) {
            Leitor leitorNaLista = leitores.get(i);
            if (leitorNaLista.getUserID() == leitorID) {
                leitores.set(i, leitor);
                salvar(this.leitores);
                return leitor;
            }
        }
        return null;
    }
}
