package com.example.Progetto.controllers;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Progetto.models.Autore;
import com.example.Progetto.models.Libro;
import com.example.Progetto.models.Utente;
import com.example.Progetto.services.ServiceAutore;
import com.example.Progetto.services.ServiceLibro;
import com.example.Progetto.services.ServiceUtente;


import jakarta.servlet.http.HttpSession;
//                      AGGIUNTO APPCONTROLLER
@Controller
public class AppController {

    @Autowired
    private ServiceUtente serviceUtente;
       @Autowired
    private  ServiceLibro serviceLibro;
    @Autowired
    private ServiceAutore serviceAutore;
    @Autowired
    private StartupInit startupInit;
    private static boolean hasRun = false;

 

    //la view è la pagina html
    //per indicare il percorso della pagina html
     @PostMapping("/api/count")
    @ResponseBody
    public void receiveDeleteCount(@RequestBody Map<String, Integer> payload, HttpSession session) {

        //se count è null, allora count sarà uguale a 0
      //  
        Integer count = payload.get("count");
      
            session.setAttribute("count", count);
    
   
    }
    
    @GetMapping("/homeUtente")
    public String home(HttpSession session,Model model){
        if (session.getAttribute("loggato")==null) {
     

            return "redirect:formLogin";
        }else{



Map<Long, Libro> map = startupInit.getMap();
List<Map<String, String>> map1 = startupInit.getMap1();

            List<Libro> ris = serviceLibro.byAnno();
            Integer deleteCount = session.getAttribute("count")==null?0:(Integer)session.getAttribute("count");


           
List<Autore > autori = serviceAutore.findAll();
//id dell'utente in sessione
            Long idUtente = (Long) session.getAttribute("idUtente");
            if (!hasRun) {
                for(Long i=1L; i<=serviceUtente.readLibriLetti(idUtente)-libriInLIsta(idUtente); i++) {
                    map.put(-i, null);
                }
           
                hasRun = true;
            }
         
    
            model.addAttribute("libri", ris);
            model.addAttribute("autori", autori);
            model.addAttribute("libriChallenge", serviceUtente.readLibriChallenge(idUtente));
      

int libriLetti=libriLetti(idUtente,ris,deleteCount,map);
    

            model.addAttribute("nlibriUtente", libriLetti);
          boolean merito=merito(libriLetti, serviceUtente.readLibriChallenge(idUtente));
            model.addAttribute("merito",merito);
            List<Libro> ris2 = new ArrayList<Libro>();
            
            for (int i = 0; i < 5; i++) {
                ris2.add(ris.get(i));
                
            }
            int appoggio = 0;

            List<Libro> ris3 = genereUtente(idUtente,session);
            model.addAttribute("libri2", ris3);
            List<Libro> ris4 = new ArrayList<Libro>();
            if (ris3.size() > 5) {
                appoggio = 5;
            } else {
                appoggio = ris3.size();
            }
            
            for (int i = 0; i < appoggio; i++) {
                ris4.add(ris3.get(i));
            }

            model.addAttribute("libr", ris2);

            model.addAttribute("librG", ris4);
            return "homeUtente.html";
        }
    }



private boolean merito(int nLibriUtente, int libriChallenge){
boolean merito=false;
    if(nLibriUtente>=libriChallenge){
        merito=true;
    }
    return merito;


}
 
    private int libriInLIsta(Long idUtente){
        List<Libro> lista = serviceLibro.readByIdUtente(idUtente);
        List<Libro> listaTot = serviceLibro.findAll();
        int sommaLibri=0;
   
        for (Libro libro : lista) {
            for (Libro libro2 : listaTot) {
                if (libro.getId()==libro2.getId()) {
                    if( serviceLibro.readPagineLette(libro.getId(), idUtente)==libro2.getNPagine())
                    {
                    sommaLibri++;
                 
                }
            }
        }
    

    }
    return sommaLibri;
}
    private int libriLetti(Long idUtente,List<Libro> listaTot, int deleteCount,Map<Long, Libro> map){
     

        int libriLetti=serviceUtente.readLibriLetti(idUtente);
        List<Libro> lista2 = serviceLibro.readByIdUtente(idUtente);
      
   
        for (Libro libro : lista2) {
            for (Libro libro2 : listaTot) {
                if (libro.getId()==libro2.getId()) {
                    if( serviceLibro.readPagineLette(libro.getId(), idUtente)==libro2.getNPagine())
                    {
               
                    map.put(libro.getId(), libro);
                    }
                }
            }
        }
        lista2 = serviceLibro.readByIdUtente(idUtente);
    List<Long> bookIds = lista2.stream().map(Libro::getId).collect(Collectors.toList()); // Get the list of book IDs

    for (Long id : new HashSet<>(map.keySet())) {
    
        if (!bookIds.contains(id)) {
            map.remove(id); 
          Random random = new Random();
Long newKey = 500L + random.nextLong() % (Long.MAX_VALUE - 500); 
            map.put(newKey, null); 
        }
    }

       
            serviceUtente.addLibroLetti(idUtente, map.size());
       
            return map.size();
            
          

                
              
       









     
       /*  int libriLetti=serviceUtente.readLibriLetti(idUtente);
        List<Libro> lista = serviceLibro.readByIdUtente(idUtente);
        int sommaLibri=0;
        //quando gli id libro della listaUtente sono uguali a quelli della listaTot, incremento sommaLibri
        for (Libro libro : lista) {
            for (Libro libro2 : listaTot) {
                if (libro.getId()==libro2.getId()) {
                    if( serviceLibro.readPagineLette(libro.getId(), idUtente)==libro2.getNPagine())
                    sommaLibri++;
                }
            }
        }
 
  





if(deleteCount>0)


{
    if(sommaLibri<libriLetti)
    {
        serviceUtente.addLibroLetti(idUtente, libriLetti);
        return libriLetti;
    }
    else
    {
        sommaLibri+=libriLetti;
sommaLibri-=libriLetti;
sommaLibri+=deleteCount;
        serviceUtente.addLibroLetti(idUtente, sommaLibri);
        return sommaLibri;
    }
}else
{
    if(sommaLibri<libriLetti)
    {
        serviceUtente.addLibroLetti(idUtente, libriLetti);
        return libriLetti;
    }
    else
    {
        sommaLibri+=libriLetti;
sommaLibri-=libriLetti;

        serviceUtente.addLibroLetti(idUtente, sommaLibri);
        return sommaLibri; 
}
}


  */      
    
        
    }

    @GetMapping("/formLogin")
    public String login(){
        return "formLogin.html";
    }

    

    @PostMapping("/login")
    public String login(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        Model model,
        HttpSession session){
            Utente utenteLoggato= serviceUtente.readByUsernameAndPassword(username, password);
        
            if (utenteLoggato==null) {
                model.addAttribute("error", "Credenziali non valide");
                return "formLogin.html";
            }
            else{
                session.setAttribute("loggato", "ok");
                session.setAttribute("utente", utenteLoggato);
                session.setAttribute("idUtente", utenteLoggato.getId());
                //nome utente in sessione
                session.setAttribute("username", utenteLoggato.getUsername());
                //verifico il ruolo dell'utente
                String ruolo= utenteLoggato.getRuolo();


                if (ruolo.equalsIgnoreCase("admin")) {
                    session.setAttribute("ruolo", "admin");
                    return "redirect:/homeUtente";
                }else if (ruolo.equalsIgnoreCase("user")){
                    session.setAttribute("ruolo", "user");
                    return "redirect:/homeUtente";
                }else{
                    session.setAttribute("loggato", null);
                    return "redirect:/home";
                }

                
            }
    }


    //Metodo che permette all'utente di fare il logout dalla sessione
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("loggato", null);
        session.setAttribute("utente", null);
        return "redirect:/home";
    }

    @GetMapping("/error")
    public String error() {
        return "mainError.html";
    }

    @GetMapping("/home")
    public String home(Model model) {

  
        List<Autore > autori = serviceAutore.findAll();
        //id dell'utente in sessione
  

        List<Libro> ris = serviceLibro.byRatings();
        List<Libro> listaOrdineUscita=serviceLibro.byAnno();
        List<Libro> ordineUscitaRidotta= new ArrayList<Libro>();
        for (int i = 0; i < 5; i++) {
            ordineUscitaRidotta.add(listaOrdineUscita.get(i));
            
        }
            
            
            model.addAttribute("libri", ris);
            model.addAttribute("autori", autori);
           
            List<Libro> ris2 = new ArrayList<Libro>();
            
            for (int i = 0; i < 5; i++) {
                ris2.add(ris.get(i));
                
            }
            int appoggio = 0;

            List<Libro> ris3 = serviceLibro.byGenere();
            model.addAttribute("libri2", ris3);
            List<Libro> ris4 = new ArrayList<Libro>();

            if (ris3.size() > 5) {
                appoggio = 5;
            } else {
                appoggio = ris3.size();
            }
            
            for (int i = 0; i < appoggio; i++) {
                ris4.add(ris3.get(i));
            }

            model.addAttribute("libr", ris2);

            model.addAttribute("librG", ris4);

        return "home.html";
    }

    @GetMapping("/gestioneAccount")
    public String gestioneAccount() {
        return "gestioneAccount.html";
    }
    @GetMapping("/chiSiamo")
    public String chiSiamo() {
        return "chiSiamo.html";
    }
    @GetMapping("/FAQ")
    public String FAQ() {
        return "FAQ.html";
    }
    @GetMapping("/lavoraConNoi")
    public String lavoraConNoi() {
        return "lavoraConNoi.html";
    }
    @GetMapping("/privacyPolicy")
    public String privacyPolicy() {
        return "privacyPolicy.html";
    }
    private List<Libro> genereUtente(Long idUtente, HttpSession session){


        List<Libro> ris = serviceLibro.readByIdUtente(idUtente);

        Map.Entry<String, Long> mostFrequentGenreEntry = ris.stream()
        .collect(Collectors.groupingBy(Libro::getGenere, Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .orElse(null);
    
    String mostFrequentGenre = null;
    Long count = 0L;

    if (mostFrequentGenreEntry != null) {
        mostFrequentGenre = mostFrequentGenreEntry.getKey();
        count = mostFrequentGenreEntry.getValue();
    }
     if(count==null)
     count=0L;
    
    
        List<Libro> ris2 = new ArrayList<>();

        if(count==0||count==0L&&ris.isEmpty())
       ris2= serviceLibro.byGenere();
        else
        ris2=serviceLibro.findByGenere(mostFrequentGenre);

        return ris2;
    }
    

}
