package com.example.socialalert.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.socialalert.R;

public class userDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView departmentTextView;
    private TextView locationTextView;
    private TextView contactTextView;
    private ImageView imageView;
    private TextView statusTextView;

    private SharedPreferences sharedPreferences;
public String problemTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        getWindow().setStatusBarColor(ContextCompat.getColor(userDetailActivity.this, R.color.theme2));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.theme2)));

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Initialize views
        titleTextView = findViewById(R.id.rdescriptions);
        descriptionTextView = findViewById(R.id.rdescriptions2);
        departmentTextView = findViewById(R.id.rdepartments);
        locationTextView = findViewById(R.id.rlocations);

        imageView = findViewById(R.id.rimages);
        statusTextView = findViewById(R.id.rStatus3);

        // Get the data passed from the previous activity or restored from savedInstanceState
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             problemTitle = extras.getString("Title");
            String description = extras.getString("Description");
            String department = extras.getString("Department");
            String location = extras.getString("Location");
           // String contact = extras.getString("Contact");
            String imageUrl = extras.getString("ImageUrl");
            String statustxt=extras.getString("Status");

            // Set the data to the corresponding views
            titleTextView.setText("Title : " + problemTitle);
            descriptionTextView.setText("Description : " + description);
            departmentTextView.setText("Department : " + department);
            locationTextView.setText("Location : " + location);
            statusTextView.setText("Status : " + statustxt);

            // Use an image loading library like Glide or Picasso to load the image
            // Example with Glide:
            Glide.with(this).load(imageUrl).into(imageView);


        }

        // Restore the toggle state from SharedPreferences

        // Set click listener on the toggleButton

    }


}
