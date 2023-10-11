package com.uefs.gerenciadorparabibliotecas.dao.leitor;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.leitorExeptions.LeitorException;

import com.uefs.gerenciadorparabibliotecas.model.Leitor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testes do DAO Leitor
 */
public class LeitorDAOListTest {
    /**
     * Objeto do tipo leitor é criado para ser utilizado ao longo dos testes
     */
    private Leitor leitor;
    @BeforeEach
    void setUp() throws LeitorException {
        this.leitor = new Leitor("Lucas","Feira VI","senha123","40028922",
                0001);
        MasterDAO.getLeitorDAO().criar(this.leitor);
    }

    @AfterEach
    void tearDown() {
        MasterDAO.getLeitorDAO().resetar();
    }

    /**
     * Teste para confirmar que a criação do objeto leitor foi feita da forma correta, todos os atributos usados
     * no construtor são verificados um por um
     * @throws LeitorException caso a busca pelo ID de um dos objetos falhe
     */
    @Test
    void criar() throws LeitorException{
        assertEquals(this.leitor,MasterDAO.getLeitorDAO().procurarPorID(0001));
    }

    /**
     * Teste para confirmar que a ação de deletar um leitor está sendo feita da forma correta, um novo
     * leitor auxiliar é criado para que o size da lista de leitores seja de 2, após deletar é verificado
     * se o size foi para 1
     * @throws LeitorException caso o objeto a ser deletado não seja encontrado
     */
    @Test
    void deletar() throws LeitorException {
        Leitor leitorAux = new Leitor("Felipe","UEFS","senha321","40045001",
                8899);
        MasterDAO.getLeitorDAO().criar(leitorAux);
        MasterDAO.getLeitorDAO().deletar(this.leitor);
        assertEquals(1, MasterDAO.getLeitorDAO().getLeitores().size());
    }

    /**
     * Teste para confirmar que após um resete, não exista nenhum leitor na lista
     */
    @Test
    void resetar() {
        MasterDAO.getLeitorDAO().resetar();
        assertEquals(0, MasterDAO.getLeitorDAO().getLeitores().size());
    }

    /**
     * Teste para confirmar que a busca por ID encontre um leitor
     * @throws LeitorException caso o leitor não seja encontrado
     */
    @Test
    void procurarPorID() throws LeitorException {
        assertEquals(leitor, MasterDAO.getLeitorDAO().procurarPorID(0001));
    }

    /**
     * Teste para verificar uma situação de falha ao deletar um leitor, um leitor auxiliar que não foi
     * adicionado a lista de leitores é usado é usado para validação
     */
    @Test
    void failDelete() {
        try {
            MasterDAO.getLeitorDAO().deletar(new Leitor("Felipe", "Parque Ipê", "4477"
                    ,"85474783",4122));
            fail("Uma exceção deveria ser gerada!!");
        } catch (LeitorException e) {
            assertEquals(LeitorException.DELETE, e.getMessage());
        }
    }

    /**
     * Teste para verificar se o método atualizar funciona corretamente, primeiramente os parâmetros do
     * leitor são alterados manualmente, logo após um objeto leitor auxiliar é criado, para receber
     * automaticamente as atualizações do leitor alterado. Os dois são comparados para atestar o funcionamento
     * do método
     */
    @Test
    void atualizar(){
        this.leitor.setEndereco("Casa");
        this.leitor.setNome("Ana");
        this.leitor.setSenha("4444");
        this.leitor.setNumeroDeTelefone("414141414");
        this.leitor.setUserID(0000);
        Leitor atual = MasterDAO.getLeitorDAO().atualizar(this.leitor);
        assertEquals(atual, this.leitor);
    }

    /**
     * Método que verifica uma situação de falha na busca por ID de um leitor, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBusca() {
        try {
            MasterDAO.getLeitorDAO().procurarPorID(9999);
        } catch (LeitorException e) {
            assertEquals(LeitorException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
        }
    }

    /**
     * Método que verifica uma falha no momento de criar um objeto leitor, é averiguado se o seu nome, senha, telefone ou
     * endereço são strings vazias. É tambem observado se o nome de usuário possui algum número ou se o telefone possui alguma letra.
     * Uma última observação é se o ID escolhido já existe em algum cadastro no sistema
     */
    @Test
    void failCreate(){
        // NOME VAZIO
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("","Feira X","1122","75998765432",1111));
        } catch (LeitorException e){
            assertEquals(LeitorException.EMPITY_INFO, e.getMessage());
        }

        // NOME COM NÚMERO
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("T3ddy","Feira X","1122","75998765432",1112));
        } catch (LeitorException e){
            assertEquals(LeitorException.INVALID_INFO + "T3ddy" + " INVÁLIDO/EXISTENTE", e.getMessage());
        }

        // TELEFONE COM LETRA
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("Carlos","Feira X","1122","7599876543C",1113));
        } catch (LeitorException e){
            assertEquals(LeitorException.INVALID_INFO + "7599876543C" + " INVÁLIDO/EXISTENTE", e.getMessage());
        }

        // TELEFONE VAZIO
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("Carlos","Feira X","1122","",1114));
        } catch (LeitorException e){
            assertEquals(LeitorException.EMPITY_INFO, e.getMessage());
        }

        // ID EXISTENTE
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("Carlos","Feira X","1122","75998765432",1114));
        } catch (LeitorException e){
            assertEquals(LeitorException.INVALID_INFO + 1114 + " INVÁLIDO/EXISTENTE", e.getMessage());
        }

        // SENHA VAZIA
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("Carlos","Feira X","","75998765432",1116));
        } catch (LeitorException e){
            assertEquals(LeitorException.EMPITY_INFO, e.getMessage());
        }

        // ENDEREÇO VAZIO
        try{
            MasterDAO.getLeitorDAO().criar(new Leitor("Carlos","","","75998765432",1116));
        } catch (LeitorException e){
            assertEquals(LeitorException.EMPITY_INFO, e.getMessage());
        }
    }
}