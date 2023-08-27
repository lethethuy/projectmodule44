package ra.demo6.service;

import java.util.List;

public interface IGenericService <T,E>{
    List<T> findAll();
    void save(T t);
    T findByID(E e);

    void deleteById(E e);

}
