package com.example.Progetto.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.example.Progetto.models.Autore;
import com.example.Progetto.models.Libro;
import com.example.Progetto.services.ServiceAutore;
import com.example.Progetto.services.ServiceLibro;
import jakarta.servlet.http.HttpSession;
import lombok.Data;


@Controller
@RequestMapping("/api/libro")
@Data
public class LibroController {
    @Autowired
    private  ServiceLibro serviceLibro;
    @Autowired
    private ServiceAutore serviceAutore;

   


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
        return "libriOrderGenere.html";
       
    }
    @GetMapping("/byGenere")
    public String orderByGenere(@RequestParam(name="genere",defaultValue = "") String genere,Model model){
        List<Libro> ris = serviceLibro.findByGenere(genere);
  
        model.addAttribute("libri", ris);
        return "archivioCompleto.html";
       
    }




    @GetMapping("/byId")
    public String findById(@RequestParam(name="idLibro", defaultValue = "1") Long id,
    Model model){
       Libro l= serviceLibro.findById(id);
       if(id==null){
        model.addAttribute("error", "id non valido!");

        return "mainError.html";
       }
       model.addAttribute("idLibro", id);   
       model.addAttribute("libro", l);
         return "dettaglioLibro.html";
    }



    @GetMapping("/libriUtente")
    public String libriUtente(Model model, HttpSession session) {
        Long idUtente = (Long) session.getAttribute("idUtente");
        List<Libro> ris = serviceLibro.readByIdUtente(idUtente);
        System.out.println(idUtente);
        model.addAttribute("libri", ris);
        return "libriUtente.html";
    }

    @GetMapping("/aggiungiLibro")
    public String aggiungiLibro(Model model,HttpSession session,@RequestParam(name="idLibro", defaultValue = "0") Long id){
        Long idUtente = (Long) session.getAttribute("idUtente");
        List<Libro> ris = serviceLibro.readByIdUtente(idUtente);
        for(Libro l:ris){
            if(l.getId()==id){
                model.addAttribute("error", "Libro già presente");
                return "mainError.html";
            }
        }
        serviceLibro.associaLU(id, idUtente);
        //libro in base all'id
        Libro l = serviceLibro.findById(id);
        model.addAttribute("inserito", "Libro inserito!!");
        model.addAttribute("libri", l);
       return "libroInserito.html";
     
     
    
       
    }
    @GetMapping("/eliminaDaLista")
    public String eliminaDaLista(Model model,HttpSession session,@RequestParam(name="idLibro", defaultValue = "0") Long id){
        Long idUtente = (Long) session.getAttribute("idUtente");
     
        serviceLibro.dissociaLU(id, idUtente);
       return "redirect:/api/libro/libriUtente";
    }


    /* //http://localhost:8080/api/libro/byTitolo?titolo=titolo
    /*@GetMapping("/byTitolo")
    public Libro findByTitolo(@RequestParam(name="titolo", defaultValue = " ")String titolo,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        token.split("-")[1].contains(titolo)) {
            return serviceLibro.findByTitolo(titolo);
        } else {
            return null;
            
        }
    } */
    @PostMapping("/search")
    public String search(@RequestBody String titolo,Model model){
        System.out.println(titolo);
        String titoloOk=titolo.substring(6,titolo.length());
        if(titoloOk.contains("+") )
            titoloOk=titoloOk.replace("+"," ");

            if(titoloOk.contains("%27") )
            titoloOk=titoloOk.replace("%27","'");
        Libro l = serviceLibro.findByTitolo(titoloOk);
        Autore a = serviceAutore.findByCognome(titoloOk);
        if (l==null) {
       model.addAttribute("autore", a);
        return "archivioAutori.html";
        } else {
            model.addAttribute("libri", l);
            return "archivioCompleto.html";

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
    }*/


    /*@GetMapping("/byGenere")
    public List<Libro> findByGenere(@RequestParam(name="genere", defaultValue = " ")String genere,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        token.split("-")[1].contains(genere)) {
            return serviceLibro.byGenere(genere);
        } else {
            return null;
            
        }
    }*/

    @PostMapping("/add")
    public String add(@RequestBody Map<String,String> map){
        serviceLibro.insert(map);
        return "redirect:/api/libro/all";
    }



   @PostMapping("/updatePagineLette")
    @ResponseBody
    public String updatePagineLette(HttpSession session, @RequestParam(name="id", defaultValue = "0") Long idLibro, 
    @RequestParam(name= "nPagineLette", defaultValue = "0") int pagineLette) {
        
        Long idUtente= (Long) session.getAttribute("idUtente");
        try {
            System.out.println("Id libro: " + idLibro + ", Pagine lette: " + pagineLette + ", Id utente: " + idUtente);
        // Chiamata al servizio per aggiornare le pagine lette
        
        serviceLibro.updatePagine(idLibro, idUtente, pagineLette);
        

            return "Aggiornamento completato con successo";
        } catch (Exception e) {
            return "Errore nell'aggiornamento delle pagine lette: " + e.getMessage();
        }
    }
}
