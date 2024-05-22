package com.example.Progetto.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    //la view è la pagina html
    //per indicare il percorso della pagina html
    @GetMapping("/homeUtente")
    public String home(HttpSession session,Model model){
        if (session.getAttribute("loggato")==null) {
     

            return "redirect:formLogin";
        }else{
            List<Libro> ris = serviceLibro.byAnno();
List<Autore > autori = serviceAutore.findAll();
            
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
            return "homeUtente.html";
        }
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
    public String home() {
        return "home.html";
    }
    

}
