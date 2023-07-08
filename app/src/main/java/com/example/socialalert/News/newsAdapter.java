package com.example.socialalert.News;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialalert.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder> {
    private ArrayList<Articles> new_article;
    private Context cont;

    public newsAdapter(ArrayList<Articles> new_article, Context cont) {
        this.new_article = new_article;
        this.cont = cont;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_holder,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        System.out.println("adding value----------------");

        Articles al=new_article.get(position);
        holder.description.setText(al.getDescription());
        holder.title.setText(al.getTitle());

        Picasso.get()
                .load(al.getUrlToImage())    ///Load image
                .into(holder.img);

        holder.itemView.setOnClickListener(v -> {
            Intent it=new Intent(cont, newsDetails.class);
            it.putExtra("title",al.getTitle());
            it.putExtra("image",al.getUrlToImage());
            it.putExtra("content",al.getContent());
            it.putExtra("description",al.getDescription());
            it.putExtra("site_url",al.getUrl());
            cont.startActivity(it);
        });



    }

    @Override
    public int getItemCount() {
        return new_article.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title;
        private TextView description;
        // private MaterialCardView cardlayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img= itemView.findViewById(R.id.news_img);
            title=itemView.findViewById(R.id.headline);
            description=itemView.findViewById(R.id.subtitle);
            // cardlayout=itemView.findViewById(R.id.new_adp);


        }
    }
}
