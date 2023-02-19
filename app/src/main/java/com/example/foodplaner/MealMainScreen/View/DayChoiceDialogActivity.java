package com.example.foodplaner.MealMainScreen.View;

import android.app.Dialog;
import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.foodplaner.R;

public class DayChoiceDialogActivity extends DialogFragment {
    int position =0;

    public interface DayChoiceListenerActivity{
        void onPositiveButtonClicked(String [] list, int position);
        void onNegativeButtonClicked();
    }
    DayChoiceDialogActivity.DayChoiceListenerActivity listenerActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

            listenerActivity= (DayChoiceDialogActivity.DayChoiceListenerActivity) context;
        } catch (Exception e) {
            throw new ClassCastException(getActivity().toString()+"DayChoiceListener must be implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        String[] list=getActivity().getResources().getStringArray(R.array.choice_items);
        builder.setTitle("select day")
                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        position=i;

                    }
                })
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listenerActivity.onPositiveButtonClicked(list,position);


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listenerActivity.onNegativeButtonClicked();

                    }
                });
        return builder.create();

    }
}

