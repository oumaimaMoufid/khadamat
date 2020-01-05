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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.khadamat.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.List;

public class Professional extends AppCompatActivity {

    private EditText phoneNumber, description;
    private EditText categories, salary;
    private Button update;
    private DatabaseReference mDatabase;
   // private String phone;

    private Spinner spinner1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);


        addListenerOnSpinnerItemSelection();


        //phoneNumber = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        //categories = findViewById(R.id.categorie);
        salary = findViewById(R.id.salary);
        update = findViewById(R.id.update);

        spinner1 = (Spinner) findViewById(R.id.spinner1);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateData();
//                Toast.makeText(Professional.this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();

            }
        });


//    private void initData() {
//        listDataHeader = new ArrayList<>();
//        listHash = new HashMap<>();
//        listDataHeader.add("Categories");
//
//        List<String> cat = new ArrayList<>();
//        cat.add("Home Services");
//        cat.add("Company Services ");
//        cat.add("Hotel Services");
//
//        listHash.put(listDataHeader.get(0),cat);

//        ArrayList<String> numbers = new ArrayList<String>();
//        int size = listHash.getAdapter().getCount();
//        for(int i = 0; i < size; i++)
//        {
//            numbers.add((Integer) myList.getAdapter().getItem(i));
//        }

    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

     private void updateData(){

           // String phone = phoneNumber.getText().toString();
            String desc = description.getText().toString();
            //String categorie = categories.getText().toString();
            String salaire = salary.getText().toString();

//            Log.v("aaaaaaaaaaaaa :",phone);
//            Toast.makeText(this, "aaaaaaaaa"+phone, Toast.LENGTH_SHORT).show();
//
//            if(phone.isEmpty())
//            {
//                Toast.makeText(this, "please write your phone number", Toast.LENGTH_SHORT).show();
//            }
//            else
            if(desc.isEmpty())
            {
                Toast.makeText(this, "please write your description", Toast.LENGTH_SHORT).show();
            }
//            else if(categorie.isEmpty())
//            {
//                Toast.makeText(this, "please write your preferable categorie", Toast.LENGTH_SHORT).show();
//            }
            else if(salaire.isEmpty())
            {
                Toast.makeText(this, "please write your salary", Toast.LENGTH_SHORT).show();
            }

            else
            {
            ValidatephoneNumber(desc,salaire);

        }

    }

            private void ValidatephoneNumber(final String desc, final String salaire) {
               //declare the root to firebase
                final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();


                RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.child("Users").child(Prevalent.currentOnlineUser.getPhone()).exists())) {
//                            Log.v("ccccccccccc :",phone);
                            HashMap<String, Object> userdataMap = new HashMap<>();
                            //userdataMap.put("phone", phone);
                            userdataMap.put("description", desc);
                            userdataMap.put("categorie",String.valueOf(spinner1.getSelectedItem()) );
                            userdataMap.put("salary", salaire);

                            //we use rootref that from every user data will be inside his phone number
                            RootRef.child("Users").child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userdataMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(Professional.this, "Congratulations, your account has been modify.", Toast.LENGTH_SHORT).show();
                                                //loadingBar.dismiss();
                                                //send user to login activity
                                                Intent intent = new Intent(Professional.this, categories.class);
                                                  startActivity(intent);
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

