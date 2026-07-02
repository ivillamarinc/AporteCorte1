# AporteCorte1

¿Cuál de las seis restricciones REST de Fielding [2] identifica con mayor claridad en su implementación? Argumente con al menos un extremo del código.

¿En qué nivel del modelo de madurez de Richardson se ubica su API? Justifique con un ejemplo
concreto de recurso, verbo y código de estado que respalde ese nivel.

Newman [3] sostiene que la elección entre monolito modular y microservicios debe hacerse en
función de coupling y cohesion. Con base en su prototipo, ¿qué acoplamiento residual observa
entre svc-catalogo y svc-pedidos y cómo lo mitigaría en producción?

Blinowski et al. [5] reportan que, sobre una sola máquina, el monolito puede superar en rendimiento a la versión microservicios equivalente. Ante ese hallazgo, ¿qué justificación técnica
sostendría la migración a microservicios en el caso de MercadoQuevedo?

¿Qué ganaría y qué perdería su prototipo si reemplazara la comunicación REST entre svc-pedidos
y svc-catalogo por gRPC? Argumente en términos de rendimiento, tipado del contrato y consumidores externos.


Si el proceso de svc-catalogo se detiene, ¿qué código HTTP recibe el cliente al crear un
pedido y por qué? ¿Cómo introduciría un patrón Circuit Breaker [1] en esa integración?
