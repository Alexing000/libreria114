package com.example.Progetto.models;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper=false)
@Data
public class Autore extends Entity {
    private String nome;
    private String cognome;
    private String biografia;
    private double rating;
    private String url;
    private List<Libro> libri;

    
}
