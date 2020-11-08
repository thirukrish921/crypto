package com.exmaple.crypto_ex2;

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
        cea=findViewById(R.id.railcard);
        vig=findViewById(R.id.rowcard);
        cea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =new Intent(getApplicationContext(),Rail_Cipher.class);
                startActivity(in);
            }
        });
        vig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in =new Intent(getApplicationContext(),Row_Cipher.class);
                startActivity(in);
            }
        });

    }


}
