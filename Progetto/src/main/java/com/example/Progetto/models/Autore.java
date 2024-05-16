package com.example.Progetto.models;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper=false)
@Data
public class Autore extends Entity {
    private String nome;
    private String cognome;
    private double rating;
    private List<Libro> libri;

    
}
