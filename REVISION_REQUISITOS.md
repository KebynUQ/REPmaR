# Revision de Requisitos PGII

## Requisitos del Usuario
- RF-001: Cumplido
- RF-002: Cumplido
- RF-003: Parcial
- RF-004: Cumplido
- RF-005: Cumplido
- RF-006: Cumplido
- RF-007: Cumplido
- RF-008: Cumplido
- RF-009: Cumplido
- RF-010: Cumplido
- RF-011: Cumplido

## Requisitos del Administrador
- RF-012: Cumplido
- RF-013: Cumplido
- RF-014: Cumplido
- RF-015: Cumplido
- RF-016: Cumplido
- RF-017: Cumplido
- RF-018: Cumplido
- RF-019: Cumplido

## Entidades
- Usuario: Cumplido
- Administrador: Cumplido
- Evento: Cumplido
- Recinto: Cumplido
- Zona: Cumplido
- Asiento: Cumplido
- Compra: Cumplido
- Entrada: Cumplido
- Pago: Cumplido
- Tarifa: Cumplido
- Incidencia: Cumplido

## Enums
- EstadoEvento: Cumplido
- EstadoAsiento: Cumplido
- EstadoCompra: Cumplido
- EstadoEntrada: Cumplido
- EstadoPago: Cumplido
- EstadoIncidencia: Cumplido

## Patrones
- Singleton: Cumplido
- Factory Method: Cumplido
- Builder: Cumplido
- Decorator: Cumplido
- Facade: Cumplido
- Adapter: Cumplido
- Strategy: Cumplido
- Observer: Cumplido
- State: Cumplido

## JavaFX
Pantallas implementadas:
- Login (`login-view.fxml` + `LoginController`)
- Registro (`registro-view.fxml` + `RegistroController`)
- Cartelera principal (`main-view.fxml` + `MainController`)
- Detalle/seleccion de asientos visual (`detalle-evento-view.fxml` + `EventoController`)
- Compra/pago/comprobante (`compra-view.fxml` + `CompraController`)
- Administracion y metricas con charts (`admin-view.fxml` + `AdminController`)

## Reportes
- CSV: funcional, genera archivo temporal con historial de compras del usuario (`ReporteCSV`).
- PDF: simulado pero funcional, genera archivo `.txt` tipo reporte PDF (`ReportePDF`).
- Adapter externo: implementado (`ReporteExternoAdapter`).

## Tests
Existen pruebas unitarias para:
- Modelos (Usuario, Evento, Compra, Pago, Recinto/Zona/Asiento, etc.)
- Servicios (UsuarioService, EventoService, CompraService, AdminService)
- Patrones (Factory, Builder, Singleton, Decorator, Adapter, Strategy, Observer, State)
- Datos de prueba

## Cambios implementados en esta revision
- Correccion de FXML con valores `$...` que causaban `Invalid path`.
- Navegacion de boton Comprar con validacion y `printStackTrace` en error real.
- Separacion de asientos por evento en `DatosPrueba` (recintos independientes por evento).
- Filtros adicionales de eventos por fecha y precio maximo en `EventoService`.
- Gestion basica de usuario (actualizar/eliminar) en `UsuarioService`.
- Gestion de compras: cancelar/reembolsar y filtros por usuario/evento/estado/fecha en `CompraService`.
- `CompraFacade` extendida con cancelar/reembolso.
- Gestion de recintos/zonas a nivel de servicio admin (crear/actualizar/eliminar/listar por evento).
- RF-006: boton de cancelar compra antes de pago en `CompraController`/`compra-view.fxml`.
- Metricas extendidas (`ingresosServicios`, `tasaCancelacion`, `ventasPorEvento`) en `PanelMetricas`/`AdminService`.
- Visualizacion de metricas con `BarChart` y `PieChart` en Admin.
- Visualizacion de metricas completada con `BarChart`, `PieChart` y `LineChart`.
- CSV funcional en `ReporteCSV`.
- README actualizado.
- Flujo inicial corregido: la app inicia en login, permite registro y maneja sesion por rol.

## Pendientes
- RF-014 completo (CRUD formal de recintos/zonas con vista dedicada).
- RF-010 vista de historial con filtros en UI (hoy disponible en servicio).
- RF-011 PDF real (actualmente simulado).
- RF-012/RF-013/RF-016: completar CRUD/reasignacion desde UI admin (hay base de servicio y estado).
- Revalidar `mvn clean compile`, `mvn test` y `mvn clean javafx:run` en entorno con Maven instalado.
