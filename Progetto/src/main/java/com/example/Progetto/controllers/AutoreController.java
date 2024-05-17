package com.example.Progetto.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Progetto.models.Autore;
import com.example.Progetto.services.ServiceAutore;
import lombok.Data;

@RestController
@Data
@RequestMapping("/api/autore")
public class AutoreController {

     private final ServiceAutore serviceAutore;

    //htpps://localhost:8080/libro/all
    @GetMapping("/all")
    public ResponseEntity<List<Autore>> all(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceAutore.findAll());
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
    public Autore findByNome(@RequestParam(name="nome", defaultValue = " ")String nome,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("autore")&&
        token.split("-")[1].contains(nome)) {
            return serviceAutore.findByNome(nome);
        } else {
            return null;
            
        }
    }



    

    @PostMapping("/add")
    public ResponseEntity<Autore> add(@RequestBody Map<String, String> map){
        
        Autore l = serviceAutore.insert(map);
        return ResponseEntity.status(HttpStatus.CREATED).body(l);

    }


    
}
