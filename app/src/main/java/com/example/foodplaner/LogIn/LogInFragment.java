package com.example.foodplaner.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplaner.MainActivity;
import com.example.foodplaner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LogInFragment extends Fragment {
    EditText email;
    EditText password;
    Button login;
    Button signup;
    Button skip;
    private FirebaseAuth auth;
    ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("TAG","loginfragment created");

        auth=FirebaseAuth.getInstance();
        email=view.findViewById(R.id.email_edit_txt);
        password=view.findViewById(R.id.password_edit_txt);
        login=view.findViewById(R.id.sign_in);
        signup=view.findViewById(R.id.sign_up);
        skip=view.findViewById(R.id.skip);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress= email.getText().toString();
                String pass=password.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                    email.setError("invalid email");
                    email.setFocusable(true);
                }
                else {
                    login(emailAddress,pass);
                }
            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    private void login(String emailAddress, String pass) {
        progressDialog.show();
        auth.signInWithEmailAndPassword(emailAddress,pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user=auth.getCurrentUser();
                            startActivity(new Intent(getActivity(), MainActivity.class));

                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "login failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }
}