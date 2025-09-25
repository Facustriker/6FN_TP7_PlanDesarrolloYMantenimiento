package org.example;

import org.example.Entity.Alimento;
import org.example.Entity.Comportamiento;
import org.example.Entity.Pato;
import org.example.Entity.TipoAlimento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlimentoIntegrationTest {
    private TipoAlimento tipoAlimento;
    private Alimento alimento;
    private Comportamiento comportamiento;

    @Test
    public void vincularTipoAlimentoConAlimento() {
        TipoAlimento tipoAlimento = TipoAlimento.builder().nombre("Verdura").build();
        Alimento alimento = Alimento.builder().nombre("Lechuga").tipoAlimento(tipoAlimento).build();

        assertNotNull(alimento, "El objeto no debería ser null");
        assertEquals("Lechuga", alimento.getNombre(),
                "El nombre debería ser 'Verdura'");
        assertEquals(tipoAlimento, alimento.getTipoAlimento(),
                "El tipo de alimento debería ser 'Verdura'");
    }

    @Test
    public void crearPato() {
        Comportamiento comportamiento = Comportamiento.builder().nombreComportamiento("Sonido").fechaHoraBajaComportamiento(null).build();
        Pato pato = Pato.builder()
                .nombrePato("Pato criollo")
                .plumajePato("Tupido")
                .nombreCientificoPato("Patitus Criullitos")
                .pesoMaxPato(10.0f)
                .pesoMinPato(5.0f)
                .comportamientoSonido(comportamiento)
                .build();

        // Validar que no es nulo
        assertNotNull(pato);

        // Validar atributos del pato
        assertEquals("Pato criollo", pato.getNombrePato());
        assertEquals("Tupido", pato.getPlumajePato());
        assertEquals("Patitus Criullitos", pato.getNombreCientificoPato());
        assertEquals(10.0f, pato.getPesoMaxPato());
        assertEquals(5.0f, pato.getPesoMinPato());

        // Validar que el comportamiento está asociado y correcto
        assertNotNull(pato.getComportamientoSonido());
        assertEquals("Sonido", pato.getComportamientoSonido().getNombreComportamiento());
        assertNull(pato.getComportamientoSonido().getFechaHoraBajaComportamiento());
    }
}
