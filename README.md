# Plataforma de Gestion de Eventos y Venta de Entradas (PGII)

Proyecto academico de Programacion II (tercer semestre) construido con Java, JavaFX y Maven.
La aplicacion permite gestionar eventos, seleccionar asientos por zonas, comprar entradas con pago simulado y administrar operaciones basicas.

## Ejecucion

1. Compilar:
`mvn clean compile`

2. Ejecutar JavaFX:
`mvn clean javafx:run`

3. Ejecutar pruebas:
`mvn test`

Nota: en este entorno no estuvo disponible el comando `mvn`, por lo tanto la verificacion de compilacion/ejecucion se hizo por revision de codigo.

## Usuarios de prueba

- Usuario 1: Mariana Rodriguez (`mariana@uq.edu.co`)
- Usuario 2: Sofia Aviles (`sofia@uq.edu.co`)
- Usuario 3: Juan Tellez (`juan@uq.edu.co`)
- Administrador: Admin Eventos (`admin@uq.edu.co`)

## Funcionalidades principales

- Cartelera de eventos publicados.
- Login y registro en memoria (sin base de datos) con sesion por rol.
- Mi perfil (ver y editar nombre, correo y telefono).
- Historial de compras con filtros por evento/estado/fecha.
- Detalle de evento con mapa visual de zonas y asientos.
- Seleccion multiple de asientos (una compra con varias entradas).
- Resumen de compra con subtotal, servicios y total final.
- Servicios adicionales: VIP, Seguro de cancelacion y Parqueadero.
- Pago simulado con Strategy: Tarjeta, PSE y Nequi.
- Comprobante en texto.
- Panel administrador por pestanas: eventos, usuarios, recintos/zonas, asientos, compras, incidencias y metricas.
- Graficos JavaFX en admin (BarChart y PieChart).

## Credenciales de prueba

- Administrador: `admin@eventosuq.com` / `admin123`
- Usuario: `mariana@eventosuq.com` / `1234`

## Requisitos funcionales (resumen)

- RF-001 a RF-005: implementados con simulacion de usuario + flujo de consulta/seleccion.
- RF-006 a RF-009: implementados (multi-entrada, servicios, pago, estados).
- RF-010: implementado a nivel de servicio (filtros por evento/estado/fecha).
- RF-011: CSV funcional (archivo temporal) y PDF simulado.
- RF-012 a RF-019: implementacion parcial/funcional basica en servicios y vista admin.

Detalle completo en `REVISION_REQUISITOS.md`.

## Patrones implementados

### Creacionales
- Singleton: `GestorReservas`.
- Factory Method: `EventoFactory`.
- Builder: `ReservaBuilder`.

### Estructurales
- Decorator: `ServicioAdicional`, `ServicioDecorator`, `ServicioVIP`, `SeguroCancelacion`, `Parqueadero`.
- Facade: `CompraFacade`.
- Adapter: `ReporteExternoAdapter`.

### Comportamiento
- Strategy: `MetodoPago`, `PagoTarjeta`, `PagoPSE`, `PagoNequi`.
- Observer: `Notificador`, `Observador`, `NotificacionUsuario`, `NotificacionAdministrador`.
- State: `EstadoCompraInterface` + estados de compra.

## SOLID aplicado

- SRP: separacion entre modelos, servicios y controladores.
- OCP: extension de servicios via Decorator y metodos de pago via Strategy.
- LSP: `Administrador` hereda de `Usuario`.
- ISP: interfaces pequenas (`MetodoPago`, `Reporte`, `Observador`, `ServicioAdicional`, `EstadoCompraInterface`).
- DIP: `Pago` depende de `MetodoPago`; reportes dependen de interfaz `Reporte`.

## Estructura del proyecto

- `src/main/java/co/edu/uniquindio/eventos/model`: entidades y enums.
- `src/main/java/co/edu/uniquindio/eventos/service`: logica de negocio.
- `src/main/java/co/edu/uniquindio/eventos/controller`: controladores JavaFX.
- `src/main/java/co/edu/uniquindio/eventos/patterns`: patrones por categoria.
- `src/main/java/co/edu/uniquindio/eventos/repository`: `DatosPrueba`.
- `src/main/resources/views`: vistas FXML.
- `src/main/resources/styles`: CSS.
- `src/test/java`: pruebas unitarias JUnit 5.

## Notas de sustentacion

- Se privilegio una solucion clara y mantenible para tercer semestre.
- No se usa Spring Boot, BD real ni APIs externas.
- Cada evento tiene mapa de asientos independiente para evitar estados compartidos.
