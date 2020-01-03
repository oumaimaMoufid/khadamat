package com.example.khadamat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button SignUp;
    Button SignIn;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          SignUp = (Button)findViewById(R.id.SignUp);
          SignIn = (Button)findViewById(R.id.SignIn);


          SignUp.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this,register.class);
              startActivity(intent);
              }
          });

          SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);

            }
           });
    }



}
