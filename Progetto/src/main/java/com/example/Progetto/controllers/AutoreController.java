package com.example.Progetto.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Progetto.models.Autore;

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

    @GetMapping("/all")
    public String all(Model model){
        List<Autore> autori = serviceAutore.findAll();
   
        for (Autore autore : autori) {
           serviceLibro.readByAutore(autore.getId());


            
        }
      
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



    

    @PostMapping("/addu")
    public String add(@RequestParam Map<String, String> map){
        
      serviceAutore.insert(map);
    

        return "redirect:/api/autore/all";

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

    @PostMapping("/update")
    public String update(@RequestParam Map<String, String> map){
        serviceAutore.update(map);
        return "redirect:/api/autore/all";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(name="idAutore", defaultValue = "0") Long id){
        serviceAutore.delete(id);
        return "redirect:/api/autore/all";
    }
}
