package com.techlab.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlab.backend.models.Articulo;

@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    // Spring Data JPA automáticamente implementa este método si sigues la convención de nombres
    List<Articulo> findByNombre(String nombre);
}