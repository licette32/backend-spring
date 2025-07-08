package com.techlab.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importación para HttpStatus
import org.springframework.http.ResponseEntity; // Importación para ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.backend.Service.ArticuloService;
import com.techlab.backend.models.Articulo;

@RestController
@RequestMapping("/api/articulos") // ¡Este es el cambio CRÍTICO para la URL!
@CrossOrigin(origins = "*") // Para desarrollo. Considera restringirlo en producción.
public class ArticuloController {

    private final ArticuloService articuloService;

    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }
    
    @GetMapping     
    public ResponseEntity<List<Articulo>> listarArticulos() {
        List<Articulo> articulos = articuloService.listarTodos();
        return new ResponseEntity<>(articulos, HttpStatus.OK);
    }

    @GetMapping("/{id}")    
    public ResponseEntity<Articulo> obtenerArticulo(@PathVariable int id) {
        Articulo articulo = articuloService.obtenerPorId(id);
        if (articulo != null) {
            return new ResponseEntity<>(articulo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 si no se encuentra
    }

    @GetMapping(value="/nombre/{nombre}")    
    public ResponseEntity<List<Articulo>> buscarArticuloPorNombreExacto(@PathVariable String nombre) {
        List<Articulo> articulos = articuloService.buscarPorNombreExacto(nombre);
        if (!articulos.isEmpty()) {
            return new ResponseEntity<>(articulos, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 si no se encuentra
    }

    @PostMapping    
    public ResponseEntity<Articulo> crearArticulo(@RequestBody Articulo nuevo) {
        Articulo articuloGuardado = articuloService.guardar(nuevo);
        return new ResponseEntity<>(articuloGuardado, HttpStatus.CREATED); // Devuelve 201 Created
    }

    @PutMapping("/{id}")    
    public ResponseEntity<Articulo> actualizarArticulo(@PathVariable int id, @RequestBody Articulo datos) {
        Articulo articuloActualizado = articuloService.actualizar(id, datos);
        if (articuloActualizado != null) {
            return new ResponseEntity<>(articuloActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 si no se encuentra
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable int id) {
        boolean eliminado = articuloService.eliminar(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 No Content para éxito sin cuerpo
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 si no se encuentra para eliminar
    }

    // Nuevo endpoint para descontar stock, si lo necesitas exponer directamente
    @PutMapping("/{id}/descontarStock/{cantidad}")
    public ResponseEntity<Articulo> descontarStockArticulo(@PathVariable Integer id, @PathVariable Integer cantidad) {
        Articulo articuloActualizado = articuloService.descontarStock(id, cantidad);
        if (articuloActualizado != null) {
            return new ResponseEntity<>(articuloActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}