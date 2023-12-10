package com.example.login;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AddUserData addUserData;
    private EditText name;
    private EditText password;
    private EditText mail;
    private EditText points;
    private Button send;
    private String URL="http://192.168.1.145:3777/";
    private ApiService apiService;


    public AddUsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddUsers.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUsersFragment newInstance(String param1, String param2) {
        AddUsersFragment fragment = new AddUsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_add_users, container, false);

        name = root.findViewById(R.id.name_input);
        password = root.findViewById(R.id.password_input);
        mail = root.findViewById(R.id.mail_inputT);
        points = root.findViewById(R.id.punts_input);

        Spinner spinnerRolesType = root.findViewById(R.id.spinnerRoles);
        String [] roles = {"teacher/admin","student"};
        // Crear un adaptador para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, roles);

        // Especificar el diseño del menú desplegable
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        spinnerRolesType.setAdapter(adapter);

        send = root.findViewById(R.id.btn_send_user);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = name.getText().toString();
                String correo = mail.getText().toString();
                String contrasenya = password.getText().toString();
                String selectedRole = spinnerRolesType.getSelectedItem().toString();
                int punts = Integer.parseInt(points.getText().toString());
                Log.d("correo: ", correo);
                Log.d("contra: ", contrasenya);


                // Create the object to insert
                AddUserData insertUser = new AddUserData(nom, contrasenya, correo,selectedRole, punts);

                // Initialize Retrofit and call the InsertUser API
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiService = retrofit.create(ApiService.class);
                Call<AddUserData> call = apiService.InsertUser(insertUser);
                call.enqueue(new Callback<AddUserData>() {
                    @Override
                    public void onResponse(Call<AddUserData> call, Response<AddUserData> response) {
                        if(response.isSuccessful()){
                            Log.d("Response", "Si llego");
                        }
                        else{
                            Log.d("Response", "No llego");
                        }
                    }

                    @Override
                    public void onFailure(Call<AddUserData> call, Throwable t) {
                        Log.e("ERROR",t.getMessage());
                    }
                });
            }
        });

        return root;
    }
}