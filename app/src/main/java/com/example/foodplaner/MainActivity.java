package com.example.foodplaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.foodplaner.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private MenuItem profileMenuItem;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        builder = new AlertDialog.Builder(this);
        builder.setMessage("please login first");

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setSupportActionBar(binding.toolbar);


        navController = Navigation.findNavController(this,R.id.nav_host_fragment_activity_main);
        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment, R.id.listFragment,R.id.allMealsFragment,
                        R.id.favouriteFragment,R.id.myPlanFragment ).build();

        NavigationUI.setupWithNavController(binding.navView, navController);
        binding.navView.setOnItemSelectedListener(item -> bottomNavigationItemSelected(item.getItemId()));
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    private boolean bottomNavigationItemSelected(int itemId) {
        AlertDialog dialog;
        switch (itemId) {
            case R.id.homeFragment:
                // Perform your condition check here
                    navController.navigate(R.id.homeFragment);
               return true;
            case R.id.listFragment:

                navController.navigate(R.id.listFragment);
                return true;
            case R.id.favouriteFragment:
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    dialog = builder.create();
                    dialog.show();
                    return false;
                }else {
                    navController.navigate(R.id.favouriteFragment);
                    return true;
                }
            case R.id.myPlanFragment:
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    dialog = builder.create();
                    dialog.show();
                    return false;
                }else {
                    navController.navigate(R.id.myPlanFragment);
                    return true;
                } case R.id.allMealsFragment:

                navController.navigate(R.id.allMealsFragment);
                return true;
            default:
                return false;
        }    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Initialize the Toolbar menu
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle Toolbar menu item clicks
        if (item.getItemId() == R.id.profile_action) {
            binding.navView.setVisibility(View.GONE);

            profileMenuItem =item;
            navController.navigate(R.id.profileFragment);
            profileMenuItem.setVisible(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle Toolbar navigation up button
        binding.navView.setVisibility(View.VISIBLE);
        profileMenuItem.setVisible(true);
        return NavigationUI.navigateUp(navController, appBarConfiguration)|| super.onSupportNavigateUp();
    }


}