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
     * @param
     * @return
     */
    public void deletar(T obj);

    /**
     * Detela todos os dados
     */
    public void resetar();

    /**
     * Atualiza um objeto
     *
     * @param
     * @return
     */

    /**
     * Ler toda a lista de dados
     *
     * @return
     */

    public T procurarPorID(Integer id);
}
