package co.edu.uniquindio.eventos.patterns.structural;

import co.edu.uniquindio.eventos.model.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReporteCSV implements Reporte {
    @Override
    public String generar(Usuario usuario) {
        StringBuilder csv = new StringBuilder("id_compra,evento,estado,total\n");
        usuario.getCompras().forEach(compra ->
                csv.append(compra.getIdCompra()).append(",")
                        .append(compra.getEvento().getNombre()).append(",")
                        .append(compra.getEstado()).append(",")
                        .append(String.format("%.0f", compra.getTotal()))
                        .append("\n"));
        try {
            Path archivo = Files.createTempFile("reporte-compras-" + usuario.getIdUsuario() + "-", ".csv");
            Files.writeString(archivo, csv.toString());
            return "Reporte CSV generado en: " + archivo.toAbsolutePath();
        } catch (IOException e) {
            return "No fue posible generar el CSV: " + e.getMessage();
        }
    }
}
