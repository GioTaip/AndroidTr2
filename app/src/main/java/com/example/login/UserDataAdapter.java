package com.example.login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserViewHolder> {
    List<UserData> users;

    public void setUsers(List<UserData> users) {
        this.users = users;
        for (UserData u:users) {
            Log.d("nameADAPTER: ", u.getName());
        }

        Log.d("tamanyo users:", "setUsers: "+ users.size());

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserData user = users.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView mail;
        private TextView points;
        private TextView role;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text);
            mail = itemView.findViewById(R.id.mail_text);
            points = itemView.findViewById(R.id.points_text);
            role = itemView.findViewById(R.id.role_text);
        }

        void bind(UserData user){
            name.setText(user.getName());
            mail.setText(user.getMail());
            points.setText(String.valueOf(user.getPoints()));
            role.setText(user.getRole());
        }
    }
}
