package com.example.socialalert.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialalert.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link admin_homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.socialalert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class admin_homeFragment extends Fragment {
    private static final String ARG_DEPARTMENT = "department";
    private String department;
    private TextView issueRaisedTextView;
    private TextView issueResolvedTextView;
    private DatabaseReference databaseReference;

    public admin_homeFragment() {
        // Required empty public constructor
    }

    public static admin_homeFragment newInstance(String department) {
        admin_homeFragment fragment = new admin_homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DEPARTMENT, department);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            department = getArguments().getString(ARG_DEPARTMENT);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("problems");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        issueRaisedTextView = view.findViewById(R.id.usersubs);
        issueResolvedTextView = view.findViewById(R.id.usersols);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        int issueRaisedCount = getIssueRaisedCount();
        issueRaisedTextView.setText(String.valueOf(issueRaisedCount));

        int issueResolvedCount = getIssueResolvedCount();
        issueResolvedTextView.setText(String.valueOf(issueResolvedCount));
    }

    private int getIssueRaisedCount() {
        Query query = databaseReference.orderByChild("department").equalTo(department);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.getChildrenCount();
                issueRaisedTextView.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        };
        query.addListenerForSingleValueEvent(eventListener);

        return 0; // You can return any default value here, the actual count will be updated in the event listener
    }

    private int getIssueResolvedCount() {
        Query query = databaseReference.orderByChild("department").equalTo(department);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String status = snapshot.child("status").getValue(String.class);
                    if (status != null && status.equals("solved")) {
                        count++;
                    }
                }
                issueResolvedTextView.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        };
        query.addListenerForSingleValueEvent(eventListener);

        return 0; // You can return any default value here, the actual count will be updated in the event listener
    }
}
