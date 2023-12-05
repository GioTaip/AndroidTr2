package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsDataAdapter extends RecyclerView.Adapter<QuestionsDataAdapter.QuestionsViewHolder> {

    private List<QuestionsData.Pregunta> preguntas;

    // Constructor para inicializar el conjunto de datos
    public QuestionsDataAdapter(List<QuestionsData.Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de elemento de lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        // Establecer el contenido de la vista en función de la posición
        QuestionsData.Pregunta pregunta = preguntas.get(position);
    }

    @Override
    public int getItemCount() {
        // Devolver la cantidad total de elementos en el conjunto de datos
        return preguntas.size();
    }

    // Método para actualizar el conjunto de datos si es necesario
    public void setPreguntas(List<QuestionsData.Pregunta> preguntas) {
        this.preguntas = preguntas;
        notifyDataSetChanged();
    }

    // Clase interna QuestionsViewHolder (que ya definiste en tu adaptador)
    public static class QuestionsViewHolder extends RecyclerView.ViewHolder {

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}