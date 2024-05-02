package com.android.optimaldistributionrelationalsystem.aya;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class Store_signin extends AppCompatActivity {
    private String dbemail="111",dbpassword="111";
    private String uemail="0",upassword="0";
    private EditText email , password;
    private Button signup;
    private Button login;
    DatabaseReference myRef;
    Store currstore;
    ArrayList<Store> storearray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_signin);

        currstore = new Store();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);


        storearray = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        myRef.child("Root").child("Store").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()) {
                        //Toast.makeText(getApplicationContext(), "sucess reg", Toast.LENGTH_LONG).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        storearray.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Store thisstore = new Store();
                            thisstore = ds.getValue(Store.class);
                            Log.v("storefinderr",thisstore.getAddress());
                            storearray.add(thisstore);

                            //  dbemail = String.valueOf(dataSnapshot.child("name").getValue());
                            //  dbpassword = String.valueOf(dataSnapshot.child("password").getValue());
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "user no exist", Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    Toast.makeText(getApplicationContext(), "failed read", Toast.LENGTH_LONG).show();
                }

            }
        });


        // String a =String.valueOf(myRef.child("Store").child(uemail).child("password"));
      //  Log.v("db test",a );



        initial();
    }
    private void initial() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Store_signin.this, StoreManagerActivity.class);
                startActivity(intent);
                finish();
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uemail=email.getText().toString();
                upassword=password.getText().toString();


                System.out.println(storearray);

                Log.v("entered email",uemail );
                Log.v("entered password",upassword );
                Log.v("db email",dbemail );
                Log.v("db password",dbpassword );
                for (int i=0;i<storearray.size();i++) {
                    Log.v("count i",String.valueOf(i) );
                    if (uemail.equals(storearray.get(i).getName()) && upassword.equals(storearray.get(i).getPassword())) {


                        Intent intent = new Intent();
                        intent.setClass(Store_signin.this, SelectServecActivity.class);
                        intent.putExtra("EXTRA_SESSION_ID",storearray.get(i).getName());
                        Log.v("storefinderr",storearray.get(i).getAddress());
                        intent.putExtra("KEY_NAME", (Serializable) storearray.get(i));
                        startActivity(intent);

                            break;
                    } else if (i==(storearray.size()-1)) {
                        Toast.makeText(getApplicationContext(), "failed login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(Store_signin.this, Store_signin.class);
                        startActivity(intent);

                    }
                }
                finish();

            }
        });

    }



}

