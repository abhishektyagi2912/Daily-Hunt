package com.example.dailyhunt;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new Adapter.ViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        ModelClass modelClass = modelClassArrayList.get(position);
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context,webView.class);
            intent.putExtra("url",modelClass.getUrl());
            context.startActivity(intent);

        });

        holder.mtime.setText("Published At :-"+ modelClass.getPublishedAt());
        holder.mauthor.setText(modelClass.getAuthor());
        holder.mheading.setText(modelClass.getTitle());
        holder.mcontent.setText(modelClass.getDescription());
        Glide.with(context).load(modelClass.getUrlToImage()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading, mcontent, mauthor, mtime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mheading  = itemView.findViewById(R.id.mainheading);
            mcontent  = itemView.findViewById(R.id.content);
            mauthor  = itemView.findViewById(R.id.author);
            imageView = itemView.findViewById(R.id.newsIV);
            mtime = itemView.findViewById(R.id.newsText);
            cardView = itemView.findViewById(R.id.newsCV);


        }
    }
}
