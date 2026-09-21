package com.tp_poo.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Tablero {

  private List<Casillero> casilleros;
  private int cantidadCasillero;
  private Map<Categoria, List<Pregunta>> bancoPreguntas;
  private Random random = new Random();

  public Tablero() {
    this(36); // Por defecto un tablero circular de 36 casilleros
  }

  public Tablero(int cantidadCasillero) {
    this.cantidadCasillero = cantidadCasillero;
    this.casilleros = new ArrayList<>(cantidadCasillero);
    this.bancoPreguntas = new EnumMap<>(Categoria.class);

    inicializarCasilleros();
    inicializarBancoPreguntas();
  }

  /*
   * Inicializa los casilleros del tablero rotando las 6 categorías
   * y marcando casilleros especiales periódicamente (cada 6 casilleros).
   */
  private void inicializarCasilleros() {
    // Obtenemos solo las 6 categorías que se juegan, ignorando comodines (grises)
    Categoria[] categorias = Categoria.getCategoriasPreguntables();

    for (int i = 0; i < cantidadCasillero; i++) {
      Categoria cat = categorias[i % categorias.length];

      // cambie este por que 1 no era muy claro, 2 algunas categorias no tendrian
      // estrellas, solo si el tablero es de 36 por que:
      // Al usar % 7 se desfasa el múltiplo y cae en distintas categorías
      // Esto pone estrellas en i = 0, 7, 14, 21, 28, 35.
      boolean esEspecial = (i % 7 == 0);

      casilleros.add(new Casillero(cat, esEspecial));
    }
  }
  // Carga un conjunto inicial de preguntas por categoría para pruebas y juego.

  private void inicializarBancoPreguntas() {
    for (Categoria cat : Categoria.getCategoriasPreguntables()) {
      bancoPreguntas.put(cat, new ArrayList<>());
    }

    agregarPregunta(new Pregunta(Categoria.CELESTE, "¿Cuál es el símbolo químico del agua?", "H2O"));
    agregarPregunta(new Pregunta(Categoria.CELESTE, "¿Cuál es el planeta más cercano al Sol?", "Mercurio"));
    agregarPregunta(new Pregunta(Categoria.MARRON, "¿En qué año llegó Colón a América?", "1492"));
    agregarPregunta(
        new Pregunta(Categoria.MARRON, "¿Quién fue el primer presidente argentino?", "Bernardino Rivadavia"));
    agregarPregunta(new Pregunta(Categoria.AMARILLO, "¿Cuál es la capital de Francia?", "París"));
    agregarPregunta(new Pregunta(Categoria.AMARILLO, "¿Qué océano baña las costas de Argentina?", "Atlántico"));
    agregarPregunta(new Pregunta(Categoria.VERDE, "¿Quién pintó la Mona Lisa?", "Leonardo da Vinci"));
    agregarPregunta(
        new Pregunta(Categoria.VERDE, "¿Quién escribió 'Don Quijote de la Mancha'?", "Miguel de Cervantes"));
    agregarPregunta(new Pregunta(Categoria.ROSA, "¿Qué superhéroe es conocido como el Caballero de la Noche?",
        "Batman"));
    agregarPregunta(
        new Pregunta(Categoria.ROSA, "¿Quién compuso la banda sonora de Star Wars?", "John Williams"));
    agregarPregunta(
        new Pregunta(Categoria.VERDE, "¿Cuántos jugadores integran un equipo de fútbol en cancha?", "11"));
    agregarPregunta(new Pregunta(Categoria.VERDE, "¿Cada cuántos años se celebran los Juegos Olímpicos?", "4"));
  }

  public void agregarPregunta(Pregunta pregunta) {
    if (pregunta == null || pregunta.getCategoria() == null) {
      return;
    }
    List<Pregunta> lista = bancoPreguntas.get(pregunta.getCategoria());
    if (lista != null) {
      lista.add(pregunta);
    }
  }

  /*
   * Resuelve qué casillero corresponde a una posición dada.
   * Como el recorrido es circular, utiliza el operador módulo.
   */
  public Casillero obtenerCasillero(int posicion) {
    if (casilleros.isEmpty()) {
      return null;
    }
    int indice = Math.abs(posicion) % casilleros.size();
    return casilleros.get(indice);
  }

  // Obtiene el primer casillero o casillero de inicio.

  public Casillero obtenerCasillero() {
    return obtenerCasillero(0);
  }

  // Intermediario para conseguir una pregunta asociada a la categoría indicada.

  public Pregunta obtenerPregunta(Categoria categoria) {
    if (categoria == null || categoria.esGris()) {
      // Si la categoría es comodín o nula, se delega en una categoría preguntable al
      // azar
      return obtenerPregunta();
    }
    List<Pregunta> preguntas = bancoPreguntas.get(categoria);
    if (preguntas == null || preguntas.isEmpty()) {
      return new Pregunta(categoria, "Pregunta genérica de " + categoria.name(), "Respuesta");
    }
    return preguntas.get(random.nextInt(preguntas.size()));
  }

  // Obtiene una pregunta aleatoria de cualquier categoría preguntable.

  public Pregunta obtenerPregunta() {
    Categoria[] cats = Categoria.getCategoriasPreguntables();
    Categoria catAleatoria = cats[random.nextInt(cats.length)];
    return obtenerPregunta(catAleatoria);
  }

  public int getCantidadCasillero() {
    return cantidadCasillero;
  }

  public List<Casillero> getCasilleros() {
    return Collections.unmodifiableList(casilleros);
  }
}
