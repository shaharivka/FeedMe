package com.example.feedme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseCookingBakery extends AppCompatActivity implements View.OnClickListener{

    Button HouseCooking;
    Button HouseBakery;
    Button ClientProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cooking_bakery);
        HouseCooking=(Button)findViewById(R.id.HouseCooking);
        HouseBakery=(Button)findViewById(R.id.HouseBakery);
        ClientProfile=(Button)findViewById(R.id.ClientProfile);

        HouseCooking.setOnClickListener(this);
        HouseBakery.setOnClickListener(this);
        ClientProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        if (view ==  HouseCooking) {
//            Intent intent = new Intent(this, .class);
//            startActivity(intent);
//        }
//        if (view ==  HouseBakery) {
//            Intent intent = new Intent(this, .class);
//            startActivity(intent);
//        }
        if (view ==  ClientProfile) {
            Intent intent = new Intent(this, ClientProfile.class);
            startActivity(intent);
        }

    }
}