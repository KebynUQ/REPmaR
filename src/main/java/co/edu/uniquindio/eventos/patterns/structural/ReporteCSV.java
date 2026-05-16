package co.edu.uniquindio.eventos.patterns.structural;

import co.edu.uniquindio.eventos.model.Usuario;

public class ReporteCSV implements Reporte {
    @Override
    public String generar(Usuario usuario) {
        return "Reporte CSV de compras para " + usuario.getNombreCompleto();
    }
}
