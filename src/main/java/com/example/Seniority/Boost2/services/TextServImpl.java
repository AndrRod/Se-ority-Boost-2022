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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;



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
    public List<Text> findAllText() {
        if(repositoryText.findAll().isEmpty()) throw new NotFoundException("no user found") ;
        return repositoryText.findAll();
    }

    public String convertHash(String hash){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashing = argon2.hash(1, 1024, 1, hash);
        return hashing;
    }
}
