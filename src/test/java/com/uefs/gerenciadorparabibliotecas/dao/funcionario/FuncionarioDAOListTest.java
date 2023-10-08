package com.uefs.gerenciadorparabibliotecas.dao.funcionario;

import com.uefs.gerenciadorparabibliotecas.dao.MasterDAO;
import com.uefs.gerenciadorparabibliotecas.exeptions.funcionarioExceptions.FuncionarioException;
import com.uefs.gerenciadorparabibliotecas.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testes do DAO Funcionário
 */
 public class FuncionarioDAOListTest {
    /**
     * objetos do tipo funcionário (Bibliotecário e administrador) são criados para serem utilizados ao longo
     * dos testes
     */
    private Funcionario funcionarioAdm;
    private Funcionario funcionarioBiblio;

    @BeforeEach
     void setUp() {
        this.funcionarioBiblio = new Bibliotecario("Luva de pedreiro","BA","1234",
                "77998765012",7777);
        this.funcionarioAdm = new Administrador("Bora Bill","BA","4321",
                "7782560864",3567);
        MasterDAO.getFuncionarioDAO().criar(this.funcionarioAdm);
        MasterDAO.getFuncionarioDAO().criar(this.funcionarioBiblio);
    }

    @AfterEach
     void tearDown() {
        MasterDAO.getFuncionarioDAO().resetar();
    }

    /**
     * Teste para confirmar que a criação do objeto funcionário foi feita da forma correta, todos os atributos usados
     * no construtor são verificados um por um
     * @throws FuncionarioException caso a busca pelo ID de um dos objetos falhe
     */
    @Test
     void criar() throws FuncionarioException{
        assertEquals("Luva de pedreiro",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getNome());
        assertEquals("Bora Bill",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getNome());
        assertEquals("1234",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getSenha());
        assertEquals("4321",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getSenha());
        assertEquals("77998765012",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getNumeroDeTelefone());
        assertEquals("7782560864",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getNumeroDeTelefone());
        assertEquals("BA",MasterDAO.getFuncionarioDAO().procurarPorID(7777).getEndereco());
        assertEquals("BA",MasterDAO.getFuncionarioDAO().procurarPorID(3567).getEndereco());
    }

    /**
     * Teste para confirmar que a ação de deletar um funcionário está sendo feita da forma correta, um novo
     * funcionário auxiliar é criado para que o size da lista de funcionários seja de 3, após deletar é verificado
     * se o size foi para 2
     * @throws FuncionarioException caso o objeto a ser deletado não seja encontrado
     */
    @Test
     void deletar() throws FuncionarioException {
        Funcionario funcionarioAdm2 = new Administrador("Bora Bill","BA","4321",
                "7782560864",3568);
        MasterDAO.getFuncionarioDAO().criar(funcionarioAdm2);
        MasterDAO.getFuncionarioDAO().deletar(this.funcionarioBiblio);
        assertNotNull(MasterDAO.getFuncionarioDAO().procurarPorID(3568));
        assertEquals(2, MasterDAO.getFuncionarioDAO().getFuncionarios().size());
    }

    /**
     * Teste para confirmar que após um resete, não exista nenhum funcionário na lista
     */
    @Test
     void resetar() {
        Funcionario funcionarioBiblio2 = new Bibliotecario("Luva de pedreiro","BA","1234",
                "77998765012",9988);
        MasterDAO.getFuncionarioDAO().criar(funcionarioBiblio2);
        MasterDAO.getFuncionarioDAO().resetar();
        assertEquals(0, MasterDAO.getFuncionarioDAO().getFuncionarios().size());
    }

    /**
     * Teste para confirmar que a busca por ID encontre um funcionário
     * @throws FuncionarioException caso o funcionário não seja encontrado
     */
    @Test
     void procurarPorID() throws FuncionarioException {
        assertEquals(this.funcionarioAdm,MasterDAO.getFuncionarioDAO().procurarPorID(3567));
        assertEquals(this.funcionarioBiblio, MasterDAO.getFuncionarioDAO().procurarPorID(7777));
    }

    /**
     * Teste para verificar uma situação de falha ao deletar um funcionário, um funcionário auxiliar que não foi
     * adicionado a lista de funcionários é usado é usado para validação
     */
    @Test
    void failDelete() {
       try {
          MasterDAO.getFuncionarioDAO().deletar(new Bibliotecario("Robertin", "UEFS", "441112", "4789652",
                  2010));
          fail("Uma exceção deveria ser gerada!!");
       } catch (FuncionarioException e) {
          assertEquals(FuncionarioException.DELETE, e.getMessage());
       }
    }

    /**
     * Teste para verificar se o método atualizar funciona corretamente, primeiramente os parâmetros do
     * funcionário são alterados manualmente, logo após um objeto funcionário auxiliar é criado, para receber
     * automaticamente as atualizações do funcionário alterado. Os dois são comparados para atestar o funcionamento
     * do método
     */
    @Test
    void atualizar() {
       this.funcionarioBiblio.setEndereco("Feira X");
       this.funcionarioBiblio.setNome("Carlos");
       this.funcionarioBiblio.setSenha("5896");
       this.funcionarioBiblio.setUserID(1200);
       this.funcionarioBiblio.setNumeroDeTelefone("77777777777");
       Funcionario atual = MasterDAO.getFuncionarioDAO().atualizar(this.funcionarioBiblio);
       assertEquals(this.funcionarioBiblio, atual);
    }

    /**
     * Método que verifica uma situação de falha na busca por ID de um funcionário, uma indentificação inválida
     * é passada como parâmetro da validação
     */
    @Test
    void failBusca(){
       try {
          MasterDAO.getFuncionarioDAO().procurarPorID(9999);
       } catch (FuncionarioException e) {
          assertEquals(FuncionarioException.SEARCH + "ID inválido:"+ 9999, e.getMessage());
       }
    }
}