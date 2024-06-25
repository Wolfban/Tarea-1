package com.project.demo.rest.producto;
import com.project.demo.logic.entity.Categoria.Categoria;
import com.project.demo.logic.entity.Categoria.CategoriaRepository;
import com.project.demo.logic.entity.Producto.Producto;
import com.project.demo.logic.entity.Producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/producto")
@RestController
public class ProductoRestController {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Producto crearProducto(@RequestBody Producto producto) {
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId())
                .orElseThrow(RuntimeException::new);

        producto.setCategoria(categoria);

        Producto savedProducto = productoRepository.save(producto);
        categoria.getProductos().add(savedProducto);
        categoriaRepository.save(categoria);
        return savedProducto;
    }


    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    @GetMapping("/list")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoDetalles.getNombre());
                    producto.setDescripcion(productoDetalles.getDescripcion());
                    producto.setPrecio(productoDetalles.getPrecio());
                    producto.setCantidadEnStock(productoDetalles.getCantidadEnStock());
                    producto.setCategoria(productoDetalles.getCategoria());
                    return productoRepository.save(producto);
                })
                .orElseThrow(RuntimeException::new);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public void deleteProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        productoRepository.delete(producto);
    }
}
