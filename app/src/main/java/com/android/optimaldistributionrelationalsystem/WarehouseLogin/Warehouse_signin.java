package com.android.optimaldistributionrelationalsystem.WarehouseLogin;

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
import com.android.optimaldistributionrelationalsystem.data.Warehouse;
import com.android.optimaldistributionrelationalsystem.WarehouseManager.WarehouseMangerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Warehouse_signin extends AppCompatActivity {
    private String dbemail="111",dbpassword="111";
    private String uemail="0",upassword="0";
    private EditText email , password;
    private Button signup;
    private Button login;
    DatabaseReference myRef;
    ArrayList<Warehouse> warearray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_signin);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        warearray = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        myRef.child("Root").child("Warehouse").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()) {
                        //Toast.makeText(getApplicationContext(), "sucess reg", Toast.LENGTH_LONG).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        warearray.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Warehouse thisware = new Warehouse();
                            thisware = ds.getValue(Warehouse.class);
                            warearray.add(thisware);

                            //  dbemail = String.valueOf(dataSnapshot.child("name").getValue());
                            //  dbpassword = String.valueOf(dataSnapshot.child("password").getValue());
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "user no exist", Toast.LENGTH_LONG).show();
                    }

                }

                else {
                  //  Toast.makeText(getApplicationContext(), "failed read", Toast.LENGTH_LONG).show();
                }

            }
        });

        initial();
    }
    private void initial() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(Warehouse_signin.this, LoginWareHouseActivity.class);
                startActivity(intent);
                finish();


            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uemail=email.getText().toString();
                upassword=password.getText().toString();


                System.out.println(warearray);

                Log.v("entered email",uemail );
                Log.v("entered password",upassword );
                Log.v("db email",dbemail );
                Log.v("db password",dbpassword );
                for (int i=0;i<warearray.size();i++) {
                    Log.v("count i",String.valueOf(i) );
                    if (uemail.equals(warearray.get(i).getName()) && upassword.equals(warearray.get(i).getPassword())) {


                        Intent intent = new Intent();
                        intent.setClass(Warehouse_signin.this, WarehouseMangerActivity.class);
                        intent.putExtra("EXTRA_SESSION_ID",warearray.get(i).getName());
                        startActivity(intent);

                        break;
                    } else if (i==(warearray.size()-1)) {
                        Toast.makeText(getApplicationContext(), "failed login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(Warehouse_signin.this, Warehouse_signin.class);
                        startActivity(intent);

                    }
                }
                finish();

            }
        });

    }



}

