package com.example.Seniority.Boost2.dto;

import com.example.Seniority.Boost2.entity.Text;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class TextDto implements Serializable {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String hash;

    @Getter @Setter
    private int chars;

    @Lob
    @Getter @Setter
    private HashMap<String, Number> result;


    public static TextDto textToDto(Text text) {
        TextDto dto = new TextDto();
        dto.setHash(text.getHash());
        dto.setId(text.getId());
        dto.setResult(text.getResult());
        dto.setChars(text.getChars());
        return dto;
    }

    public static List<TextDto> listTextDto(List<Text> textList) {
        List<TextDto> listaDto = new ArrayList<>();
        textList.forEach(textDto -> listaDto.add(textToDto(textDto)));
        return listaDto;
    }

}
