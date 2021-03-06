package com.example.khadamat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class home extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
       setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(home.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(home.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0)//Vetements
                    {
                        String nom= "Vetements";
                        Intent intent = new Intent(home.this, categories.class);
                        Bundle b = new Bundle();
                        b.putString("produit", nom);
                        intent.putExtras(b);
                        startActivity(intent);

                    } else if (finalI == 1)//Accessoires
                    {

                        String nom= "Accessoires";
                        Intent intent = new Intent(home.this, Professional.class);
                        Bundle b = new Bundle();
                        b.putString("produit", nom);
                        intent.putExtras(b);
                        startActivity(intent);

                    } else if (finalI == 2)//Maquillage
                    {

                        String nom= "Maquillage";
                        Intent intent = new Intent(home.this, AjouterPoste.class);
                        Bundle b = new Bundle();
                        b.putString("produit", nom);
                        intent.putExtras(b);
                        startActivity(intent);

                    } else if (finalI == 3)//Produits
                    {
                        String nom= "Produits";
                        Intent intent = new Intent(home.this, ListPostes.class);
                        Bundle b = new Bundle();
                        b.putString("produit", nom);
                        intent.putExtras(b);
                        startActivity(intent);

                    } else if (finalI == 4)//Chaussures
                    {
                        String nom= "Chaussures";
                        Intent intent = new Intent(home.this, profile.class);
                        Bundle b = new Bundle();
                        b.putString("produit", nom);
                        intent.putExtras(b);
                        startActivity(intent);

                    } else if (finalI == 5)//open chat
                    {
                        Intent intent = new Intent(home.this, MainActivity.class);
                        startActivity(intent);

                    }
                }
            });
        }
    }


}
