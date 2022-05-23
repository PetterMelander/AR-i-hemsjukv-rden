package com.example.android.glass.cardsample.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.glass.cardsample.R;

public class AddChecklistFragment extends BaseFragment{



    public static AddChecklistFragment newInstance() {
        final AddChecklistFragment myFragment = new AddChecklistFragment();

        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_checklist_layout, container, false);
        return view;
    }

    @Override
    public void onSingleTapUp() {
        final Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "sv-SE");
        startActivityForResult(intent, REQUEST_CODE);
    }

}
