package br.com.rossalli.sample.java.api.hero.service;

import java.util.List;

public interface CrudService<T> {
    T save(T input);

    T getById(Long id);

    List<T> getAll();

    T update(Long id, T input);

    void delete(Long id);
}
