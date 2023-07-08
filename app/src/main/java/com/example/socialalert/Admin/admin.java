package com.example.socialalert.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.socialalert.MainActivity;
import com.example.socialalert.R;
import com.example.socialalert.databinding.AdminBinding;

public class admin extends AppCompatActivity {
    AdminBinding binding;
    AlertDialog.Builder builder;
    String department;
    String depart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(admin.this, R.color.theme2));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.theme2)));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("department")) {
            department = intent.getStringExtra("department");
            depart = intent.getStringExtra("department");
        }

        builder = new AlertDialog.Builder(this);
        replaceFragment(new admin_homeFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                      replaceFragment(admin_homeFragment.newInstance(department)); // Pass the username to the fragment

                    break;
                case R.id.issue:
                    replaceFragment(admin_issueFragment.newInstance(department));
                    break;
                case R.id.Issues:
                    builder.setTitle("Logging out")
                            .setMessage("Are you sure?")
                            .setCancelable(true)
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                   // adminLogout();
                                }
                            })
                            .show();
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("department", department); // Pass the username to the fragment
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void adminLogout() {
        Toast.makeText(admin.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(admin.this, MainActivity.class);
        startActivity(intent);
    }
}

