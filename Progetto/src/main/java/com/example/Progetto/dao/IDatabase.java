package com.example.Progetto.dao;

import java.util.Map;

public interface IDatabase {
      Long executeDML(String query, Object... params);
      Map<Long,Map<String,String>> executeDQL(String query, Object... params);

}
