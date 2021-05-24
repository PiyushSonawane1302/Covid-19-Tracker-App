package com.example.covid19tracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;


public class SymptomsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RecyclerView symptomRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_symptoms, container, false);

        String[] symptom = new String[Symptoms.symptoms.length];
        for (int i = 0; i < symptom.length; i++) {
            symptom[i] = Symptoms.symptoms[i].getSymptom();
        }

        int[] symptomImg = new int[Symptoms.symptoms.length];
        for (int i = 0; i < symptom.length; i++) {
            symptomImg[i] = Symptoms.symptoms[i].getImageResourceId();
        }

        SymptomsAdapter adapter = new SymptomsAdapter(symptom, symptomImg);
        symptomRecyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        symptomRecyclerView.setLayoutManager(layoutManager);

        return symptomRecyclerView;

    }
}