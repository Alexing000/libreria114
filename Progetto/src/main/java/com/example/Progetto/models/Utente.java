package com.example.Progetto.models;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class Utente extends Entity{
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String ruolo;
    private List    <Libro> libri;

}
