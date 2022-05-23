package com.example.android.glass.cardsample.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.glass.cardsample.R;
import com.example.android.glass.cardsample.data.Patient;

public class PatientMenuFragment extends BaseFragment {
    private static final String TITLE_KEY = "title key";
    private static final int TITLE_TEXT_SIZE = 60;

    public static PatientMenuFragment newInstance(Patient patient) {
        final PatientMenuFragment myFragment = new PatientMenuFragment();

        final Bundle args = new Bundle();
        args.putString(TITLE_KEY, patient.get("Name:") + ", " + patient.get("Age:"));
        myFragment.setArguments(args);

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.patient_menu_layout, container, false);
        if (getArguments() != null) {
            TextView titleTextView = view.findViewById(R.id.patient_menu_textView);
            titleTextView.setText(getArguments().getString(TITLE_KEY));
            titleTextView.setTextSize(TITLE_TEXT_SIZE);
            titleTextView.setTypeface(Typeface.create(getString(R.string.thin_font), Typeface.NORMAL));
            titleTextView.setGravity(Gravity.CENTER);
        }
        return view;
    }

}
