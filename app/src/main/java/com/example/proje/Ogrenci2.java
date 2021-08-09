package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ogrenci2 extends AppCompatActivity {

    public Button btn_Ogrenci;
    public EditText numara;

    String no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci2);

        btn_Ogrenci=findViewById(R.id.btn_ogrenci);
        numara=findViewById(R.id.numara);
        btn_Ogrenci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no=numara.getText().toString();
                Intent NO = new Intent(Ogrenci2.this,Ogrenci.class);
                NO.putExtra("numaram",no);
                startActivity(NO);
            }
        });
    }
}