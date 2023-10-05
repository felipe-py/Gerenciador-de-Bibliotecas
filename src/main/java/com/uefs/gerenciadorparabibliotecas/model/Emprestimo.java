package com.uefs.gerenciadorparabibliotecas.model;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe Model do empréstimo
 */
public class Emprestimo {
    /**
     * empréstimoID: inteiro que armazena a indentificação do empréstimo
     * dataEmprestimo: localDate usado para definir a data em que o empréstimo iniciou
     * dataDevolucaoEsperada: localDate usado para definir a data em que se espera o fim do empréstimo
     * atraso: inteiro que armazena a quantidade de dias em atraso de um empréstimo no momento da entrega
     * livroEmprestado: Livro que está sendo objeto de empréstimo
     * mutuario: Leitor que realizou o empréstimo
     * numeroDeRenovações: Integer que conta a quantidade de vezes que o empréstimo foi renovado
     * naoDevolvido: booleano que marca se um empréstimo está finalizado ou não
     * dataDevolvidoFinal: localDate que armazena a data em que o empréstimo foi finalizado
     */
    private int emprestimoID;
    private LocalDate dataEmprestimo; // deve ser adicionada no momento em que o objeto for criado
    private LocalDate dataDevolucaoEsperada; // deve ser adicionada no momento em que o objeto for criado
    private int atraso;
    private Livro livroEmprestado;
    private Leitor mutuario;
    private Integer numeroDeRenovacoes;
    private boolean naoDevolvido;
    private LocalDate dataDevolvidoFinal;

    /**
     * Construtor para a classe empréstimo e seus atributos de criação
     * @param novaDataEmprestimo data em que o empréstimo é criado
     * @param novaDataDeDevolucao data em que se espera que o empréstimo seja finalizado
     * @param novoLivroEmprestado livro que serpa emprestado
     * @param novoMutuario leitor responsável pelo empréstimo
     */
    public Emprestimo (LocalDate novaDataEmprestimo, LocalDate novaDataDeDevolucao,
                       Livro novoLivroEmprestado, Leitor novoMutuario) {
        this.dataEmprestimo = novaDataEmprestimo;
        this.dataDevolucaoEsperada = novaDataDeDevolucao;
        this.atraso = 0;
        this.livroEmprestado = novoLivroEmprestado;
        this.mutuario = novoMutuario;
        this.numeroDeRenovacoes = 0;
        this.naoDevolvido = true;
    }

    /**
     * Acessa o valor da data de devolução final do empréstimo
     * @return valor acessado
     */
    public LocalDate getDataDevolvidoFinal() {
        return dataDevolvidoFinal;
    }

    /**
     * Modifica o valor da data de devolução final
     * @param dataDevolvidoFinal nova data de devolução final
     */
    public void setDataDevolvidoFinal(LocalDate dataDevolvidoFinal) {
        this.dataDevolvidoFinal = dataDevolvidoFinal;
    }

    /**
     * Acessar Indentificação do empréstimo
     * @return indentificação acessada
     */
    public int getEmprestimoID() {
        return emprestimoID;
    }

    /**
     * Acessar data em que empréstimo iniciou
     * @return data em que foi iniciado o empréstimo
     */
    public LocalDate getDataEmprestimo () {
        return dataEmprestimo;
    }

    /**
     * Acessar data em que se espera que o empréstimo acabe
     * @return data em que se esperava o fim do empréstimo
     */
    public LocalDate getdataDevolucaoEsperada () {
        return dataDevolucaoEsperada;
    }

    /**
     * Acessar dias em atraso do empréstimo
     * @return dias em que o empréstimo esta em atraso
     */
    public int getAtraso () {
        return atraso;
    }

    /**
     * Acessar livro que foi emprestado
     * @return livro emprestado
     */
    public Livro getLivroEmprestado () {
        return livroEmprestado;
    }

    /**
     * Acessar leitor que realizou o empréstimo
     * @return leitor que realizou o empréstimo
     */
    public Leitor getMutuario () {
        return mutuario;
    }

    /**
     * Acessar número de renovações do empréstimo
     * @return número de renovações
     */
    public int getNumeroDeRenovacoes () {
        return numeroDeRenovacoes;
    }

    /**
     * Acessar informação que informa se o empréstimo foi encerrado ou não
     * @return status do empréstimo
     */
    public boolean getNaoDevolvido () { return naoDevolvido; }

    /**
     * Modifica o valor de indentificação do empréstimo
     * @param emprestimoID novo ID do empréstimo
     */
    public void setEmprestimoID(int emprestimoID) { this.emprestimoID = emprestimoID; }

    /**
     * Modifica a data de início do empréstimo
     * @param dataEmprestimo nova data de início
     */
    public void setDataEmprestimo(LocalDate dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    /**
     * Modifica a data de devolução esperada do empréstimo
     * @param dataDevolucaoEsperada nova data de devolução esperada
     */
    public void setdataDevolucaoEsperada(LocalDate dataDevolucaoEsperada) { this.dataDevolucaoEsperada = dataDevolucaoEsperada; }

    /**
     * Modifica os dias em que o empréstimo esta em atraso
     * @param atraso novo atraso do empréstimo
     */
    public void setAtraso(int atraso) { this.atraso = atraso; }

    /**
     * Modifica o livro que foi emprestado
     * @param livroEmprestado novo livro
     */
    public void setLivroEmprestado(Livro livroEmprestado) { this.livroEmprestado = livroEmprestado; }

    /**
     * Modifica o leitor que realizou o empréstimo
     * @param mutuario nova data de início
     */
    public void setMutuario(Leitor mutuario) { this.mutuario = mutuario; }

    /**
     * Modifica o número de renovações do empréstimo
     * @param numeroDeRenovacoes nova data de início
     */
    public void setNumeroDeRenovacoes(int numeroDeRenovacoes) { this.numeroDeRenovacoes = numeroDeRenovacoes; }

    /**
     * Modifica o status de devolução do empréstimo
     * @param naoDevolvido nova data de início
     */
    public void setNaoDevolvido(boolean naoDevolvido) { this.naoDevolvido = naoDevolvido; }

    /**
     * Método para renovar empréstimo, verificações são feitas para garantir integridade da ação. Objeto empréstimo
     * é atualizado no mesmo momento pelo seu DAO.
     * @param emprestimo que serpa extendido
     * @throws EmprestimoException caso o número de renovações tenha sido atingido ou o livro emprestado esteja reservado
     */
    public void estenderEmprestimo (Emprestimo emprestimo) throws EmprestimoException {
        // NÚMERO DE RENOVAÇÕES E NOVA DATA ESPERADA DE DEVOLUÇÃO SÃO ALTERADAS CASO CONDIÇÃO SEJA ACEITA
        if (emprestimo.numeroDeRenovacoes < 1 && emprestimo.getLivroEmprestado().naoEstaReservado()) {
            emprestimo.numeroDeRenovacoes++;
            emprestimo.dataDevolucaoEsperada = dataDevolucaoEsperada.plus(7, ChronoUnit.DAYS);
            MasterDAO.getEmprestimoDAO().atualizar(emprestimo);
        }
        else {
            throw new EmprestimoException(EmprestimoException.RENEW, emprestimo);
        }
    }

    /**
     * Método para calcular a diferença de dias entre a data de devolução esperada e a de entrega do empréstimo
     * @param emprestimo que será usado como base de cálculo
     * @param data que deve ser utilizada como parâmetro para calculo do atraso
     * @return diferença entre a data passada como parâmetro e a data em que se esperava a finalização do empréstimo
     */
    public int calcularAtraso (Emprestimo emprestimo, LocalDate data) {
        int diferenca = (int) ChronoUnit.DAYS.between(data, emprestimo.dataDevolucaoEsperada);
        if (diferenca >0) {
            return 0;
        } else {
            return diferenca;
        }
    }

    /**
     * Método para calcular a multa que um usuário deve receber caso atrase um empréstimo,
     * o dobro de dias em atraso multiplicado por -1, devido a diferença negativa de dias
     * @param emprestimo que será usado como base do cálculo
     * @param data para calcular o atraso
     * @return quantidade de dias que o usuário ficará impossibilitado de realizar um empréstimo
     */
    public int calcularMulta (Emprestimo emprestimo, LocalDate data) {
        int atraso = this.calcularAtraso(emprestimo, data);
        int multa  = (2*atraso)*-1;
        return multa;
    }

    /**
     * Método para aplicar a multa a um usuário, definindo quando ele poderá novamente realizar um empréstimo
     * Método usado para decidir quando a multa aplicada ao leitor irá acabar, utiliza o calcularMulta e calcularAtraso
     * @param emprestimo que foi finalizado
     * @param data do fim do empréstimo
     */
    public void aplicarMulta (Emprestimo emprestimo, LocalDate data) {
        int multa = this.calcularMulta(emprestimo, data);
        if (multa > 0) {
            emprestimo.mutuario.setDiasDeMulta(multa);
            if ((emprestimo.mutuario.getDataDoFimDaMulta()).isBefore(data)) {
                emprestimo.mutuario.setDataDoFimDaMulta(data.plusDays(multa));
            } else {
                emprestimo.mutuario.setDataDoFimDaMulta((emprestimo.mutuario.getDataDoFimDaMulta()).plusDays(multa));
            }
        }
    }

    /**
     * Método que reúne as ferramentas de aplicarMulta, calcularMulta e calcularAtraso para
     * finalizar um empréstimo
     * @param emprestimo que será finalizado
     * @param data de devolução do empréstimo
     * Após todos os cálculos os DAOs de leitor, livro e empréstimo são utilizados para
     * atualizar os atributos modificados
     */
    public void finalizarEmprestimo (Emprestimo emprestimo, LocalDate data) {
        emprestimo.aplicarMulta(emprestimo, data);
        emprestimo.livroEmprestado.setDisponibilidade(true);
        emprestimo.setNaoDevolvido(false);
        emprestimo.setAtraso(emprestimo.calcularAtraso(emprestimo, data));
        MasterDAO.getLeitorDAO().atualizar(emprestimo.getMutuario());
        MasterDAO.getLivroDAO().atualizar(emprestimo.getLivroEmprestado());
        MasterDAO.getEmprestimoDAO().atualizar(emprestimo);
    }

    /**
     * Método que reúne as verificações que devem ser feitas na criação de um empréstimo
     * @param emprestimo a ser criado
     * @return booleano caso passe em todas as condições
     * @throws Exception caso livro esteja reservado, usuário esteja banido ou multado
     * Método ainda não utilizado no sistema, vericação está sendo feita de forma manual no método
     * criar do DAO empréstimo
     */
    public boolean verificacao (Emprestimo emprestimo) throws Exception {
        if (!emprestimo.getLivroEmprestado().estaReservado()) {
            throw new EmprestimoException(EmprestimoException.RENEW, emprestimo);           //criar exception para livro não disponivel
        }
        if (emprestimo.getMutuario().getDiasDeMulta() > 0) {
            throw new LeitorException(LeitorException.USERFINED, emprestimo.getMutuario());           //criar exception para usuario multado
        }
        if (emprestimo.mutuario.isLeitorBanido()) {    //criar atributo bool para verificar se esta banido
            throw new LeitorException(LeitorException.BAN, emprestimo.getMutuario());         //criar exception para usuario banido
        }
        return true;
    }

    /**
     * Método toString do empréstimo, usado para exibir seus atributos
     * @return String do empréstimo com seus atributos
     */
    @Override
    public String toString() {
        return "Emprestimo{" +
                "emprestimoID=" + emprestimoID +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucaoEsperada=" + dataDevolucaoEsperada +
                ", atraso=" + atraso +
                ", livroEmprestado=" + livroEmprestado +
                ", mutuario=" + mutuario +
                ", numeroDeRenovacoes=" + numeroDeRenovacoes +
                ", naoDevolvido=" + naoDevolvido +
                '}';
    }

    /**
     * Método que realiza o equal do objeto empréstimo
     * @param obj empréstimo a ser comparado
     * @return equal do objeto
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
