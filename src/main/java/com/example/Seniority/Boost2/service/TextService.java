package com.example.Seniority.Boost2.service;

import com.example.Seniority.Boost2.entity.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface TextService {
    Text saveText(Text text);
    void borrarText (Long id);
    List<String> arrayCadenaSeparadaPorParametro(String cadena, int paramUsuario);
    HashMap<String, Number> mapTextoRepeticiones(List cadenaSeparada);
    Optional<Text> findTextById(Long id);
    List<Text> pagination(String page, String rpp);
    boolean existsHashWithChars(Text text);
    String convertHash(String hash);
    List<Text> pagAndFindByChars(String page, String rpp, String chars);
}

