package com.example.socialalert.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialalert.MainActivity;
import com.example.socialalert.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class signup extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        getWindow().setStatusBarColor(ContextCompat.getColor(signup.this, R.color.theme2));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.theme2)));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        mAuth = FirebaseAuth.getInstance();

        final EditText userName = findViewById(R.id.userName);
        final EditText mail = findViewById(R.id.mail);
        final EditText phone = findViewById(R.id.phone);
        final EditText pass = findViewById(R.id.pass);
        final Button button = findViewById(R.id.signUpBtn);
        final EditText dob=findViewById(R.id.dob);
        final EditText location=findViewById(R.id.location);

        dob.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Get current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Handle the selected date
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dob.setText(selectedDate);
                }, year, month, day);

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
            return true;
        }  );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneTxt = phone.getText().toString();
                final String passTxt = pass.getText().toString();
                final String mailTxt = mail.getText().toString();
                final String unameTxt = userName.getText().toString();
                final String dobTxt=dob.getText().toString();
                final String locTxt=location.getText().toString();

                if (phoneTxt.isEmpty() || passTxt.isEmpty() || mailTxt.isEmpty() || unameTxt.isEmpty()||dobTxt.isEmpty()||locTxt.isEmpty()) {
                    Toast.makeText(signup.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(mailTxt, passTxt)
                            .addOnCompleteListener(signup.this, task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user != null) {
                                        String userId = user.getUid();

                                        DatabaseReference userRef = databaseReference.child(unameTxt);
                                        userRef.child("email").setValue(mailTxt);
                                        userRef.child("phone").setValue(phoneTxt);
                                        userRef.child("password").setValue(passTxt);
                                        userRef.child("username").setValue(unameTxt);
                                        userRef.child("firebaseUserId").setValue(userId);
                                        userRef.child("dob").setValue(dobTxt);
                                        userRef.child("place").setValue(locTxt);
                                        Toast.makeText(signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(signup.this, MainActivity.class));
                                        finish();
                                    }
                                } else {
                                    try {
                                        throw task.getException();
                                    } catch (FirebaseAuthInvalidUserException invalidEmail) {
                                        Toast.makeText(signup.this, "Invalid email", Toast.LENGTH_SHORT).show();
                                    } catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                                        Toast.makeText(signup.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                    } catch (Exception e) {
                                        Toast.makeText(signup.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
