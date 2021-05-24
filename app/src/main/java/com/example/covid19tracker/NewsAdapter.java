package com.example.covid19tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    ArrayList<News> items = new ArrayList<>();
    private NewsItemClicked listener;

    public NewsAdapter(NewsItemClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(items.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News currentItem = items.get(position);
        holder.textView.setText(currentItem.title);
        holder.source.setText(currentItem.source);
        Glide.with(holder.itemView.getContext()).load(currentItem.imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView,source;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.news_img);
            this.textView = itemView.findViewById(R.id.news_title);
            this.source = itemView.findViewById(R.id.news_source);
        }

    }

    public void updateNews(ArrayList<News> updateNews) {
        items.clear();
        items.addAll(updateNews);
        notifyDataSetChanged();
    }


    interface NewsItemClicked{
        void onItemClicked(News item);
    }

}
