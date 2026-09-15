package com.tp_poo.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partida {

  private Jugador ganador;
  private int turnoActual;
  private List<Jugador> jugadores;
  private Tablero tablero;
  private boolean partidaFinalizada;

  public Partida() {
    this(new ArrayList<>(), new Tablero());
  }

  /*
   * Operador ternario: ?, es un if else comprimido en una linea
   * esto se lee como: si jugadores es distinto de null, entonces crea un
   * ArrayList con los jugadores, si no, crea un ArrayList vacio
   */
  public Partida(List<Jugador> jugadores, Tablero tablero) {
    this.jugadores = jugadores != null ? new ArrayList<>(jugadores) : new ArrayList<>();
    this.tablero = tablero != null ? tablero : new Tablero();
    this.turnoActual = 0;
    this.ganador = null;
    this.partidaFinalizada = false;
  }

  public void agregarJugador(Jugador jugador) {
    this.jugadores.add(jugador);
  }

  // Inicia la partida reiniciando el turno y el estado general.
  public void iniciarPartida() {
    if (this.jugadores.isEmpty()) {
      throw new IllegalStateException("No se puede iniciar una partida sin jugadores.");
    }
    this.turnoActual = 0;
    this.ganador = null;
    this.partidaFinalizada = false;
  }

  // Pasa el turno al siguiente jugador.
  public void siguienteTurno() {
    if (partidaFinalizada || jugadores.isEmpty()) {
      return;
    }
    this.turnoActual = (this.turnoActual + 1) % jugadores.size();
  }

  // Obtiene el jugador cuyo turno está activo.
  public Jugador getJugadorActual() {
    if (jugadores.isEmpty()) {
      return null;
    }
    return jugadores.get(turnoActual % jugadores.size());
  }

  /*
   * Procesa una respuesta correcta del jugador actual.
   * Si el casillero en el que se encuentra es especial, le asigna la estrella
   * correspondiente.
   * Luego verifica si el jugador reunió las estrellas necesarias para ganar.
   */
  public void respuestaCorrecta() {
    if (partidaFinalizada) {
      return;
    }

    Jugador actual = getJugadorActual();
    if (actual == null) {
      return;
    }

    Casillero casilleroActual = tablero.obtenerCasillero(actual.getPosicion());
    if (casilleroActual != null && casilleroActual.esEspecial()) {
      actual.sumarEstrella(casilleroActual.getCategoria());

      // En Carrera de Mente se gana al completar las estrellas de todos los colores
      // (6)
      if (actual.getCantidadEstrellasTotal() >= Categoria.values().length) {
        this.ganador = actual;
        finalizarPartida();
      }
    }
  }

  // Finaliza la partida y bloquea nuevos turnos.
  public void finalizarPartida() {
    this.partidaFinalizada = true;
  }

  public Jugador getGanador() {
    return ganador;
  }

  public void setGanador(Jugador ganador) {
    this.ganador = ganador;
  }

  public int getTurnoActual() {
    return turnoActual;
  }

  public List<Jugador> getJugadores() {
    return Collections.unmodifiableList(jugadores);
  }

  public Tablero getTablero() {
    return tablero;
  }

  public boolean isPartidaFinalizada() {
    return partidaFinalizada;
  }
}
