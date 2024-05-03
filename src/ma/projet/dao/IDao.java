package ma.projet.dao;

import java.util.List;

public interface IDao<T> {
	boolean create(T obj);
    boolean delete(T obj);
    boolean update(T obj);
    T findById(int id);
    List<T> findAll();
}
