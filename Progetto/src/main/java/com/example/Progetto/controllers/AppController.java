package com.example.Progetto.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Progetto.models.Libro;
import com.example.Progetto.models.Utente;
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


    //la view è la pagina html
    //per indicare il percorso della pagina html
    @GetMapping("/home")
    public String home(HttpSession session,Model model){
        if (session.getAttribute("loggato")==null) {
            System.out.println(session.getAttribute("utente"));
            System.out.println("loggato");
            return "redirect:formLogin";
        }else{

            List<Libro> ris = serviceLibro.byAnno();
            model.addAttribute("libri", ris);
    List<Libro> ris2 = new ArrayList<Libro>();
    for (int i = 0; i < 5; i++) {
        ris2.add(ris.get(i));
    }
    model.addAttribute("libr", ris2);
            return "home.html";
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
                //verifico il ruolo dell'utente
                String ruolo= utenteLoggato.getRuolo();

                System.out.println(utenteLoggato);

                if (ruolo.equalsIgnoreCase("admin")) {
                    return "redirect:/home";
                }else if (ruolo.equalsIgnoreCase("user")){
                    return "redirect:/home";
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
    

}
