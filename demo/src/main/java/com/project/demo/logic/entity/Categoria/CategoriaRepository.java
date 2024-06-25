package com.project.demo.logic.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c JOIN FETCH c.productos")
    List<Categoria> findAllWithProductos();
}
