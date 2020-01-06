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

public class login extends AppCompatActivity {

    private Button LoginButton;
    private EditText InputPhoneNum,InputPassword;
    private ProgressDialog loadingBar;
    //final ==>no
    private String parentDbName = "Users";

    private TextView AdminLink,NotAdminLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.sign);
        InputPhoneNum = (EditText) findViewById(R.id.tel);
        InputPassword = (EditText) findViewById(R.id.password);
        AdminLink = findViewById(R.id.admin);
        NotAdminLink = findViewById(R.id.notadmin);

        loadingBar =new ProgressDialog(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName="Admins";

            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Sign Up");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName="Users";
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(this, categories.class));
        }
    }

    private void LoginUser(){

        String phone = InputPhoneNum.getText().toString();
        String password=InputPassword.getText().toString();


         if(phone.isEmpty())
        {
            Toast.makeText(this, "please write your mail", Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this, "please write your password", Toast.LENGTH_SHORT).show();
        }
        else
         {
             loadingBar.setTitle("Login Account");
             loadingBar.setMessage("please wait, while we are checking the credentials.");
             loadingBar.setCanceledOnTouchOutside(false);
             loadingBar.show();

             AllowAccessToAccount(phone, password);
         }

    }

    private void AllowAccessToAccount(final String phone, final String password){

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersDate = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);//recupere les donnéer saisir

                    if (usersDate.getPhone().equals(phone))
                    {
                        if (usersDate.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Admins")){
                                Toast.makeText(login.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(login.this,AdminHome.class);
                                Prevalent.currentOnlineUser = usersDate;
                                // intent.putExtra("phone", usersDate.getPhone());
                                Log.v("phone: ",usersDate.getPhone());
                                startActivity(intent);
                            }
                            else if(parentDbName.equals("Users")){
                                Toast.makeText(login.this, "Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(login.this,categories.class);
                                Prevalent.currentOnlineUser = usersDate;
                                // intent.putExtra("phone", usersDate.getPhone());
                                Log.v("phone: ",usersDate.getPhone());
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(login.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(login.this, "Account with this" + phone + "do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(login.this, "you need to create a new Account.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }




}
