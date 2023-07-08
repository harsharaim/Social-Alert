package com.example.socialalert.User;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialalert.Issue.DataClass;
import com.example.socialalert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userIssues extends Fragment {
    private RecyclerView recyclerViews;
    public int count=0;
    private static final String ARG_USERNAME = "username";
    private userAdapter adapter;
    private String username;
    private List<DataClass> dataLists;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    public userIssues() {
        // Required empty public constructor
    }

    public static userIssues newInstance(String username) {
        userIssues fragment = new userIssues();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username= getArguments().getString(ARG_USERNAME);
            Log.d("userIssues", "Username: " + username);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_issues, container, false);

        recyclerViews = view.findViewById(R.id.recyclerViews);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViews.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataLists = new ArrayList<>();
        adapter = new userAdapter(dataLists, getActivity());
        recyclerViews.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("problems");
        Query query = databaseReference;
        dialog.show();

        eventListener = query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataLists.clear();
                for (DataSnapshot itemSnapShot : snapshot.getChildren()) {
                    DataClass dataClass = itemSnapShot.getValue(DataClass.class);
                    if (dataClass != null && dataClass.getUsername() != null && dataClass.getUsername().equals(username)) {
                        dataLists.add(dataClass);
                    }
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (eventListener != null && databaseReference != null) {
            databaseReference.removeEventListener(eventListener);
        }
    }
}