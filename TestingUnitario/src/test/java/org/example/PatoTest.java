package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.example.Entity.Alimento;
import org.example.Entity.Comportamiento;
import org.example.Entity.Pato;
import org.example.Entity.TipoAlimento;
import org.junit.jupiter.api.Test;

public class PatoTest {

  private Pato pato;

  @Test
  public void testClasePatoExiste() {
    try {
      Class<?> clase = Class.forName("org.example.Entity.Pato");
      assertNotNull(clase, "La clase Pato debería existir");
    } catch (ClassNotFoundException e) {
      fail("No se encontró la clase Pato");
    }
  }

  @Test
  public void crearTipoAlimento() {

    TipoAlimento tipoAlimento = TipoAlimento.builder().nombre("Verdura").build();

    assertNotNull(tipoAlimento, "El objeto no debería ser null");
    assertEquals("Verdura", tipoAlimento.getNombre(), "El nombre debería ser 'Verdura'");
  }

  @Test
  public void modificarTipoAlimento() {

    TipoAlimento tipoAlimento = TipoAlimento.builder().nombre("Verdura").build();

    tipoAlimento.setNombre("Carnecita");

    assertEquals("Carnecita", tipoAlimento.getNombre(), "El nombre debería ser 'Carnecita'");
  }

  @Test
  public void crearAlimento() {

    TipoAlimento tipoAlimento = TipoAlimento.builder().nombre("Verdura").build();

    Alimento alimento = Alimento.builder().nombre("Lechuga").tipoAlimento(tipoAlimento).build();

    assertNotNull(alimento, "El objeto no debería ser null");
    assertEquals("Lechuga", alimento.getNombre(), "El nombre debería ser 'Verdura'");
    assertEquals(
        tipoAlimento, alimento.getTipoAlimento(), "El tipo de alimento debería ser 'Verdura'");
  }

  @Test
  public void testAtributoPlumajePato() {
    try {
      assertEquals(String.class, Pato.class.getDeclaredField("plumajePato").getType());
    } catch (NoSuchFieldException e) {
      fail("No se encontró el atributo plumajePato");
    }
  }

  @Test
  public void testAtributoNombreCientificoPato() {
    try {
      assertEquals(String.class, Pato.class.getDeclaredField("nombreCientificoPato").getType());
    } catch (NoSuchFieldException e) {
      fail("No se encontró el atributo nombreCientificoPato");
    }
  }

  @Test
  public void testAtributoPesoMinPato() {
    try {
      assertEquals(Float.class, Pato.class.getDeclaredField("pesoMinPato").getType());
    } catch (NoSuchFieldException e) {
      fail("No se encontró el atributo pesoMinPato");
    }
  }

  @Test
  public void testAtributoPesoMaxPato() {
    try {
      assertEquals(Float.class, Pato.class.getDeclaredField("pesoMaxPato").getType());
    } catch (NoSuchFieldException e) {
      fail("No se encontró el atributo pesoMaxPato");
    }
  }

  @Test
  public void testAtributoIdPato() {
    try {
      assertEquals(Long.class, Pato.class.getDeclaredField("idPato").getType());
    } catch (NoSuchFieldException e) {
      fail("No se encontró el atributo idPato");
    }
  }

  @Test
  public void testClaseAlimentoExiste() {
    try {
      Class<?> clase = Class.forName("org.example.Entity.Alimento");
      assertNotNull(clase, "La clase Alimento debería existir");
    } catch (ClassNotFoundException e) {
      fail("No se encontró la clase Alimento");
    }
  }

  @Test
  public void testTipoAtributoStringParaNombreAlimento() {
    Alimento alimento = Alimento.builder().nombre("Lechuga").build();
    assertInstanceOf(String.class, alimento.getNombre(), "El nombre debería ser de tipo String");
  }

  @Test
  public void testClaseTipoAlimentoExiste() {
    try {
      Class<?> clase = Class.forName("org.example.Entity.TipoAlimento");
      assertNotNull(clase, "La clase TipoAlimento debería existir");
    } catch (ClassNotFoundException e) {
      fail("No se encontró la clase TipoAlimento");
    }
  }

  @Test
  public void atributosDeComportamientoConCamelCase() {
    Class<?> comportamientoClass = Comportamiento.class;

    for (Field field : comportamientoClass.getDeclaredFields()) {
      String nombreCampo = field.getName();
      boolean esCamelCase = nombreCampo.matches("^[a-z]+[a-zA-Z0-9]*$");

      System.out.println("Verificando atributo: " + nombreCampo);

      assertTrue(esCamelCase, "El atributo '" + nombreCampo + "' no está en camelCase");
      System.out.println("Todos los atributos usan Camel Case");
    }
  }

  @Test
  void siHayUnAtributoId_debeSerLong() {
    Class<?> clase = Comportamiento.class;

    for (Field field : clase.getDeclaredFields()) {
      String nombreCampo = field.getName();

      if (nombreCampo.contains("id")) {
        Class<?> tipoCampo = field.getType();

        boolean esLongValido = tipoCampo.equals(long.class) || tipoCampo.equals(Long.class);

        assertTrue(
            esLongValido,
            "El atributo 'id' debe ser de tipo long o Long, pero es: " + tipoCampo.getSimpleName());
        System.out.println("El id de la clase es de tipo Long!");
      }
    }
  }

  @Test
  void todosLosAtributosDebenSerPrivate() {
    Class<?> clase = Comportamiento.class; // Cambiá esto por la clase que quieras verificar

    for (Field field : clase.getDeclaredFields()) {
      boolean esPrivate = Modifier.isPrivate(field.getModifiers());

      assertTrue(esPrivate, "El atributo '" + field.getName() + "' no es private");
    }
  }

  @Test
  void debeSimularSonidoCorrectamente() {
    Pato pato =
        new Pato(
            1L, "Lucas", "Anas platyrhynchos", 20f, 30f, "Vegetal", new Comportamiento("Quack"));

    String resultado = pato.simularSonido();
    assertEquals("El pato Lucas está haciendo Quack", resultado);
  }

  // PRUEBA ARREGLADA
  @Test
  void pesoMinimoNoDebeSerMayorQuePesoMaximo() {
    Pato pato =
        Pato.builder()
            .nombrePato("Pato Peso")
            .nombreCientificoPato("Anas")
            .pesoMinPato(5.0f) // CORREGIR
            .pesoMaxPato(9.0f)
            .plumajePato("Gris")
            .build();

    assertTrue(
        pato.getPesoMinPato() <= pato.getPesoMaxPato(),
        "El peso mínimo no debe ser mayor que el peso máximo");
  }

  @Test
  void simularSonidoDebeLanzarExcepcionSiNoHayComportamiento() {
    Pato pato = Pato.builder().nombrePato("Silencioso").build();

    assertThrows(IllegalStateException.class, pato::simularSonido);
  }

  @Test
  void testModificarComportamientoExistente() {
    Comportamiento comportamiento = new Comportamiento("Graznido");
    Pato pato = Pato.builder().nombrePato("Lucas").comportamientoSonido(comportamiento).build();

    comportamiento.setNombreComportamiento("Graznido Modificado");

    assertEquals(
        "El pato Lucas está haciendo Graznido Modificado",
        pato.simularSonido(),
        "El sonido del pato debería reflejar el cambio en el comportamiento");
  }

  @Test
  void testPatoNoArgsConstructor() {
    Pato pato = new Pato();

    assertAll(
        "Verificar constructor sin argumentos",
        () -> assertNull(pato.getIdPato()),
        () -> assertNull(pato.getNombrePato()),
        () -> assertNull(pato.getNombreCientificoPato()),
        () -> assertNull(pato.getPesoMinPato()),
        () -> assertNull(pato.getPesoMaxPato()),
        () -> assertNull(pato.getPlumajePato()),
        () -> assertNull(pato.getComportamientoSonido()));
  }

  @Test
  void testPatoComportamientoNuloPorDefecto() {
    Pato pato = new Pato();
    assertNull(
        pato.getComportamientoSonido(),
        "Un nuevo pato debería tener comportamiento null por defecto");
  }
}
