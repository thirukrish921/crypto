package com.exmaple.crypto_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CardView cea,vig,pla,hill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        cea=findViewById(R.id.ceacard);
        vig=findViewById(R.id.vigcard);
        pla=findViewById(R.id.placard);
        hill=findViewById(R.id.hillcard);
        cea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in =new Intent(getApplicationContext(),Ceasar_Cipher.class);
               startActivity(in);
            }
        });
        vig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Vigenere_Cipher.class);
                startActivity(in);
            }
        });
        pla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Playfair_Cipher.class);
                startActivity(in);
            }
        });
        hill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Hill_Cipher.class);
                startActivity(in);
            }
        });
    }


}
