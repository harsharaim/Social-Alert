package com.example.socialalert.Admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.socialalert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView departmentTextView;
    private TextView locationTextView;
    private TextView contactTextView;
    private ImageView imageView;
    private TextView statusTextView;
    private ToggleButton toggleButton;
    private DatabaseReference problemRef;

    private String problemTitle;
    private boolean isSolved; // Variable to store the status value
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Initialize views
        titleTextView = findViewById(R.id.rdescriptions);
        descriptionTextView = findViewById(R.id.rdescriptions2);
        departmentTextView = findViewById(R.id.rdepartments);
        locationTextView = findViewById(R.id.rlocations);
        contactTextView = findViewById(R.id.rcontacts);
        imageView = findViewById(R.id.rimages);
        statusTextView = findViewById(R.id.rStatus3);
        toggleButton = findViewById(R.id.toggleButtonss);

        // Get the data passed from the previous activity or restored from savedInstanceState
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            problemTitle = extras.getString("Title");
            String description = extras.getString("Description");
            String department = extras.getString("Department");
            String location = extras.getString("Location");
            String contact = extras.getString("Contact");
            String imageUrl = extras.getString("ImageUrl");

            // Set the data to the corresponding views
            titleTextView.setText("Title : " + problemTitle);
            descriptionTextView.setText("Description : " + description);
            departmentTextView.setText("Department : " + department);
            locationTextView.setText("Place : " + location);
            contactTextView.setText("User Name : " + contact);

            // Use an image loading library like Glide or Picasso to load the image
            // Example with Glide:
            Glide.with(this).load(imageUrl).into(imageView);
        }

        // Restore the toggle state from SharedPreferences
        isSolved = sharedPreferences.getBoolean("IsSolved", false);
        toggleButton.setChecked(isSolved);

        // Set the status value to the statusTextView
        statusTextView.setText("Status: " + (isSolved ? "Solved" : "Unsolved"));

        // Set click listener on the toggleButton
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isSolved = isChecked;
            // Update the status value in the statusTextView
            statusTextView.setText("Status: " + (isChecked ? "Solved" : "Unsolved"));
            // Save the toggle state in SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("IsSolved", isChecked);
            editor.apply();

            // Update the problem status in Firebase
            updateProblemStatus(isChecked ? "solved" : "pending");
        });
    }

    private void updateProblemStatus(String status) {
        DatabaseReference problemRef = FirebaseDatabase.getInstance().getReference().child("problems");
        problemRef.orderByChild("title").equalTo(problemTitle).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot problemSnapshot = dataSnapshot.getChildren().iterator().next();
                    String problemId = problemSnapshot.getKey();

                    if (problemId != null) {
                        problemRef.child(problemId).child("status").setValue(status);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error case if necessary
            }
        });
    }
}
