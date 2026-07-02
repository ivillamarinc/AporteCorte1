package uteq.aporte.catalogo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uteq.aporte.catalogo.model.Producto;
import uteq.aporte.catalogo.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }
    
    public Optional<Producto> obtenerPorSku(String sku) {
        return productoRepository.findBySku(sku);
    }
    
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public Producto actualizar(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecio(productoActualizado.getPrecio());
                    producto.setStock(productoActualizado.getStock());
                    producto.setActivo(productoActualizado.getActivo());
                    return productoRepository.save(producto);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }
    
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
    
    public Integer obtenerStock(Long id) {
        return productoRepository.findById(id)
                .map(Producto::getStock)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
    }
    
    public void actualizarStock(Long id, Integer cantidad) {
        productoRepository.findById(id)
                .ifPresentOrElse(
                        producto -> {
                            producto.setStock(producto.getStock() - cantidad);
                            productoRepository.save(producto);
                        },
                        () -> {
                            throw new RuntimeException("Producto no encontrado: " + id);
                        }
                );
    }
}
