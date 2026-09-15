package com.tp_poo.modelo;

public class Pregunta {

  private Categoria categoria;
  private String enunciadoPregunta;
  private String respuestaCorrecta;

  public Pregunta(Categoria categoria, String enunciadoPregunta, String respuestaCorrecta) {
    this.categoria = categoria;
    this.enunciadoPregunta = enunciadoPregunta;
    this.respuestaCorrecta = respuestaCorrecta;
  }

  /*
   * Valida la respuesta enviada por el jugador contra la respuesta correcta.
   * No distingue mayúsculas/minúsculas y recorta espacios en blanco.
   */
  public boolean validaRespuesta(String respuesta) {
    if (respuesta == null || this.respuestaCorrecta == null) {
      return false;
    }
    return this.respuestaCorrecta.trim().equalsIgnoreCase(respuesta.trim());
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getEnunciadoPregunta() {
    return enunciadoPregunta;
  }

  public String getEnunciado() {
    return enunciadoPregunta;
  }

  public String getRespuestaCorrecta() {
    return respuestaCorrecta;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public void setEnunciadoPregunta(String enunciadoPregunta) {
    this.enunciadoPregunta = enunciadoPregunta;
  }

  public void setRespuestaCorrecta(String respuestaCorrecta) {
    this.respuestaCorrecta = respuestaCorrecta;
  }
}
