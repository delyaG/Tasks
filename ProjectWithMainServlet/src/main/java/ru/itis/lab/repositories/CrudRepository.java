package ru.itis.lab.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    void save(T model);
    Optional<T> findById(ID id);
    List<T> findAll();
}
