package com.example.khadamat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Professional extends AppCompatActivity {

    private EditText phoneNumber,description;
    private EditText categories,salary;
    private Button update;
    private DatabaseReference mDatabase;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        phoneNumber = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        categories = findViewById(R.id.categorie);
        salary = findViewById(R.id.salary);
        update = findViewById(R.id.update);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");



    //        FirebaseDatabase  database = FirebaseDatabase.getInstance();
    //        DatabaseReference mDatabaseRef = database.getReference();
    //
    //        mDatabaseRef.child("Users").child("description").setValue(description);


            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    updateData();

                }
            });

        }

        private void updateData(){

            String phone = phoneNumber.getText().toString();
            String desc = description.getText().toString();
            String categorie = categories.getText().toString();
            String salaire = salary.getText().toString();
            Log.v("aaaaaaaaaaaaa :",phone);
            Toast.makeText(this, "aaaaaaaaa"+phone, Toast.LENGTH_SHORT).show();

            if(phone.isEmpty())
            {
                Toast.makeText(this, "please write your phone number", Toast.LENGTH_SHORT).show();
            }
            else if(desc.isEmpty())
            {
                Toast.makeText(this, "please write your description", Toast.LENGTH_SHORT).show();
            }
        else if(categorie.isEmpty())
        {
            Toast.makeText(this, "please write your preferable categorie", Toast.LENGTH_SHORT).show();
        }
        else if(salaire.isEmpty())
        {
            Toast.makeText(this, "please write your salary", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(this, "test ----------1", Toast.LENGTH_SHORT).show();
            /*loadingBar.setTitle("Update Your Account");
            loadingBar.setMessage("please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();*/
            Log.v("bbbbbbbbphone",phone);
            Toast.makeText(this, "bbbbbb"+phone, Toast.LENGTH_SHORT).show();
            ValidatephoneNumber(phone,desc,categorie,salaire);

//                Query updateQuery = mDatabase.orderByChild("phone").equalTo(phone);
//                updateQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot edtData: dataSnapshot.getChildren()){
//                            edtData.getRef().child("categorie").setValue(categories);
//                            edtData.getRef().child("salary").setValue(salary);
//                        }
//                        Toast.makeText(Professional.this,"Data Edited",Toast.LENGTH_LONG).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(Professional.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
//
//                    }
//                });
//
        }


    }

    private void ValidatephoneNumber( final String phone, final String desc, final String categorie, final String salaire) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.child("Users").child(phone).exists())) {
                    Log.v("ccccccccccc :",phone);
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    //userdataMap.put("phone", phone);
                    userdataMap.put("description", desc);
                    userdataMap.put("categorie", categorie);
                    userdataMap.put("salary", salaire);
                    //we use rootref that from every user data will be inside his phone number
                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Professional.this, "Congratulations, your account has been modify.", Toast.LENGTH_SHORT).show();
                                        //loadingBar.dismiss();
                                        //send user to login activity
                                        //Intent intent = new Intent(Professional.this, categories.class);
                                        //  startActivity(intent);
                                    } else {
                                        Toast.makeText(Professional.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                       // loadingBar.dismiss();
                                    }

                                }
                            });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }}

