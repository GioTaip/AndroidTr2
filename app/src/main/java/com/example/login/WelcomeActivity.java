package com.example.login;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class WelcomeActivity extends AppCompatActivity {
    HomeFragment homeFragment = new HomeFragment();
    DashboardFragment dashboardFragment = new DashboardFragment();
    NotificationsFragment notificationsFragment = new NotificationsFragment();
    WelcomeFragment welcomeFragment = new WelcomeFragment();
    AddUsersFragment addUsersFragment = new AddUsersFragment();

    FloatingActionButton btnAddProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // Inflar el diseño de fragment_dashboard
        View dashboardLayout = getLayoutInflater().inflate(R.layout.fragment_dashboard, null);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnItemSelectedListener(navigationSelectedListener);

        String user = getIntent().getStringExtra("user");
        String userMail = getIntent().getStringExtra("userMail");
        Bundle bundle = new Bundle();
        bundle.putString("userName", user.toString());
        welcomeFragment.setArguments(bundle);
        dashboardFragment.setArguments(bundle);

        loadFragment(welcomeFragment);

        btnAddProfile = dashboardLayout.findViewById(R.id.btnAddProfile);

        btnAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUsersFragment(v);
            }
        });

    }
    public void AddUsersFragment(View view) {
        // Lógica para cargar el fragmento AddUsersFragment
        loadFragment(addUsersFragment);
    }

    private final NavigationBarView.OnItemSelectedListener navigationSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                loadFragment(homeFragment);
                return true;
            } else if (itemId == R.id.navigation_dashboard) {
                loadFragment(dashboardFragment);
                return true;
            } else if (itemId == R.id.navigation_notifications) {
                loadFragment(notificationsFragment);
                return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_welcome, fragment);
        transaction.commit();
    }

}