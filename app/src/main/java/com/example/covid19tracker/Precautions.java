package com.example.covid19tracker;

public class Precautions {
    String description;
    int imageResourceId;

    public static final Precautions[] precautions = {
            new Precautions("WASH YOUR HANDS", R.drawable.ic_wash_hand_precaution),
            new Precautions("STAY AT HOME", R.drawable.ic_stay_home_precaution),
            new Precautions("AVOID CONTACT", R.drawable.ic_avoid_contact_precaution),
            new Precautions("WEAR FACE MASK", R.drawable.ic_wear_mask_precaution),
            new Precautions("CLEAN SURFACES", R.drawable.ic_clean_surfaces_precaution),
            new Precautions("EAT HEALTHY", R.drawable.ic_eat_healthy_precaution)
    };

    public Precautions(String description, int imageResourceId) {
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
