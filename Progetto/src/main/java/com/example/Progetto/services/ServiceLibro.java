package com.example.Progetto.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Progetto.dao.DaoLibro;
import com.example.Progetto.models.Libro;

@Service
public class ServiceLibro extends GenericService <Long, Libro, DaoLibro>{

    @Override
    public Libro createEntity(Map<String, String> map) {
        Libro l = getContext().getBean(Libro.class, map);
        return l;
    }

    public Libro insert(Map<String, String> map){
        Libro l = createEntity(map);
        Long id = getDao().create(l);
        l.setId(id);
        return l;
    }
    
    public List<Libro> findAll() {
        return getDao().read().values().stream().map(x -> {
            return (Libro) x;
        }).toList();
    }

    public Libro findByTitolo(String titolo) {
        return getDao().readByTitolo(titolo);
    }

    public List<Libro> byAnno() {
        return getDao().orderByAnno();
    }

    public List<Libro> byGenere() {
        return getDao().orderByGenere();
    }

    
}
