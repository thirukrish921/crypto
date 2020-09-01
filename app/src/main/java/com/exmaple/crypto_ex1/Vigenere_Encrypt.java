package com.exmaple.crypto_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Vigenere_Encrypt extends AppCompatActivity {
    private TextInputLayout ptext, pkey;
    Button b;
    TextView restext;
    static String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere__encrypt);
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
                    System.out.println(key+" "+plain);
                    String res=vigenereEncryption(key,plain);
                    System.out.println(res);
                    if(!res.equals("no")){
                        restext.setText("Encrypted Text:   "+res)  ;
                    }

                }
            }
        });
    }
    static String vigenereEncryption(String key,String plain)
    {
        String cipher="";
        int j=0;
        for(int i=0;i<plain.length();i++)
        {
            if((alpha.indexOf(plain.charAt(i))!=-1))
            {
                cipher+=alpha.charAt((alpha.indexOf(plain.charAt(i)) + alpha.indexOf(key.charAt(j)))%26);
                j+=1;
                j=j%key.length();
            }
        }
        return cipher;
    }

}
