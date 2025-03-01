package com.sergio.toDoList.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Boolean concluida;
    private LocalDateTime  dataInsercao;
    private LocalDateTime  dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Tarefa() {}

    public Tarefa(String titulo, String descricao, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.concluida = false;
        this.dataInsercao = LocalDateTime.now();
        this.dataFinalizacao = null;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(LocalDateTime  dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDataInsercaoFormatada() {
        return formatarData(dataInsercao);
    }

    public String getDataFinalizacaoFormatada() {
        return formatarData(dataFinalizacao);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(id, tarefa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private String formatarData(LocalDateTime data) {
        if (data == null) return "Não finalizada";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return data.format(formatter);
    }

    public void finalizarTarefa(){
        this.concluida = true;
        this.dataFinalizacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String statusFinalizacao = (this.dataFinalizacao == null) ? "Ainda não finalizada" : this.formatarData(dataFinalizacao);

        String statusConclusao = this.concluida ? "Concluída" : "Não concluída";

        return "Tarefa:\n" +
                "ID: " + this.id + "\n" +
                "Título: " + this.titulo + "\n" +
                "Descrição: " + this.descricao + "\n" +
                "Status: " + statusConclusao + "\n" +
                "Criada em: " + this.formatarData(this.dataInsercao) + "\n" +
                "Finalização: " + statusFinalizacao + "\n" +
                this.usuario;
    }

}
