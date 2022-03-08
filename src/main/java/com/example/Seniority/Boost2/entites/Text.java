package com.example.Seniority.Boost2.entites;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;

@Entity
@Table
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "no debe estar en blanco.")
    private String hash;
    private Integer chars;
    private HashMap<String, Number> result;

    public Text() {
    }

    public Text(Long id, String hash, Integer chars, HashMap<String, Number> result) {
        this.id = id;
        this.hash = hash;
        this.chars = chars;
        this.result = result;
    }

    public Text(Long id, String hash, Integer chars) {
        this.id = id;
        this.hash = hash;
        this.chars = chars;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", chars=" + chars +
                ", result=" + result +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getChars() {
        return chars;
    }

    public void setChars(int chars) {
        this.chars = chars;
    }

    public void setChars(Integer chars) {
        this.chars = chars;
    }

    public HashMap<String, Number> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Number> result) {
        this.result = result;
    }
}
