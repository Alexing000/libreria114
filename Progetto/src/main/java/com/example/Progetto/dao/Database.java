package com.example.Progetto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;


@ConditionalOnProperty(name = "db.type", havingValue = "mysql")
@Service
public class Database implements IDatabase{
    @Value("${db.mysql.username}")
    private  String username;
    @Value("${db.mysql.password}")
    private  String password;
    @Value("${db.mysql.path}")
    private  String path;
    @Value("${db.mysql.timezone}")
    private String timezone;
    @Value("${db.mysql.schema}")
    private String nomeDb;

    private Connection connection;


 public Database() {
 }

@PostConstruct//avvia metodo dopo aver creato costruttore
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path + nomeDb + timezone, username, password);
        }
        catch(SQLException e){
            System.out.println("Errore connesione: " + e.getMessage());
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver non trovato");
        }
    }
    public void closeConnection(){
        try{
            connection.close();
        }
        catch(SQLException e){
            System.out.println("Errore chiusura connessione");
        }
    }
    public Connection getConnection(){
        return connection;
    }
    @Override
    public Long executeDML(String query, Object... params) {
      
        Long id=1L;
        PreparedStatement ps=null;
        ResultSet rs=null;
     
    try{
        ps = connection.prepareStatement(query);
        for(int i=0;i<params.length;i++){
            ps.setString(i+1, params[i].toString());
        }
        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        if(rs.next()){
            id = rs.getLong(1);
        }
        if(ps != null){
            ps.close();
        }
        if(rs != null){
            rs.close();
        }
    } catch(Exception e){
        System.out.println("Errore esecuzione query: " + e.getMessage());
        e.printStackTrace();
        return -2L;
    }
    return id;

    }
    @Override
    public Map<Long, Map<String, String>> executeDQL(String query, Object... params) {
        
      Map<Long,Map<String,String>> result = new HashMap<>();
      PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(query);
            for(int i=0;i<params.length;i++){
                ps.setString(i+1, params[i].toString());
            }
            rs = ps.executeQuery();
            Map<String,String> row;
            while(rs.next()){
                 row = new HashMap<>();
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
                    row.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                result.put(rs.getLong("id"), row);
            }
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        } catch(Exception e){
            System.out.println("Errore esecuzione query: " + e.getMessage());
            e.printStackTrace();
    }
    return result;
    }
}


