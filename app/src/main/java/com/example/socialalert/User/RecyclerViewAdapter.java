package com.example.socialalert.User;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialalert.Issue.Model;
import com.example.socialalert.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Model> modelArrayList;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Model> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.call_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Model model = modelArrayList.get(position);
        holder.profileLetter.setText(model.getProfileLetter().toString());
        holder.name.setText(model.getName());
        holder.number.setText(model.getNumber());

        final String phoneNumber = model.getNumber();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri phoneNumberUri = Uri.parse("tel:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL, phoneNumberUri);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView profileLetter, name, number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileLetter = itemView.findViewById(R.id.profileLetter);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
        }
    }
}
