package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class Ogretmen2 extends AppCompatActivity {

    public Button btn_Ogr;
    public EditText edt_mesaj;

    String mesaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen2);

        btn_Ogr=findViewById(R.id.btn_ogr);
        edt_mesaj=findViewById(R.id.edt_mesaj);
        btn_Ogr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mesaj=edt_mesaj.getText().toString();
                Intent msj= new Intent(Ogretmen2.this,Ogretmen.class);
                msj.putExtra("mesajım",mesaj);
                startActivity(msj);



            }
        });
    }
}