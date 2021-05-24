package com.example.covid19tracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PrecautionsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RecyclerView precautionsRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_precautions, container, false);

        String[] precautionDesc = new String[Precautions.precautions.length];
        for (int i = 0; i < precautionDesc.length; i++) {
            precautionDesc[i] = Precautions.precautions[i].getDescription();
        }

        int[] precautionImg = new int[Precautions.precautions.length];
        for (int i = 0; i < precautionImg.length; i++) {
            precautionImg[i] = Precautions.precautions[i].getImageResourceId();
        }

        PrecautionsAdapter adapter = new PrecautionsAdapter(precautionDesc, precautionImg);
        precautionsRecyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        precautionsRecyclerView.setLayoutManager(layoutManager);

        return precautionsRecyclerView;

    }
}