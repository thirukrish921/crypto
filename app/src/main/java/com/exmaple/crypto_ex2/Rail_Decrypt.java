package com.exmaple.crypto_ex2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import com.google.android.material.textfield.TextInputLayout;

public class Rail_Decrypt extends AppCompatActivity {
    private TextInputLayout ptext, pkey;
    Button b;
    TextView restext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rail_decrypt);
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
                if(plain.equals(null) || plain.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter text!",Toast.LENGTH_LONG).show();
                }
                else if(key.equals(null) || key.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter Key!",Toast.LENGTH_LONG).show();
                }
                else{
                    Integer key1=Integer.parseInt(key);
                    System.out.println(key1+" "+plain);
                    String res= null;
                    try {
                        res = decode(plain,key1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(res);
                    if(!res.equals("no")){
                        restext.setText("Decrypted Text:   "+res)  ;
                    }

                }
            }
        });
    }
    String decode(String encmsg, int depth) throws Exception {
        int r = depth;
        int l = encmsg.length();
        int c = l / depth;
        int k = 0;
        char mat[][] = new char[r][c];
        String dec = "";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = encmsg.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                dec += mat[j][i];
            }
        }
        return dec;
    }
}





