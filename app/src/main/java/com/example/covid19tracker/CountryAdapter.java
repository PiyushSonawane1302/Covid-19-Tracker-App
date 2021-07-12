package com.example.covid19tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Adapter for the country
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    ArrayList<Country> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country currentItem = items.get(position);
        holder.country.setText(currentItem.country);
        holder.total.setText(currentItem.total);
        holder.active.setText(currentItem.active);
        holder.recovered.setText(currentItem.recovered);
        holder.death.setText(currentItem.deaths);
        Glide.with(holder.itemView.getContext()).load(currentItem.flagUrl).into(holder.flag); //Used Glide to load image


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView total, active, recovered, death, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.flag = itemView.findViewById(R.id.flag_img);
            this.country = itemView.findViewById(R.id.country_name);
            this.total = itemView.findViewById(R.id.val_total);
            this.active = itemView.findViewById(R.id.val_active);
            this.recovered = itemView.findViewById(R.id.val_recv);
            this.death = itemView.findViewById(R.id.val_death);
        }
    }

    public void updateCountry(ArrayList<Country> updateCountry) {
        items.clear();
        items.addAll(updateCountry);
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Country> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }


}
