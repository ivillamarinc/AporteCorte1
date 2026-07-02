package uteq.aporte.pedidos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uteq.aporte.pedidos.model.EstadoPedido;
import uteq.aporte.pedidos.model.ItemPedido;
import uteq.aporte.pedidos.model.Pedido;
import uteq.aporte.pedidos.repository.ItemPedidoRepository;
import uteq.aporte.pedidos.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }
    
    public Optional<Pedido> obtenerPorNumeroOrden(String numeroOrden) {
        return pedidoRepository.findByNumeroOrden(numeroOrden);
    }
    
    public Pedido crear(Pedido pedido) {
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setFechaActualizacion(LocalDateTime.now());
        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedidoRepository.save(pedido);
    }
    
    public Pedido actualizar(Long id, Pedido pedidoActualizado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setCliente(pedidoActualizado.getCliente());
                    pedido.setEmail(pedidoActualizado.getEmail());
                    pedido.setEstado(pedidoActualizado.getEstado());
                    pedido.setFechaActualizacion(LocalDateTime.now());
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + id));
    }
    
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    public void cambiarEstado(Long id, EstadoPedido nuevoEstado) {
        pedidoRepository.findById(id)
                .ifPresentOrElse(
                        pedido -> {
                            pedido.setEstado(nuevoEstado);
                            pedido.setFechaActualizacion(LocalDateTime.now());
                            pedidoRepository.save(pedido);
                        },
                        () -> {
                            throw new RuntimeException("Pedido no encontrado: " + id);
                        }
                );
    }
    
    public List<ItemPedido> obtenerItems(Long pedidoId) {
        return itemPedidoRepository.findByPedidoId(pedidoId);
    }
    
    public void agregarItem(Long pedidoId, ItemPedido item) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + pedidoId));
        item.setPedido(pedido);
        itemPedidoRepository.save(item);
    }
}
