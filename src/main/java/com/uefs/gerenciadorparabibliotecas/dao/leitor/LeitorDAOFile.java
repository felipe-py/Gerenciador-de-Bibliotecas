package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorDAOFile implements LeitorDAO{
    private List<Leitor> leitores;
    private String filename;

    public LeitorDAOFile(String filename) {
        this.filename = filename;
        this.leitores = new ArrayList<>();
        this.leitores = ler();
    }

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
