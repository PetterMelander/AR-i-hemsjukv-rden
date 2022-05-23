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

package com.example.android.glass.cardsample;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.android.glass.cardsample.data.Patient;
import com.example.android.glass.cardsample.fragments.AddChecklistFragment;
import com.example.android.glass.cardsample.fragments.BaseFragment;
import com.example.android.glass.cardsample.fragments.ChecklistFragment;
import com.example.android.glass.cardsample.fragments.PatientLayoutFragment;
import com.example.glass.ui.GlassGestureDetector.Gesture;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity of the application. It provides viewPager to move between fragments.
 */
public class PatientCheckupActivity extends BaseActivity {

    private static final int REQUEST_CODE = 999;

    private Patient patient;
    private List<BaseFragment> fragments = new ArrayList<>();
    private ViewPager viewPager;
    final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
            getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("test");

        setContentView(R.layout.view_pager_layout);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(screenSlidePagerAdapter);

        fragments.add(PatientLayoutFragment
                .newInstance(patient));

        for (int i = 0; i < patient.getChecklist().length; i++) {
            fragments.add(ChecklistFragment.newInstance(patient.getChecklist()[i]));
        }
        fragments.add(AddChecklistFragment.newInstance());

        screenSlidePagerAdapter.notifyDataSetChanged();

        final TabLayout tabLayout = findViewById(R.id.page_indicator);
        tabLayout.setupWithViewPager(viewPager, true);

        //getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).commit();
    }

    @Override
    public boolean onGesture(Gesture gesture) {
        switch (gesture) {
            case TAP:
                fragments.get(viewPager.getCurrentItem()).onSingleTapUp();
                screenSlidePagerAdapter.notifyDataSetChanged();
                return true;
            case TWO_FINGER_SWIPE_DOWN:
                Fragment fragment = fragments.get(viewPager.getCurrentItem());
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                if(fragment != null)
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                fragments.remove(fragment);
                screenSlidePagerAdapter.notifyDataSetChanged();
                return true;
            case TWO_FINGER_SWIPE_BACKWARD:
                viewPager.setCurrentItem(0);
                return true;
            case TWO_FINGER_SWIPE_FORWARD:
                viewPager.setCurrentItem(fragments.size());
                return true;
            default:
                return super.onGesture(gesture);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            final List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && results.size() > 0 && !results.get(0).isEmpty()) {
                String speechResult = results.get(0);

                //getFragmentManager().beginTransaction().add(fragments.size()-2, ChecklistFragment.newInstance(speechResult)).commit();

                /*ChecklistFragment fragment = ChecklistFragment.newInstance(speechResult);
                BaseFragment temp = fragments.get(fragments.size()-1);
                fragments.add(fragments.size()-2, fragment);
                //fragments.add(0, ChecklistFragment.newInstance(speechResult));
                getSupportFragmentManager().beginTransaction().replace()
                getSupportFragmentManager().beginTransaction().add(fragment, null);
                screenSlidePagerAdapter.notifyDataSetChanged();*/

                fragments.remove(fragments.size()-1);
                fragments.add(ChecklistFragment
                        .newInstance(speechResult));
                fragments.add(AddChecklistFragment.newInstance());
                screenSlidePagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(fragments.size()-2);
            }
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
