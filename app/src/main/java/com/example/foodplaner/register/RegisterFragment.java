package com.example.foodplaner.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplaner.MainActivity;
import com.example.foodplaner.R;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {

    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    private void registerUser(String password,String userName){
        auth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener(getActivity(),task -> {
            if(task.isSuccessful()){
                Toast.makeText(this.getContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }else{
                Toast.makeText(this.getContext(), "Register failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}