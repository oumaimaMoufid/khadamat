package com.example.khadamat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class categories extends AppCompatActivity {

    private LinearLayout click, click1;
    private TextView professional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        click = findViewById(R.id.linear1);
        click1 = findViewById(R.id.linear2);
        professional = findViewById(R.id.profesId);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this, ProfessionalList.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.addPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(categories.this, AjouterPoste.class));
            }
        });

        findViewById(R.id.showPostes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(categories.this, ListPostes.class));
            }
        });


        professional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(categories.this,Professional.class);
                startActivity(intent);
            }
        });

}}
