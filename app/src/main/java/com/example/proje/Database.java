package com.example.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Database extends AppCompatActivity {

    TextView textView;
    String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        textView= (TextView) findViewById(R.id.textView);

        DatabaseHelper db = new DatabaseHelper(this);


        List<Student> students = db.getAllStudents();

        for (Student s: students){
            String log = "" + s.getId() + ",      " + s.getNo() + "\n";
            text = text + log;
        }
        for (Student s: students){
            String log = "" + s.getId() + ",      " + s.getNo() + s.getCourse() + "\n";
            text = text + log;
        }
        textView.setText(text);
    }
}