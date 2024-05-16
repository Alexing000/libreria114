package com.example.Progetto.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.Progetto.models.Utente;

import lombok.Data;

@Service
@Data
public class DaoUtente implements IDao<Long, Utente>{
    
    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    public Long create(Utente e) {
        String query = "INSERT INTO utente username, password, nome, cognome, email, ruolo VALUES (?, ?, ?, ?, ?, ?)";
        Long id = null;
        id = database.executeDML(query, 
                e.getUsername(),
                e.getPassword(),
                e.getNome(),
                e.getCognome(),
                e.getEmail(),
                e.getRuolo());
        return id;
    }


    
    @Override
    public Map<Long, Utente> read() {
        String query = "SELECT * FROM utente";
        Map<Long, Map<String, String>> ris = database.executeDQL(query);
        Utente u = null;
        Map<Long, Utente> utentiMap = new HashMap<>();
        for (Map<String, String> map : ris.values()) {
            u = context.getBean(Utente.class, map);
            utentiMap.put(u.getId(), u);
        }
        return utentiMap;
    }

    @Override
    public void update(Utente e) {
        String query = "UPDATE utente SET username = ?, password = ?, nome = ?, cognome = ?, email = ?, ruolo = ? WHERE id = ?";
        database.executeDML(query, 
                e.getUsername(),
                e.getPassword(),
                e.getNome(),
                e.getCognome(),
                e.getEmail(),
                e.getRuolo());
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM utente WHERE id = ?";
        database.executeDML(query, String.valueOf(id));
    }

    @Override
    public Utente readById(Long id) {
        String query = "SELECT * FROM utente WHERE id = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(id));
        Utente u = null;
        for (Map<String, String> map : ris.values()) {
            u = context.getBean(Utente.class, map);
        }
        return u;
    }

}