package com.example.Progetto.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Progetto.models.Utente;
import com.example.Progetto.services.ServiceUtente;

import jakarta.servlet.http.HttpSession;
//                      AGGIUNTO UTENTECONTROLLER
@Controller
public class UtenteController {
    @Autowired
    private ServiceUtente serviceUtente;

    @Autowired
    private ApplicationContext applicationContext;

    
    @GetMapping("/signup")
    public String register(Model model) {
        Utente u= applicationContext.getBean("utente", Utente.class);
        model.addAttribute("utente", u);
        return "registrazioneUtente.html";
    }

    
    

    //questo metodo permette di registrare un utente
    //svolge la funzione di legare i dati inviati dalla form di registrazione
    //all'oggetto utente e di inviarli al database
    //in questo modo potrò verificare se i dati sono corretti 
    //che la password sia uguale a quella di conferma
    //e che il db non sia già presente nel db


    @PostMapping("/register")
    public String registerUser(Model model,
    @RequestParam("confermaPassword") String confermaPassword,
    @ModelAttribute Utente utente,
    HttpSession session,
    @RequestParam Map<String,String> allParams){//il paramento HttpSession rappresenta una sessione Http
        //è un oggetto che permette di memorizzare informazioni relative alla sessione
        //in questo caso memorizzerò l'utente loggato
        if(serviceUtente.readByUserName(utente.getUsername())){
            model.addAttribute("error", "Username già esistente");
            return "registrazioneUtente.html";
        }
        //verifico che la password sia lunga almeno 8 caratteri e non superiore ai 12
        if(utente.getPassword().length() < 8 || utente.getPassword().length() > 12){
            model.addAttribute("error", "Password non valida");
            return "registrazioneUtente.html";
        }


        
       
            
        
        //verifico che la password sia uguale a quella di conferma
        if(!utente.getPassword().equals(confermaPassword)){
            model.addAttribute("error", "Password non corrispondenti!");
            return "registrazioneUtente.html";
        }
        //se la registrazione va a buon fine
        serviceUtente.create(allParams);
        //aggiungo l'utente alla sessione
        session.setAttribute("utente", utente);

        return "confermaRegistrazione.html";
        
    }

    





}
