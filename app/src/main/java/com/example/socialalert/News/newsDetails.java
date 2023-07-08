package com.example.socialalert.News;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.socialalert.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class newsDetails extends AppCompatActivity {

    private String site_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        getWindow().setStatusBarColor(ContextCompat.getColor(newsDetails.this, R.color.theme2));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.theme2)));

        String title = getIntent().getStringExtra("title");
        String imgurl = getIntent().getStringExtra("image");
        String content = getIntent().getStringExtra("content");
        String description = getIntent().getStringExtra("description");
        site_url=getIntent().getStringExtra("site_url");
        TextView tlt = findViewById(R.id.title);
        TextView des = findViewById(R.id.description);
        TextView con = findViewById(R.id.content);
        ImageView img = findViewById(R.id.imgs);
        tlt.setText(title);
        des.setText(description);
        con.setText(content);
        Picasso.get().load(imgurl).into(img);

        FloatingActionButton b1 = findViewById(R.id.visit);
        b1.setOnClickListener(v -> {
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(site_url));
            startActivity(i);
        });
    }
}