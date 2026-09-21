package com.tp_poo.modelo;

import java.util.Random;

public class Jugador {

  private String nombre;
  private int posicion;
  /*
   * Representación de la Matriz [Color][Cantidad]
   * Fila = índice de cada color/categoría.
   * Columna 0 = índice del color, Columna 1 = cantidad de estrellas obtenidas (no
   * puede ser mas de 1).
   */
  private int[][] estrellas;
  private Random random = new Random();

  public Jugador(String nombre) {
    this(nombre, Categoria.values().length);
  }

  public Jugador(String nombre, int cantidadColores) {
    this.nombre = nombre;
    this.posicion = 0;
    this.estrellas = new int[cantidadColores][2];
    for (int i = 0; i < cantidadColores; i++) {
      this.estrellas[i][0] = i;
      this.estrellas[i][1] = 0;
    }
  }

  /*
   * Resuelve internamente el tiro del dado (1 a 6) y avanza las posiciones
   * correspondientes
   * 
   * @return El número de casillas avanzadas en el dado.
   */
  public int avanzar() {
    int dado = tirarDado();
    this.posicion += dado;
    return dado;
  }

  // Permite avanzar una cantidad fija de casillas.
  public void avanzar(int casillas) {
    this.posicion += casillas;
  }

  // Genera un valor aleatorio de dado entre 1 y 6.
  public int tirarDado() {
    return random.nextInt(6) + 1;
  }

  // Responde a una pregunta delegando la validación en el objeto Pregunta.
  public boolean responderPregunta(Pregunta pregunta, String respuesta) {
    if (pregunta == null) {
      return false;
    }
    return pregunta.validaRespuesta(respuesta);
  }

  // Suma una estrella de la categoría indicada al inventario del jugador.
  public void sumarEstrella(Categoria categoria) {
    int index = categoria.ordinal();
    if (index < this.estrellas.length) {
      this.estrellas[index][1] = 1; // En Carrera de Mente se obtiene 1 estrella por color
    }
  }

  // Consulta si el jugador ya posee la estrella de una categoría dada.
  public boolean tieneEstrella(Categoria categoria) {
    int index = categoria.ordinal();
    if (index < this.estrellas.length) {
      return this.estrellas[index][1] > 0;
    }
    return false;
  }

  // Calcula la cantidad total de estrellas acumuladas.
  public int getCantidadEstrellasTotal() {
    int total = 0;
    for (int i = 0; i < this.estrellas.length; i++) {
      total += this.estrellas[i][1];
    }
    return total;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getPosicion() {
    return posicion;
  }

  public void setPosicion(int posicion) {
    this.posicion = posicion;
  }

  public int[][] getEstrellas() {
    return estrellas;
  }
}
