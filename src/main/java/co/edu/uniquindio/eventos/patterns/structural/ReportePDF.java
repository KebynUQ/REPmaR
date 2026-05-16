package co.edu.uniquindio.eventos.patterns.structural;

import co.edu.uniquindio.eventos.model.Usuario;

public class ReportePDF implements Reporte {
    @Override
    public String generar(Usuario usuario) {
        return "Reporte PDF de compras para " + usuario.getNombreCompleto();
    }
}
