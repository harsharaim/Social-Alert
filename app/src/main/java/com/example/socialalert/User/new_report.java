package com.example.socialalert.User;

import static android.app.PendingIntent.getActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.socialalert.Issue.DataClass;
import com.example.socialalert.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.socialalert.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class new_report extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private Button exit;
    private Button submit;
    private Spinner spinner;
    private ImageButton imageButton;
    private ImageView itemImg;
    private EditText descriptionEditText;
    private String imageURL;
    private EditText locationEditText;
    private EditText contactEditText;
    private EditText titles;
    Uri uri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_report);
        getWindow().setStatusBarColor(ContextCompat.getColor(new_report.this, R.color.theme2));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.theme2)));

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://social-alert1-default-rtdb.firebaseio.com/");
        storageReference = FirebaseStorage.getInstance().getReference();

        exit = findViewById(R.id.exit);
        submit = findViewById(R.id.submit);
        spinner = findViewById(R.id.spinner);
        descriptionEditText = findViewById(R.id.editTextTextMultiLine);
        locationEditText = findViewById(R.id.place);
        contactEditText = findViewById(R.id.editTextTextPersonName2);
        titles = findViewById(R.id.tiles);
        itemImg = findViewById(R.id.camera);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            itemImg.setImageURI(uri);
                            System.out.println("The uri value after is: " + uri);
                        } else {
                            Toast.makeText(new_report.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Fire Safety");
        spinnerItems.add("Road Safety");
        spinnerItems.add("Electric Dept");
        spinnerItems.add("Police Dept");
        spinnerItems.add("Environment Dept");

        // Spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selectedOption = spinner.getSelectedItem().toString();
                String headtitle = titles.getText().toString().trim();
                // Get the entered description, location, and contact
                String description = descriptionEditText.getText().toString().trim();
                String location = locationEditText.getText().toString().trim();
                String name = contactEditText.getText().toString().trim();
                if (uri == null) {
                    Toast.makeText(new_report.this, "Please select an image", Toast.LENGTH_SHORT).show();
                } else if (selectedOption.isEmpty()) {
                    Toast.makeText(new_report.this, "Please add Department", Toast.LENGTH_SHORT).show();
                }
                else if (location.isEmpty()) {
                    Toast.makeText(new_report.this, "Please add Place", Toast.LENGTH_SHORT).show();
                }else if (headtitle.isEmpty()) {
                    Toast.makeText(new_report.this, "Please add Title", Toast.LENGTH_SHORT).show();
                } else if (description.isEmpty()) {
                    Toast.makeText(new_report.this, "Please add Description", Toast.LENGTH_SHORT).show();
                }  else if (name.isEmpty()) {
                    Toast.makeText(new_report.this, "Please add Your user Name", Toast.LENGTH_SHORT).show();
                } else {
                    saveData();
                }

            }
        });

    }

    public void saveData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("problems")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(new_report.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        System.out.println("The uri value at saveData(): " + uri);
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });

    }

    public void uploadData() {
        String department = spinner.getSelectedItem().toString();
        String title = titles.getText().toString().trim();
        // Get the entered description, location, and contact
        String description = descriptionEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();
        String username = contactEditText.getText().toString().trim();
String status="pending";
        DataClass dataClass = new DataClass(title, description,department, location,  username, imageURL,status);

        FirebaseDatabase.getInstance().getReference("problems").child(title)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(new_report.this, "Issue Added", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(new_report.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        titles.setText("");
        descriptionEditText.setText("");
        locationEditText.setText("");
        contactEditText.setText("");


        uri = Uri.parse("android.resource://com.example.SocialAlert/drawable/img");
        itemImg.setImageURI(uri);
    }
}












