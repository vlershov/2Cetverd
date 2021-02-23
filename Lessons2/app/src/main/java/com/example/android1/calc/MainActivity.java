package com.example.android1.calc;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
    private static String firsLp = "";
    private static String secondLp  = "";
    private static String deistvir = "";
    private static TextView textView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.image);
        String imageUrl = "https://c.pxhere.com/images/97/67/a1d8006025137144702b9879c0cd-1418295.jpg!d";

        textView = findViewById(R.id.RezultText);
        new AsyncRequest().execute(imageUrl);

    }

    class AsyncRequest extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... args) {
            try {
                java.net.URL url = new java.net.URL(args[0]);
                HttpURLConnection connection = (HttpURLConnection) url .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                mImageView.setImageBitmap(myBitmap);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }


        }

    }


    public void buttonRed_Click(View v){
        switch (v.getId())
        {
            case R.id.button_0:
                AddsetRezult("0");
                break;
            case R.id.button_1:
                AddsetRezult("1");
                break;
            case R.id.button_2:
                AddsetRezult("2");
                break;
            case R.id.button_3:
                AddsetRezult("3");
                break;
            case R.id.button_4:
                AddsetRezult("4");
                break;
            case R.id.button_5:
                AddsetRezult("5");
                break;
            case R.id.button_6:
                AddsetRezult("6");
                break;
            case R.id.button_7:
                AddsetRezult("7");
                break;
            case R.id.button_8:
                AddsetRezult("8");
                break;
            case R.id.button_9:
                AddsetRezult("9");
                break;
            case R.id.button_simv:
                AddsetRezultSimvol(".");
                break;
        }
    }

    protected void AddsetRezultSimvol(String rezult){

        try {
            if (deistvir == "") {
                if (firsLp == ""){
                    firsLp = "0" + rezult;

                }else {
                    firsLp = firsLp +rezult;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView.setText(firsLp+deistvir);
    }

    protected void AddsetRezult(String rezult){

     try {
         if (deistvir == "") {
             firsLp = firsLp + rezult;
         }else{
             secondLp = secondLp + rezult;
         }
      } catch (Exception e) {
          e.printStackTrace();
      }
        textView.setText(firsLp+deistvir+secondLp);
    }
    public void buttonRed_Click_deistvie(View v){
        switch (v.getId())
        {
            case R.id.button_del:
                deistvir = "/";
                AddSimvol();
                break;
            case R.id.button_min:
                deistvir = "-";
                AddSimvol();
                break;
            case R.id.button_plus:
                deistvir = "+";
                AddSimvol();
                break;
            case R.id.button_ravn:

                GetRezult();
                break;
            case R.id.button_umn:
                deistvir = "*";
                AddSimvol();
                break;
        }
    }

    protected void AddSimvol(){

        textView.setText(firsLp + deistvir);
    }

    protected void GetRezult(){
        Float pervoe = Float.parseFloat(firsLp);
        Float vtoroe = Float.parseFloat(secondLp);
        Float rez;
        if (deistvir == "-"){
            rez = pervoe - vtoroe;
            textView.setText(rez.toString());

        }else if (deistvir == "*"){
            rez = pervoe * vtoroe;
            textView.setText(rez.toString());
        }else if (deistvir == "+"){
            rez = pervoe + vtoroe;
            textView.setText(rez.toString());
        }else if (deistvir == "/"){
            if(vtoroe==0){
                textView.setText("Делить на 0 нельзя.");
            }else {
                rez = pervoe / vtoroe;
                textView.setText(rez.toString());
            }
        }
        firsLp = "";
        secondLp = "";
        deistvir = "";
    }

}