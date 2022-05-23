package com.example.android.glass.cardsample;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.android.glass.cardsample.data.Patient;
import com.example.android.glass.cardsample.fragments.BaseFragment;
import com.example.android.glass.cardsample.fragments.PatientMenuFragment;
import com.example.glass.ui.GlassGestureDetector;
import com.google.android.material.tabs.TabLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<BaseFragment> fragments = new ArrayList<>();
    public List<Patient> patients = new ArrayList<>();
    private ViewPager viewPager;
    final ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(
            getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(screenSlidePagerAdapter);

        Patient patient1 = new Patient(getResources().getStringArray(R.array.data_patient_1_array), getResources().getStringArray(R.array.conditions_patient_1_array), getResources().getStringArray(R.array.checklist_patient_1_array), getResources().getStringArray(R.array.test_patient_1_array));
        Patient patient2 = new Patient(getResources().getStringArray(R.array.data_patient_2_array), getResources().getStringArray(R.array.conditions_patient_2_array), getResources().getStringArray(R.array.checklist_patient_2_array), getResources().getStringArray(R.array.test_patient_1_array));
        Patient patient3 = new Patient(getResources().getStringArray(R.array.data_patient_3_array), getResources().getStringArray(R.array.conditions_patient_3_array), getResources().getStringArray(R.array.checklist_patient_3_array), getResources().getStringArray(R.array.test_patient_1_array));

        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);

        fragments.add(PatientMenuFragment
                .newInstance(patient1));
        fragments.add(PatientMenuFragment
                .newInstance(patient2));
        fragments.add(PatientMenuFragment
                .newInstance(patient3));

        screenSlidePagerAdapter.notifyDataSetChanged();

        final TabLayout tabLayout = findViewById(R.id.page_indicator);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    public boolean onGesture(GlassGestureDetector.Gesture gesture) {
        switch (gesture) {
            case TAP:
                Intent intent = new Intent(this, PatientCheckupActivity.class);
                intent.putExtra("test", (Serializable) patients.get(viewPager.getCurrentItem()));
                startActivity(intent);
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

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
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