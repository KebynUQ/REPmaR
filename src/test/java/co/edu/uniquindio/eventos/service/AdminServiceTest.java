package co.edu.uniquindio.eventos.service;

import co.edu.uniquindio.eventos.model.Incidencia;
import co.edu.uniquindio.eventos.model.PanelMetricas;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminServiceTest {

    @Test
    void debeRegistrarIncidenciaYObtenerMetricas() {
        AdminService service = new AdminService();
        int cantidadInicial = service.listarIncidencias().size();

        Incidencia incidencia = service.registrarIncidencia("Prueba", "Descripcion", "Entidad");
        PanelMetricas metricas = service.obtenerMetricasBasicas();

        assertNotNull(incidencia);
        assertTrue(service.listarIncidencias().size() >= cantidadInicial + 1);
        assertNotNull(metricas);
    }
}
