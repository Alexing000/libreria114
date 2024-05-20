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
       
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.example.Progetto.dao.DaoUtente;
import com.example.Progetto.models.Entity;
import com.example.Progetto.models.Utente;

        //AGGIUNTO SERVICEUTENTE
public class ServiceUtente {

    @Autowired
    private DaoUtente daoUtente;

    @Autowired
    private ApplicationContext applicationContext;

    public void create(Map<String,String> params) {
        Utente u = applicationContext.getBean("utente",Utente.class);
        daoUtente.create(u);
    }

    

    public Utente readById(Long id) {
        Entity e = daoUtente.readById(id);
        if(e instanceof Utente)
            return (Utente)e;
        return null;
    }

    public void update(Map<String,String> params) {
        Utente u = applicationContext.getBean("utente",Utente.class);
        daoUtente.update(u);
    }

    public void delete(Long id) {
        daoUtente.delete(id);
    }

    public boolean readByUserName(String username){
        return daoUtente.userExists(username);
    }

    public Utente readByUsernameAndPassword(String username, String password) {
        Map<String,String> map= daoUtente.autentica(username, password);
        if (map!=null) {
            return applicationContext.getBean(Utente.class,map);
        }
        return null;

    }
}
