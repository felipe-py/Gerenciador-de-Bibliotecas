package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.model.Leitor;

import java.util.ArrayList;
import java.util.List;

public class LeitorDAOList implements LeitorDAO {

    private List<Leitor> leitores = new ArrayList<>();

    public LeitorDAOList() {
        this.leitores = leitores;
    }

    public List<Leitor> getLeitores() {
        return leitores;
    }

    @Override
    public Leitor criar(Leitor leitor){
        this.leitores.add(leitor);
        return leitor;
    }

    @Override
    public void deletar(Leitor leitor) throws LeitorException {
        boolean remove = this.leitores.remove(leitor);
        if (!remove) {
            throw new LeitorException(LeitorException.DELETE, leitor);
        }
    }

    @Override
    public void resetar() {
        this.leitores = new ArrayList<>();
    }

    @Override
    public Leitor procurarPorID(Integer id) {
        for (Leitor leitor : this.leitores) {
            if (leitor.getUserID().equals(id)) {
                return leitor;
            }
        }
        return null;
    }
}
