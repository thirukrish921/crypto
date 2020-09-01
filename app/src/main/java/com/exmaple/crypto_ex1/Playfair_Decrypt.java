package com.exmaple.crypto_ex1;
import androidx.appcompat.app.AppCompatActivity;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Playfair_Decrypt extends AppCompatActivity {
    private TextInputLayout ptext, pkey;
    static String[] a=new String[5];
    Button b;
    TextView restext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playfair__decrypt);
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
                    if(plain.length()%2==0) {
                        System.out.println(key + "here " + plain);
                        matrix(key);
                        String res = playfairDecryption(plain);
                        System.out.println(res);
                        if (!res.equals("no")) {
                            StringBuffer sb= new StringBuffer(res);
////invoking the method
                            sb.deleteCharAt(sb.length()-1);
                            System.out.println(sb);
                            restext.setText("Decrypted Text:   "+sb);
                        }
                    }
                    else{
                        System.out.println(key + " " + plain);
                        matrix(key);
                        String res = playfairDecryption(plain+"Z");
                        System.out.println(res);
                        if (!res.equals("no")) {

                            restext.setText("Decrypted Text:   "+res);
                        }
                    }

                }
            }
        });
    }
    public static boolean find(String[] a ,char b,int j)
    {
        int i=0;
        while(i<=j)
        {
            if(a[i].indexOf(b)!=-1)
            {
                return false;
            }
            i+=1;
        }
        return true;
    }

    public static int matrix(String s)
    {
        String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int j=0,k=0;
        for(int i=0;i<5;i++)
            a[i]="";
        for(int i=0;i<s.length();i++)
        {
            if(k%5==0 && k!=0){
                j+=1;
            }
            if( find(a,s.charAt(i),j) && s.charAt(i)!='J')
            {
                k+=1;
                a[j]+=s.charAt(i);
            }
            else if(k%5==0)
            {
                k=0;
            }
        }
        k=k%5;
        int p=0;
        while(k<5)
        {
            if( find(a,alpha.charAt(p),j) && alpha.charAt(p)!='J')
            {
                a[j]+=alpha.charAt(p);
                k+=1;
            }
            p+=1;
        }
        for (int i=j+1;i<5;i++)
        {
            int l=0;
            while(l<5)
            {
                if( find(a,alpha.charAt(p),i) && alpha.charAt(p)!='J')
                {
                    a[i]+=alpha.charAt(p);
                    l+=1;
                }
                p+=1;
            }
        }
        return 0;
    }

    static String playfairEncryption(String plain)
    {
        String cipher="";
        int x=0,y=0,x1=0,y1=0,p=0;

        while(p<plain.length()-1)
        {
            for (int i=0;i<5;i++)
            {
                y=a[i].indexOf(plain.charAt(p));
                if(y!=-1 )
                {
                    x=i;
                    break;
                }
                else if(plain.charAt(p)=='J')
                {
                    y=a[i].indexOf('I');
                    if(y!=-1 )
                    {
                        x=i;
                        break;
                    }
                }
            }
            p+=1;
            for (int i=0;i<5;i++)
            {
                y1=a[i].indexOf(plain.charAt(p));
                if(y1!=-1 )
                {
                    x1=i;
                    break;
                }
                else if(plain.charAt(p)=='J')
                {
                    y=a[i].indexOf('I');
                    if(y!=-1 )
                    {
                        x=i;
                        break;
                    }
                }
            }
            p+=1;
            if(x1 == x)
            {
                cipher+=a[x].charAt((y-1+5)%5);
                cipher+=a[x].charAt((y1-1+5)%5);
            }
            else if(y1 == y)
            {
                cipher+=a[(x-1+5)%5].charAt(y);
                cipher+=a[(x1-1+5)%5].charAt(y1);
            }
            else{
                cipher+=a[x].charAt(y1);
                cipher+=a[x1].charAt(y);
            }
            //System.out.println(cipher);
        }
        return cipher;
    }

    static String playfairDecryption(String cipher)
    {
        String plain="";
        int x=0,y=0,x1=0,y1=0,p=0;
        while(p<cipher.length()-1)
        {
            for (int i=0;i<5;i++)
            {
                y=a[i].indexOf(cipher.charAt(p));
                if(y!=-1 )
                {
                    x=i;
                    break;
                }
                else if(cipher.charAt(p)=='J')
                {
                    y=a[i].indexOf('I');
                    if(y!=-1 )
                    {
                        x=i;
                        break;
                    }
                }
            }
            p+=1;
            for (int i=0;i<5;i++)
            {
                y1=a[i].indexOf(cipher.charAt(p));
                if(y1!=-1 )
                {
                    x1=i;
                    break;
                }
                else if(cipher.charAt(p)=='J')
                {
                    y=a[i].indexOf('I');
                    if(y!=-1 )
                    {
                        x=i;
                        break;
                    }
                }
            }
            p+=1;
            if(x1 == x)
            {
                plain+=a[x].charAt((y+1)%5);
                plain+=a[x].charAt((y1+1)%5);
            }
            else if(y1 == y)
            {
                plain+=a[(x+1)%5].charAt(y);
                plain+=a[(x1+1)%5].charAt(y1);
            }
            else{
                plain+=a[x].charAt(y1);
                plain+=a[x1].charAt(y);
            }
            //System.out.println(cipher);
        }
        return plain;
    }
}