package repository;

import exception.BuscaInvalidaException;
import exception.FormularioIncorretoException;

import java.util.List;

public interface IRepository<Object> {


    void add(Object obj) throws RuntimeException;

    void remove(Object obj) throws BuscaInvalidaException;

    void update(Object obj) throws BuscaInvalidaException, FormularioIncorretoException;

    Object findById(long id) throws BuscaInvalidaException;

    List<Object> findAll() throws BuscaInvalidaException;

    int getIndex(long id);

    boolean existsById(long id) throws BuscaInvalidaException;
}
