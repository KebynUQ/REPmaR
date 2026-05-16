package co.edu.uniquindio.eventos;

import co.edu.uniquindio.eventos.model.Asiento;
import co.edu.uniquindio.eventos.model.Entrada;
import co.edu.uniquindio.eventos.model.Evento;
import co.edu.uniquindio.eventos.model.Recinto;
import co.edu.uniquindio.eventos.model.Usuario;
import co.edu.uniquindio.eventos.model.Zona;

import java.time.LocalDateTime;

public class TestDataUtil {
    public static Usuario crearUsuario() {
        return new Usuario("U-T", "Usuario Prueba", "usuario@prueba.com", "3000000000");
    }

    public static Recinto crearRecinto() {
        return new Recinto("R-T", "Recinto Prueba", "Calle 1", "Armenia");
    }

    public static Zona crearZona() {
        Zona zona = new Zona("Z-T", "General", 10, 50000);
        zona.agregarAsiento(new Asiento("A-1", "A", 1));
        zona.agregarAsiento(new Asiento("A-2", "A", 2));
        return zona;
    }

    public static Evento crearEvento() {
        Recinto recinto = crearRecinto();
        recinto.agregarZona(crearZona());
        return new Evento("E-T", "Evento Prueba", "Concierto",
                "Descripcion de prueba", "Armenia", LocalDateTime.now().plusDays(2), recinto);
    }

    public static Entrada crearEntrada() {
        Zona zona = crearZona();
        Asiento asiento = zona.getAsientos().get(0);
        return new Entrada("ENT-T", zona, asiento, zona.getPrecioBase());
    }
}
