package com.arbocontrol.sisvetor.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
public class SubItem implements Serializable{
    @Id
    private BigInteger id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}