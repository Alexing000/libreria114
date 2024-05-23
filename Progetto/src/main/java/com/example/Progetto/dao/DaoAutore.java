package com.example.Progetto.dao;


import java.util.HashMap;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.Progetto.models.Autore;


import lombok.Data;

@Service
@Data
public class DaoAutore implements IDao<Long, Autore>{

    private final Database database;
    private final ApplicationContext context;
    
    
    //creare un autore
    @Override
    public Long create(Autore e) {
       String query = "INSERT INTO autore (nome, cognome, biografia, rating) VALUES (?, ?, ?, ?)";
       Long id= database.executeDML(query, e.getNome(), e.getCognome(), e.getBiografia(), String.valueOf(e.getRating()));   
    return id;
    }
    
    //leggere tutti gli autori
    @Override
    public Map<Long, Autore> read() {
        String query = "SELECT * FROM autore";
        Map<Long, Map<String, String>> ris= database.executeDQL(query);
        Autore a = null;
        Map<Long, Autore> autoriMap = new HashMap<>();
        for (Map<String, String> map : ris.values()) {
            a = context.getBean(Autore.class, map);
            autoriMap.put(a.getId(), a);
       }
         return autoriMap;
    }
    
    
    //aggiornare un autore
    @Override
    public void update(Autore e) {
        String query = "UPDATE autore SET nome = ?, cognome = ?, rating = ? WHERE id = ?";
        database.executeDML(query, 
                e.getNome(),
                e.getCognome(),
                String.valueOf(e.getRating()));
                
    }
    @Override
    public void delete(Long id) {
        String query = "DELETE FROM autore WHERE id = ?";
        database.executeDML(query, String.valueOf(id));
    }


    @Override
    public Autore readById(Long id) {
        String query = "SELECT * FROM autore WHERE id = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(id));
        Autore a = null;
        for (Map<String, String> map : ris.values()) {
            a = context.getBean(Autore.class, map);
        }
        return a;
    }

    //cercare un autore per nome 
    public Autore readByCognome(String cognome) {
        String query = "SELECT * FROM autore WHERE cognome = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, cognome);
        Autore a = null;
        for (Map<String, String> map : ris.values()) {
            a = context.getBean(Autore.class, map);
        }
        return a;
    }
    public Autore readByNome(String nome) {
        String query = "SELECT * FROM autore WHERE nome = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, nome);
        Autore a = null;
        for (Map<String, String> map : ris.values()) {
            a = context.getBean(Autore.class, map);
        }
        return a;
    }

    //cercare un autore per nome o cognome
    public Autore readByNomeCognome(String nome, String cognome) {
        String query = "SELECT * FROM autore WHERE nome = ? AND cognome = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, nome, cognome);
        Autore a = null;
        for (Map<String, String> map : ris.values()) {
            a = context.getBean(Autore.class, map);
        }
        return a;
    }




    

}
