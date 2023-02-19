package com.example.foodplaner.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplaner.LaunchActivity2;
import com.example.foodplaner.Model.Meal;
import com.example.foodplaner.Model.User;
import com.example.foodplaner.database.MealLocalSource;
import com.example.foodplaner.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {
    FirebaseFirestore db ;
    private static final String TAG = "ProfileFragment";
    private FragmentProfileBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        if(FirebaseAuth.getInstance().getCurrentUser() !=null){
            binding.profileLogin.setVisibility(View.GONE);
            binding.logout.setVisibility(View.VISIBLE);
            binding.imageView5.setVisibility(View.VISIBLE);
            binding.sync.setVisibility(View.VISIBLE);
            db = FirebaseFirestore.getInstance();
            binding.logout.setOnClickListener(v -> {
                logOut();
            });
            binding.sync.setOnClickListener(v -> {
                sync();
            });
        }else {
            binding.logout.setVisibility(View.GONE);
            binding.imageView5.setVisibility(View.GONE);
            binding.sync.setVisibility(View.GONE);
            binding.profileLogin.setVisibility(View.VISIBLE);
            binding.profileLogin.setOnClickListener(v -> {
                Intent i = new Intent(getContext(), LaunchActivity2.class);
                startActivity(i);
                getActivity().finish();
            });

        }
        return binding.getRoot();
    }

    private void sync() {
        ArrayList<Meal> meals =new ArrayList<>();
       MealLocalSource.getInstance(getContext()).getMeals(FirebaseAuth.getInstance().getUid()).observe(getActivity(),users -> {
           for (User user:users){
               saveToFireBase(user);
           }
           getDataFromFireBase();
       });
        



    }

    private void getDataFromFireBase() {
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("meals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                SaveDataToDataBase(document.toObject(Meal.class));

                            }
                            Toast.makeText(getContext(), "sync complete", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void SaveDataToDataBase(Meal meal) {
        Log.i(TAG, "SaveDataToDataBase: "+meal.strMeal);
        MealLocalSource.getInstance(getContext()).insertMeal(new User(FirebaseAuth.getInstance().getUid(),meal));
    }
    private void saveToFireBase(User user) {
        db.collection("users").document(user.getUserName())
                .collection("meals").document(user.getMealId()).set(user.getMeal());
    }

    private void logOut() {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(getContext(), LaunchActivity2.class);
        startActivity(i);
        getActivity().finish();
    }
}