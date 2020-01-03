package com.example.khadamat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Professional extends AppCompatActivity {

    private EditText phone,name,description,categories,salary;
    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        phone = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        categories = findViewById(R.id.categorie);
        salary = findViewById(R.id.salary);
        send = findViewById(R.id.sendToAdmin);

    }
}
