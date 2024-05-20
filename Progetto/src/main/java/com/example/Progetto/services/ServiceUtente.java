package com.example.Progetto.services;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.example.Progetto.dao.DaoUtente;

import com.example.Progetto.models.Utente;

        //AGGIUNTO SERVICEUTENTE
@Service
public class ServiceUtente extends GenericService<Long, Utente, DaoUtente>{



   
 public Utente insert(Map<String, String> map){
        Utente l = createEntity(map);
        Long id = getDao().create(l);
        l.setId(id);
        return l;
    }
    
    public List<Utente> findAll() {
        return getDao().read().values().stream().map(x -> {
            return (Utente) x;
        }).toList();
    }





    @Override
    public Utente createEntity(Map<String, String> map) {
        Utente l = getContext().getBean(Utente.class, map);
        return l;
    }
}
