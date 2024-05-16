package com.example.Progetto.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


import com.example.Progetto.models.Autore;

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

}
