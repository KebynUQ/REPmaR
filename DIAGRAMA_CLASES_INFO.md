# Información para Diagrama de Clases UML - Eventos UQ

## 1. Resumen del sistema
Sistema Java/JavaFX para gestionar eventos y vender entradas en memoria.
Incluye dos roles (Usuario y Administrador), flujo de compra (asientos, servicios, pago, comprobante), historial, reportes, panel admin con métricas y patrones de diseño (creacionales, estructurales y de comportamiento).

## 2. Clases principales del dominio

### Usuario
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Representa al usuario comprador y su perfil/historial.

**Atributos:**
- idUsuario: String
- nombreCompleto: String
- correo: String
- telefono: String
- contrasena: String
- metodosPago: List<MetodoPago>
- compras: List<Compra>

**Métodos principales:**
- registrarse(): String
- iniciarSesion(): boolean
- actualizarPerfil(String): void
- actualizarPerfil(String, String, String): void
- consultarHistorialCompras(): List<Compra>
- descargarReporteCompras(Reporte): String
- agregarMetodoPago(MetodoPago): void
- agregarCompra(Compra): void

**Relaciones:**
- Asociación con Compra (1 a 0..*)
- Dependencia con Reporte (descarga reportes)
- Dependencia con MetodoPago

**Notas UML:**
- Debe aparecer en el diagrama principal.
- Clase central del dominio.

### Administrador
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Usuario con capacidades administrativas.

**Atributos:**
- codigoEmpleado: String

**Métodos principales:**
- gestionarUsuarios(): String
- gestionarEventos(): String
- gestionarRecintos(): String
- gestionarAsientos(): String
- gestionarCompras(): String
- registrarIncidencia(): String
- consultarMetricas(): String

**Relaciones:**
- Herencia con Usuario

**Notas UML:**
- Debe aparecer en diagrama principal.

### Evento
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Define evento publicable con datos de recinto y estado.

**Atributos:**
- idEvento: String
- nombre: String
- categoria: String
- descripcion: String
- ciudad: String
- fechaHora: LocalDateTime
- estado: EstadoEvento
- politicaCancelacion: String
- politicaReembolso: String
- recinto: Recinto

**Métodos principales:**
- publicar(): void
- pausar(): void
- cancelar(): void
- finalizar(): void
- consultarDisponibilidad(): List<Zona>
- getPrecioDesde(): double
- actualizarDatos(...): void
- asignarRecinto(Recinto): void

**Relaciones:**
- Asociación con Recinto (1 a 1)
- Asociación con Compra (1 a 0..*)

**Notas UML:**
- Clase central del dominio.

### Recinto
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Lugar del evento con zonas.

**Atributos:**
- idRecinto: String
- nombre: String
- direccion: String
- ciudad: String
- zonas: List<Zona>

**Métodos principales:**
- agregarZona(Zona): void
- eliminarZona(String): void
- consultarZonas(): List<Zona>
- actualizarDatos(String, String, String): void

**Relaciones:**
- Composición con Zona (1 a 1..*)

**Notas UML:**
- Clase principal del dominio.

### Zona
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Subdivisión del recinto con precio/capacidad y asientos.

**Atributos:**
- idZona: String
- nombre: String
- capacidad: int
- precioBase: double
- asientos: List<Asiento>

**Métodos principales:**
- agregarAsiento(Asiento): void
- consultarOcupacion(): double
- definirPrecioBase(double): void
- actualizarDatos(String, int, double): void
- consultarAsientosDisponibles(): List<Asiento>

**Relaciones:**
- Composición con Asiento (1 a 0..*)

**Notas UML:**
- Clase principal del dominio.

### Asiento
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Estado de un asiento para selección/venta.

**Atributos:**
- idAsiento: String
- fila: String
- numero: int
- estado: EstadoAsiento

**Métodos principales:**
- reservar(): void
- vender(): void
- bloquear(): void
- liberar(): void
- getEtiqueta(): String

**Relaciones:**
- Asociación con Entrada (1 a 0..1 por compra concreta)

**Notas UML:**
- Clase principal del dominio.

### Compra
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Orquesta entradas, servicios, pago, estado y total.

**Atributos:**
- idCompra: String
- usuario: Usuario
- evento: Evento
- fechaCreacion: LocalDateTime
- total: double
- estado: EstadoCompra
- entradas: List<Entrada>
- servicios: List<ServicioAdicional>
- descuentoPromocional: double
- pago: Pago
- estadoCompraActual: EstadoCompraInterface

**Métodos principales:**
- agregarEntrada(Entrada): void
- quitarEntrada(Entrada): void
- agregarServicio(ServicioAdicional): void
- calcularTotal(): double
- aplicarDescuentoPromocional(double): void
- pagar(Pago): boolean
- confirmar(): void
- cancelar(): void
- reembolsar(): void
- generarComprobante(): String

**Relaciones:**
- Asociación con Usuario (muchas compras por usuario)
- Asociación con Evento
- Composición con Entrada (1 a 1..*)
- Agregación con ServicioAdicional (1 a 0..*)
- Asociación con Pago (0..1)
- Dependencia con EstadoCompraInterface (State)

**Notas UML:**
- Clase central del dominio.

### Entrada
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Ticket individual dentro de una compra.

**Atributos:**
- idEntrada: String
- zona: Zona
- asiento: Asiento
- precioFinal: double
- estado: EstadoEntrada

**Métodos principales:**
- activar(): void
- usar(): void
- anular(): void

**Relaciones:**
- Asociación con Zona (1)
- Asociación con Asiento (1)

**Notas UML:**
- Clase principal del dominio.

### Pago
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Simulación de pago con Strategy.

**Atributos:**
- idPago: String
- valor: double
- fechaPago: LocalDateTime
- estado: EstadoPago
- metodoPago: MetodoPago

**Métodos principales:**
- procesarPago(): boolean
- generarComprobante(): String
- registrarReembolso(): void

**Relaciones:**
- Asociación/dependencia con MetodoPago (1)

**Notas UML:**
- Clase principal del dominio.

### Tarifa
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Cálculo simple de precio final por descuento.

**Atributos:**
- idTarifa: String
- nombre: String
- valorBase: double
- descuento: double

**Métodos principales:**
- calcularPrecioFinal(): double

**Relaciones:**
- Sin relaciones estructurales fuertes actualmente.

**Notas UML:**
- Aparece en diagrama como clase de negocio auxiliar.

### Incidencia
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Registro de incidentes administrativos.

**Atributos:**
- idIncidencia: String
- tipo: String
- descripcion: String
- fecha: LocalDateTime
- estado: EstadoIncidencia
- entidadAfectada: String

**Métodos principales:**
- registrar(): void
- consultarDetalle(): String

**Relaciones:**
- Asociación administrativa (evento/compra por texto).

**Notas UML:**
- Clase principal de soporte administrativo.

### PanelMetricas
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Contenedor de KPIs para panel admin.

**Atributos:**
- totalVentas: double
- ingresosServicios: double
- tasaCancelacion: double
- cantidadCompras: int
- cantidadEventosPublicados: int
- ocupacionPorZona: Map<String, Double>
- ventasPorEvento: Map<String, Double>
- ventasPorPeriodo: Map<String, Double>

**Métodos principales:**
- getters/setters

**Relaciones:**
- Dependencia desde AdminService/AdminController.

**Notas UML:**
- Clase de transferencia para métricas (incluir).

### Promocion
**Tipo:** Clase  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Regla simple de descuento promocional.

**Atributos:**
- idPromocion: String
- nombre: String
- descripcion: String
- porcentajeDescuento: double
- condicion: String
- activa: boolean

**Métodos principales:**
- aplicarDescuento(double): double
- estaActiva(): boolean

**Relaciones:**
- Dependencia desde PromocionService y CompraController.

**Notas UML:**
- Clase del dominio ampliado.

### SesionActual
**Tipo:** Clase (utilidad estática)  
**Paquete:** `co.edu.uniquindio.eventos.model`  
**Responsabilidad:** Mantener usuario autenticado en memoria.

**Atributos:**
- usuarioActual: Usuario (static)

**Métodos principales:**
- iniciarSesion(Usuario): void
- getUsuarioActual(): Usuario
- esAdministrador(): boolean
- cerrarSesion(): void

**Relaciones:**
- Dependencia desde LoginController/MainController/MainApp.

**Notas UML:**
- Clase auxiliar del código (incluir opcionalmente).

## 3. Otras clases reales por capa

### Servicios (paquete `co.edu.uniquindio.eventos.service`)

#### UsuarioService
- **Responsabilidad:** registro, login, búsqueda, actualización, eliminación de usuarios.
- **Métodos clave:** registrarUsuario, iniciarSesion, buscarPorCorreo, listarUsuarios, actualizarUsuario, eliminarUsuario.
- **Relaciones:** depende de DatosPrueba; usa Usuario/Administrador.

#### EventoService
- **Responsabilidad:** CRUD/filtros y cambio de estado de eventos.
- **Métodos clave:** listarEventosPublicados, filtrarPorCiudad/Categoria/Fecha/Precio, agregar/actualizar/eliminar, publicar/pausar/cancelar.
- **Relaciones:** depende de DatosPrueba; usa Evento/EstadoEvento.

#### CompraService
- **Responsabilidad:** flujo de compra de alto nivel (crear, agregar entradas/servicios, pagar, confirmar, cancelar, filtrar historial).
- **Métodos clave:** crearCompra(...), agregarEntrada, quitarEntrada, pagarCompra, confirmarCompra, cancelarCompra, reembolsarCompra, filtrarComprasUsuario.
- **Relaciones:** usa CompraFacade; depende de DatosPrueba.

#### AdminService
- **Responsabilidad:** operaciones administrativas (eventos, usuarios, recintos/zonas, asientos, incidencias, compras, métricas).
- **Métodos clave:** obtenerMetricasBasicas, registrarIncidencia, bloquear/liberar asiento, CRUD básico admin.
- **Relaciones:** depende de DatosPrueba y clases de dominio.

#### PromocionService
- **Responsabilidad:** catálogo de promociones activas y aplicación de descuento.
- **Métodos clave:** listarPromociones, listarPromocionesActivas, aplicarPromocion.
- **Relaciones:** usa Promocion.

### Repositorio

#### DatosPrueba (`co.edu.uniquindio.eventos.repository`)
- **Responsabilidad:** cargar datos en memoria (usuarios, admin, eventos, compras, incidencias, notificador).
- **Relación clave:** Singleton de datos de arranque por instancia estática.

### Controladores JavaFX (paquete `co.edu.uniquindio.eventos.controller`)

#### LoginController
- Login por correo/contraseña, navegación a registro.

#### RegistroController
- Registro de usuario con validaciones básicas.

#### MainController
- Menú principal por rol, cartelera, servicios, promociones, perfil, historial, navegación a detalle/compra/admin.

#### EventoController
- Detalle de evento y mapa visual de asientos (selección múltiple).

#### CompraController
- Aplicar servicios/promociones, pagar, confirmar/cancelar compra, comprobante.

#### HistorialController
- Filtros de compras, ver comprobante, descargar CSV, generar PDF simulado.

#### PerfilController
- Actualización de perfil de usuario.

#### AdminController
- Gestión por pestañas: eventos, usuarios, recintos/zonas, asientos, compras, incidencias, métricas y charts.

**Clases de controller mencionadas en solicitud pero no encontradas en código:**
- `ServiciosController` (no existe)
- `PromocionesController` (no existe)

### App / Util / Infra

#### MainApp (`co.edu.uniquindio.eventos.app`)
- Arranque JavaFX, servicios globales, flujo login/registro/principal.

#### Launcher (`co.edu.uniquindio.eventos.app`)
- Lanzador de MainApp.

#### Navigator (`co.edu.uniquindio.eventos`)
- Navegación central entre cartelera/detalle/compra/admin.

#### AlertUtil (`co.edu.uniquindio.eventos.util`)
- Utilitario de alertas JavaFX.

## 4. Relaciones UML recomendadas

- `Usuario <|-- Administrador`  
  Tipo: Herencia / Generalización  
  Multiplicidad: no aplica  
  Explicación: Administrador hereda comportamiento base de Usuario.

- `Usuario "1" -- "0..*" Compra`  
  Tipo: Asociación  
  Rol: un usuario realiza varias compras.

- `Evento "1" -- "0..*" Compra`  
  Tipo: Asociación  
  Rol: compras ligadas a un evento.

- `Compra "1" *-- "1..*" Entrada`  
  Tipo: Composición (sugerida para UML)  
  Rol: una compra contiene entradas.

- `Entrada "1" -- "1" Asiento`  
  Tipo: Asociación  
  Rol: cada entrada apunta a un asiento.

- `Entrada "1" -- "1" Zona`  
  Tipo: Asociación  
  Rol: cada entrada pertenece a una zona.

- `Evento "1" -- "1" Recinto`  
  Tipo: Asociación  
  Rol: el evento se realiza en un recinto.

- `Recinto "1" *-- "1..*" Zona`  
  Tipo: Composición  
  Rol: zonas contenidas por recinto.

- `Zona "1" *-- "0..*" Asiento`  
  Tipo: Composición  
  Rol: asientos contenidos por zona.

- `Compra "1" o-- "0..*" ServicioAdicional`  
  Tipo: Agregación/Asociación  
  Rol: servicios adicionales aplicados.

- `Compra "1" -- "0..1" Pago`  
  Tipo: Asociación  
  Rol: pago asociado a la compra.

- `Pago "1" --> "1" MetodoPago`  
  Tipo: Dependencia/Estrategia  
  Rol: Pago delega el procesamiento al método.

- `Compra ..> EstadoCompraInterface`  
  Tipo: Dependencia (State)  
  Rol: la compra delega transiciones de estado.

- `Notificador "1" o-- "0..*" Observador`  
  Tipo: Agregación  
  Rol: notifica observadores registrados.

- `CompraFacade ..> GestorReservas`  
  Tipo: Dependencia  
  Rol: reserva/libera/vende asientos en flujo de compra.

- `CompraFacade ..> ReservaBuilder`  
  Tipo: Dependencia  
  Rol: crea compras con Builder.

- `ReporteCSV ..|> Reporte` / `ReportePDF ..|> Reporte` / `ReporteExternoAdapter ..|> Reporte`  
  Tipo: Realización  
  Rol: implementaciones de reportes.

- `ReporteExternoAdapter --> ServicioReporteExterno`  
  Tipo: Asociación/Dependencia (Adapter)

- `PagoTarjeta ..|> MetodoPago`, `PagoPSE ..|> MetodoPago`, `PagoNequi ..|> MetodoPago`  
  Tipo: Realización (Strategy)

- `ServicioDecorator ..|> ServicioAdicional`  
  Tipo: Realización  
  Rol: base del Decorator.

- `ServicioVIP --|> ServicioDecorator`, `SeguroCancelacion --|> ServicioDecorator`, `Parqueadero --|> ServicioDecorator`, `Merchandising --|> ServicioDecorator`, `AccesoPreferencial --|> ServicioDecorator`  
  Tipo: Herencia

- `ServicioDecorator "1" -- "1" ServicioAdicional`  
  Tipo: Asociación  
  Rol: mantiene componente decorado.

## 5. Guía de flechas para Draw.io

- **Asociación:** línea continua normal.  
  Ejemplo: `Usuario -- Compra`.

- **Composición:** línea continua con rombo negro en contenedor.  
  Ejemplo: `Recinto *-- Zona`, `Zona *-- Asiento`.

- **Agregación:** línea continua con rombo blanco.  
  Ejemplo: `Notificador o-- Observador`, `Compra o-- ServicioAdicional`.

- **Herencia / Generalización:** línea continua con triángulo blanco hacia padre.  
  Ejemplo: `Administrador --|> Usuario`.

- **Realización (implementación de interface):** línea punteada con triángulo blanco hacia interface.  
  Ejemplo: `PagoTarjeta ..|> MetodoPago`.

- **Dependencia:** línea punteada con flecha normal.  
  Ejemplo: `CompraFacade ..> GestorReservas`.

## 6. Patrones de diseño en el diagrama

### Singleton
**Clase principal:** `GestorReservas`  
**Tipo:** Creacional  
**Debe aparecer en diagrama:** Sí  
**Relaciones UML:** `CompraFacade ..> GestorReservas`  
**Nota:** “Patrón creacional: Singleton”.

### Factory Method
**Clase principal:** `EventoFactory`  
**Tipo:** Creacional  
**Relaciones UML:** `AdminController ..> EventoFactory`, `DatosPrueba ..> EventoFactory`.

### Builder
**Clase principal:** `ReservaBuilder`  
**Tipo:** Creacional  
**Relaciones UML:** `CompraFacade ..> ReservaBuilder`, `ReservaBuilder --> Compra`.

### Decorator
**Clases:** `ServicioAdicional` (interface), `ServicioBase`, `ServicioDecorator` (abstracta), `ServicioVIP`, `SeguroCancelacion`, `Parqueadero`, `Merchandising`, `AccesoPreferencial`.

### Facade
**Clase principal:** `CompraFacade`  
**Relaciones:** simplifica creación/pago/confirmación/cancelación de compra.

### Adapter
**Clases:** `Reporte` (interface), `ReporteCSV`, `ReportePDF`, `ReporteExternoAdapter`, `ServicioReporteExterno`.

### Strategy
**Clases:** `MetodoPago` (interface), `PagoTarjeta`, `PagoPSE`, `PagoNequi`, `Pago`.

### Observer
**Clases:** `Observador` (interface), `Notificador`, `NotificacionUsuario`, `NotificacionAdministrador`.

### State
**Clases:** `EstadoCompraInterface` (interface), `EstadoCreada`, `EstadoPagada`, `EstadoConfirmada`, `EstadoCancelada`, `EstadoReembolsada`, `EstadoIncidenciaCompra`.

**Nota de consistencia:** En README aparece “EstadoIncidencia”; en código real la clase es `EstadoIncidenciaCompra`.

## 7. Enumeraciones

### EstadoEvento (`co.edu.uniquindio.eventos.model.enums`)
Valores: `BORRADOR`, `PUBLICADO`, `PAUSADO`, `CANCELADO`, `FINALIZADO`.
Usada por: `Evento`, `EventoService`, `AdminService`.

### EstadoAsiento
Valores: `DISPONIBLE`, `RESERVADO`, `VENDIDO`, `BLOQUEADO`.
Usada por: `Asiento`, `Zona`, `EventoController`, `GestorReservas`.

### EstadoCompra
Valores: `CREADA`, `PAGADA`, `CONFIRMADA`, `CANCELADA`, `REEMBOLSADA`, `INCIDENCIA`.
Usada por: `Compra`, `CompraService`, `AdminService`, `HistorialController`.

### EstadoEntrada
Valores: `ACTIVA`, `USADA`, `ANULADA`.
Usada por: `Entrada`.

### EstadoPago
Valores: `PENDIENTE`, `APROBADO`, `RECHAZADO`, `REEMBOLSADO`.
Usada por: `Pago`.

### EstadoIncidencia
Valores: `ABIERTA`, `EN_PROCESO`, `RESUELTA`, `CERRADA`.
Usada por: `Incidencia`.

## 8. Clases que se pueden omitir del diagrama principal

Para evitar saturar el diagrama principal (sin perder análisis):
- `MainApp`, `Launcher`, `Navigator` (infraestructura UI)
- `AlertUtil` (utilidad)
- Controladores JavaFX (`*Controller`) si haces un UML estrictamente de dominio
- Clases de test (`src/test/java`)

**Recomendación:** Crear dos diagramas:
1) Dominio + patrones (principal)  
2) Arquitectura UI/servicios (secundario).

## 9. Organización recomendada para Draw.io

- **Izquierda (actores/dominio usuario):** `Usuario`, `Administrador`, `SesionActual`, `Promocion`.
- **Centro (núcleo de negocio):** `Evento`, `Recinto`, `Zona`, `Asiento`, `Compra`, `Entrada`, `Pago`, `Tarifa`, `Incidencia`, `PanelMetricas`.
- **Derecha (patrones):**
  - Creacionales: `GestorReservas`, `EventoFactory`, `ReservaBuilder`
  - Estructurales: `CompraFacade`, bloque Decorator, bloque Adapter
  - Comportamiento: bloque Strategy, Observer, State
- **Inferior:** enums (`Estado*`) conectados por dependencia a clases que los usan.

## 10. Posibles problemas encontrados

1. **Inconsistencia README vs código (correos de prueba):** README muestra `@uq.edu.co`, código usa `@eventosuq.com`.
2. **Inconsistencia de nombre en State:** README menciona `EstadoIncidencia`; código real: `EstadoIncidenciaCompra`.
3. **Clases mencionadas en solicitud pero no encontradas:** `Notificacion` (clase simple de dominio), `ServiciosController`, `PromocionesController`, `RecintoService`, `SistemaEventos/PlataformaEventos`.
4. **RF-011 PDF real:** implementación es simulada (archivo `.txt`), no PDF binario.
5. **Relaciones evento-recinto:** en lógica actual cada evento puede tener recinto independiente (aunque nombre físico coincida), esto debe reflejarse como instancia separada por evento en datos de prueba.

## 11. Resumen para quien hará el diagrama

- **Número aproximado de clases principales de dominio:** 12 (`Usuario`, `Administrador`, `Evento`, `Recinto`, `Zona`, `Asiento`, `Compra`, `Entrada`, `Pago`, `Tarifa`, `Incidencia`, `PanelMetricas`) + `Promocion` + `SesionActual`.
- **Interfaces principales:** 5 (`MetodoPago`, `ServicioAdicional`, `Reporte`, `Observador`, `EstadoCompraInterface`).
- **Enums:** 6 (`EstadoEvento`, `EstadoAsiento`, `EstadoCompra`, `EstadoEntrada`, `EstadoPago`, `EstadoIncidencia`).
- **Patrones encontrados:** Singleton, Factory Method, Builder, Decorator, Facade, Adapter, Strategy, Observer, State.
- **Relaciones críticas a no dibujar mal:**
  - `Administrador --|> Usuario`
  - `Recinto *-- Zona`, `Zona *-- Asiento`
  - `Compra *-- Entrada`
  - `Pago --> MetodoPago`
  - `Notificador o-- Observador`
  - Implementaciones (`..|>`) de Strategy/Adapter/Decorator/State
- **Advertencia:** no mezclar dependencias de controladores con asociaciones de dominio en el mismo nivel visual; separar dominio y UI para claridad.
