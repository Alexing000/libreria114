package com.example.Progetto.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Progetto.models.Autore;
import com.example.Progetto.models.Libro;
import com.example.Progetto.services.ServiceAutore;
import com.example.Progetto.services.ServiceLibro;

import lombok.Data;

@Controller
@Data
@RequestMapping("/api/autore")
public class AutoreController {
@Autowired
     private  ServiceAutore serviceAutore;
     @Autowired
     private ServiceLibro serviceLibro;

    //htpps://localhost:8080/libro/all
    @GetMapping("/alll")
    public ResponseEntity<List<Autore>> all(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceAutore.findAll());
    }
    @GetMapping("/all")
    public String all(Model model){
        List<Autore> autori = serviceAutore.findAll();
   
        for (Autore autore : autori) {
           serviceLibro.readByAutore(autore.getId());


            
        }
       System.out.println(autori);
        model.addAttribute("autore", autori);
        return "archivioAutori.html";
    }

    @GetMapping("/byId")
    public Autore findById(@RequestParam(name="idAutore", defaultValue = "0") Long id,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("autore")&&
        Long.parseLong(token.split("-")[1])==id) {
            return serviceAutore.findById(id);
        } else {
            return null;
            
        }
    }


    //http://localhost:8080/api/libro/byTitolo?titolo=titolo
    @GetMapping("/byNome")
    public String findByNome(@RequestParam(name="nome", defaultValue = "") String nomeC,Model model){
         
      
  

 
        Autore a = serviceAutore.findByNome( nomeCognome(nomeC));
         model.addAttribute("autore", a);
        return  "archivioAutori.html";
    }



    

    @PostMapping("/add")
    public ResponseEntity<Autore> add(@RequestBody Map<String, String> map){
        
        Autore l = serviceAutore.insert(map);
        return ResponseEntity.status(HttpStatus.CREATED).body(l);

    }


    private String nomeCognome(String nomec) {
         
        //creare una stringa in ordine inverso
        String nome = new StringBuilder(nomec).reverse().toString();

        //eliminare tutti i caratteri finchè non incontra uno spazio vuoto
     
        int spaceIndex = nome.indexOf(" ");
        if (spaceIndex != -1) {
            nome = nome.substring(spaceIndex + 1);
        }
       
       
       
       
     
        if(nome.startsWith("ed"))
        nome=nome.substring(3, nome.length());
    
   
        return new StringBuilder(nome).reverse().toString();
    }
}
