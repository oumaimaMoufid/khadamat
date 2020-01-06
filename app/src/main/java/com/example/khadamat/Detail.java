package com.example.khadamat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khadamat.Model.Users;
import com.example.khadamat.prevalent.Prevalent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {

    private TextView nameText, professionalText;
    private TextView emailText, telText, adrText, cityText;

    private ImageView userImageView, mailImageView, phoneImageView, adrImageView, cityImageView;

    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String userPhone;
//    private FirebaseAuth firebaseAuth;
//    private FirebaseDatabase firebaseDatabase;

    private static final String USERS = "Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
//**************************************************************************************************
        userPhone = getIntent().getStringExtra("num");


      //  getIntent().getStringExtra("pid");
        Log.v("num",userPhone);
//**************************************************************************************************
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS+"/"+userPhone);
        //Log.v("Users", userRef.getKey());

        nameText = findViewById(R.id.name);
        professionalText = findViewById(R.id.profDescription);
        emailText = findViewById(R.id.email);
        telText = findViewById(R.id.tel);
        adrText = findViewById(R.id.adr);
        cityText = findViewById(R.id.city);

        userImageView = findViewById(R.id.imageView);

        mailImageView = findViewById(R.id.emailImage);
        phoneImageView = findViewById(R.id.phoneImage);
        adrImageView = findViewById(R.id.adrImage);
        cityImageView = findViewById(R.id.cityImage);

//



        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Users u = dataSnapshot.getValue(Users.class);
                    nameText.setText(u.getName());
                    emailText.setText(u.getMail());
                    telText.setText(u.getPhone());
                    adrText.setText(u.getAddress());
                    cityText.setText(u.getCity());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        })
       ;

    }
}