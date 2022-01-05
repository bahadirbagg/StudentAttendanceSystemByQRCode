package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button btn_Ogr;
    public Button btn_Ogrenci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Ogrenci=findViewById(R.id.btn_ogrenci);
        btn_Ogrenci.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ogrenci = new Intent(MainActivity.this, Ogrenci2.class);
                startActivity(ogrenci);
            }
        });

        btn_Ogr=findViewById(R.id.btn_ogr);
        btn_Ogr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ogretmen= new Intent(MainActivity.this, Ogretmen2.class);
                startActivity(ogretmen);

            }
        });
    }

}