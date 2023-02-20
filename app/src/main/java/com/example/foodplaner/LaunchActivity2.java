package com.example.foodplaner;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplaner.databinding.ActivityLaunch2Binding;
import com.google.firebase.auth.FirebaseAuth;

public class LaunchActivity2 extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLaunch2Binding binding;
    private NavController navController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLaunch2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if(FirebaseAuth.getInstance().getCurrentUser() !=null){
            startActivity(new Intent(this, MainActivity.class));

            finish();
        }

        navController = Navigation.findNavController(this,R.id.nav_host_fragment_activity_launch);




    }

}


