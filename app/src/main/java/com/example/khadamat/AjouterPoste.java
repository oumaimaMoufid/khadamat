package com.example.khadamat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khadamat.Model.Poste;
import com.example.khadamat.prevalent.Prevalent;
import com.google.firebase.database.FirebaseDatabase;

public class AjouterPoste extends AppCompatActivity {

    private EditText description;
    private String descriptionText;

    public AjouterPoste() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_poste);

        description = findViewById(R.id.description);

        findViewById(R.id.ajouterPoste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()){
                    Poste poste = new Poste();
                    poste.setDescription(descriptionText);
                    poste.setPhone(Prevalent.currentOnlineUser.getPhone());
                    FirebaseDatabase.getInstance().getReference().child("Postes")
                            .push().setValue(poste);

                    Intent intent = new Intent(AjouterPoste.this, home.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        descriptionText = description.getText().toString();
        if (TextUtils.isEmpty(descriptionText)) {
            description.setError("Required.");
            valid = false;
        } else {
            description.setError(null);
        }

        return valid;


    }
}
