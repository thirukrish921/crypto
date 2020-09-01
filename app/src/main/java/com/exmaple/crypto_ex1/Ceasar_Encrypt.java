package com.exmaple.crypto_ex1;

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

public class Ceasar_Encrypt extends AppCompatActivity {
    private TextInputLayout ptext, pkey;
    Button b;
    TextView restext;
    static String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceasar__encrypt);
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
                    Toast.makeText(getApplicationContext(),"Please Enter Key!",Toast.LENGTH_LONG).show();
                }
                else{
                    Integer key1 =Integer.parseInt(key);
                        System.out.println(key1+" "+plain);
                        String res=ceasarEncryption(key1,plain);
                    System.out.println(res);
                    if(!res.equals("no")){
                        restext.setText("Encrypted Text:   "+res)  ;
                    }

                }
            }
        });
    }

        String ceasarEncryption(int key,String plain)
        {
            String cipher="";
            for(int i=0;i<plain.length();i++)
            {
                if((alpha.indexOf(plain.charAt(i))!=-1))
                {
                    cipher+=alpha.charAt((alpha.indexOf(plain.charAt(i)) + key)%26);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Text!",Toast.LENGTH_LONG).show();
                    return "no";
                }
            }
            return cipher;
        }

        static String ceasarDecryption(int key,String cipher)
        {
            String plain="";
            int index;
            for(int i=0;i<cipher.length();i++)
            {
                if((alpha.indexOf(cipher.charAt(i))!=-1))
                {
                    index=(alpha.indexOf(cipher.charAt(i)) - key);
                    if(index<0)
                    {
                        if( index*-1 >26 )
                        {
                            int j= (index*-1)/26;
                            index=((26*(j+1))-(index*-1))%26;
                        }
                        else
                            index=26+index;
                    }
                    plain+=alpha.charAt(index);
                }
            }
            return plain;
        }






}
