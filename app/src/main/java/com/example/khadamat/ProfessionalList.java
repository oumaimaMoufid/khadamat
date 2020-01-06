package com.example.khadamat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.khadamat.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfessionalList extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Users> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);



          recyclerView = findViewById(R.id.myRecyclerView);
          recyclerView.setLayoutManager(new LinearLayoutManager(this));
          list = new ArrayList<>();
          reference = FirebaseDatabase.getInstance().getReference().child("Users");
          reference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                  {
                      if (dataSnapshot1.child("categorie").exists()){
                          Users u = dataSnapshot1.getValue(Users.class);
                         // u.setImageUrl(dataSnapshot1.child("image_url").getValue(String.class));

                          if (u.getCategorie().equals("Home Services")){
                              u.setImageUrl(dataSnapshot1.child("image_url").getValue(String.class));

                              list.add(u);
                              Toast.makeText(ProfessionalList.this, "imagel 1 "+u.getImageUrl(), Toast.LENGTH_LONG).show();
                          }else{
                              Toast.makeText(ProfessionalList.this, " the list of professional  is empty", Toast.LENGTH_LONG).show();

                          }
                         // list.add(u);
                         // Toast.makeText(ProfessionalList.this, "imagel 1 "+u.getImageUrl(), Toast.LENGTH_LONG).show();

                      }
                      else{
                          Toast.makeText(ProfessionalList.this, "Category not found ", Toast.LENGTH_LONG).show();
                      }
                  }
                  adapter = new MyAdapter(ProfessionalList.this,list);
                  recyclerView.setAdapter(adapter);
              }


              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Toast.makeText(ProfessionalList.this, "opps something is wrong", Toast.LENGTH_SHORT).show();
              }
          });

    }
}
