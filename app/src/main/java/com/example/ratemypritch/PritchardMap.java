package com.example.ratemypritch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PritchardMap extends AppCompatActivity {
    private ImageButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pritchard_map);
        ImageButton home = (ImageButton)findViewById(R.id.homeBtnPritchardMapView);
        ImageButton settings = (ImageButton)findViewById(R.id.settingsBtn);

        Fragment fragment = new Map_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
        Intent intentHome = new Intent(this, MainView.class);
        Intent intentSettings = new Intent(this, my_reviews.class);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentHome);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentSettings);
            }
        });
    }
}