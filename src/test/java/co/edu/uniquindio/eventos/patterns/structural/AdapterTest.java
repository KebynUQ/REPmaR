package co.edu.uniquindio.eventos.patterns.structural;

import co.edu.uniquindio.eventos.TestDataUtil;
import co.edu.uniquindio.eventos.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AdapterTest {

    @Test
    void debeGenerarReportesNoVacios() {
        Usuario usuario = TestDataUtil.crearUsuario();

        String pdf = new ReportePDF().generar(usuario);
        String csv = new ReporteCSV().generar(usuario);
        String externo = new ReporteExternoAdapter(new ServicioReporteExterno()).generar(usuario);

        assertFalse(pdf.isBlank());
        assertFalse(csv.isBlank());
        assertFalse(externo.isBlank());
    }
}
