package com.tp_poo.modelo;

public class Casillero {

  private boolean casilleroEspecial;
  private Categoria categoria;

  // constructor
  public Casillero(Categoria categoria, boolean casilleroEspecial) {
    this.categoria = categoria;
    this.casilleroEspecial = casilleroEspecial;
  }

  public Casillero(Categoria categoria) {
    this(categoria, false);
  }

  // Informa si el casillero es especial (otorga estrella).
  public boolean esEspecial() {
    return this.casilleroEspecial;
  }

  // getter
  public Categoria getCategoria() {
    return this.categoria;
  }

  // setter
  public void setCasilleroEspecial(boolean casilleroEspecial) {
    this.casilleroEspecial = casilleroEspecial;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
}
