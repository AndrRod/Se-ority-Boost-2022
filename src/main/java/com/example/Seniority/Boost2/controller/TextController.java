package com.example.Seniority.Boost2.controller;

import com.example.Seniority.Boost2.entites.Text;
import com.example.Seniority.Boost2.exception.responseEdit;
import com.example.Seniority.Boost2.services.TextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/text")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class TextController {
    @Autowired
    private TextService textService;

    @PostMapping(value = "/")
    public ResponseEntity<?> savingText(@Valid @RequestBody Text text, WebRequest request){
            textService.saveText(text);
            return ResponseEntity.ok(new responseEdit(text.getId(), ((ServletWebRequest)request).getRequest().getRequestURI()+text.getId()));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteByIdText(@Valid @PathVariable Long id, WebRequest request){
        Text text = textService.findTextById(id).get();
        textService.borrarText(id);
        return ResponseEntity.ok().build();
    }

//    PRUEBA DE PAGINACION
//    @GetMapping(value = "/pag/{page}/{size}")
//    public ResponseEntity<?> getAllText(@PathVariable String page, @PathVariable String size){
//        return ResponseEntity.ok(textService.findAllText(Integer.valueOf(page), Integer.valueOf(size)));
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getByIdText(@Valid @PathVariable Long id){
        Optional<Text> text = textService.findTextById(id);
        text.get().setHash(textService.convertHash(text.get().getHash()));
        return ResponseEntity.ok(text);
    }

    @GetMapping(value = "")
    public ResponseEntity<?> findTextBy(
                                @RequestParam(value = "chars", required = false) String chars,
                                @RequestParam(value = "rpp", required = false) String rpp,
                                @RequestParam(value = "page", required = false) String page)
        {
        if(chars != null)return ResponseEntity.ok(textService.pagAndFindByChars(page, rpp, chars));
        return ResponseEntity.ok(textService.pagination(page, rpp));
    }
}
