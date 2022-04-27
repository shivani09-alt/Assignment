package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    public static String BASE_URL;
    ArrayList<Model> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recylerview);
        progressBar=findViewById(R.id.progressBar);
        error=findViewById(R.id.error);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(getIntent().hasExtra("url")) {
            BASE_URL = getIntent().getStringExtra("url");
            new Response().execute();
        }


    }
    public class Response extends AsyncTask{
        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
            error.setVisibility(View.GONE);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                String stationId,stationName,logo;

                URL url = new URL(BASE_URL);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

                for (int i=0;i< doc.getElementsByTagName("Item").getLength()-1;i++){
                    NodeList nlList = doc.getElementsByTagName("Item").item(i).getChildNodes();
                    Node nValue = (Node) nlList.item(0);
                    if(nValue != null) {

                        stationId = doc.getElementsByTagName("StationId").item(i).getTextContent();
                        logo = doc.getElementsByTagName("Logo").item(i).getTextContent();
                        stationName = doc.getElementsByTagName("StationName").item(i).getTextContent();
                        InputStream inputStream = new URL(logo).openStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        arrayList.add(new Model(stationId,stationName,bitmap));
                    }


                }
            }
            catch (Exception ex)
            {
                runOnUiThread(() -> {
                    error.setVisibility(View.VISIBLE);
                });

                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            recyclerView.setAdapter(new Adapter(getApplicationContext(),arrayList));
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}
