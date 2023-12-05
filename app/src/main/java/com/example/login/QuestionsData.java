package com.example.login;

import java.util.List;

public class QuestionsData {
    private String nombre;
    private List<Pregunta> preguntes;

    public QuestionsData(String nombre, List<Pregunta> preguntes) {
        this.nombre = nombre;
        this.preguntes = preguntes;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pregunta> getPreguntes() {
        return preguntes;
    }

    public static class Pregunta {
        private String enunciado;
        private String respuesta_correcta;
        private List<String> opciones;
        private String dificultad;
        private int puntuacion;

        public Pregunta(String enunciado, String respuesta_correcta, List<String> opciones, String dificultad, int puntuacion) {
            this.enunciado = enunciado;
            this.respuesta_correcta = respuesta_correcta;
            this.opciones = opciones;
            this.dificultad = dificultad;
            this.puntuacion = puntuacion;
        }

        public String getEnunciado() {
            return enunciado;
        }

        public String getRespuesta_correcta() {
            return respuesta_correcta;
        }

        public List<String> getOpciones() {
            return opciones;
        }

        public String getDificultad() {
            return dificultad;
        }

        public int getPuntuacion() {
            return puntuacion;
        }
    }

}
