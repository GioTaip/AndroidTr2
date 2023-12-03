package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private UserDataAdapter userAdapter;
    List<UserData> users;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        userAdapter = new UserDataAdapter();

        recyclerView.setAdapter(userAdapter);

        mSocket.connect();

        mSocket.emit("getUsers", 0);
        Log.d("call", "call: no entra");
        mSocket.on("users", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.d("call", "call: entra");
                Type listType = new TypeToken<List<UserData>>() {}.getType();
                Gson gson = new Gson();
                final List<UserData> newProducts = gson.fromJson(args[0].toString(), listType);
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newProducts != null) {
                            users = new ArrayList<>();

                            for (UserData p : newProducts) {
                                users.add(p);
                            }
                            for (UserData p: newProducts) {
                                Log.d("userdata", "run: " + p.getName());
                            }
                            userAdapter.setUsers(users);
                            userAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        });



        return root;
    }

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.1.145:3777/");
        } catch (URISyntaxException e) {}
    }



}