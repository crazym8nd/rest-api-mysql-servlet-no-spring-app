package com.vitaly.rest_api_no_spring_app.repository;
//  20-Jan-24
// gh crazym8nd


import java.util.List;

public interface GenericRepository<T,Id> {

    List<T> getAll();

    T getById(Id id);

    T create (T t);

    T update (T t);

    void deleteById(Id id);
}
