package com.example.Seniority.Boost2.controller;

import com.example.Seniority.Boost2.dto.TextDto;
import com.example.Seniority.Boost2.entity.Text;
import com.example.Seniority.Boost2.exception.ResponseEdit;
import com.example.Seniority.Boost2.service.TextService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/text")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class TextController {
    @Autowired
    private TextService textService;

    private TextDto textDto;

    @PostMapping(value = "/")
    public ResponseEntity<?> savingText(@Valid @RequestBody Text text, WebRequest request){
            textService.saveText(text);
            return ResponseEntity.created(null).body(new ResponseEdit(text.getId(), ((ServletWebRequest)request).getRequest().getRequestURI()+text.getId()));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteByIdText(@Valid @PathVariable Long id, WebRequest request){
        Text text = textService.findTextById(id).get();
        textService.borrarText(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getByIdText(@Valid @PathVariable Long id){
        Text text = textService.findTextById(id).get();
            return ResponseEntity.ok(textDto.textToDto(text));
    }
    @GetMapping(value = "")
    public ResponseEntity<?> findTextBy(
                                @RequestParam(value = "chars", required = false) String chars,
                                @RequestParam(value = "rpp", required = false) String rpp,
                                @RequestParam(value = "page", required = false) String page)
        {
        if(chars != null)return ResponseEntity.ok(textDto.listTextDto(textService.pagAndFindByChars(page, rpp, chars)));
        return ResponseEntity.ok(textDto.listTextDto(textService.pagination(page, rpp)));
    }
}
