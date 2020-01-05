package com.example.khadamat;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.khadamat.Model.Users;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthUserCollisionException;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    ////upload image in firebase
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private ImageView  mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    //////////////
    private Button registerButton;
    private EditText  InputName,InputLastName,InputMail,InputPassword,InputPhone,InputAdr,InputCity;
    private ImageView ImageURL;
    private RadioButton genderMale,genderFemale;
    private ProgressDialog loadingBar;
    //  private FirebaseAuth mAuth,mAuthListener;

    DatabaseReference rootRef,demoRef;

    //users user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ////select + uploadpic

        mButtonChooseImage = findViewById(R.id.btn_choose_image);
        mButtonUpload = findViewById(R.id.btn_upload);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);
      //Users == uploads
        mStorageRef = FirebaseStorage.getInstance().getReference("Users");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");


        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });


          registerButton = (Button) findViewById(R.id.register);

          InputName = findViewById(R.id.userName);
          InputLastName = (EditText) findViewById(R.id.userLastName);
          InputMail = (EditText) findViewById(R.id.usermail);
          InputPassword = (EditText) findViewById(R.id.password);
          InputPhone = (EditText) findViewById(R.id.tel);
          InputAdr = (EditText) findViewById(R.id.adresse);
          InputCity = (EditText) findViewById(R.id.city);
          //ImageURL=(ImageView)=findViewById(R.id)
          loadingBar = new ProgressDialog(this);

          //user = new users();

   

          registerButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  createAccount();


              }
          });
      }


// select /upload
    private void openFileChooser() {
        Intent intent = new Intent();
          intent.setType("image/*");
          intent.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    //select pic
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);
            //Picasso.with(this).load(mImageUri).into(mImageView);
            // Picasso.get().load(model.getImage_url()).into(holder.imageView);

            //mImageView.setImageURI(mImageUri);
        }
    }
    //upload pic
    private  String  getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(final String phone) {
        if (mImageUri != null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+
                    "." + getFileExtension(mImageUri));

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //progressDialog.dismiss();
                            //statusImageCallback.onSuccess("images/"+user.getUid()+"/" + UUID.randomUUID().toString());
                            Log.v("getDownloadUrl",  fileReference.getDownloadUrl().toString());

                            //final StorageReference refs = ref.child("images/mountains.jpg");
                            UploadTask uploadTask = fileReference.putFile(mImageUri);

                            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if (!task.isSuccessful()) {
                                        throw task.getException();
                                    }

                                    // Continue with the task to get the download URL
                                    Log.v("getDownloadUrl",  fileReference.getDownloadUrl().toString());
                                    return fileReference.getDownloadUrl();

                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()) {
                                        Uri downloadUri = task.getResult();
                                        Toast.makeText(register.this, "uploas successful", Toast.LENGTH_SHORT).show();

                                        HashMap<String, Object> users = new HashMap<>();
                                        users.put("image_url", downloadUri.toString());
                                        mDatabaseRef.child(phone).updateChildren(users);

                                    }
                                }
                            });


                            //Toast.makeText(AddProduct.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });




        }else
        {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


    private  void createAccount(){

        String name=InputName.getText().toString();
        String lastname=InputLastName.getText().toString();
        String mail=InputMail.getText().toString();
        String password=InputPassword.getText().toString();
        String phone=InputPhone.getText().toString();
        String address=InputAdr.getText().toString();
        String city=InputCity.getText().toString();
      //  String imageUrl=ImageURL.getText().toString();

        if(name.isEmpty())
        {
            Toast.makeText(this, "please write your name", Toast.LENGTH_SHORT).show();
        }
        else if(lastname.isEmpty())
        {
            Toast.makeText(this, "please write your last name", Toast.LENGTH_SHORT).show();
        }
        else if(mail.isEmpty())
        {
            Toast.makeText(this, "please write your mail", Toast.LENGTH_SHORT).show();
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this, "please write your password", Toast.LENGTH_SHORT).show();
        }
        else if(phone.isEmpty())
        {
            Toast.makeText(this, "please write your phone number", Toast.LENGTH_SHORT).show();
        }
        else if(address.isEmpty())
        {
            Toast.makeText(this, "please write your address", Toast.LENGTH_SHORT).show();
        }
        else if(city.isEmpty())
        {
            Toast.makeText(this, "please write your city", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            ValidatephoneNumber(name,lastname,mail,password,phone,address,city);



        }


    }






    private void ValidatephoneNumber(final String name, final String lastname, final String mail, final String password, final String phone, final String address, final String city)
    {



        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();


        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Toast.makeText(register.this, "2", Toast.LENGTH_SHORT).show();

                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    Toast.makeText(register.this, "3", Toast.LENGTH_SHORT).show();
                    HashMap<String,Object> userdataMap = new HashMap<>();
                    userdataMap.put("name",name);
                    userdataMap.put("last name",lastname);
                    userdataMap.put("mail",mail);
                    userdataMap.put("password",password);
                    userdataMap.put("phone",phone);
                    userdataMap.put("address",address);
                    userdataMap.put("city",city);
                    //we use rootref that from every user data will be inside his phone number
                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    Toast.makeText(register.this, "4", Toast.LENGTH_SHORT).show();

                                    if (task.isSuccessful())
                                    {
                                        uploadFile(phone);
                                        Toast.makeText(register.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        //send user to login activity
                                        Intent intent = new Intent(register.this,login.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(register.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }

                                }
                            });
                }
                else
                {
                    Toast.makeText(register.this, "This"+phone+"already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(register.this, "Please try using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(register.this,home.class);
                    startActivity(intent);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
