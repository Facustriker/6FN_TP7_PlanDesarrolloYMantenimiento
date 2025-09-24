package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

//lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//JPA
@Entity


public class Pato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPato;

    private String nombrePato;
    private String nombreCientificoPato;
    private Float pesoMinPato;
    private Float pesoMaxPato;
    private String plumajePato;

    @ManyToOne
    private Comportamiento comportamientoSonido;

    // Metodos del Pato
    public String simularSonido(){
        if (comportamientoSonido == null) {
            throw new IllegalStateException("No se ha definido un comportamiento de sonido");
        }
        return "El pato " + nombrePato + " está haciendo " + comportamientoSonido.getNombreComportamiento();
    }
}
