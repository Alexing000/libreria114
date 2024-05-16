package com.example.Progetto.configuration;

import java.sql.Date;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import com.example.Progetto.models.Autore;
import com.example.Progetto.models.Libro;

@Configuration
public class ModelsContext {
    

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
        a.setRating(Double.parseDouble(map.get("rating")));
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
        l.setDataPubblicazione(Date.valueOf((map.get("dataPubblicazione"))));
        l.setRating(Double.parseDouble(map.get("rating")));

        return l;
    }
}
