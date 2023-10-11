package com.uefs.gerenciadorparabibliotecas.dao.livro;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.emprestimoExceptions.EmprestimoException;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;
import com.uefs.gerenciadorparabibliotecas.exeptions.livroExceptions.LivroException;
import com.uefs.gerenciadorparabibliotecas.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testes do DAO Livro
 */
public class LivroDAOListTest {
    /**
     * objeto livro é criado para ser utilizado durante os testes
     */
    private Livro livro;

    @BeforeEach
    void setUp() throws LivroException {
        this.livro = new Livro("Diário de um banana","Zezinho","Cultura","4455883",
                "2013",CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(this.livro);

    }

    @AfterEach
    void tearDown() {
        MasterDAO.getLivroDAO().resetar();
        MasterDAO.getEmprestimoDAO().resetar();
        MasterDAO.getLeitorDAO().resetar();
        MasterDAO.getReservaDAO().resetar();
    }

    /**
     * Teste para confirmar que a criação do objeto livro foi feita da forma correta, todos os atributos usados
     * no construtor são verificados um por um
     * @throws LivroException caso a busca pelo ID de um dos objetos falhe
     */
    @Test
    void criar() throws LivroException{
        assertEquals(this.livro, MasterDAO.getLivroDAO().procurarPorID(0));
    }

    /**
     * Teste para confirmar que a busca por ID encontre um livro
     * @throws LivroException caso o livro não seja encontrado
     */
    @Test
    void buscarPorID() throws LivroException{
        assertEquals(livro, MasterDAO.getLivroDAO().procurarPorID(0));
    }

    /**
     * Teste para confirmar que a busca por ISBN encontre um ou mais livros
     * @throws LivroException caso nenhum livro seja encontrado
     */
    @Test
    void buscarPorISBN() throws LivroException {
        Livro livroAux2 = new Livro("Diário de um banana","Zezinho","Cultura",
                "4455783","2013", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux2);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorISBN("4455883");
        assertNotEquals(testeAux1,testeAux2);
    }

    /**
     * Teste para confirmar que a busca por autor encontre um ou mais livros em que o autor esteja presente
     * @throws LivroException caso nenhum livro seja encontrado
     */
    @Test
    void buscarPorAutor() throws LivroException{
        Livro livroAux3 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux3);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorAutor("Zezinho");
        assertEquals(testeAux2,testeAux1);
    }

    /**
     * Teste para confirmar que a busca por título encontre algum livro com o título passado como parâmetro
     * @throws LivroException caso nenhum livro seja encontrado
     */
    @Test
    void buscarPorTitulo() throws LivroException {
        Livro livroAux4 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux4);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorTitulo("Diário de um banana");
        assertNotEquals(testeAux2,testeAux1);
    }

    /**
     * Teste para confirmar que a busca por categoria encontre os livros da categoria buscada
     * @throws LivroException caso nenhum livro seja encontrado
     */
    @Test
    void buscarPorCategoria() throws LivroException{
        Livro livroAux5 = new Livro("Diário de um banana 2","Zezinho","Cultura",
                "4455999","2014", CategoriaLivro.OUTRA,LocalizacaoLivro.alaC);
        MasterDAO.getLivroDAO().criar(livroAux5);
        List<Livro> testeAux1 = MasterDAO.getLivroDAO().getLivros();
        List<Livro> testeAux2 = MasterDAO.getLivroDAO().procurarPorCategoria(CategoriaLivro.OUTRA);
        assertEquals(testeAux2,testeAux1);
    }

    /**
     * Teste para confirmar que a ação de deletar um livro está sendo feita da forma correta, um novo
     * livro auxiliar é criado para confirmar que este não será nulo, um assertNull é dado no livro que
     * foi apagado
     * @throws LivroException caso o objeto a ser deletado não seja encontrado
     */
    @Test
    void deletar() throws LivroException {
        Livro livroAux = new Livro("Harry Potter 1","A chata","Warner","8899452"
                ,"2001",
                CategoriaLivro.FANTASIA,LocalizacaoLivro.alaD);
        MasterDAO.getLivroDAO().criar(livroAux);
        MasterDAO.getLivroDAO().deletar(this.livro);
        assertNull(MasterDAO.getLivroDAO().procurarPorID(this.livro.getLivroID()));
        assertNotNull(MasterDAO.getLivroDAO().procurarPorID(livroAux.getLivroID()));
    }

    /**
     * Teste para confirmar que após um resete, não exista nenhum livro não nulo na lista
     */
    @Test
    void resetar() {
        MasterDAO.getLivroDAO().resetar();
        assertEquals(0,MasterDAO.getLivroDAO().getLivros().size());
    }

    /**
     * Teste para verificar se o método atualizar funciona corretamente, primeiramente os parâmetros do
     * livro são alterados manualmente, logo após um objeto livro auxiliar é criado, para receber
     * automaticamente as atualizações do livro alterado. Os dois são comparados para atestar o funcionamento
     * do método
     */
    @Test
    void atualizar() throws LivroException {
        Livro livroAux = new Livro("Harry Potter 6","A CHATA","Warner Bros","9999999","2008",
                CategoriaLivro.FANTASIA,LocalizacaoLivro.alaF);
        MasterDAO.getLivroDAO().criar(livroAux);
        this.livro.setTitulo("Casinha");
        this.livro.setIsbn("444444444");
        this.livro.setLocalizacao(LocalizacaoLivro.alaC);
        this.livro.setAutor("pedrinho");
        this.livro.setEditora("casa");
        this.livro.setAnoDePublicacao("1345");
        this.livro.setCategoria(CategoriaLivro.HISTORIA);
        Livro atual = MasterDAO.getLivroDAO().atualizar(this.livro);
        assertEquals(this.livro, atual);
    }

    /**
     * Teste para verificar uma situação de falha ao deletar um livro, um livro auxiliar que não foi
     * adicionado a lista de livros é usado para validação
     */
    @Test
    void failDelete() {
        try {
            MasterDAO.getLivroDAO().deletar(new Livro("2001", "Fulano", "HBO","4789652",
                    "2010",CategoriaLivro.HISTORIA,LocalizacaoLivro.alaD));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LivroException e) {
            assertEquals(LivroException.DELETE, e.getMessage());
        }
    }

    /**
     * Método que verifica uma situação de falha na busca por ID de um livro, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBuscaID(){
        try {
            MasterDAO.getLivroDAO().procurarPorID(9999);
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH + "9999" + "inválido(a)", e.getMessage());
        }
    }

    /**
     * Método que verifica uma situação de falha na busca por autor de um livro, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBuscaAutor(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorAutor("Zezinho"));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH, e.getMessage());
        }
    }

    /**
     * Método que verifica uma situação de falha na busca pelo ISBN de um livro, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBuscaISBN(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorISBN("00000000"));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH , e.getMessage());
        }
    }

    /**
     * Método que verifica uma situação de falha na busca por título de um livro, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBuscaTitulo(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorTitulo("00000000"));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH, e.getMessage());
        }
    }

    /**
     * Método que verifica uma situação de falha na busca pela categoria de um livro, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBuscaCategoria(){
        try{
            MasterDAO.getLivroDAO().livrosEncontrados(MasterDAO.getLivroDAO().procurarPorCategoria(CategoriaLivro.POESIA));
        } catch (LivroException e) {
            assertEquals(LivroException.SEARCH, e.getMessage());
        }
    }

    /**
     * Teste para averiguar se a visualização decrescente dos livros mais populares está sendo feita da forma
     * correta, empréstimos auxiliares são criados para preencher a lista de empréstimos
     */
    @Test
    void livrosPopulares() throws LeitorException, EmprestimoException, LivroException {

        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);

        Livro livro01 = new Livro("Harry Potter e a Pedra Filosofal","Zezinho","Cultura","12345678","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        Leitor leitor01 = new Leitor("Lucas","Feira VI","senha123","40028922",
                1010);
        Emprestimo emprestimo01 = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livro01,leitor01);

        Livro livro02 = new Livro("Harry Potter e a Pedra Filosofal","Zezinho","Cultura","12345678","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        Leitor leitor02 = new Leitor("Felipe","Feira VI","senha123","40028922",
                1011);
        Emprestimo emprestimo02 = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livro02,leitor02);

        Livro livro04 = new Livro("Diário de um banana 2","Zezinho","Cultura","4455884","2013",
                CategoriaLivro.OUTRA, LocalizacaoLivro.alaC);
        Leitor leitor04 = new Leitor("Lucas","Feira VI","senha123","40028922",
                1013);
        Emprestimo emprestimo04 = new Emprestimo(dataEmprestimo,dataDevolucaoEsperada,livro04,leitor04);

        MasterDAO.getLivroDAO().criar(livro01);
        MasterDAO.getLivroDAO().criar(livro02);
        MasterDAO.getLivroDAO().criar(livro04);

        MasterDAO.getLeitorDAO().criar(leitor01);
        MasterDAO.getLeitorDAO().criar(leitor02);
        MasterDAO.getLeitorDAO().criar(leitor04);

        MasterDAO.getEmprestimoDAO().criar(emprestimo01);
        MasterDAO.getEmprestimoDAO().criar(emprestimo02);
        MasterDAO.getEmprestimoDAO().criar(emprestimo04);
        MasterDAO.getEmprestimoDAO().criar(emprestimo04);
        MasterDAO.getEmprestimoDAO().criar(emprestimo04);

        System.out.println("\n= TESTE DOS LIVROS MAIS POPULARES =\n");

        int soma = 1;
        for (Map.Entry<Integer, List<String>> entry : MasterDAO.getLivroDAO().livrosPopulares(MasterDAO.getLivroDAO().agruparLivrosPorISBN()).entrySet()) {
            System.out.println(soma + "º: Número de empréstimos => " + entry.getKey() + " || Livro: " + entry.getValue());
            soma++;
        }
    }

    /**
     * Teste para averiguar se as validações feitas no momento da criação de um livro estão corretas, é observado se o título, autor, editora, ano de publicação e
     * ISBN são valores vazios, autor informado possui algum número, ano de publicação possui letra
     */
    @Test
    void failCreate() {
        // TÍTULO VAZIO
        try{
            MasterDAO.getLivroDAO().criar(new Livro("","Zezinho","Cultura","12345678","2013",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.EMPITY_INFO, e.getMessage());
        }

        // AUTOR VAZIO
        try{
            MasterDAO.getLivroDAO().criar(new Livro("Harry","","Cultura","12345678","2013",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.EMPITY_INFO, e.getMessage());
        }

        // EDITORA VAZIO
        try{
            MasterDAO.getLivroDAO().criar(new Livro("Harry","Zezo","","12345678","2013",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.EMPITY_INFO, e.getMessage());
        }

        // ANO DE PUBLICAÇÃO VAZIO
        try{
            MasterDAO.getLivroDAO().criar(new Livro("Harry","Zezo","Cultura","12345678","",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.EMPITY_INFO, e.getMessage());
        }

        // ISBN VAZIO
        try{
            MasterDAO.getLivroDAO().criar(new Livro("Harry","Zezo","Cultura","","1978",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.EMPITY_INFO, e.getMessage());
        }

        // AUTOR COM NÚMERO NO NOME
        try{
            MasterDAO.getLivroDAO().criar(new Livro("Harry","Zez3","Cultura","147896325","1978",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.INVALID_INFO + "Zez3" + " INVÁLIDO/EXISTENTE", e.getMessage());
        }

        // ANO DE PUBLICAÇÃO COM LETRA
        try{
            MasterDAO.getLivroDAO().criar(new Livro("Harry","Zezo","Cultura","147896325","197E",
                    CategoriaLivro.OUTRA, LocalizacaoLivro.alaC));
        } catch (LivroException e){
            assertEquals(LivroException.INVALID_INFO + "197E" + " INVÁLIDO/EXISTENTE", e.getMessage());
        }
    }
}