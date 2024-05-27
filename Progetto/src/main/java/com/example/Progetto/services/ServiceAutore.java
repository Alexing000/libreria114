package com.example.Progetto.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Progetto.dao.DaoAutore;
import com.example.Progetto.models.Autore;

@Service
public class ServiceAutore extends GenericService<Long, Autore, DaoAutore>{

    @Override
    public Autore createEntity(Map<String, String> map) {
        Autore a = getContext().getBean(Autore.class, map);
        return a;
    }

    public Autore insert(Map<String, String> map) {
        Autore a = createEntity(map);
        Long id = getDao().create(a);
        a.setId(id);
        return a;
    }

    public List<Autore> findAll(){
        return getDao().read()
                .values()
                .stream()
                .map(
                    X -> {
                        return (Autore) X;
                    }
                )
                .toList();


}
public boolean delete(Long id) {
    getDao().delete(id);
    return true;
}

// cercare un autore per nome
public Autore findByCognome(String cognome) {
    return getDao().readByCognome(cognome);
}
//cercare autore per nome
public Autore findByNome(String nome) {
    return getDao().readByNome(nome);
}
public boolean update(Map<String, String> map) {
    Autore a = createEntity(map);
    getDao().update(a);
    return true;
}

}