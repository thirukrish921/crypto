package com.exmaple.crypto_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import com.google.android.material.textfield.TextInputLayout;

public class Rail_Encrypt extends AppCompatActivity {
    private TextInputLayout ptext, pkey;
    Button b;
    TextView restext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rail_encrypt);
        getSupportActionBar().hide();
        ptext=findViewById(R.id.lplainText);
        pkey=findViewById(R.id.lTextkey);
        b=findViewById(R.id.btnencrypt);
        restext=findViewById(R.id.restext);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plain=ptext.getEditText().getText().toString().trim();
                String key=pkey.getEditText().getText().toString().trim();

                if(plain.equals(null) || plain.equals("") || plain.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter text!",Toast.LENGTH_LONG).show();
                }
                else if(key.equals(null) || key.equals("") || key.toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Depth!",Toast.LENGTH_LONG).show();
                }
                else{
                    Integer key1 =Integer.parseInt(key);
                    System.out.println(key1+" "+plain);
                    String res= null;
                    try {
                        res = encode(plain,key1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(res);
                    if(!res.equals("no")){
                        restext.setText("Encrypted Text:   "+res)  ;
                    }

                }
            }
        });
    }

    String encode(String msg, int depth)  {
        int r = depth;
        int l = msg.length();
        int c = l / depth;
        int k = 0;
        char mat[][] = new char[r][c];
        String enc = "";
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != l) {
                    mat[j][i] = msg.charAt(k++);
                } else {
                    mat[j][i] = 'X';
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                enc += mat[i][j];
            }
        }
        System.out.println(enc);
        return enc;
    }





}
