package com.example.Seniority.Boost2.services;
import com.example.Seniority.Boost2.entites.Text;
import com.example.Seniority.Boost2.exception.BadRequestException;
import com.example.Seniority.Boost2.exception.NotFoundException;
import com.example.Seniority.Boost2.repository.TextRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TextServImpl implements TextService{

    private final TextRepository repositoryText;

    @Override
    public Text saveText(Text text) {
        if(repositoryText.existsByHash(text.getHash())){
            throw new BadRequestException("The hash '" + text.getHash() + "' is already registered");
        }
        List<String> stringList = arrayCadenaSeparadaPorParametro(text.getHash(), text.getChars());
        log.info(stringList.toString());
        log.info(mapTextoRepeticiones(stringList).toString());
        text.setResult(mapTextoRepeticiones(stringList));
//        text.setHash(convertHash(text.getHash()));
        return repositoryText.save(text);
    }
    @Override
    public void borrarText(Long id) {
        repositoryText.deleteById(id);
    }

    @Override
    public List<String> arrayCadenaSeparadaPorParametro(String cadena, int paramUsuario) {
        List<String> textArrayList = new ArrayList<>();
        String cadenString = cadena.toLowerCase(Locale.ROOT);
        int parametroUsuario = paramUsuario;
        int inicial = 0;
        int fin = 0;
        int largoTexto = cadena.length();
        do {
            fin+=parametroUsuario;
            if(fin<largoTexto){
                String st = cadenString.substring(inicial, fin);
                System.out.println(st);
                textArrayList.add(st);
            }
            if(fin>=largoTexto){
                String st = cadenString.substring(inicial, largoTexto);
                System.out.println(st);
                textArrayList.add(st);
            }
            inicial+=parametroUsuario;
        }while(inicial<largoTexto);
        return textArrayList;
    }

    @Override
    public HashMap<String, Number> mapTextoRepeticiones(List cadenaSeparada) {
        return (HashMap<String, Number>) cadenaSeparada.stream().
                collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }


    @Override
    public Optional<Text> findTextById(Long id) {
        if(repositoryText.findById(id).isEmpty()) throw new NotFoundException("Text not found");
        return repositoryText.findById(id);
    }



    @Override
    public List<Text> findByChars(int chars) {
        List<Text> listText = repositoryText.findByChars(chars);
        if(listText.isEmpty()) throw new NotFoundException("no user with that chars found") ;
        return listText;
    }

    @Override
    public Page<Text> findAllText(int pag, int size) {
        if(repositoryText.findAll().isEmpty()) throw new NotFoundException("no user found");
        return  repositoryText.findAll(PageRequest.of(pag, size));
    }

    @Override
    public Page<Text> paginacion(String page, String rpp) {
        int page1;
        int rpp1;
        if(page == null || rpp == null){
            page1 = page == null ? 0 : Integer.valueOf(page);
            rpp1 = page == null ? 10 : Integer.valueOf(rpp) > 100 ? 100 : Integer.valueOf(rpp);
        }
        page1 = Integer.valueOf(page) < 0 ? 0 : Integer.valueOf(page);
        rpp1 = Integer.valueOf(rpp) < 10 ? 10 : Integer.valueOf(rpp) > 100 ? 100 : Integer.valueOf(rpp);
        log.warn(String.valueOf(page1));
        log.warn(String.valueOf(rpp1));
//        page1 = page < 0 ? 0 : page;
//        rpp1 = rpp < 10 ? 10 : rpp > 100 ? 100 : rpp;

//        rpp1 = rpp > 100 ? 100 : rpp;
//        Page page1 = repositoryText.findAll(PageRequest.of(page, rpp));
//        log.warn(String.valueOf(page1.getContent()));
//        if(page1.isEmpty()) throw new NotFoundException("no user found") ;
        if (repositoryText.findAll(PageRequest.of(page1, rpp1)).getContent().isEmpty()) throw new NotFoundException("no user found");
        return repositoryText.findAll(PageRequest.of(page1, rpp1));
    }



    public String convertHash(String hash){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashing = argon2.hash(1, 1024, 1, hash);
        return hashing;
    }
}
