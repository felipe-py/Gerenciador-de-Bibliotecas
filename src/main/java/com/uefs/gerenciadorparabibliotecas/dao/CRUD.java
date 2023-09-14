package com.uefs.gerenciadorparabibliotecas.dao;

public interface CRUD<T> {
    /**
     * Cria novo objeto
     *
     * @param obj
     * @return
     */
    public T criar(T obj);

    /**
     * Deleta um objeto
     *
     * @param id id
     * @return
     */
    public void deletar(int id);

    /**
     * Detela todos os dados
     */
    public void resetar();

    /**
     * Atualiza um objeto
     *
     * @param obj
     * @return
     */
    public T atualizar(T obj);

    /**
     * Ler toda a lista de dados
     *
     * @return
     */

    public T procurarPorID(int id);
}
