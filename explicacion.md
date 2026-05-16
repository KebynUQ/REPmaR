# Explicacion del Proyecto PlataformaEventosPGII

## 1. Que es este proyecto

Este proyecto es una aplicacion de escritorio hecha en `Java`, `JavaFX` y `Maven`.
La idea general es simular una plataforma de gestion de eventos y venta de entradas.

El sistema permite:

- ver eventos en una cartelera,
- revisar el detalle de un evento,
- elegir zona y asiento,
- crear una compra,
- agregar servicios adicionales,
- pagar de forma simulada,
- confirmar la compra,
- consultar un comprobante,
- entrar a un modo administrador para gestionar eventos, asientos, incidencias y metricas.

No usa:

- base de datos,
- internet,
- APIs externas reales,
- Spring,
- seguridad avanzada.

Toda la informacion se maneja en memoria usando listas y objetos Java.

---

## 2. Idea general de la arquitectura

El proyecto esta dividido en paquetes para que el codigo quede ordenado y facil de explicar.

### Paquetes principales

- `app`: arranque de la aplicacion.
- `controller`: controladores JavaFX.
- `model`: clases del dominio.
- `model.enums`: enumeraciones de estados.
- `patterns.creational`: patrones creacionales.
- `patterns.structural`: patrones estructurales.
- `patterns.behavioral`: patrones de comportamiento.
- `repository`: datos de prueba.
- `service`: logica de negocio sencilla.
- `util`: utilidades.

La idea de separarlo asi es que cada paquete tenga una responsabilidad clara.

---

## 3. Flujo general de funcionamiento

El flujo principal del sistema es este:

1. Se inicia la aplicacion desde `Launcher`.
2. `Launcher` llama a `MainApp`.
3. `MainApp` carga los datos de prueba y abre la vista principal.
4. `MainController` muestra la cartelera de eventos.
5. El usuario entra al detalle de un evento.
6. Escoge zona y asiento.
7. Se crea una compra.
8. En la vista de compra puede agregar servicios adicionales y pagar.
9. La compra puede confirmarse.
10. El administrador puede entrar al panel admin y cambiar estados o registrar incidencias.

---

## 4. Arranque de la aplicacion

### 4.1 `Launcher`

Archivo:

- `src/main/java/co/edu/uniquindio/eventos/app/Launcher.java`

Esta clase existe para solucionar el problema comun de JavaFX en IntelliJ:

`JavaFX runtime components are missing`

Su trabajo es muy simple:

- tiene el metodo `main`,
- llama a `MainApp.main(args)`.

No contiene logica del negocio.
Solo sirve como punto de entrada seguro para ejecutar desde IntelliJ.

### 4.2 `MainApp`

Archivo:

- `src/main/java/co/edu/uniquindio/eventos/app/MainApp.java`

Esta clase:

- extiende `Application`,
- es la clase principal de JavaFX,
- crea y abre la ventana principal,
- carga los datos de prueba,
- crea los servicios del sistema,
- deja disponible un usuario simulado,
- carga el FXML principal `main-view.fxml`,
- conecta el controlador principal,
- inicializa el `Navigator`.

Tambien guarda informacion temporal como:

- `usuarioActual`,
- `eventoSeleccionado`,
- `compraActual`.

Eso permite que varias pantallas compartan contexto sin usar base de datos.

---

## 5. Navegacion de pantallas

### `Navigator`

Archivo:

- `src/main/java/co/edu/uniquindio/eventos/Navigator.java`

Esta clase centraliza el cambio entre vistas.

Su idea es parecida al proyecto de referencia que usaron.

Funciones principales:

- cargar vistas FXML,
- ir a cartelera,
- ir a detalle de evento,
- ir a compra,
- ir a admin,
- volver a la vista anterior.

Tambien tiene una pila simple llamada `historial`.
Eso permite recordar desde donde venia el usuario.

Ejemplo:

- si el usuario estaba en cartelera y entra a detalle, el historial guarda `cartelera`,
- si luego entra a compra, guarda `detalle`,
- si pulsa volver, regresa al detalle.

Este diseño es sencillo y facil de explicar.

---

## 6. Vistas JavaFX

Las vistas estan en:

- `src/main/resources/views`

Y los estilos en:

- `src/main/resources/styles/styles.css`

### 6.1 `main-view.fxml`

Es el layout principal.
Tiene:

- barra superior roja,
- nombre `EVENTOS UQ`,
- menu con `Cartelera`, `Servicios`, `Promociones` y `Admin`,
- un `StackPane` llamado `contenidoPane` donde se cargan las pantallas internas,
- una barra inferior para notificaciones.

### 6.2 `detalle-evento-view.fxml`

Muestra:

- nombre del evento,
- categoria,
- ciudad,
- fecha,
- descripcion,
- recinto,
- politicas,
- combo de zonas,
- combo de asientos,
- boton para continuar compra.

### 6.3 `compra-view.fxml`

Muestra:

- usuario simulado,
- evento,
- entradas seleccionadas,
- servicios adicionales,
- metodo de pago,
- total,
- botones de pagar y confirmar,
- comprobante.

### 6.4 `admin-view.fxml`

Muestra:

- lista de eventos,
- lista de asientos,
- botones para publicar, pausar o cancelar evento,
- botones para bloquear y liberar asiento,
- formulario para registrar incidencia,
- metricas basicas.

---

## 7. Controladores JavaFX

Los controladores estan en:

- `src/main/java/co/edu/uniquindio/eventos/controller`

### 7.1 `MainController`

Es el controlador principal del layout.

Responsabilidades:

- mostrar cartelera,
- crear visualmente las tarjetas de eventos,
- cargar la vista de detalle,
- cargar la vista de compra,
- cargar la vista de admin,
- mostrar secciones informativas de servicios y promociones,
- actualizar la barra de notificaciones.

Este controlador es importante porque controla el `contenidoPane`.
Ese `StackPane` es donde se reemplaza la pantalla central.

### 7.2 `EventoController`

Responsabilidades:

- recibir el evento seleccionado,
- mostrar todos sus datos,
- llenar el combo de zonas,
- actualizar el combo de asientos disponibles,
- validar que el usuario escoja zona y asiento,
- continuar hacia la compra.

### 7.3 `CompraController`

Responsabilidades:

- recibir la compra actual,
- mostrar usuario, evento y entradas,
- permitir seleccionar metodo de pago,
- permitir agregar servicios,
- actualizar el total,
- procesar el pago,
- confirmar la compra,
- mostrar comprobante.

### 7.4 `AdminController`

Responsabilidades:

- listar eventos,
- mostrar asientos del evento seleccionado,
- publicar, pausar o cancelar eventos,
- bloquear o liberar asientos,
- registrar incidencias,
- mostrar metricas basicas.

---

## 8. Modelos del dominio

Los modelos representan las cosas reales del sistema.

### 8.1 `Usuario`

Representa un cliente del sistema.

Tiene:

- `idUsuario`
- `nombreCompleto`
- `correo`
- `telefono`
- `List<MetodoPago> metodosPago`
- `List<Compra> compras`

Metodos principales:

- `registrarse()`
- `iniciarSesion()`
- `actualizarPerfil()`
- `consultarEventos()`
- `consultarHistorialCompras()`
- `descargarReporteCompras()`
- `agregarMetodoPago()`
- `agregarCompra()`

En este proyecto no hay login real.
El metodo `iniciarSesion()` solo simula una validacion sencilla.

### 8.2 `Administrador`

Hereda de `Usuario`.

Agrega:

- `codigoEmpleado`

Metodos:

- `gestionarUsuarios()`
- `gestionarEventos()`
- `gestionarRecintos()`
- `gestionarAsientos()`
- `gestionarCompras()`
- `registrarIncidencia()`
- `consultarMetricas()`

Estos metodos son sencillos porque la logica real se maneja en `AdminService`.

### 8.3 `Evento`

Representa un evento publicado en la cartelera.

Tiene:

- `idEvento`
- `nombre`
- `categoria`
- `descripcion`
- `ciudad`
- `fechaHora`
- `estado`
- `politicaCancelacion`
- `politicaReembolso`
- `recinto`

Metodos:

- `publicar()`
- `pausar()`
- `cancelar()`
- `finalizar()`
- `consultarDisponibilidad()`
- `getPrecioDesde()`

`getPrecioDesde()` revisa las zonas del recinto y busca el menor precio base.

### 8.4 `Recinto`

Representa el lugar donde se realiza un evento.

Tiene:

- `idRecinto`
- `nombre`
- `direccion`
- `ciudad`
- `zonas`

Metodos:

- `agregarZona()`
- `eliminarZona()`
- `consultarZonas()`

### 8.5 `Zona`

Representa una seccion del recinto.

Tiene:

- `idZona`
- `nombre`
- `capacidad`
- `precioBase`
- `asientos`

Metodos:

- `agregarAsiento()`
- `consultarOcupacion()`
- `definirPrecioBase()`
- `consultarAsientosDisponibles()`

`consultarOcupacion()` calcula el porcentaje ocupado contando asientos vendidos o reservados.

### 8.6 `Asiento`

Representa un asiento dentro de una zona.

Tiene:

- `idAsiento`
- `fila`
- `numero`
- `estado`

Metodos:

- `reservar()`
- `vender()`
- `bloquear()`
- `liberar()`
- `getEtiqueta()`

La etiqueta combina fila y numero, por ejemplo `A1`.

### 8.7 `Compra`

Es una de las clases mas importantes.

Representa todo el proceso de compra.

Tiene:

- `idCompra`
- `usuario`
- `evento`
- `fechaCreacion`
- `total`
- `estado`
- `entradas`
- `servicios`
- `pago`
- `estadoCompraActual`

Metodos:

- `agregarEntrada()`
- `quitarEntrada()`
- `agregarServicio()`
- `calcularTotal()`
- `pagar()`
- `confirmar()`
- `cancelar()`
- `reembolsar()`
- `generarComprobante()`
- `setEstadoCompraActual()`

Esta clase usa el patron `State`.
Eso significa que no maneja los estados con muchos `if`, sino con objetos que representan cada estado.

### 8.8 `Entrada`

Representa una entrada comprada.

Tiene:

- `idEntrada`
- `zona`
- `asiento`
- `precioFinal`
- `estado`

Metodos:

- `activar()`
- `usar()`
- `anular()`

### 8.9 `Pago`

Representa un pago simulado.

Tiene:

- `idPago`
- `valor`
- `fechaPago`
- `estado`
- `metodoPago`

Metodos:

- `procesarPago()`
- `generarComprobante()`
- `registrarReembolso()`

Usa el patron `Strategy` porque el metodo de pago puede cambiar.

### 8.10 `Tarifa`

Representa una forma sencilla de calcular precio con descuento.

Tiene:

- `idTarifa`
- `nombre`
- `valorBase`
- `descuento`

Metodo:

- `calcularPrecioFinal()`

### 8.11 `Incidencia`

Representa un problema o novedad administrativa.

Tiene:

- `idIncidencia`
- `tipo`
- `descripcion`
- `fecha`
- `estado`
- `entidadAfectada`

Metodos:

- `registrar()`
- `consultarDetalle()`

### 8.12 `PanelMetricas`

Es una clase sencilla para agrupar metricas.

Tiene:

- `totalVentas`
- `cantidadCompras`
- `cantidadEventosPublicados`
- `ocupacionPorZona`

Sirve para que el panel admin tenga los datos listos para mostrar.

---

## 9. Enumeraciones

Las enumeraciones existen para controlar estados posibles y evitar valores inventados.

### `EstadoEvento`

- `BORRADOR`
- `PUBLICADO`
- `PAUSADO`
- `CANCELADO`
- `FINALIZADO`

### `EstadoAsiento`

- `DISPONIBLE`
- `RESERVADO`
- `VENDIDO`
- `BLOQUEADO`

### `EstadoCompra`

- `CREADA`
- `PAGADA`
- `CONFIRMADA`
- `CANCELADA`
- `REEMBOLSADA`
- `INCIDENCIA`

### `EstadoEntrada`

- `ACTIVA`
- `USADA`
- `ANULADA`

### `EstadoPago`

- `PENDIENTE`
- `APROBADO`
- `RECHAZADO`
- `REEMBOLSADO`

### `EstadoIncidencia`

- `ABIERTA`
- `EN_PROCESO`
- `RESUELTA`
- `CERRADA`

---

## 10. Repository y datos de prueba

### `DatosPrueba`

Archivo:

- `src/main/java/co/edu/uniquindio/eventos/repository/DatosPrueba.java`

Es un repositorio simple en memoria.

Su trabajo es:

- crear usuarios,
- crear administrador,
- crear recintos,
- crear zonas,
- crear asientos,
- crear eventos,
- crear compras de ejemplo,
- crear incidencias,
- crear un notificador.

Se comporta como un singleton simple por medio de `getInstancia()`.

Esto significa que toda la aplicacion trabaja con el mismo conjunto de datos cargados.

### Datos que carga

- 3 usuarios
- 1 administrador
- 5 eventos
- 2 recintos
- zonas `VIP`, `Preferencial`, `General`
- asientos para cada zona
- 2 compras de ejemplo
- incidencias de ejemplo

### Notificador en datos de prueba

Dentro de `DatosPrueba` se crea un `Notificador`.
Tambien se agregan dos observadores:

- `NotificacionUsuario`
- `NotificacionAdministrador`

Asi el sistema puede guardar el ultimo mensaje enviado.

---

## 11. Servicios

Los servicios concentran la logica principal del sistema para no meter todo dentro de los controladores.

### 11.1 `UsuarioService`

Metodos:

- `registrarUsuario()`
- `buscarPorCorreo()`
- `listarUsuarios()`
- `obtenerUsuarioSimulado()`

Se apoya en `DatosPrueba`.

### 11.2 `EventoService`

Metodos:

- `listarEventos()`
- `listarEventosPublicados()`
- `filtrarPorCiudad()`
- `filtrarPorCategoria()`
- `agregarEvento()`
- `publicarEvento()`
- `pausarEvento()`
- `cancelarEvento()`

Sirve para mantener la logica de eventos separada del controlador.

### 11.3 `CompraService`

Metodos:

- `crearCompra()`
- `agregarCompra()`
- `agregarServicio()`
- `pagarCompra()`
- `confirmarCompra()`
- `listarCompras()`
- `listarComprasPorUsuario()`
- `calcularTotalCompra()`

Internamente usa `CompraFacade`.

### 11.4 `AdminService`

Metodos:

- `obtenerEventos()`
- `bloquearAsiento()`
- `liberarAsiento()`
- `registrarIncidencia()`
- `listarIncidencias()`
- `obtenerMetricasBasicas()`
- `consultarMetricas()`

Este servicio deja lista la informacion para el panel de administrador.

---

## 12. Patrones de diseño implementados

Este proyecto usa 9 patrones de diseño.
La idea fue implementarlos de forma sencilla y explicable.

## 12.1 Patrones creacionales

### Singleton: `GestorReservas`

Archivo:

- `patterns/creational/GestorReservas.java`

Comentario en codigo:

- `// Patron Singleton aplicado para controlar reservas`

Que hace:

- controla reserva, liberacion y venta de asientos desde un unico objeto.

Por que sirve:

- evita que existan muchos gestores diferentes manipulando asientos.

Metodos:

- `getInstancia()`
- `reservarAsiento()`
- `liberarAsiento()`
- `venderAsiento()`

### Factory Method: `EventoFactory`

Archivo:

- `patterns/creational/EventoFactory.java`

Que hace:

- crea eventos segun la categoria.

Detalle:

- si la categoria es `Concierto`, `Teatro` o `Festival`, el evento queda publicado.

Esto permite centralizar la forma de crear eventos.

### Builder: `ReservaBuilder`

Archivo:

- `patterns/creational/ReservaBuilder.java`

Que hace:

- construye una compra paso a paso.

Metodos:

- `conDatosBasicos()`
- `agregarEntrada()`
- `agregarServicio()`
- `construir()`

Es util porque una compra se arma por partes.

---

## 12.2 Patrones estructurales

### Decorator: servicios adicionales

Clases:

- `ServicioAdicional`
- `ServicioBase`
- `ServicioDecorator`
- `ServicioVIP`
- `SeguroCancelacion`
- `Parqueadero`

Idea:

- agregar extras a una compra sin modificar la clase `Compra`.

Funcionamiento:

- `ServicioBase` representa compra sin extras.
- `ServicioVIP` suma costo VIP.
- `SeguroCancelacion` suma costo de seguro.
- `Parqueadero` suma costo de parqueadero.

Todos pueden combinarse.

Ejemplo:

- `new Parqueadero(new SeguroCancelacion(new ServicioVIP(new ServicioBase())))`

Eso construye una cadena de servicios.

### Facade: `CompraFacade`

Archivo:

- `patterns/structural/CompraFacade.java`

Que hace:

- simplifica el flujo de compra.

Metodos:

- `crearCompra()`
- `agregarServicio()`
- `pagarCompra()`
- `confirmarCompra()`

Internamente coordina:

- `GestorReservas`
- `Entrada`
- `Pago`
- `ReservaBuilder`
- `Compra`

Esto evita que el controlador tenga demasiada logica.

### Adapter: reportes

Clases:

- `Reporte`
- `ReportePDF`
- `ReporteCSV`
- `ServicioReporteExterno`
- `ReporteExternoAdapter`

Idea:

- adaptar un servicio externo a la interfaz del sistema.

`Reporte` es la interfaz comun.
`ServicioReporteExterno` no cumple esa interfaz.
`ReporteExternoAdapter` lo adapta.

---

## 12.3 Patrones de comportamiento

### Strategy: metodos de pago

Clases:

- `MetodoPago`
- `PagoTarjeta`
- `PagoPSE`
- `PagoNequi`

Idea:

- cambiar la forma de procesar un pago sin cambiar la clase `Pago`.

En este proyecto la logica es simple:

- si el valor es mayor a cero, el pago es valido.

### Observer: notificaciones

Clases:

- `Observador`
- `Notificador`
- `NotificacionUsuario`
- `NotificacionAdministrador`

Idea:

- enviar mensajes a distintos interesados cuando ocurre algo.

`Notificador` guarda una lista de observadores.
Cuando se llama `notificar()`, todos reciben el mensaje.

Tambien tiene:

- `agregarObservador()`
- `eliminarObservador()`
- `getCantidadObservadores()`

### State: estados de compra

Clases:

- `EstadoCompraInterface`
- `EstadoCreada`
- `EstadoPagada`
- `EstadoConfirmada`
- `EstadoCancelada`
- `EstadoReembolsada`
- `EstadoIncidenciaCompra`

Idea:

- cada estado sabe que acciones permite.

Por ejemplo:

- `EstadoCreada` permite pagar o cancelar.
- `EstadoPagada` permite confirmar, cancelar o reembolsar.
- `EstadoConfirmada` puede pasar a cancelada o reembolsada.

La clase `Compra` delega el comportamiento a estos objetos.

---

## 13. Flujo detallado de una compra

Este es el flujo completo de compra en el sistema.

### Paso 1: ver cartelera

`MainController` obtiene los eventos publicados desde `EventoService`.

### Paso 2: abrir detalle

Al pulsar `Comprar`, `MainApp` guarda el evento seleccionado y `Navigator` cambia de vista.

### Paso 3: elegir zona y asiento

`EventoController`:

- muestra zonas,
- filtra asientos disponibles,
- valida que el usuario haya elegido ambos.

### Paso 4: crear compra

`MainApp.mostrarCompra()` llama a `CompraService.crearCompra()`.

Ese metodo usa `CompraFacade`, que:

- reserva el asiento con `GestorReservas`,
- crea la entrada,
- construye la compra con `ReservaBuilder`.

### Paso 5: agregar servicios

En `CompraController`, si el usuario marca servicios:

- VIP
- seguro
- parqueadero

Entonces se agregan a la compra y cambia el total.

### Paso 6: pagar

`CompraController` toma el metodo de pago seleccionado.
Luego llama a `CompraService.pagarCompra()`.

Eso llega a `CompraFacade.pagarCompra()`:

- crea un `Pago`,
- procesa el pago,
- si fue aprobado vende los asientos.

### Paso 7: confirmar compra

Luego `CompraController.confirmarCompra()` llama al servicio.

La compra cambia de estado a `CONFIRMADA`.

### Paso 8: notificar

Cada vez que ocurre un cambio importante, se usa `DatosPrueba.getInstancia().getNotificador().notificar(...)`.

El mensaje queda visible en la barra inferior del sistema.

---

## 14. Flujo del administrador

El administrador usa la vista `admin-view.fxml`.

### Gestion de eventos

Puede:

- publicar,
- pausar,
- cancelar.

Esto llama a `EventoService`.

### Gestion de asientos

Puede:

- bloquear,
- liberar.

Esto llama a `AdminService`.

### Registro de incidencias

Llena:

- tipo,
- descripcion,
- evento afectado.

Luego `AdminService.registrarIncidencia()` crea la incidencia y la guarda en `DatosPrueba`.

### Metricas

`AdminService.obtenerMetricasBasicas()` calcula:

- total de ventas,
- cantidad de compras,
- eventos publicados,
- ocupacion por zona.

---

## 15. Estilo visual

El archivo `styles.css` define la apariencia.

Colores principales:

- rojo
- negro
- blanco
- gris

Clases CSS importantes:

- `.top-bar`
- `.logo-label`
- `.hero-bar`
- `.section-wrapper`
- `.evento-card`
- `.poster-box`
- `.primary-button`
- `.secondary-button`
- `.detail-panel`
- `.footer-bar`

La idea fue dar una apariencia tipo cartelera, sin copiar marcas reales.

---

## 16. Archivo `pom.xml`

Este archivo configura Maven.

Contiene:

- Java 17
- JavaFX 21.0.2
- dependencias `javafx-controls` y `javafx-fxml`
- plugin compilador
- plugin de JavaFX
- JUnit 5 para pruebas
- Surefire para ejecutar tests

La clase principal configurada para JavaFX es:

- `co.edu.uniquindio.eventos.app.Launcher`

---

## 17. Pruebas unitarias

Las pruebas estan en:

- `src/test/java/co/edu/uniquindio/eventos`

Se probaron:

- modelos,
- servicios,
- repository,
- patrones de diseño.

No se probaron vistas JavaFX todavia.

Eso se hizo asi porque el objetivo era mantener pruebas sencillas y entendibles.

---

## 18. Como ejecutar el proyecto

### Desde IntelliJ

Ejecutar:

- `co.edu.uniquindio.eventos.app.Launcher`

### Desde Maven

Comandos:

```bash
mvn clean javafx:run
```

Para pruebas:

```bash
mvn test
```

---

## 19. Como explicar el proyecto en sustentacion

Una forma sencilla de explicarlo es esta:

### Parte 1: idea general

Decir:

- es una plataforma de eventos hecha en JavaFX,
- maneja datos en memoria,
- separa interfaz, logica y modelos.

### Parte 2: estructura

Explicar:

- `model` tiene las entidades,
- `service` la logica,
- `controller` conecta la interfaz,
- `repository` carga datos,
- `patterns` muestra los patrones de diseño.

### Parte 3: flujo del usuario

Explicar:

- entra a cartelera,
- mira el detalle,
- elige asiento,
- compra,
- paga,
- confirma.

### Parte 4: flujo admin

Explicar:

- cambia estados de eventos,
- bloquea asientos,
- registra incidencias,
- consulta metricas.

### Parte 5: patrones

Explicar con frases cortas:

- `Singleton`: un solo gestor de reservas.
- `Factory`: centraliza la creacion de eventos.
- `Builder`: arma una compra paso a paso.
- `Decorator`: agrega servicios adicionales.
- `Facade`: simplifica la compra.
- `Adapter`: adapta reportes externos.
- `Strategy`: cambia el metodo de pago.
- `Observer`: manda notificaciones.
- `State`: cambia comportamiento segun estado de compra.

---

## 20. Resumen final

Este proyecto busca ser:

- funcional,
- claro,
- organizado,
- facil de explicar,
- adecuado para tercer semestre.

No pretende ser una solucion empresarial compleja.
Su objetivo principal es demostrar:

- programacion orientada a objetos,
- JavaFX,
- separacion por capas simples,
- uso de patrones de diseño,
- manejo de colecciones en memoria.

Si quieren, este archivo tambien se puede convertir despues en:

- guia de sustentacion,
- documento tecnico,
- base para el informe final.
