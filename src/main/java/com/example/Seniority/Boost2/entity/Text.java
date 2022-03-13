package com.example.Seniority.Boost2.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String hash;
    private int chars;
    @Lob
    private HashMap<String, Number> result;


    public Text(Long id, String text, String hash, int chars, HashMap<String, Number> result) {
        this.id = id;
        this.text = text;
        this.hash = hash;
        this.chars = chars;
        this.result = result;
    }

    public Text() {
    }

    public Long getId() {
        return id;
    }
    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHash() {
        return hash;
    }

    @JsonIgnore
    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getChars() {
        return chars;
    }

    public void setChars(int chars) {
        this.chars = chars;
    }

    public HashMap<String, Number> getResult() {
        return result;
    }
    @JsonIgnore
    public void setResult(HashMap<String, Number> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", hash='" + hash + '\'' +
                ", chars=" + chars +
                ", result=" + result +
                '}';
    }

}
