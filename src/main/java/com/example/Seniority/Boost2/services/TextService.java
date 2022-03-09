package com.example.Seniority.Boost2.services;

import com.example.Seniority.Boost2.entites.Text;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public interface TextService {
    Text saveText(Text text);
    void borrarText (Long id);
    List<String> arrayCadenaSeparadaPorParametro(String cadena, int paramUsuario);
    HashMap<String, Number> mapTextoRepeticiones(List cadenaSeparada);
    Optional<Text> findTextById(Long id);
    Page<Text> findAllText(int page, int size);
    List<Text> findByChars(int chars);
    Page<Text> paginacion(String page, String rpp);
}

