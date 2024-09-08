package com.trabalhofullstack.cadastro.model;

import jakarta.persistence.*;

@Table(name = "pessoasfisicas")
@Entity(name = "pessoasfisicas")
public class Pessoa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumero() {
        return telefone;
    }

    public void setNumero(String numero) {
        this.telefone = numero;
    }

}
