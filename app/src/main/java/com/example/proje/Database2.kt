package com.example.proje

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.proje.databinding.ActivityDatabase2Binding
import com.example.proje.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

class Database2 : AppCompatActivity() {
    lateinit var binding: ActivityDatabase2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDatabase2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var database = FirebaseDatabase.getInstance().reference
        var getdata = object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for (i in snapshot.children){
                    var sno = i.children

                    sb.append(" ${i.key} \n ${i.value} \n  ")
                }
                binding.text2.setText(sb)
            }

        }
        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)
    }
}