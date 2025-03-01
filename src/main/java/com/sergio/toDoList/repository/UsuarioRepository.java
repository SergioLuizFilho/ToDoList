package com.sergio.toDoList.repository;

import com.sergio.toDoList.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query(value = "SELECT * FROM usuario WHERE login = :login", nativeQuery = true)
    Usuario buscarPorLogin(@Param("login") String login);

}
