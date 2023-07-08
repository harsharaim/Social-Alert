package com.example.socialalert.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialalert.R;

public class admin_login extends Fragment {

    EditText userName, password;
    Button login;

    public admin_login() {
        // Required empty public constructor
    }

    public static admin_login newInstance() {
        return new admin_login();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_login, container, false);

        userName = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = userName.getText().toString();
                String enteredPassword = password.getText().toString();

                if (enteredUsername.equals("firesafety") && enteredPassword.equals("fire@123")) {
                    Toast.makeText(getActivity(), "Fire Department Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), admin.class);
                    intent.putExtra("department", "Fire Safety");
                    startActivity(intent);
                } else if (enteredUsername.equals("roadsafety") && enteredPassword.equals("road@123")) {
                    Toast.makeText(getActivity(), "Road Department Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), admin.class);
                    intent.putExtra("department", "Road Safety");
                    startActivity(intent);
                } else if (enteredUsername.equals("policedept") && enteredPassword.equals("police@123")) {
                    Toast.makeText(getActivity(), "Police Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), admin.class);
                    intent.putExtra("department", "Police Dept");
                    startActivity(intent);
                } else if (enteredUsername.equals("electric") && enteredPassword.equals("electric@123")) {
                    Toast.makeText(getActivity(), "Electricity Department Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), admin.class);
                    intent.putExtra("department", "Electric Dept");
                    startActivity(intent);
                } else if (enteredUsername.equals("environ") && enteredPassword.equals("environ")) {
                    Toast.makeText(getActivity(), "Environment Department Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), admin.class);
                    intent.putExtra("department", "Environment Dept");
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
