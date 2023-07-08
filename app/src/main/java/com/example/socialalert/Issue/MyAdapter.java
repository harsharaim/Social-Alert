package com.example.socialalert.Issue;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialalert.Admin.DetailActivity;
import com.example.socialalert.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataClass data = dataList.get(position);

        // Set the data to the corresponding views
        holder.descriptionTextView.setText(data.getTitle());
        holder.departmentTextView.setText("Department : " + data.getDepartment());
        holder.locationTextView.setText("Location : " + data.getLocation());
        holder.statusTextView.setText(data.getStatus());

        //=======holder.contactTextView.setText("Contact: " + data.getContact());
        if (data.getStatus().equalsIgnoreCase("solved")) {
            holder.statusTextView.setBackgroundColor(context.getResources().getColor(R.color.theme2)); // Set green color
        } else {
            holder.statusTextView.setBackgroundColor(context.getResources().getColor(R.color.theme)); // Set red color
        }

        // Load the image using Glide or any other image loading library
        Glide.with(context).load(data.getUri()).into(holder.imageView);

        // Set click listener on the CardView
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start DetailActivity and pass the data through intent extras
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("Title", data.getTitle());
                intent.putExtra("Description", data.getDescription());
                intent.putExtra("Department", data.getDepartment());
                intent.putExtra("Location", data.getLocation());
                intent.putExtra("Contact", data.getUsername());
                intent.putExtra("ImageUrl", data.getUri());
                intent.putExtra("Status", data.getStatus());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView descriptionTextView;
        public TextView departmentTextView;
        public TextView locationTextView;
        public TextView contactTextView;
        public ImageView imageView;
        public CardView cardView;
        public TextView statusTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.rDescription);
            departmentTextView = itemView.findViewById(R.id.rDepartment);
            locationTextView = itemView.findViewById(R.id.rLocation);
            statusTextView = itemView.findViewById(R.id.rStatus);
            //========contactTextView = itemView.findViewById(R.id.rContact);
            imageView = itemView.findViewById(R.id.rImage);
            cardView = itemView.findViewById(R.id.recCard);
        }
    }
}