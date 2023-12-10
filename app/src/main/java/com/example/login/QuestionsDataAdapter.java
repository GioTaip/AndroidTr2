package com.example.login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsDataAdapter extends RecyclerView.Adapter<QuestionsDataAdapter.QuestionsViewHolder> {

    List<QuestionsData.Question> questions;

    // Constructor para inicializar el conjunto de datos
    public void setQuestions(List<QuestionsData.Question> questions) {
        this.questions = questions;
        Log.d("nameADAPTER: ", "no entro");
        for (QuestionsData.Question q:questions) {
            Log.d("nameADAPTER: ", q.getEnunciado());
        }
        notifyDataSetChanged();
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
        QuestionsData.Question question = questions.get(position);
        holder.bind(question);
    }

    @Override
    public int getItemCount() {
        // Devolver la cantidad total de elementos en el conjunto de datos
        return questions != null ? questions.size() : 0;
    }

    // Clase interna QuestionsViewHolder (que ya definiste en tu adaptador)
    static class QuestionsViewHolder extends RecyclerView.ViewHolder {

        private TextView theme_question;
        private TextView getStatement;
        private TextView getAnswerCorrect;
        private TextView getAnswer1;
        private TextView getAnswer2;
        private TextView getAnswer3;
        private TextView getAnswer4;
        private TextView getDifficulty;
        private TextView getPunts;

            QuestionsViewHolder(@NonNull View itemView) {
                super(itemView);
                theme_question = itemView.findViewById(R.id.theme_question);
                getStatement = itemView.findViewById(R.id.getStatement);
                getAnswerCorrect = itemView.findViewById(R.id.getAnswerCorrect);
                getAnswer1 = itemView.findViewById(R.id.getAnswer1);
                getAnswer2 = itemView.findViewById(R.id.getAnswer2);
                getAnswer3 = itemView.findViewById(R.id.getAnswer3);
                getAnswer4 = itemView.findViewById(R.id.getAnswer4);
                getDifficulty = itemView.findViewById(R.id.getDifficulty);
                getPunts = itemView.findViewById(R.id.getPunts);
            }

            // Método para asignar datos a la vista dentro de ViewHolder
            void bind(QuestionsData.Question question) {

                getStatement.setText(question.getEnunciado());
                getAnswerCorrect.setText(question.getRespuesta_correcta());
                getAnswer1.setText(question.getOpciones().get(0));
                getAnswer2.setText(question.getOpciones().get(1));
                getAnswer3.setText(question.getOpciones().get(2));
                getAnswer4.setText(question.getOpciones().get(3));
                getDifficulty.setText(question.getDificultad());
                getPunts.setText(question.getPuntuacion());

                Log.d("Datos", getStatement.toString());
            }
        }
    }