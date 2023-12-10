package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginboton, seepassword;
    private String URL="http://192.168.1.74:3777/";
    private ApiService apiService;

    private boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BUSCAR IDS
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginboton = (Button) findViewById(R.id.loginboton);
        seepassword = (Button) findViewById(R.id.seepassword);


        //LOGIN USUARIO
        loginboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiService = retrofit.create(ApiService.class);
                UserRequestLogin u = new UserRequestLogin(user, pass);
                Call<Answer> call = apiService.ResquestLogin(u);

                call.enqueue(new Callback<Answer>() {
                    @Override
                    public void onResponse(Call<Answer> call, Response<Answer> response) {
                        if(response.isSuccessful()){
                            Log.d("CONNECTION", String.valueOf(R.string.CONEXIONEXITO));

                            Answer answer = response.body();
                            Log.d("name::", "onResponse: " + answer.getName());
                            if(answer.isAuthorization()){
                                Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
                                intent.putExtra("user", answer.getName());
                                intent.putExtra("userMail", user);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, R.string.LoginUser, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Log.e("CONNECTION", String.valueOf(R.string.CONEXIONFALLIDA));
                        }
                    }

                    @Override
                    public void onFailure(Call<Answer> call, Throwable t) {
                        Log.e("ERROR",t.getMessage());
                    }
                });
            }
        });

        //PARA VER CONTRASEÑA
        seepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visible){
                    password.setInputType(129);
                    visible = false;
                    seepassword.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.baseline_key_24,0);

                }else{
                    password.setInputType(128);
                    visible = true;
                    seepassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_key_off_24, 0);
                }
            }
        });



    }
}