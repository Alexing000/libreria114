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
import com.example.Progetto.services.ServiceLibro;
import com.example.Progetto.services.ServiceUtente;

import jakarta.servlet.http.HttpSession;
//                      AGGIUNTO UTENTECONTROLLER
@Controller
public class UtenteController {
    @Autowired
    private ServiceUtente serviceUtente;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ServiceLibro serviceLibro;

    
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


    

    

   
    @PostMapping("/modificaUsername")
public String modificaUsername(@RequestParam("newUsername") String newUsername, HttpSession session, Model model) {
    Object idSalvato = session.getAttribute("idUtente");
    Long idUtente = (Long) idSalvato;
    System.out.println("idUtente: " + idUtente);

    if (serviceUtente.readByUserName(newUsername)) {
        model.addAttribute("errorMessage", "Username già esistente. Si prega di sceglierne un altro.");
        return "homeUtente.html"; // Oppure il nome della pagina che mostra il form per modificare il username
    }

    serviceUtente.updateUsername(idUtente, newUsername);
    return "redirect:/homeUtente"; // Usare redirect per evitare il problema del refresh
}
    //metodo per modificare la password: è identico a modificaUser, però va aggiunto un controllo della password inserita per effettuare il login
    //l'utente deve inserire la vecchia password per poterla modificare: se la vecchia password è corretta, allora si può procedere con la modifica
    //altrimenti si restituisce un errore
    @PostMapping("/modificaPassword")
public String modificaPassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, HttpSession session, Model model) {
    Object idSalvato = session.getAttribute("idUtente");
    Long idUtente = (Long) idSalvato;
    System.out.println("idUtente: " + idUtente);

    Utente u = serviceUtente.readById(idUtente);

    // Verifica se la vecchia password è corretta
    if (!u.getPassword().equals(oldPassword)) {
        model.addAttribute("error", "Password attuale errata");
        return "homeUtente.html";
    }

    // Verifica se la nuova password è diversa dalla vecchia password
    if (u.getPassword().equals(newPassword)) {
        model.addAttribute("error", "La nuova password non può essere uguale a quella attuale");
        return "homeUtente.html";
    }

    // Aggiorna la password
    serviceUtente.updatePassword(idUtente, newPassword);
    return "redirect:/homeUtente"; // Usa redirect per evitare il problema del refresh
}
    

    


    //per eliminare un account prendo dalla sessione l'id e passarlo al metodo delete del serviceUtente
    //quando cancello l'utente, si viene reindirizzati al logout
    @PostMapping("/eliminaAccount") 
    public String deleteAccount(HttpSession session){
        Object idSalvato=session.getAttribute("idUtente");
        Long idUtente= (Long) idSalvato;
        serviceUtente.delete(idUtente);
        return "redirect:/home";
    }
   


    @PostMapping("/register")
    public String registerUser(Model model,
                           @RequestParam("confermaPassword") String confermaPassword,
                           @ModelAttribute Utente utente,
                           HttpSession session,
                           @RequestParam Map<String, String> allParams) {
    boolean utenteEsistente = serviceUtente.readByUserName(utente.getUsername());
    boolean emailEsistente = serviceUtente.readByEmail(utente.getEmail());
    boolean passwordNonValida = utente.getPassword().length() < 8 || utente.getPassword().length() > 12;
    boolean passwordNonCorrispondenti = !utente.getPassword().equals(confermaPassword);
if (utenteEsistente || emailEsistente || passwordNonValida || passwordNonCorrispondenti) {
        model.addAttribute("utenteEsistente", utenteEsistente);
        model.addAttribute("emailEsistente", emailEsistente);
        model.addAttribute("passwordNonValida", passwordNonValida);
        model.addAttribute("passwordNonCorrispondenti", passwordNonCorrispondenti);

        // Mostra un messaggio di errore nella vista
        return "registrazioneUtente.html";
    }
    System.out.println(allParams);
    // Altrimenti, procedi con la registrazione dell'utente
    serviceUtente.create(allParams);
 
    session.setAttribute("utente", utente);
    return "confermaRegistrazione.html";
}





}
