package com.example.Progetto.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.Progetto.dao.DaoAutore;
import com.example.Progetto.dao.DaoLibro;
import com.example.Progetto.models.Autore;
import com.example.Progetto.models.Libro;
import com.example.Progetto.models.Utente;

@Configuration
public class ModelsContext {
    @Autowired
    private DaoLibro daoLibro;
    @Autowired
    private DaoAutore daoAutore;

    @Bean
    @Scope("prototype")
    public Autore autore (Map<String, String> map) {
        Autore a = new Autore();
        Long id = -1L;
        if(map.containsKey("id")) {
            id = Long.parseLong(map.get("id"));
        }
        a.setId(id);
        a.setNome(map.get("nome"));
        a.setCognome(map.get("cognome"));
        a.setBiografia(map.get("biografia"));
        a.setRating(Double.parseDouble(map.get("rating")));
        a.setUrl(map.get("url_autore"));
        a.setLibri(daoLibro.readLibriAutore(id));
        return a;

    }


    @Bean
    @Scope("prototype")
    public Libro libro (Map<String, String> map) {
        Libro l = new Libro();
        Long iLong = -1L;
        if(map.containsKey("id")) {
            iLong = Long.parseLong(map.get("id"));
        }
        l.setId(iLong);
        l.setTitolo(map.get("titolo"));
        l.setTrama(map.get("trama"));
        l.setAutore(map.get("autore"));
        l.setNPagine(Integer.parseInt(map.get("nPagine")));
        l.setGenere(map.get("genere"));
        l.setDataPubblicazione(Integer.parseInt(map.get("dataPubblicazione")));
        l.setRating(Double.parseDouble(map.get("rating")));
        l.setUrl(map.get("url"));
        

        return l;
    }

    //creare bean di tipo Utente
    @Bean
    @Scope("prototype")
    public Utente utente(Map<String, String> map) {
        Utente u = new Utente();
        Long id = -1L;
        if(map.containsKey("id")) {
            id = Long.parseLong(map.get("id"));
        }
        u.setId(id);
        u.setUsername(map.get("username"));
        u.setPassword(map.get("password"));
        u.setNome(map.get("nome"));
        u.setCognome(map.get("cognome"));
        u.setEmail(map.get("email"));
        u.setRuolo(map.get("ruolo"));
        u.setLibri(daoLibro.readByUtente(id));
        return u;
    }



}
