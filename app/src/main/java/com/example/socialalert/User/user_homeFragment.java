package com.example.socialalert.User;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialalert.Admin.admin;
import com.example.socialalert.MainActivity;
import com.example.socialalert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_homeFragment extends Fragment {
    private static final String ARG_USERNAME = "username";
    private String username;
    private TextView Namelist;
    private TextView submit;
    private TextView solved;
    private DatabaseReference databaseReference;
private Button loggouts;
    public user_homeFragment() {
        // Required empty public constructor
    }

    public static user_homeFragment newInstance(String username) {
        user_homeFragment fragment = new user_homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("problems");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        Namelist = view.findViewById(R.id.unamedisp);
        submit = view.findViewById(R.id.userSubmit);
        solved = view.findViewById(R.id.usersolved);
        loggouts=view.findViewById(R.id.logout);
        Namelist.append("Hi " + username);
loggouts.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
});
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        int problemCount = getProblemCount(username);
        submit.setText("    " + problemCount);

        int solvedCount = getSolvedProblemCount(username);
        solved.setText(" " + solvedCount);
    }

    private int getProblemCount(String username) {
        Query query = databaseReference.orderByChild("username").equalTo(username);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.getChildrenCount();
                submit.setText(" " + count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        };

        query.addListenerForSingleValueEvent(eventListener);

        return 0; // You can return any default value here, the actual count will be updated in the event listener
    }

    private int getSolvedProblemCount(String username) {
        Query query = databaseReference.orderByChild("username").equalTo(username);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String status = snapshot.child("status").getValue(String.class);
                    if (status != null && status.equals("solved")) {
                        count++;
                    }
                }
                solved.setText(" " + count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        };
        query.addListenerForSingleValueEvent(eventListener);
        return 0;
    }
}
