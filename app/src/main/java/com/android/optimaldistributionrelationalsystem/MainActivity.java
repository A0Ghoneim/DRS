package com.android.optimaldistributionrelationalsystem;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.WarehouseLogin.Warehouse_signin;
import com.android.optimaldistributionrelationalsystem.StoreSignin.Store_signin;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityMainBinding;
import com.android.optimaldistributionrelationalsystem.DriverSignup.DriverActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    DatabaseReference myRef;
    @SuppressLint("SuspiciousIndentation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();


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


        // temporary code



        // temp
        onClick();
    }

    private void onClick() {

        binding.btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // myRef.child("store").child("id").child("halalmode").setValue("nice");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DriverActivity.class);
                startActivity(intent);
            }
        });

        binding.btnStoreManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //       myRef.child("store").child("id").child("workonmobile").setValue("yes");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Store_signin.class);
                startActivity(intent);
            }
        });

        binding.btnWarehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Warehouse_signin.class);
                startActivity(intent);
            }
        });
    }
}