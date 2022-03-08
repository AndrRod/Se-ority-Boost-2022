package com.example.Seniority.Boost2.services;

import com.example.Seniority.Boost2.entites.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public interface TextService {
    Text saveText(Text text);
    void borrarText (Long id);
    List<String> arrayCadenaSeparadaPorParametro(String cadena, int paramUsuario);
    HashMap<String, Number> mapTextoRepeticiones(List cadenaSeparada);
    Optional<Text> findTextById(Long id);
    List<Text> findAllText();
}

