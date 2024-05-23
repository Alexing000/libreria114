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
    public void associaLU(Long idLibro, Long idUtente) {
         getDao().aLibroUtente(idLibro, idUtente);
    }
    public void dissociaLU(Long idLibro, Long idUtente) {
        getDao().dLibroUtente(idLibro, idUtente);
    }
    public Libro findByTitolo(String titolo) {
        return getDao().readByTitolo(titolo);
    }
    public List<Libro> findByGenere(String genere) {
        return getDao().readByGenere(genere);
    }

    public List<Libro> byAnno() {
        return getDao().orderByAnno();
    }

    public List<Libro> byGenere() {
        return getDao().orderByGenere();
    }

    public List<Libro> readByIdUtente(Long id) {
        return getDao().readByUtente(id);
    }
    public List<Libro> readByAutore(Long id) {
        return getDao().readLibriAutore(id);
    }
    //lista delle recensioni di un determinato libro
    public List<Map<String,String>> readRecensioni(Long id) {
        return getDao().readRecensione(id);
    }

    public void aggiungiRecensione(Map<String,String> map,Long idUtente) {

        //prendimi dalla mappa il value della primary key id
    Long idLibro= Long.parseLong(map.get("id"));
    String recensione= map.get("recensione");
if(map.get("recensione")!=null){
       System.out.println("id libro: "+idLibro+" id utente: "+idUtente+" recensione: "+recensione);

        getDao().addRecensione(idLibro, idUtente, recensione);
    
            
    
}
}
}
