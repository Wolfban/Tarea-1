package com.project.demo.rest.categoria;
import com.project.demo.logic.entity.Categoria.Categoria;
import com.project.demo.logic.entity.Categoria.CategoriaRepository;
import com.project.demo.logic.entity.Producto.Producto;
import com.project.demo.logic.entity.Producto.ProductoRepository;
import com.project.demo.logic.entity.rol.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/categoria")
@RestController
public class CategoriaRestController {
    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);

    }
    @GetMapping("/list")
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public Categoria actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetalles) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaDetalles.getNombre());
                    categoria.setDescripcion(categoriaDetalles.getDescripcion());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(RuntimeException::new);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public void deleteCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        categoriaRepository.delete(categoria);
    }
}




