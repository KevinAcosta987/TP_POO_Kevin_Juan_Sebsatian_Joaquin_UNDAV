package com.tp_poo.modelo;

/*
 * Representa las categorías del juego Carrera de Mente.
 * Se define como una enumeración (enum) que asocia los colores clásicos con sus respectivas temáticas.
 * Usamos enum para que solo puedan ser estos valores y no cualquier string.
 */
public enum Categoria {
  CELESTE("Ciencias"),
  MARRON("Historia"),
  AMARILLO("Geografía"),
  VERDE("Artes"),
  ROSA("Espectáculos y Entretenimiento"),
  GRIS("Elige el jugador");

  private final String descripcion;

  Categoria(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  // Indica si esta categoría es gris.
  public boolean esGris() {
    return this == GRIS;
  }

  // Retorna únicamente las categorías temáticas que tienen preguntas.
  public static Categoria[] getCategoriasPreguntables() {
    return java.util.Arrays.stream(values())
        .filter(c -> !c.esGris())
        .toArray(Categoria[]::new);
  }

  /*
   * El @Override es para sobreescribir el método toString que viene por defecto y
   * que retorne el nombre de la categoría seguido de su descripción, en ves de
   * limitarse a devolver solo AZUL, retorna AZUL(Ciencias).
   */

  @Override
  public String toString() {
    return this.name() + " (" + descripcion + ")";
  }
}
