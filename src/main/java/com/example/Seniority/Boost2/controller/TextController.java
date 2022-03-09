package com.example.Seniority.Boost2.controller;

import com.example.Seniority.Boost2.entites.Text;
import com.example.Seniority.Boost2.exception.NotFoundException;
import com.example.Seniority.Boost2.exception.ResponseCreate;
import com.example.Seniority.Boost2.services.TextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/text")
public class TextController {
    @Autowired
    private TextService textService;
    @PostMapping(value = "/")
    public ResponseEntity<?> savingText(@Valid @RequestBody Text text, WebRequest request){
            textService.saveText(text);
            return ResponseEntity.ok(new ResponseCreate(text.getId(), ((ServletWebRequest)request).getRequest().getRequestURI()+text.getId()));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getByIdText(@Valid @PathVariable Long id){
        return ResponseEntity.ok(textService.findTextById(id));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteByIdText(@Valid @PathVariable Long id){
        Optional<Text> texto = textService.findTextById(id);
        textService.borrarText(id);
        return new ResponseEntity<>("borrado con exito el texto: " + texto.get().getHash(), HttpStatus.OK);
    }

    @GetMapping(value = "/pag/{page}/{size}")
    public ResponseEntity<?> getAllText(@PathVariable String page, @PathVariable String size){
        return ResponseEntity.ok(textService.findAllText(Integer.valueOf(page), Integer.valueOf(size)));
    }
    @GetMapping
    public ResponseEntity<?> findTextBy(
                                @RequestParam(value = "chars", required = false) String chars,
                                @RequestParam(value = "page", required = false) String page,
                                @RequestParam(value = "rpp", required = false) String rpp)
        {
        if(chars != null)return ResponseEntity.ok(textService.findByChars(parseInt(chars)));
        if(page != null) return ResponseEntity.ok(textService.paginacion(page, "10").getContent());
        if(rpp != null) return ResponseEntity.ok(textService.paginacion("0", rpp).getContent());
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }
//    private static boolean isNumeric(String str){
//        return str != null && str.matches("[0-9.]+");
//    }
}
