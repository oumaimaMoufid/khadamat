package com.example.khadamat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.khadamat.Model.Poste;
import com.example.khadamat.Model.Users;
import com.example.khadamat.prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListPostes extends AppCompatActivity {

    RecyclerView rc2;
    DatabaseReference reference;
    ArrayList<Poste> list;
    PostesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_postes);
        rc2 = findViewById(R.id.rc2);
        rc2.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Postes")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Poste u = dataSnapshot1.getValue(Poste.class);
                            u.setDescription(dataSnapshot1.child("description").getValue(String.class));
                            u.setPhone(dataSnapshot1.child("phone").getValue(String.class));

                            Log.v("hlkjlk", u.getDescription());
                            list.add(u);
                        }
                        adapter = new PostesAdapter(ListPostes.this, list);
                        rc2.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        return;
                    }
                });
    }
}
