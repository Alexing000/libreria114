package com.example.Progetto.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.Progetto.models.Autore;
import com.example.Progetto.models.Libro;
import com.example.Progetto.services.ServiceAutore;
import com.example.Progetto.services.ServiceLibro;

import com.example.Progetto.services.ServiceUtente;
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
    @Autowired
    private ServiceUtente serviceUtente;

   


    //htpps://localhost:8080/libro/all
    @GetMapping("/all")
    public String all(Model model,HttpSession session){
     List<Libro> ris = serviceLibro.findAll();      
  

        Long idUtente = (Long) session.getAttribute("idUtente");
        List<Libro> ris2 = serviceLibro.readByIdUtente(idUtente);
        for(Libro l:ris){
            for(Libro l2:ris2){
                if(l.getId()==l2.getId()){
                    l.setTitolo(l.getTitolo()+" ");
                }
            }
        }

    

        model.addAttribute("libri", ris);
        return "archivioCompleto.html";
    }

    @PostMapping("/votoo")
 public String voto(@RequestParam(name="idLibro", defaultValue = "0") Long id, 
 @RequestParam(name="rating", defaultValue = "0") double voto,@RequestParam(name="votoVecchio", defaultValue = "0") double votoVecchio,Model model,HttpSession session){
 Long idUtente = (Long) session.getAttribute("idUtente");
    double votoFinale=(voto+votoVecchio)/2;
      serviceLibro.vota(id, votoFinale);
      serviceLibro.addRatingPersonale(id,idUtente, voto);
        return "redirect:/api/libro/byId?idLibro="+id;
    }

    

    @GetMapping("byId")
    public String dettagliLibro(@RequestParam(name="idLibro", defaultValue = "0") Long id,Model model,HttpSession session){
        Libro l = serviceLibro.findById(id);
        List<Map<String,String>> ris =   serviceLibro.readRecensioni(id);
        Long idUtente = (Long) session.getAttribute("idUtente");
        int paginelette = serviceLibro.readPagineLette(id, idUtente);
        String username = (String) session.getAttribute("username");
        double votoUtente=serviceLibro.readRatingPersonale(id, idUtente);
//se esiste un'associazione tra l'id del libro e l'id dell'utente allora la variabile associazione è true
        boolean associazione=serviceLibro.readAssociazione(id, idUtente);
     
       //se ris contiene nella primary key recensione una recensione allora la variabile avereRec è true
       if(id==null)
        model.addAttribute("error", "id non valido!");

       for(Map<String,String> m:ris){

           if(m.get("recensione")!=null&&m.get("username").equals(username)){
               model.addAttribute("avereRec", "true")    ; 
        
              break;
           }
           else
           {

                model.addAttribute("avereRec", "false");
             
               break;
           }
        }

       //id del libro
    System.out.println("ratingPersonale"+votoUtente);
       model.addAttribute("ratingPersonale", votoUtente);
        model.addAttribute("idLibro", id);
       model.addAttribute("paginelette", paginelette);
      model.addAttribute("associazione", associazione);
  
        model.addAttribute("recensioni", ris);
        model.addAttribute("libro", l);
        return "dettaglioLibro.html";
    }
    @GetMapping("/recenti")
    public String orderBy(Model model){
        List<Libro> ris = serviceLibro.byAnno();
  
        model.addAttribute("libri", ris);
        return "libriOrderUscita.html";
       
    }

    @GetMapping("/ratings")
    public String orderByRatings(Model model){
        List<Libro> ris = serviceLibro.byRatings();
  
        model.addAttribute("libri", ris);
        return "libriOrderRatings.html";
       
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

//aggiungere recensione ad un libro in base all'utente in sessione

@PostMapping("/aggiungiRecensione")
public String aggiungiRecensione(@RequestParam Map<String,String> params,Model model,HttpSession session){


    
    Long idUtente = (Long) session.getAttribute("idUtente");
   
    serviceLibro.aggiungiRecensione(params, idUtente);
    //ottieni l'id del da params
    Long id = Long.parseLong(params.get("id"));
    //ritorna alla pagina dei libriutenti
    return "redirect:/api/libro/byId?idLibro="+id;


}

    @GetMapping("/byAutore")
    public String orderByAutore(@RequestParam(name="idAutore", defaultValue = "0") Long id,Model model){
        List<Libro> ris = serviceLibro.readByAutore(id);
  
        model.addAttribute("libri", ris);
        return "archivioCompleto.html";
}
@PostMapping("challenge")
public String libriChallenge(@RequestParam(name="libriChallenge", defaultValue = "0") int idLibroCh,Model model,HttpSession session){
    //id utenete in sessione
    Long idUtente = (Long) session.getAttribute("idUtente");
    serviceUtente.addLibroChallenge(idUtente, idLibroCh);
    return "redirect:/homeUtente";
}

    /*@GetMapping("/byId")
    public Libro findById(@RequestParam(name="idLibro", defaultValue = "0") Long id,
    @RequestHeader("token")String token){
        if (token.split("-")[0].equals("libro")&&
        Long.parseLong(token.split("-")[1])==id) {
            return serviceLibro.findById(id);
        } else {
            return null;
            
        }
    }*/
   /*  public String findById(@RequestParam(name="idLibro", defaultValue = "1") Long id,
    Model model){
       Libro l= serviceLibro.findById(id);
       if(id==null){
        model.addAttribute("error", "id non valido!");

        return "mainError.html";
       }
       model.addAttribute("idLibro", id);   
       model.addAttribute("libro", l);
         return "dettaglioLibro.html";
    }*/
    @GetMapping("/libriUtente")
    public String libriUtente(Model model, HttpSession session){
        Long idUtente = (Long) session.getAttribute("idUtente");
        List<Libro> ris = serviceLibro.readByIdUtente(idUtente);
 
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
    public String add(@RequestParam Map<String,String> map){
        serviceLibro.insert(map);
        System.out.println(map);
        return "redirect:/api/libro/all";
    }



   @PostMapping("/updatePagineLette")
    public String updatePagineLette(HttpSession session, @RequestParam(name="id", defaultValue = "0") Long idLibro, 
    @RequestParam(name= "nPagineLette", defaultValue = "0") int pagineLette,Model model) {
        
        Long idUtente= (Long) session.getAttribute("idUtente");
        try {
        // Chiamata al servizio per aggiornare le pagine lette
        
        serviceLibro.updatePagine(idLibro, idUtente, pagineLette);
        

            return "redirect:/api/libro/byId?idLibro="+idLibro;
        } catch (Exception e) {
            model.addAttribute("error", "Errore nell'aggiornamento delle pagine lette");
            return "mainError.html";
        }
    }
}
