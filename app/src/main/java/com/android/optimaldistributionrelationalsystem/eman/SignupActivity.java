package com.android.optimaldistributionrelationalsystem.eman;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Driver;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class SignupActivity extends AppCompatActivity {
    EditText name, address, email, password, code;
    String name1,addr,em,pass,co;
    private Button login;
    private  Button signup;
    ActivityResultLauncher<String> Alancher;
    DatabaseReference myRef;
    StorageReference mstorageReference;
    Driver currdriver;
    ImageView im2;
    Uri imuri;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        mstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        name=(EditText) findViewById(R.id.signup_name);
        address = (EditText) findViewById(R.id.signup_address);
        email = (EditText) findViewById(R.id.signup_email);
        password= (EditText) findViewById(R.id.signup_password);
        code= (EditText) findViewById(R.id.signup_code_car);
        login= (Button) findViewById(R.id.login_login);



        im2 = (ImageView)findViewById(R.id.special_image);
        Alancher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imuri=result;
                im2.setImageURI(result);
            }
        });



        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("makan", "elgdeed gdeeed");
                Alancher.launch("image/*");
            }
        });





        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(SignupActivity.this, EmanActivity.class);
                startActivity(intent);
                finish();


            }
        });


        signup= (Button) findViewById(R.id.signup2_button);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name1=name.getText().toString();
                addr=address.getText().toString();
                em=email.getText().toString();
                pass=password.getText().toString();
                co=code.getText().toString();

                if (validate(name)&&validate(address)
                        &&validate(email)&&validate(password)&&validate(code)&&pssw(password)&&pssw(code))
                {

                    StorageReference storageReference = mstorageReference.child("images/drivers/"+name1+"/"+name1+".jpg");
                    if (imuri!=null) {
                        storageReference.putFile(imuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //    Toast.makeText(getApplicationContext(), "upload sucess ", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                             //   Toast.makeText(getApplicationContext(), "upload faliure ", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    currdriver = new Driver(name1,em,pass,addr,co);
                    myRef.child("Root").child("Driver").child(name1).setValue(currdriver);

                    Toast.makeText(getApplicationContext(),"SuccessFully Register",Toast.LENGTH_LONG).show();
                    if(v.getId()==R.id.signup2_button){
                        Intent intent= new Intent(SignupActivity.this,EmanActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }



    private boolean validate(EditText editText) {
        if (editText.getText().toString().trim().length() < 1) {
            editText.setError("Please Fill This.!!!");
            editText.requestFocus();
            return false;
        }
        return true;
    }
    private boolean pssw(EditText editText) {
        if (editText.getText().toString().trim().length() < 6) {
            editText.setError("less than 6");
            editText.requestFocus();
            return false;
        }
        return true;
    }

}


