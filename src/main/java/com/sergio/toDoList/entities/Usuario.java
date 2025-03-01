package com.sergio.toDoList.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;

    @OneToMany(mappedBy = "usuario")
    private Set<Tarefa> produtos;

    public Usuario(){ }

    public Usuario(String nome, String login) {
        this.nome = nome;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Tarefa> getProdutos() {
        return produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return  "Usuário:\n" +
                "ID: " + this.id + "\n" +
                "Nome: " + this.nome + "\n" +
                "Login: " + this.login;
    }
}
