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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.android.glass.cardsample.R;

import java.util.List;

/**
 * Fragment with the main card layout.
 */
public class ChecklistFragment extends BaseFragment {

  private static final String TEXT_KEY = "text_key";
  private static final int BODY_TEXT_SIZE = 50;

  private CheckBox checkBox;
  private boolean checked = false;


  public static ChecklistFragment newInstance(String string) {
    final ChecklistFragment myFragment = new ChecklistFragment();

    final Bundle args = new Bundle();
    args.putString(TEXT_KEY, string);
    myFragment.setArguments(args);

    return myFragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.left_column_layout, container, false);
    if (getArguments() != null) {
      checkBox = view.findViewById(R.id.checkBox);

      TextView textView = new TextView(getActivity());
      textView.setText(getArguments().getString(TEXT_KEY));
      textView.setTextSize(BODY_TEXT_SIZE);
      textView.setTypeface(Typeface.create(getString(R.string.thin_font), Typeface.NORMAL));
      textView.setGravity(Gravity.CENTER_VERTICAL);

      final FrameLayout rightLayout = view.findViewById(R.id.right_column);
      rightLayout.addView(textView);
    }
    return view;
  }

  @Override
  public void onResume() {
    super.onResume();
    checkBox.setChecked(checked);
  }

  @Override
  public void onSingleTapUp() {
    checked = !checked;
    checkBox.setChecked(checked);
  }

}
