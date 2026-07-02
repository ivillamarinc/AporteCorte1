package uteq.aporte.pedidos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uteq.aporte.pedidos.model.EstadoPedido;
import uteq.aporte.pedidos.model.ItemPedido;
import uteq.aporte.pedidos.model.Pedido;
import uteq.aporte.pedidos.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PedidoController {
    
    private final PedidoService pedidoService;
    
    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/numero/{numeroOrden}")
    public ResponseEntity<Pedido> obtenerPorNumeroOrden(@PathVariable String numeroOrden) {
        return pedidoService.obtenerPorNumeroOrden(numeroOrden)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.crear(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.actualizar(id, pedido));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestParam EstadoPedido nuevoEstado) {
        pedidoService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemPedido>> obtenerItems(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.obtenerItems(id));
    }
    
    @PostMapping("/{id}/items")
    public ResponseEntity<Void> agregarItem(@PathVariable Long id, @RequestBody ItemPedido item) {
        pedidoService.agregarItem(id, item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
