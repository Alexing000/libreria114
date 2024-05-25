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
        String query = "INSERT INTO utente (username, password, nome, cognome, email, ruolo,libriChallenge,libriLetti) VALUES (?, ?, ?, ?, ?, ?,?,?)";
        Long id = 0L;
        id = database.executeDML(query, 
                e.getUsername(),
                e.getPassword(),
                e.getNome(),
                e.getCognome(),
                e.getEmail(),
                e.getRuolo(),
                0,
                0);
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

    // Metodo per la ricerca del ruolo di un utente tramite password e username
    public String readByUsernameAndPassword(String username, String password){
        String query = "SELECT ruolo FROM utente WHERE username = ? AND password = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, username, password);
        String ruolo = null; // Initialize ruolo with a default value
        for (Map<String, String> map : ris.values()) {
            ruolo = map.get("ruolo");
            return ruolo;
        }
        return ruolo;
    }
    // NUOVI METODI AGGIUNTI

    public boolean userExists(String username){
        String query = "select * from utente where username = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, username);
        return ris.size() > 0;
    }



    public Map<String,String> autentica(String username, String password) {
        String query = "SELECT * FROM utente WHERE username = ? AND password = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, username, password);
         
        if (ris.size() == 0) {
            return null;
        }
        Map<String, String> firstValue = ris.values().iterator().next();
        System.out.println(firstValue);
        return firstValue;

    }
    public void updateUsername(Long id, String newUsername) {
        String query = "UPDATE utente SET username = ? WHERE id = ?";
        database.executeDML(query, newUsername, id);
    }
    public void updatePassword(Long id, String newPassword) {
        String query = "UPDATE utente SET password = ? WHERE id = ?";
        database.executeDML(query, newPassword, id);
    }

    
    public boolean userExistsByEmail(String email) {
        String query = "SELECT * FROM utente WHERE email = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, email);
        return !ris.isEmpty();
    }
    public int readLibriChallenge(Long idUtente)
    {
        String query ="select libriChallenge from utente where id=?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idUtente));
        int libriChallenge = 0;
        for (Map<String, String> map : ris.values()) {
            if (map.get("libriChallenge") != null)
            {
            libriChallenge = Integer.parseInt(map.get("libriChallenge"));
            return libriChallenge;
            }
        }
        return libriChallenge;
    }
    public void addLibroChallenge(Long idUtente,int libCh)
    {
        String query = "UPDATE utente SET libriChallenge = ? WHERE id = ?";
        database.executeDML(query, libCh, idUtente);
    }
    public int readLibriLetti (Long idUtente)
    {
        String query ="select libriLetti from utente where id=?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idUtente));
        int libriLetti = 0;
        for (Map<String, String> map : ris.values()) {
            if (map.get("libriLetti") != null)
            {
            libriLetti = Integer.parseInt(map.get("libriLetti"));
            return libriLetti;
            }
        }
        return libriLetti;
    }
    public void addLibroLetti(Long idUtente,int libLett)
    {
        String query = "UPDATE utente SET libriLetti = ? WHERE id = ?";
        database.executeDML(query, libLett, idUtente);
    }


}