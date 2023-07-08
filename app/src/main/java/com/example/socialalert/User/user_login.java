package com.example.socialalert.User;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.socialalert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_login extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://social-alert1-default-rtdb.firebaseio.com/");

    private EditText userName;
    private EditText password;
    private Button login;
    private TextView signup;

    public user_login() {
        // Required empty public constructor
    }

    public static user_login newInstance() {
        return new user_login();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_login, container, false);

        userName = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);
        signup = view.findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userNames = userName.getText().toString();
                final String passwordTxt = password.getText().toString();
                if (userNames.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter mobile or password completely!", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(userNames)) {
                                final String getPassword = snapshot.child(userNames).child("password").getValue(String.class);
                                if (getPassword.equals(passwordTxt)) {
                                    Toast.makeText(getActivity(), "Successful login", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getActivity(), user.class);

                                    intent.putExtra("name", userNames);


                                    // Finish the current activity
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(), "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Wrong username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getActivity(), "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), com.example.socialalert.User.signup.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
