package com.exmaple.crypto_ex1;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Hill_Decrypt extends AppCompatActivity {
    int[] lm;
    int[][] km;
    int[] rm;
    static int choice;
    int [][] invK;
    static int n;
    private TextInputLayout ptext, pkey;
    static String[] a=new String[5];
    Button b;
    TextView restext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hill__decrypt);
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
                    choice=2;
                    System.out.println(key+" "+plain);
                    double sq = Math.sqrt(key.length());
                    if (sq != (long) sq)
                        System.out.println("Cannot Form a square matrix");
                    else
                    {
                        int size = (int) sq;
                        if (check(key, size))
                        {

                            System.out.println("Result:");
                            cofact(km, size);
                            performDivision(plain, size);


                        }
                    }


                }
            }
        });
    }

    public void performDivision(String temp, int s)
    {
        while (temp.length() > s)
        {
            String line = temp.substring(0, s);
            temp = temp.substring(s, temp.length());
            calLineMatrix(line);
            if(choice ==1){
                multiplyLineByKey(line.length());
            }else{
                multiplyLineByInvKey(line.length());
            }
            showResult(line.length());
        }
        if (temp.length() == s){

            if(choice ==1){
                calLineMatrix(temp);
                multiplyLineByKey(temp.length());
                showResult(temp.length());
            }
            else{
                calLineMatrix(temp);
                this.multiplyLineByInvKey(temp.length());
                showResult(temp.length());
            }
        }
        else if (temp.length() < s)
        {
            for (int i = temp.length(); i < s; i++)
                temp = temp + 'x';
            if(choice ==1){
                calLineMatrix(temp);
                multiplyLineByKey(temp.length());
                showResult(temp.length());
            }
            else{
                calLineMatrix(temp);
                multiplyLineByInvKey(temp.length());
                showResult(temp.length());
            }
        }
    }


    public void calKeyMatrix(String key, int len)
    {
        km = new int[len][len];
        int k = 0;
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                km[i][j] = ((int) key.charAt(k)) - 65;
                k++;
            }
        }
    }

    public void calLineMatrix(String line)
    {
        lm = new int[line.length()];
        for (int i = 0; i < line.length(); i++)
        {
            lm[i] = ((int) line.charAt(i)) - 65;
        }
    }

    public void multiplyLineByKey(int len)
    {
        rm = new int[len];
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                rm[i] += km[i][j] * lm[j];
            }
            rm[i] %= 26;
        }
    }
    public void multiplyLineByInvKey(int len)
    {

        rm = new int[len];
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                rm[i] += invK[i][j] * lm[j];
            }
            rm[i] %= 26;
        }
    }


    public void showResult(int len)
    {
        String result = "";
        for (int i = 0; i < len; i++)
        {
            result += (char) (rm[i] + 65);
        }
        restext.setText("Decrypted Text:   "+result);
        System.out.print(result);
    }



    public int calDeterminant(int A[][], int N)
    {
        int resultOfDet;
        switch (N) {
            case 1:
                resultOfDet = A[0][0];
                break;
            case 2:
                resultOfDet = A[0][0] * A[1][1] - A[1][0] * A[0][1];
                break;
            default:
                resultOfDet = 0;
                for (int j1 = 0; j1 < N; j1++)
                {
                    int m[][] = new int[N - 1][N - 1];
                    for (int i = 1; i < N; i++)
                    {
                        int j2 = 0;
                        for (int j = 0; j < N; j++)
                        {
                            if (j == j1)
                                continue;
                            m[i - 1][j2] = A[i][j];
                            j2++;
                        }
                    }
                    resultOfDet += Math.pow(-1.0, 1.0 + j1 + 1.0) * A[0][j1]
                            * calDeterminant(m, N - 1);
                }   break;
        }
        return resultOfDet;
    }

    public void cofact(int num[][], int f)
    {
        int b[][], fac[][];
        b = new int[f][f];
        fac = new int[f][f];
        int p, q, m, n, i, j;
        for (q = 0; q < f; q++)
        {
            for (p = 0; p < f; p++)
            {
                m = 0;
                n = 0;
                for (i = 0; i < f; i++)
                {
                    for (j = 0; j < f; j++)
                    {
                        b[i][j] = 0;
                        if (i != q && j != p)
                        {
                            b[m][n] = num[i][j];
                            if (n < (f - 2))
                                n++;
                            else
                            {
                                n = 0;
                                m++;
                            }
                        }
                    }
                }
                fac[q][p] = (int) Math.pow(-1, q + p) * calDeterminant(b, f - 1);
            }
        }
        trans(fac, f);
    }

    void trans(int fac[][], int r)
    {
        int i, j;
        int b[][], inv[][];
        b = new int[r][r];
        inv = new int[r][r];
        int d = calDeterminant(km, r);
        int mi = mi(d % 26);
        mi %= 26;
        if (mi < 0)
            mi += 26;
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                b[i][j] = fac[j][i];
            }
        }
        for (i = 0; i < r; i++)
        {
            for (j = 0; j < r; j++)
            {
                inv[i][j] = b[i][j] % 26;
                if (inv[i][j] < 0)
                    inv[i][j] += 26;
                inv[i][j] *= mi;
                inv[i][j] %= 26;
            }
        }
        //System.out.println("\nInverse key:");
        //matrixtoinvkey(inv, r);

        invK = inv;
    }

    public int mi(int d)
    {
        int q, r1, r2, r, t1, t2, t;
        r1 = 26;
        r2 = d;
        t1 = 0;
        t2 = 1;
        while (r1 != 1 && r2 != 0)
        {
            q = r1 / r2;
            r = r1 % r2;
            t = t1 - (t2 * q);
            r1 = r2;
            r2 = r;
            t1 = t2;
            t2 = t;
        }
        return (t1 + t2);
    }

    public void matrixtoinvkey(int inv[][], int n)
    {
        String invkey = "";
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                invkey += (char) (inv[i][j] +65);
            }
        }
        System.out.print(invkey);
    }
    public boolean check(String key, int len)
    {
        calKeyMatrix(key, len);
        int d = calDeterminant(km, len);
        d = d % 26;
        if (d == 0)
        {
            System.out.println("Key is not invertible");
            return false;
        }
        else if (d % 2 == 0 || d % 13 == 0)
        {
            System.out.println("Key is not invertible");
            return false;
        }
        else
        {
            return true;
        }
    }



}
