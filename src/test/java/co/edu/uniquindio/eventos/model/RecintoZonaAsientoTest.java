package co.edu.uniquindio.eventos.model;

import co.edu.uniquindio.eventos.model.enums.EstadoAsiento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecintoZonaAsientoTest {

    @Test
    void debeCrearRecintoYAgregarZona() {
        Recinto recinto = new Recinto("R1", "Coliseo", "Calle 1", "Armenia");
        Zona zona = new Zona("Z1", "VIP", 5, 90000);

        recinto.agregarZona(zona);

        assertEquals(1, recinto.getZonas().size());
    }

    @Test
    void debeAgregarAsientosAZona() {
        Zona zona = new Zona("Z1", "VIP", 5, 90000);
        zona.agregarAsiento(new Asiento("A1", "A", 1));
        zona.agregarAsiento(new Asiento("A2", "A", 2));

        assertEquals(2, zona.getAsientos().size());
    }

    @Test
    void debeReservarBloquearYLiberarAsiento() {
        Asiento asiento = new Asiento("A1", "A", 1);

        asiento.reservar();
        assertEquals(EstadoAsiento.RESERVADO, asiento.getEstado());

        asiento.bloquear();
        assertEquals(EstadoAsiento.BLOQUEADO, asiento.getEstado());

        asiento.liberar();
        assertEquals(EstadoAsiento.DISPONIBLE, asiento.getEstado());
    }

    @Test
    void debeConsultarAsientosDisponibles() {
        Zona zona = new Zona("Z1", "General", 5, 40000);
        Asiento asiento1 = new Asiento("A1", "A", 1);
        Asiento asiento2 = new Asiento("A2", "A", 2);
        asiento2.reservar();
        zona.agregarAsiento(asiento1);
        zona.agregarAsiento(asiento2);

        assertEquals(1, zona.consultarAsientosDisponibles().size());
    }
}
