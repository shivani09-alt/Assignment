package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubmitUrl extends AppCompatActivity {
   Button submit;
   EditText url;

   String errorMsg="Please Enter Valid URL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_url);
        url=findViewById(R.id.urlText);
        submit=findViewById(R.id.submitUrl);
        submit.setOnClickListener(v -> {
            String urlValue=url.getText().toString();


            if(!url.getText().toString().isEmpty()) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("url", urlValue);
                startActivity(intent);
            }
            else{
                url.setError(errorMsg);
            }
        });
    }

}