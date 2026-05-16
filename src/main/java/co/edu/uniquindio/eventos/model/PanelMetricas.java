package co.edu.uniquindio.eventos.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class PanelMetricas {
    private double totalVentas;
    private int cantidadCompras;
    private int cantidadEventosPublicados;
    private Map<String, Double> ocupacionPorZona;

    public PanelMetricas() {
        this.ocupacionPorZona = new LinkedHashMap<>();
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public int getCantidadCompras() {
        return cantidadCompras;
    }

    public void setCantidadCompras(int cantidadCompras) {
        this.cantidadCompras = cantidadCompras;
    }

    public int getCantidadEventosPublicados() {
        return cantidadEventosPublicados;
    }

    public void setCantidadEventosPublicados(int cantidadEventosPublicados) {
        this.cantidadEventosPublicados = cantidadEventosPublicados;
    }

    public Map<String, Double> getOcupacionPorZona() {
        return ocupacionPorZona;
    }
}
