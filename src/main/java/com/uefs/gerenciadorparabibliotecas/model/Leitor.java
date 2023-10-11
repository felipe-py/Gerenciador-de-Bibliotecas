package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe Model do leitor
 */
public class Leitor extends Pessoa {
    /**
     * diasDemulta: inteiro que armazena os dias que o usuário permanecerá multado
     * dataDoFimDaMulta: LocalDate que armazena o dia em a multa do usuário acaba
     * leitorBanido: booleano que informa se o usuário está ou não banido
     * históricoEmpréstimos: lista que armazena todos os empréstimos do leitor
     */
    private int diasDeMulta;
    private LocalDate dataDoFimDaMulta;

    /**
     * Acessa o status do leitor
     * @return status do leitor
     */
    public boolean isLeitorBanido() {
        return leitorBanido;
    }

    /**
     * Modifica o status da conta do leitor
     * @param leitorBanido novo valor que será aplicado ao status da conta do leitor
     */
    public void setLeitorBanido(boolean leitorBanido) {
        this.leitorBanido = leitorBanido;
    }

    private boolean leitorBanido = false;
    private List<Emprestimo> historicoEmprestimos;

    /**
     * Método para adcionar um empréstimo a lista de empréstimos do leitor
     * @param emprestimo empréstimo que o leitor realizou
     */
    public void adicionarEmprestimo(Emprestimo emprestimo){
        this.historicoEmprestimos.add(emprestimo);
    }

    /**
     * Construtor do leitor que reutiliza a classe Pessoa, sua superclasse, os atributos relacionados a multa e
     * histórico de empréstimo são adicionados
     * @param novoNome
     * @param novoEndereco
     * @param novaSenha
     * @param novoNumeroDeTelefone
     * @param novoUserID
     * O método de validação do leitor é chamado para averiguar as informações enviadas
     */
    public Leitor(String novoNome, String novoEndereco, String novaSenha, String novoNumeroDeTelefone, int novoUserID) throws LeitorException{
        super(novoNome, novoEndereco, novaSenha, novoNumeroDeTelefone, novoUserID);
        this.diasDeMulta = 0;
        this.historicoEmprestimos = new ArrayList<>();
        this.dataDoFimDaMulta = LocalDate.now();
        validacaoLeitor();
    }

    /**
     * Acessa a data do fim da multa do leitor
     * @return data em que a multa do leitor irá terminar
     */
    public LocalDate getDataDoFimDaMulta() {
        return dataDoFimDaMulta;
    }

    /**
     * Modifica a data do fim da multa do leitor
     * @param dataDoFimDaMulta nova data para o fim da multa do leitor
     */
    public void setDataDoFimDaMulta(LocalDate dataDoFimDaMulta) {
        this.dataDoFimDaMulta = dataDoFimDaMulta;
    }

    /**
     * Acessa a quantidade de dias de multa do leitor
     * @return quantidade de dias em que o leitor irá permanecer multado
     */
    public int getDiasDeMulta() {
        return diasDeMulta;
    }

    /**
     * Acessa a lista de empréstimos do leitor
     * @return lista com o histórico de empréstimos do leitor
     */
    public List<Emprestimo> getEmprestimos() {
        return historicoEmprestimos;
    }

    /**
     * Modifica a quantidade de dias de multa do leitor
     * @param diasDeMulta nova quantidade de dias de multa do leitor
     */
    public void setDiasDeMulta(int diasDeMulta) {
        this.diasDeMulta = diasDeMulta;
    }

    /**
     * Modifica a lista de empréstimos do leitor
     * @param emprestimos nova lista de empréstimos
     */
    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.historicoEmprestimos = emprestimos;
    }

    /**
     * Método toString do leitor para exibir seus parâmetros, herdado da classe Pessoa
     * @return String com os atributos do leitor
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Método que realiza as principais validações para criação do leitor, é observado se o nome possui somente
     * letras em sua formação, se o número de telefone é composto apenas por números ou se o ID enviado já está
     * cadastrado n sistema
     * @throws LeitorException caso qualquer uma das validações não passe
     */
    private void validacaoLeitor() throws LeitorException {
        if(!(this.getNome().matches("^[a-zA-Z\\s]*$"))) {
            throw new LeitorException(LeitorException.INVALID_INFO,this.getNome());
        }
        if(!(this.getNumeroDeTelefone().matches("^[0-9\\s-]*$"))){
            throw new LeitorException(LeitorException.INVALID_INFO, this.getNumeroDeTelefone());
        }
        if(MasterDAO.getLeitorDAO().getLeitores().contains(this.getUserID())){
            throw new LeitorException(LeitorException.INVALID_INFO, this.getUserID());
        }
        if(this.getNome().isEmpty() || this.getNumeroDeTelefone().isEmpty() || this.getSenha().isEmpty() ||
        this.getEndereco().isEmpty()){
            throw new LeitorException(LeitorException.EMPITY_INFO, this);
        }
    }
}
