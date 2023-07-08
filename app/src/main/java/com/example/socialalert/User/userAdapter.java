package com.example.socialalert.User;

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
import com.example.socialalert.Issue.DataClass;
import com.example.socialalert.R;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.UserViewHolder> {

    private List<DataClass> dataLists;
    private Context context;

    public userAdapter(List<DataClass> dataList, Context context) {
        this.dataLists = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        DataClass data = dataLists.get(position);

        holder.title.setText(data.getTitle());
        holder.department.setText("Department : " + data.getDepartment());
        holder.location.setText("Location : " + data.getLocation());
        holder.status.setText(data.getStatus());

        if (data.getStatus().equalsIgnoreCase("solved")) {
            holder.status.setBackgroundColor(context.getResources().getColor(R.color.theme2));
        } else {
            holder.status.setBackgroundColor(context.getResources().getColor(R.color.theme));
        }

        Glide.with(context).load(data.getUri()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, userDetailActivity.class);
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
        return dataLists.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView department;
        private TextView location;
        private TextView status;
        public ImageView imageView;
        private CardView cardView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rDescriptions);
            department = itemView.findViewById(R.id.rDepartments);
            location = itemView.findViewById(R.id.rLocations);
            status = itemView.findViewById(R.id.rStatuss);
            imageView = itemView.findViewById(R.id.rImages);
            cardView = itemView.findViewById(R.id.recCards);
        }
    }
}
