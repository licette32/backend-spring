package com.techlab.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.backend.models.Articulo;
import com.techlab.backend.repository.ArticuloRepository;

@Service
public class ArticuloService {
    private final ArticuloRepository repo;

    @Autowired
    public ArticuloService(ArticuloRepository repo) {
        this.repo = repo;
    }

    public List<Articulo> listarTodos() {
        return repo.findAll();
    }

    public Articulo obtenerPorId(int id) {

        return repo.findById(id).orElse(null);
    }

    public Articulo guardar(Articulo p) {
        return repo.save(p);
    }

    public Articulo actualizar(int id, Articulo datos) {

        Optional<Articulo> articuloExistente = repo.findById(id);
        if (articuloExistente.isPresent()) {
            Articulo p = articuloExistente.get();
            p.setNombre(datos.getNombre());
            p.setCategoria(datos.getCategoria());
            p.setPrecio(datos.getPrecio());
            p.setCantidadEnStock(datos.getCantidadEnStock());
            p.setPathFoto(datos.getPathFoto());
            return repo.save(p);
        }
        return null; 
    }

    public boolean eliminar(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Articulo> buscarPorNombreExacto(String nombre) {
        return repo.findByNombre(nombre);
    }

    public Articulo descontarStock(Integer id, Integer stockADescontar) {
        Optional<Articulo> articuloExistente = repo.findById(id);
        if (articuloExistente.isPresent()) {
            Articulo p = articuloExistente.get();
            p.setCantidadEnStock(p.getCantidadEnStock() - stockADescontar);
            return repo.save(p);
        } else {
            return null; 
        }
    }
}