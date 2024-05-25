package com.example.Progetto.dao;

import java.util.ArrayList;
import java.util.Comparator;
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
        String query = "INSERT INTO libro (titolo, trama, autore, nPagine, genere, dataPubblicazione, rating, url) VALUES (?, ?, ?, ?, ?, ?, ,?)";
        Long id = null;
        if (e != null && e instanceof Libro){
            id = database.executeDML(query,
            ((Libro)e).getTitolo(),
            ((Libro)e).getTrama(),
            ((Libro)e).getAutore(),
            String.valueOf(((Libro)e).getNPagine()),
            ((Libro)e).getGenere(),
            String.valueOf(((Libro)e).getDataPubblicazione()),
            String.valueOf(((Libro)e).getRating()),
            ((Libro)e).getUrl());
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
            String.valueOf(e.getUrl()),
            String.valueOf(e.getId()));
            


    }

    
    public void updatePagineLette(Long idLibro, Long idUtente, int pagineLette) {
        String query = "UPDATE associa SET pagineLette = ? WHERE id_libro = ? AND id_utente = ?";
        database.executeDML(query, String.valueOf(pagineLette), String.valueOf(idLibro), String.valueOf(idUtente));
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM libro WHERE id = ?";
        database.executeDML(query, String.valueOf(id));
    }
    public void aLibroUtente(Long idLibro, Long idUtente) {
        String query = "INSERT INTO associa (id_libro, id_utente) VALUES (?, ?)";
        database.executeDML(query, String.valueOf(idLibro), String.valueOf(idUtente));
    }
    public void dLibroUtente(Long idLibro, Long idUtente) {
        String query = "DELETE FROM associa WHERE id_libro = ? AND id_utente = ?";
        database.executeDML(query, String.valueOf(idLibro), String.valueOf(idUtente));
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
        String query = "SELECT * FROM libro ORDER BY dataPubblicazione desc";
        Map<Long, Map<String, String>> libri = database.executeDQL(query);
        Libro l = new Libro();
        List<Libro> libriList = new ArrayList<Libro>();

        for (Map<String, String> map : libri.values()) {
            l = context.getBean(Libro.class, map);
            libriList.add(l);
        }



        libriList.sort(Comparator.comparing(Libro::getDataPubblicazione).reversed());
        return libriList;
    }

public List<Libro> readByGenere(String genere) {
        String query = "SELECT * FROM libro WHERE genere = ?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, genere);
        Libro l = null;
        List<Libro> libriList = new ArrayList<Libro>();
        for (Map<String, String> map : ris.values()) {
            l = context.getBean(Libro.class, map);
            libriList.add(l);
        }
        return libriList;
    }

    public List<Libro> orderByGenere(){
        String query = "SELECT * FROM libro WHERE genere = (SELECT genere FROM libro GROUP BY genere ORDER BY COUNT(*) DESC LIMIT 1);";
        Map<Long, Map<String, String>> libri = database.executeDQL(query);
        Libro l = new Libro();
        List<Libro> libriList = new ArrayList<Libro>();

        for (Map<String, String> map : libri.values()) {
            l = context.getBean(Libro.class, map);
            libriList.add(l);
        }
    
        libriList.sort(Comparator.comparing(Libro::getGenere));
        return libriList;
    }

    public List<Libro> orderByRatings(){
        String query = "SELECT * FROM libro ORDER BY rating desc";
        Map<Long, Map<String, String>> libri = database.executeDQL(query);
        Libro l = new Libro();
        List<Libro> libriList = new ArrayList<Libro>();

        for (Map<String, String> map : libri.values()) {
            l = context.getBean(Libro.class, map);
            libriList.add(l);
        }
    
        libriList.sort(Comparator.comparing(Libro::getRating).reversed());
        return libriList;
    }



    public List<Libro> readByUtente(Long id)
    {
       String query=" select l.* from libro as l join associa as a on a.id_libro=l.id join utente as u on a.id_utente=u.id where u.id=?";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(id));
        Libro l = null;
        List<Libro> libriList = new ArrayList<Libro>();
        for (Map<String, String> map : ris.values()) {
            l = context.getBean(Libro.class, map);
            libriList.add(l);
        }
        return libriList;
    }
    //cercare i libri di un autore
    public List<Libro> readLibriAutore(Long id) {
        String query = " select l.* from autore as a join libro as l on l.id_autore=a.id where a.id=?;";
        Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(id));
        Libro l = null;
        List<Libro> libri = new ArrayList<>();
        for (Map<String, String> map : ris.values()) {
            l = context.getBean(Libro.class, map);
            libri.add(l);
        }
        return libri;
    }
//cercare recensione per ogni libro
public List<Map<String,String>> readRecensione(Long id) {
    String query =" select u.id,u.username ,a.recensione from libro as l join associa as a on a.id_libro=l.id join utente as u on a.id_utente=u.id where l.id=?;";
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(id));
    List<Map<String,String>> libriList = new ArrayList<Map<String,String>>();
    for (Map<String, String> map : ris.values()) {
        libriList.add(map);
    }
    

    return libriList;
   
}
//aggiungere recensione ad un libro in base all'utente in sessione
public void addRecensione(Long idLibro, Long idUtente, String recensione) {
    String query = "update associa set recensione=? where id_libro=? and id_utente=?";

   
    database.executeDML(query, recensione, String.valueOf(idLibro), String.valueOf(idUtente));
    System.out.println(query);
}
public int readPagineLette(Long idLibro, Long idUtente) {
    String query = "SELECT pagineLette FROM associa WHERE id_libro =? AND id_utente =?";
    System.out.println("id libror: "+idLibro+" id utenter: "+idUtente);
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idLibro), String.valueOf(idUtente));
    int pagineLette = 0;
    for (Map<String, String> map : ris.values()) {
        //se la mappa non è vuota allora la variabile pagineLette è uguale a 0
        if (map.get("pagineLette") != null)
        pagineLette = Integer.parseInt(map.get("pagineLette"));
        
    }
    return pagineLette;
}
//se esiste un'associazione tra l'id del libro e l'id dell'utente allora la variabile associazione è true
public boolean readAssociazione(Long idLibro, Long idUtente) {
    String query = "SELECT * FROM associa WHERE id_libro = ? AND id_utente = ?";
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idLibro), String.valueOf(idUtente));
    System.out.println("dimensione"+ris.size());
    if (ris.size() > 0) {
        return true;
    }
    return false;
}
public void vota(Long idLibro, double voto) {
    String query = "UPDATE libro SET rating = ? WHERE id = ?";
    database.executeDML(query, String.valueOf(voto), String.valueOf(idLibro));
}
public double readRatingPersonale(Long idLibro, Long idUtente) {
    String query = "SELECT ratingPersonale FROM associa WHERE id_libro = ? AND id_utente = ?";
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idLibro), String.valueOf(idUtente));
    double voto = 0;
    for (Map<String, String> map : ris.values()) {
        if(map.get("ratingPersonale")!=null)
        {
        voto = Double.parseDouble(map.get("ratingPersonale"));
        return voto;
        }
       
    }
     
    return -1;
  
}
public void addRatingPersonale(Long idLibro, Long idUtente, Double voto) {
    String query = "UPDATE associa SET ratingPersonale = ? WHERE id_libro = ? AND id_utente = ?";
    if(voto==-1)
    {
        
        database.executeDML(query,"", String.valueOf(idLibro), String.valueOf(idUtente));
    }
    else{
    System.out.println("il vtooo"+voto);
    database.executeDML(query, String.valueOf(voto), String.valueOf(idLibro), String.valueOf(idUtente));
    }
   
}
public int numeroVotazioni(double idLibro){
    String query = "SELECT COUNT(*) FROM associa WHERE ratingPersonale IS NOT NULL and id_libro=?";
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idLibro));
    int numeroVotazioni = 0;
    for (Map<String, String> map : ris.values()) {
        //se la mappa è vuota allora la variabile numeroVotazioni è uguale a 0

      
        numeroVotazioni = Integer.parseInt(map.get("COUNT(*)"));
    }
    return numeroVotazioni;
}
public double sommaVotazioni(double idLibro){
    String query = "SELECT SUM(ratingPersonale) FROM associa WHERE ratingPersonale IS NOT NULL and id_libro=?";
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idLibro));
    double sommaVotazioni = 0;
    for (Map<String, String> map : ris.values()) {
        if(map.get("SUM(ratingPersonale)")!=null)
        sommaVotazioni = Double.parseDouble(map.get("SUM(ratingPersonale)"));
    }
    return sommaVotazioni;
}
public double readVoti(Long idLibro){
   //voto del libro con id =idLibro
   String query ="select rating from libro where id=?";
   System.out.println(query);
    Map<Long, Map<String, String>> ris = database.executeDQL(query, String.valueOf(idLibro));
    System.out.println(ris);
    double voti = 0;
    for (Map<String, String> map : ris.values()) {
        voti = Double.parseDouble(map.get("rating"));
    }
    System.out.println("voti: "+voti);
    return voti;
}
}