package com.example.Progetto.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.Progetto.models.Libro;

import lombok.Data;

@Service
@Data
public class DaoLibro implements IDao<Long, Libro>{


    private final IDatabase database;

    private final ApplicationContext context;

    @Override
    public Long create(Libro e) {
        String query = "INSERT INTO libro (titolo, trama, autore, nPagine, genere, dataPubblicazione, rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Long id = null;
        if (e != null && e instanceof Libro){
            id = database.executeDML(query,
            ((Libro)e).getTitolo(),
            ((Libro)e).getTrama(),
            ((Libro)e).getAutore(),
            String.valueOf(((Libro)e).getNPagine()),
            ((Libro)e).getGenere(),
            String.valueOf(((Libro)e).getDataPubblicazione()),
            String.valueOf(((Libro)e).getRating()));
       }
       return id;
    }

    @Override
    public Map<Long, Libro> read() {
        String query = "SELECT * FROM libro";
        Map<Long, Map<String, String>> libri = database.executeDQL(query);
        Libro l = null;
        Map<Long, Libro> libriMap = new HashMap<>();
        for (Map<String, String> map : libri.values()) {
            l = context.getBean(Libro.class, map);
            libriMap.put(l.getId(), l);
        }
        return libriMap;
        
    }

    @Override
    public void update(Libro e) {
        String query = "UPDATE libro SET titolo = ?, trama = ?, autore = ?, nPagine = ?, genere = ?, dataPubblicazione = ?, rating = ? where id = ?";
        database.executeDML(query, 
            e.getTitolo(),
            e.getTrama(),
            e.getAutore(),
            String.valueOf(e.getNPagine()),
            e.getGenere(),
            String.valueOf(e.getDataPubblicazione()),
            String.valueOf(e.getRating()),
            String.valueOf(e.getId()));
            


    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM libro WHERE id = ?";
        database.executeDML(query, String.valueOf(id));
    }

    @Override
    public Libro readById(Long id) {
        String query = "SELECT * FROM libro WHERE id = ?";
        Map<Long, Map<String, String>> libri = database.executeDQL(query, String.valueOf(id));
        Libro l = null;
        for (Map<String, String> map : libri.values()) {
            l = context.getBean(Libro.class, map);
        }
        return l;
    }

    //cercare un autore per nome 
    public Libro readByTitolo(String titolo) {
        String query = "SELECT * FROM libro WHERE titolo = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, titolo);
        Libro l = null;
        for (Map<String, String> map : ris.values()) {
            l = context.getBean(Libro.class, map);
        }
        return l;
    }

    public List<Libro> orderByAnno(){
        String query = "SELECT titolo,autore FROM libro ORDER BY dataPubblicazione desc limit 5";
        Map<Long, Map<String, String>> libri = database.executeDQL(query);
        Libro l = null;
        List<Libro> libriList = null;
        for (Map<String, String> map : libri.values()) {
            l = context.getBean(Libro.class, map);
            libriList.add(l);
        }
        return libriList;
    } 
}
