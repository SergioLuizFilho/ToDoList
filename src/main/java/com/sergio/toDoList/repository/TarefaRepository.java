package com.sergio.toDoList.repository;

import com.sergio.toDoList.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

    @Query(value = "SELECT * FROM tarefa WHERE usuario_id = :id", nativeQuery = true)
    List<Tarefa> buscarTarefaPorUsuario(@Param("id") Long id);
}
