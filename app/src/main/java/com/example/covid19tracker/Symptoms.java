package com.example.covid19tracker;

public class Symptoms {

    String symptom;
    int imageResourceId;

    public static final Symptoms[] symptoms = {
            new Symptoms("Cough", R.drawable.ic_cough),
            new Symptoms("Headache", R.drawable.ic_headache),
            new Symptoms("Fever", R.drawable.ic_fever),
            new Symptoms("Short Breath", R.drawable.ic_short_breath),
            new Symptoms("Soar Throat", R.drawable.ic_soar_throat),
            new Symptoms("Nausea", R.drawable.ic_nausea)
    };

    public Symptoms(String symptom, int imageResourceId) {
        this.symptom = symptom;
        this.imageResourceId = imageResourceId;
    }

    public String getSymptom() {
        return symptom;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
