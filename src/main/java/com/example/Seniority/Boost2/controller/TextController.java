package com.example.Seniority.Boost2.controller;

import com.example.Seniority.Boost2.entites.Text;
import com.example.Seniority.Boost2.exception.ResponseCreate;
import com.example.Seniority.Boost2.services.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/text/")
public class TextController {
    @Autowired
    private TextService textService;
    @PostMapping
    public ResponseEntity<?> savingText(@Valid @RequestBody Text text, WebRequest request){
            textService.saveText(text);
            return ResponseEntity.ok(new ResponseCreate(text.getId(), ((ServletWebRequest)request).getRequest().getRequestURI()+text.getId()));
    }
    @GetMapping
    public ResponseEntity<?> getAllText(){
        return ResponseEntity.ok(textService.findAllText());
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getByIdText(@Valid @PathVariable Long id){
        return ResponseEntity.ok(textService.findTextById(id));
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteByIdText(@Valid @PathVariable Long id){
        Optional<Text> texto = textService.findTextById(id);
        textService.borrarText(id);
        return new ResponseEntity<>("borrado con exito el texto: " + texto.get().getHash(), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<?> buscarPeliculasPor(@RequestParam(value = "chars", required = false) String chars,
//                                                @RequestParam(value = "page", required = false) int page,
//                                                @RequestParam(value = "rpp", required = false) int rpp) {
//        List<Text> texts;
//    }
}
