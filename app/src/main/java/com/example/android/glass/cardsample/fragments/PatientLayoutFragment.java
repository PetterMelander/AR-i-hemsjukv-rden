/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.glass.cardsample.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.glass.cardsample.R;
import com.example.android.glass.cardsample.data.Patient;

/**
 * Fragment with the main card layout.
 */
public class PatientLayoutFragment extends BaseFragment {

  private static final String TITLE_KEY = "title key";
  private static final String TEXT_KEY = "text_key";
  private static final int BODY_TEXT_SIZE = 40;
  private static final int TITLE_TEXT_SIZE = 50;

  public static PatientLayoutFragment newInstance(Patient patient) {
    final PatientLayoutFragment myFragment = new PatientLayoutFragment();

    final Bundle args = new Bundle();
    args.putString(TITLE_KEY, patient.getName());
    args.putString(TEXT_KEY, patient.getData());
    myFragment.setArguments(args);

    return myFragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.patient_card_layout, container, false);
    if (getArguments() != null) {

      TextView titleTextView = view.findViewById(R.id.patient_header_textView);
      titleTextView.setText(getArguments().getString(TITLE_KEY));
      titleTextView.setTextSize(TITLE_TEXT_SIZE);
      titleTextView.setTypeface(Typeface.create(getString(R.string.thin_font), Typeface.NORMAL));

      TextView bodyTextView = view.findViewById(R.id.patient_info_textView);
      bodyTextView.setText(getArguments().getString(TEXT_KEY));
      bodyTextView.setTextSize(BODY_TEXT_SIZE);
      bodyTextView.setTypeface(Typeface.create(getString(R.string.thin_font), Typeface.NORMAL));

    }
    return view;
  }

}
