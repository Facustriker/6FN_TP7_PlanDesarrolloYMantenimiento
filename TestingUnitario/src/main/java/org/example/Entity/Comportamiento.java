package org.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comportamiento {

  public Comportamiento(String nombreComportamiento) {
    this.nombreComportamiento = nombreComportamiento;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idComportamiento;

  private String nombreComportamiento;
  private Date fechaHoraBajaComportamiento;
}
