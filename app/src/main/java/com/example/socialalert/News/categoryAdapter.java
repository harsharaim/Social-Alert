package com.example.socialalert.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialalert.R;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    private ArrayList<categoryModel> articles;
    private Context cont;
    Catagory_click_interface ca;



    public categoryAdapter(ArrayList<categoryModel> articles, Context cont, Catagory_click_interface ca) {
        this.articles = articles;
        this.cont = cont;
        this.ca = ca;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        categoryModel ml=articles.get(position);
        int pos=position;
        holder.c_name.setText(ml.getC_name());

        holder.itemView.setOnClickListener(v -> ca.catagory_click(pos));


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }




    public interface Catagory_click_interface{
        void catagory_click(int position);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView c_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ImageView img = itemView.findViewById(R.id.catagory_Image);
            c_name= itemView.findViewById(R.id.catagory_name);
        }
    }


}
