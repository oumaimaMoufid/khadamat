package com.example.khadamat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.khadamat.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recycler extends AppCompatActivity {

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
                      Users u = dataSnapshot1.getValue(Users.class);
                      u.setImageUrl(dataSnapshot1.child("image_url").getValue(String.class));
                      list.add(u);
                      Toast.makeText(Recycler.this, "imagel 1 "+u.getImageUrl(), Toast.LENGTH_LONG).show();

                  }
                  adapter = new MyAdapter(Recycler.this,list);
                  recyclerView.setAdapter(adapter);
              }


              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {
                  Toast.makeText(Recycler.this, "opps something is wrong", Toast.LENGTH_SHORT).show();
              }
          });

    }
}
