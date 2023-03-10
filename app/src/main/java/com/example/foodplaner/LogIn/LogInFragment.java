package com.example.foodplaner.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodplaner.CheckInternet;
import com.example.foodplaner.MainActivity;
import com.example.foodplaner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LogInFragment extends Fragment {
    EditText email;
    EditText password;
    Button login;
    Button signup;
    Button skip;
    ImageView google;
    GoogleSignInClient googleSignInClient;


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
        google = view.findViewById(R.id.google);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getActivity(), gso);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavDirections action = LogInFragmentDirections.actionLogInFragmentToRegisterFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress= email.getText().toString();
                String pass=password.getText().toString().trim();
                Log.i("TAG", "onClick: "+pass);
                if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                    email.setError("invalid email");
                    email.setFocusable(true);
                }else if(pass==null){
                    Toast.makeText(getActivity(), "pass is empty", Toast.LENGTH_SHORT).show();

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

                getActivity().finish();
            }
        });
        google.setOnClickListener(v -> {

            Intent intent = googleSignInClient.getSignInIntent();
            // Start activity for result
            startActivityForResult(intent, 100);

        });

    }

    private void login(  String emailAddress, String pass) {
        progressDialog.show();
        auth.signInWithEmailAndPassword(emailAddress,pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user=auth.getCurrentUser();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();

                        }
                        else {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "login failed", Toast.LENGTH_LONG).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Log.i("TAG", "onFailure: "+e.getMessage());
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                String s = "Google sign in successful";
                // Display Toast
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        auth.signInWithCredential(authCredential).addOnCompleteListener(getActivity(), task -> {
                            if(task.isSuccessful()){

                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();

                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "login failed", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}