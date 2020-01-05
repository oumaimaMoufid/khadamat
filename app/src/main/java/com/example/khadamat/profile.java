package com.example.khadamat;

import android.content.Intent;
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

public class profile extends AppCompatActivity {

    private TextView nameText, lastnameText;
    private TextView emailText, telText, adrText, cityText;

    private ImageView userImageView, mailImageView, phoneImageView, adrImageView, cityImageView;

    //private String phone, password;

    private FirebaseDatabase database;
    private DatabaseReference userRef;
//    private FirebaseAuth firebaseAuth;
//    private FirebaseDatabase firebaseDatabase;

    private static final String USERS = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

      //  Intent intent = getIntent();
      //  phone = intent.getStringExtra("phone");

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = rootRef.child(USERS);
        //Log.v("Users", userRef.getKey());

        nameText = findViewById(R.id.name);
        lastnameText = findViewById(R.id.lastname);
        emailText = findViewById(R.id.email);
        telText = findViewById(R.id.tel);
        adrText = findViewById(R.id.adr);
        cityText = findViewById(R.id.city);

        userImageView = findViewById(R.id.image_view);
        mailImageView = findViewById(R.id.emailImage);
        phoneImageView = findViewById(R.id.phoneImage);
        adrImageView = findViewById(R.id.adrImage);
        cityImageView = findViewById(R.id.cityImage);

//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();

//        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

//        database = FirebaseDatabase.getInstance();
//        userRef = database.getReference(USERS);
        Toast.makeText(this, "11111111111111111", Toast.LENGTH_SHORT).show();

                        Log.v( "4444444444444",Prevalent.currentOnlineUser.getCity() );
                        Toast.makeText(profile.this, "333333333333333333", Toast.LENGTH_SHORT).show();
                        //Users users = ds.child("Users").child(Prevalent.currentOnlineUser.getPhone()).getValue(Users.class);
                        nameText.setText(Prevalent.currentOnlineUser.getName());
                        //lastnameText.setText(Prevalent.currentOnlineUser.getLastname());
                        emailText.setText(Prevalent.currentOnlineUser.getMail());
                        telText.setText(Prevalent.currentOnlineUser.getPhone());
                        adrText.setText(Prevalent.currentOnlineUser.getAddress());
                        cityText.setText(Prevalent.currentOnlineUser.getCity());
//                Users users=dataSnapshot.getValue(Users.class);
//                nameText.setText(users.getName());
//                lastnameText.setText(users.getLastname());
//                emailText.setText(users.getMail());
//                telText.setText(users.getPhone());
//                adrText.setText(users.getAddress());
//                cityText.setText(users.getCity());


    }
}