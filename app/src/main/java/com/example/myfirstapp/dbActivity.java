package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class dbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        TextView tv = findViewById(R.id.db_out_textView);
        dbAssistant assistant = new dbAssistant(this);
        assistant.open();
        tv.setText(assistant.getAllAsText());
    }
}