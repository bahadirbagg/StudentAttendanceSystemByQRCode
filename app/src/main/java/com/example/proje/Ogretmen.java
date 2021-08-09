package com.example.proje;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.util.Calendar;

public class Ogretmen extends AppCompatActivity implements View.OnClickListener{
    public TextView txt_alinan;
    Button scanBtn;
    public Button Seeinfo;
    public Button Seeinfo2;
    String data2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Calendar calendar = Calendar.getInstance();
    String currentdate = DateFormat.getDateInstance().format((calendar.getTime()));
    public String sText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen);

        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);



        txt_alinan=findViewById(R.id.alinan_mesaj);
        Intent al =getIntent();
        String alinanMesaj = al.getStringExtra("mesajım");
        txt_alinan.setText("                    Welcome                              Prof.Dr  " + alinanMesaj);


        Seeinfo2=findViewById(R.id.seeinfo2);
        Seeinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent see2 = new Intent(Ogretmen.this,Database2.class);
                startActivity(see2);

            }
        });

        Seeinfo=findViewById(R.id.seeinfo);
        Seeinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent see = new Intent(Ogretmen.this,Database.class);
                startActivity(see);

            }
        });

    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode(){

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if(result.getContents() != null){
                DatabaseHelper db = new DatabaseHelper(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Reslts ");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                String sText = String.valueOf(resultCode);
                String data2 = String.valueOf(result.getContents());
                db.addStudent(new Student(data2,null));

                sText3 = data2 + "   Date=" + currentdate;

                String ilk = data2.substring(15, 21);
                String ilk2 = data2.substring(21, 38);
                sText3 = ilk + "   Date=" + currentdate;
                String sText4 = ilk2 + "   Date=" + currentdate;

                DatabaseReference myRef = database.getReference("Student");
                myRef.child(sText3).child(sText4).setValue("+");

            }
            else{
                Toast.makeText(this,"No Results", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode,resultCode, data);

        }
    }
}