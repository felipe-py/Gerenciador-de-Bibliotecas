package com.uefs.gerenciadorparabibliotecas.dao;

/**
 * CRUD genérico que será utilizado por todos os DAOs
 * @param <T>
 * @param <E>
 */
public interface CRUD<T, E extends Exception> {
    /**
     * Cria novo objeto
     *
     * @param obj
     * @return objeto criado
     */
    public T criar(T obj);  // VERIFICAÇÕES DE CRIAÇÃO SERÃO COLOCADAS POSTERIOMENTE NO PROJETO

    /**
     * Deleta um objeto
     *
     * @param obj
     * @return void
     */
    public void deletar(T obj) throws E;

    /**
     * Detela todos os dados
     */
    public void resetar();

    /**
     * Atualiza um objeto
     *
     * @param  obj
     * @return
     */
    public T atualizar(T obj);

    /**
     * Procura objeto utilizando seu ID
     * @param id
     * @return objeto encontrado
     * @throws E caso não encontre nenhum objeto com o ID informado
     */
    public T procurarPorID(Integer id) throws E;
}
