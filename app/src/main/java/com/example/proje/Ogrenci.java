package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ogrenci extends AppCompatActivity {

    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Calendar calendar = Calendar.getInstance();
    String currentdate = DateFormat.getDateInstance().format((calendar.getTime()));



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci);

        etInput = findViewById(R.id.et_input);
        btGenerate = findViewById(R.id.bt_generate);
        ivOutput = findViewById(R.id.iv_output);

        Intent al=getIntent();
        String sText = etInput.getText().toString().trim();
        String alinanNumara = al.getStringExtra("numaram");
        String sText2 = sText + alinanNumara;
        DatabaseHelper db = new DatabaseHelper(this);




        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sText = etInput.getText().toString().trim();


                String sText2 = "   Course Name="+sText +"   No=" + alinanNumara;
                String sText3 =sText + "   Date=" + currentdate;
                String sText4 = "   No=" + alinanNumara + "   Date=" + currentdate;

                db.addStudent(new Student(alinanNumara,sText2));
                MultiFormatWriter writer = new MultiFormatWriter();

                DatabaseReference myRef = database.getReference("AllStudent");
                myRef.child(sText3).child(sText4).setValue("?");

                try {
                    BitMatrix matrix = writer.encode(sText2, BarcodeFormat.QR_CODE
                            ,350,350);
                    BarcodeEncoder encoder = new BarcodeEncoder();

                    Bitmap bitmap = encoder.createBitmap(matrix);

                    ivOutput.setImageBitmap(bitmap);

                    InputMethodManager menager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );

                    menager.hideSoftInputFromWindow(etInput.getApplicationWindowToken()
                            ,0);
                }
                catch (WriterException e){
                    e.printStackTrace();

                }

            }
        });
    }
}