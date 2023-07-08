package com.example.socialalert.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.socialalert.R;
import com.example.socialalert.databinding.UserBinding;

public class user extends AppCompatActivity {
    UserBinding binding;
    AlertDialog.Builder builder;
    String username;
    String homename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(user.this, R.color.theme2));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.theme2)));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            username = intent.getStringExtra("name");
            homename = intent.getStringExtra("name");
        }

        builder = new AlertDialog.Builder(this);
        replaceFragment(user_homeFragment.newInstance(username)); // Pass the username to the fragment
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(user_homeFragment.newInstance(username)); // Pass the username to the fragment
                    break;
                case R.id.contact:
                    replaceFragment(new user_contactFragment());
                    break;
                case R.id.news:
                    replaceFragment(new user_newsFragment());
                    break;
                case R.id.Issues:
                    replaceFragment(userIssues.newInstance(username)); // Pass the username
                    break;
            }
            return true;
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user.this, new_report.class);
                startActivity(intent);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("username", username); // Pass the username to the fragment
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}