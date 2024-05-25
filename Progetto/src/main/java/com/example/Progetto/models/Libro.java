package com.example.Progetto.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Libro extends Entity{

    private String titolo;
    private String trama;
    private String autore;
    private int nPagine;
    private String genere;
    private int dataPubblicazione;
    private double rating;
    private String url;
    private int id_autore;
    
}
