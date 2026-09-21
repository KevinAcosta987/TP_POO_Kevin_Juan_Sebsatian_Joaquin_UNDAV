package com.tp_poo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testCategoriaComodin() {
        com.tp_poo.modelo.Categoria gris = com.tp_poo.modelo.Categoria.GRIS;
        assertTrue(gris.esGris());
        assertFalse(com.tp_poo.modelo.Categoria.CELESTE.esGris());

        com.tp_poo.modelo.Categoria[] preguntables = com.tp_poo.modelo.Categoria.getCategoriasPreguntables();
        assertEquals(com.tp_poo.modelo.Categoria.values().length - 1, preguntables.length);
        for (int i = 0; i < preguntables.length; i++) {
            assertFalse(preguntables[i].esGris());
        }
    }

    public void testTableroPreguntasGris() {
        com.tp_poo.modelo.Tablero tablero = new com.tp_poo.modelo.Tablero();
        com.tp_poo.modelo.Pregunta p = tablero.obtenerPregunta(com.tp_poo.modelo.Categoria.GRIS);
        assertNotNull(p);
        assertFalse(p.getCategoria().esGris());
    }

    public void testPartidaRespuestaCorrectaConCategoriaElegida() {
        com.tp_poo.modelo.Jugador jugador = new com.tp_poo.modelo.Jugador("Kevin");
        com.tp_poo.modelo.Partida partida = new com.tp_poo.modelo.Partida();
        partida.agregarJugador(jugador);
        partida.iniciarPartida();

        jugador.setPosicion(0);
        assertTrue(partida.getTablero().obtenerCasillero(0).esEspecial());

        partida.respuestaCorrecta(com.tp_poo.modelo.Categoria.CELESTE);
        assertTrue(jugador.tieneEstrella(com.tp_poo.modelo.Categoria.CELESTE));
    }
}
