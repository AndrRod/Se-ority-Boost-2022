package com.example.Seniority.Boost2.services;
import com.example.Seniority.Boost2.entites.Text;
import com.example.Seniority.Boost2.exception.BadRequestException;
import com.example.Seniority.Boost2.exception.NotFoundException;
import com.example.Seniority.Boost2.repository.TextRepository;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        if(text.getChars() < 2) text.setChars(2);
        if(text.getText() == null){
            throw new BadRequestException("The text can´t be null");
        }
        if(text.getChars() > text.getText().length()) text.setChars(text.getText().length());
        if(existsHashWithChars(text)){
            throw new BadRequestException("The same text '"+ text.getText() + "' is already registered with the same chars " + text.getChars());
        }
        List<String> stringList = arrayCadenaSeparadaPorParametro(text.getText(), text.getChars());
        text.setResult(mapTextoRepeticiones(stringList));
        text.setHash(convertHash(text.getText()));
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
//                System.out.println(st);
                textArrayList.add(st);
            }
            if(fin>=largoTexto){
                String st = cadenString.substring(inicial, largoTexto);
//                System.out.println(st);
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
//    @Override
//    public List<Text> findByChars(int chars) {
//        List<Text> listText = repositoryText.findByChars(chars);
//        if(listText.isEmpty()) throw new NotFoundException("no user with that chars found") ;
//        return listText;
//    }
//    @Override
//    public Page<Text> findAllText(int pag, int size) {
//        if(repositoryText.findAll().isEmpty()) throw new NotFoundException("no user found");
//        return  repositoryText.findAll(PageRequest.of(pag, size));
//    }
    @Override
    public boolean existsHashWithChars(Text text) {
        List <Text> hashEqual = repositoryText.findByText(text.getText());
        boolean textEqual = hashEqual.stream().anyMatch(text1 -> text1.getChars() == text.getChars());
        return textEqual;
    }
    @Override
    public String convertHash(String hash){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashing = argon2.hash(1, 1024, 1, hash);
        return hashing;
    }


    @Override
    public List<Text> pagination(String page, String rpp) {
        List<Integer> integers = filtersToPageAnRpp(page, rpp);
        int page1 = integers.get(0);
        int rpp1 = integers.get(1);
        if (repositoryText.findAll(PageRequest.of(page1, rpp1)).getContent().isEmpty()) throw new NotFoundException("no user found");
        return repositoryText.findAll(PageRequest.of(page1, rpp1)).getContent();
    }


    @Override
    public List<Text> pagAndFindByChars(String page, String rpp, String chars) {
        List<Integer> integers = filtersToPageAnRpp(page, rpp);
//        System.out.println(integers);
        int page1 = integers.get(0);
        int rpp1 = integers.get(1);
        int chars1;
//        System.out.println(chars);
        if (chars != null) {
            chars1 = Integer.valueOf(chars);
        } else {
          return pagination(page, rpp);
        }
//        System.out.println(isNumeric(chars));
        List <Text> returnPagination = repositoryText.findAllByPageableAndChars(PageRequest.of(page1, rpp1), chars1).getContent();
        if(returnPagination.isEmpty()) throw new NotFoundException("no user found");
        return returnPagination;
    }

    public List<Integer> filtersToPageAnRpp(String page, String rpp){
        int page1 = 0;
        int rpp1 = 10;
        if (page != null || isNumeric(page)) page1 = Integer.valueOf(page);
        if (rpp != null || isNumeric(rpp)) rpp1 = Integer.valueOf(rpp);
        if (rpp1 > 100) rpp1 = 100;
        if (rpp1 < 10) rpp1 = 10;
        List<Integer> resul = List.of(page1, rpp1);
        return resul;
    }
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
}
