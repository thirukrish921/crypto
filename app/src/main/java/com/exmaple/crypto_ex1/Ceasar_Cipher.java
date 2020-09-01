package com.exmaple.crypto_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Ceasar_Cipher extends AppCompatActivity {
    CardView enc,dec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceasar);
        getSupportActionBar().hide();
        enc=findViewById(R.id.cea_enc);
        dec=findViewById(R.id.cea_dec);
        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Ceasar_Encrypt.class);
                startActivity(in);
            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Ceasar_Decrypt.class);
                startActivity(in);
            }
        });
    }



}
