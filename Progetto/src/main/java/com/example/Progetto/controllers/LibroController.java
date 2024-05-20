package com.example.Progetto.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model; 

import com.example.Progetto.models.Libro;
import com.example.Progetto.services.ServiceLibro;

import lombok.Data;

@Controller
@RequestMapping("/api/libro")
@Data
public class LibroController {
    @Autowired
    private  ServiceLibro serviceLibro;

    //htpps://localhost:8080/libro/all
    @GetMapping("/all")
    public String all(Model model){
        List<Libro> ris = serviceLibro.findAll();      
        model.addAttribute("libri", ris);
        return "archivioCompleto.html";
    }

    @GetMapping("/recenti")
    public String orderBy(Model model){
        List<Libro> ris = serviceLibro.byAnno();
        model.addAttribute("libri", ris);
        return "libriOrderUscita.html";
       
    }


    @GetMapping("/genere")
    public String orderByGenere(Model model){
        List<Libro> ris = serviceLibro.byGenere();
        model.addAttribute("libri", ris);
        return "libriOrderUscita.html";
       
    }

    @GetMapping("/byId")
    public Libro findById(@RequestParam(name="idLibro", defaultValue = "0") Long id,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        Long.parseLong(token.split("-")[1])==id) {
            return serviceLibro.findById(id);
        } else {
            return null;
            
        }
    }


    //http://localhost:8080/api/libro/byTitolo?titolo=titolo
    @GetMapping("/byTitolo")
    public Libro findByTitolo(@RequestParam(name="titolo", defaultValue = " ")String titolo,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        token.split("-")[1].contains(titolo)) {
            return serviceLibro.findByTitolo(titolo);
        } else {
            return null;
            
        }
    } 
    /*
    @GetMapping("/byAutore")
    public List<Libro> findByAutore(@RequestParam(name="autore", defaultValue = " ")String autore,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        token.split("-")[1].contains(autore)) {
            return serviceLibro.findByAutore(autore);
        } else {
            return null;
            
        }
    }


    @GetMapping("/byGenere")
    public List<Libro> findByGenere(@RequestParam(name="genere", defaultValue = " ")String genere,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        token.split("-")[1].contains(genere)) {
            return serviceLibro.findByGenere(genere);
        } else {
            return null;
            
        }
    }*/

    @PostMapping("/add")
    public ResponseEntity<Libro> add(@RequestBody Map<String, String> map){
        
        Libro l = serviceLibro.insert(map);
        return ResponseEntity.status(HttpStatus.CREATED).body(l);

    }
    
}
