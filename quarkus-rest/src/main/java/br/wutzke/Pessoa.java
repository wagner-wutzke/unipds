package br.wutzke;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Pessoa extends PanacheEntity {

    public String nome;
    public int anoNascimento;

    public static List<Pessoa> findByAnoNascimento(int anoNascimento) {
        return Pessoa.find("anoNascimento", anoNascimento).list();
    }

}
