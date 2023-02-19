package com.example.foodplaner.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplaner.LogIn.LogInFragmentDirections;
import com.example.foodplaner.MainActivity;
import com.example.foodplaner.R;
import com.example.foodplaner.databinding.FragmentRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {

    private FirebaseAuth auth;
    FragmentRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        auth=FirebaseAuth.getInstance();
        binding.login.setOnClickListener(v -> {
            registerUser(binding.username.getText().toString(),binding.password.getText().toString());
        });
        binding.goToSignIn.setOnClickListener(v -> {

            NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLogInFragment();
            Navigation.findNavController(v).navigate(action);
        });
        return binding.getRoot();
    }
    private void registerUser(String userName ,String password){
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(getContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Log.i("TAG", "registerUser: "+userName+password);

        auth.createUserWithEmailAndPassword(userName,password.trim()).addOnCompleteListener(getActivity(),task -> {
            if(task.isSuccessful()){
                Toast.makeText(this.getContext(), "Register Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }else{
                Toast.makeText(this.getContext(), "Register failed", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_LONG).show();

        });
    }
}