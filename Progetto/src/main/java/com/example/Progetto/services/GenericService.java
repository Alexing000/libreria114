package com.example.Progetto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.Progetto.dao.IDao;
import com.example.Progetto.models.Entity;

import lombok.Data;

@Data
public abstract class GenericService <TipoID,
                                        E extends Entity,
                                        D extends IDao<TipoID,E>>        {

@Autowired
private D dao;

@Autowired 
private ApplicationContext context;

public List<E> findAll(){
    Map<TipoID, E> result = dao.read();
    List<E> list =  new ArrayList<>();
    for(E e : result.values()) {
        list.add(e);
    }
    return list;
}

public E findById(TipoID id) {
    E e = dao.readById(id);
    return e;
}

public boolean update(Map<String, String> map) {
    E e = createEntity(map);
    dao.update(e);
    return true;
}


public abstract E createEntity(Map<String, String> map);

public boolean delete(TipoID id) {
    dao.delete(id);
    return true;
}
}
