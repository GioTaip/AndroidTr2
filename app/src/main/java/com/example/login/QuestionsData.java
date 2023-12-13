package com.example.login;

import java.util.List;

public class QuestionsData {
    private String _id;

    private String name;
    private List<Question> questions;

    public QuestionsData(String _id, String nombre, List<Question> preguntes) {
        this._id = _id;
        this.name = nombre;
        this.questions = preguntes;
    }
    public String get_id() {
        return _id;
    }
    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public static class Question {
        private String enunciado;
        private String respuesta_correcta;
        private List<String> opciones;
        private String dificultad;
        private int puntuacion;

        public Question(String enunciado, String respuesta_correcta, List<String> opciones, String dificultad, int puntuacion) {
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
