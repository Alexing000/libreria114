package com.example.Progetto.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Progetto.models.Libro;
import com.example.Progetto.services.ServiceLibro;

import jakarta.annotation.PostConstruct;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
@Data
public class StartupInit {
    private Map<Long, Libro> map;
    @Autowired
    private ServiceLibro serviceLibro;
    private List<Map<String,String>> map1;

    @PostConstruct
    public void init() {
        map = new HashMap<>();

       
    }
  
}
