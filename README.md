# PlataformaEventosPGII

Proyecto Java de Programacion II construido con `Java 17`, `JavaFX` y `Maven`.  
Simula una plataforma de gestion de eventos y venta de entradas con datos en memoria, pensada para ser entendible por estudiantes de tercer semestre.

## Descripcion

La aplicacion permite:

- Ver eventos disponibles en una cartelera.
- Consultar detalle del evento, recinto, zonas y asientos.
- Crear una compra sencilla.
- Agregar servicios adicionales.
- Pagar de forma simulada.
- Confirmar la compra y ver un comprobante.
- Entrar a un modo administrador para gestionar eventos, asientos, incidencias y metricas basicas.

## Tecnologias

- Java 17
- JavaFX
- Maven
- Datos en memoria con `ArrayList`

## Estructura principal

```text
src/main/java/co/edu/uniquindio/eventos/
├── app
├── Navigator.java
├── controller
├── model
├── model/enums
├── patterns/behavioral
├── patterns/creational
├── patterns/structural
├── repository
├── service
└── util
```

## Patrones implementados

- `Singleton`: `GestorReservas`
- `Factory Method`: `EventoFactory`
- `Builder`: `ReservaBuilder`
- `Decorator`: `ServicioBase`, `ServicioDecorator`, `ServicioVIP`, `SeguroCancelacion`, `Parqueadero`
- `Facade`: `CompraFacade`
- `Adapter`: `ReporteExternoAdapter`
- `Strategy`: `MetodoPago`, `PagoTarjeta`, `PagoPSE`, `PagoNequi`
- `Observer`: `Notificador`, `Observador`, `NotificacionUsuario`, `NotificacionAdministrador`
- `State`: `EstadoCompraInterface` y sus estados concretos

Las clases de patrones tienen comentarios cortos para sustentacion, por ejemplo:

- `// Patron Singleton aplicado para controlar reservas`
- `// Patron Strategy aplicado para cambiar metodo de pago`
- `// Patron Decorator aplicado para agregar servicios adicionales`

## Usuarios de prueba

- Usuario 1: `Mariana Rodriguez` - `mariana@uq.edu.co`
- Usuario 2: `Sofia Aviles` - `sofia@uq.edu.co`
- Usuario 3: `Juan Tellez` - `juan@uq.edu.co`
- Administrador: `Admin Eventos` - `admin@uq.edu.co`

La aplicacion inicia con el primer usuario de prueba como usuario simulado.

## Datos de prueba cargados automaticamente

- 3 usuarios
- 1 administrador
- 5 eventos
- 2 recintos
- zonas `VIP`, `Preferencial` y `General`
- asientos por zona
- 2 compras de ejemplo
- incidencias listas para registrar desde el panel administrador

## Interfaz

La interfaz esta inspirada visualmente en una cartelera:

- barra superior roja con el nombre `EVENTOS UQ`
- menu superior con `Cartelera`, `Servicios`, `Promociones` y `Admin`
- tarjetas de eventos con colores rojo, negro, blanco y gris
- vista de detalle para seleccionar zona y asiento
- vista de compra con servicios, metodo de pago y comprobante
- panel administrador con listas simples y metricas

## Como ejecutar

1. Tener instalado `JDK 17` o superior.
2. Tener `Maven` configurado en el sistema.
3. Desde la raiz del proyecto ejecutar:

```bash
mvn clean javafx:run
```

Si prefieres compilar primero:

```bash
mvn clean package
```

## Pantallas principales

- Recursos actuales:
  `src/main/resources/views`
  `src/main/resources/styles`
- `main-view.fxml`: pantalla principal con cartelera.
- `detalle-evento-view.fxml`: detalle del evento y seleccion de zona/asiento.
- `compra-view.fxml`: compra, servicios, pago y comprobante.
- `admin-view.fxml`: panel de administracion.

## Notas para sustentacion

- No usa base de datos ni servicios externos reales.
- Toda la informacion vive en memoria dentro de `DatosPrueba`.
- Los pagos son simulados.
- La navegacion se hace con JavaFX, `MainApp`, `Navigator` y controladores sencillos.
- El objetivo fue mantener el codigo funcional, organizado y facil de explicar.

## Clase principal

La aplicacion arranca desde:

`co.edu.uniquindio.eventos.app.MainApp`
