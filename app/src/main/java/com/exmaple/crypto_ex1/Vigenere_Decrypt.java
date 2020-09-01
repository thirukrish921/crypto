package com.exmaple.crypto_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Vigenere_Decrypt extends AppCompatActivity {
    private TextInputLayout ptext, pkey;
    Button b;
    TextView restext;
    static String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere__decrypt);
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
                    String res=vigenereDecryption(key,plain);
                    System.out.println(res);
                    if(!res.equals("no")){
                        restext.setText("Decrypted Text:   "+res)  ;
                    }

                }
            }
        });
    }
    static String vigenereDecryption(String key,String cipher)
    {
        String plain="";
        int index,k=0;
        for(int i=0;i<cipher.length();i++)
        {
            if((alpha.indexOf(cipher.charAt(i))!=-1))
            {
                index=(alpha.indexOf(cipher.charAt(i)) - alpha.indexOf(key.charAt(k)));
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
                k+=1;
                k=k%key.length();
                plain+=alpha.charAt(index);
            }
        }
        return plain;
    }

}
