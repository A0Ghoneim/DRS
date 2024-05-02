package com.android.optimaldistributionrelationalsystem.amal;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Product;
import com.android.optimaldistributionrelationalsystem.data.Warehouse;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityLoginBinding;
import com.android.optimaldistributionrelationalsystem.sara.WarehouseMangerActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LoginWareHouseActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    Warehouse currwarehouse;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.v("ana", "the name is");
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


        onClick();
    }

    private void onClick() {

        binding.btnLogin.setOnClickListener(v -> {
            validation();
        });
    }

    private void validation() {

        String name = binding.name.getText().toString();
        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();
        String phone = binding.phoneNumber.getText().toString();
        String latitude = binding.location.getText().toString();
        String longitude = binding.productType.getText().toString();
        String address = binding.codeWarehouse.getText().toString();

        if (name.isEmpty()) {
            binding.name.setError(getString(R.string.required));
        } else if (email.isEmpty()) {
            binding.email.setError(getString(R.string.required));
        } else if (password.isEmpty()) {
            binding.password.setError(getString(R.string.required));
        } else if (phone.isEmpty()) {
            binding.phoneNumber.setError(getString(R.string.required));
        } else if (latitude.isEmpty()) {
            binding.location.setError(getString(R.string.required));
        } else if (longitude.isEmpty()) {
            binding.productType.setError(getString(R.string.required));
        } else if (address.isEmpty()) {
            binding.codeWarehouse.setError(getString(R.string.required));
        } else {
            Log.v("ana", "the name is");
            Log.v("ana", name);
            System.out.println("the name is");
            System.out.println(name);
           Double lat = Double.parseDouble(latitude);
           Double longt = Double.parseDouble(longitude);
            currwarehouse = new Warehouse(name,lat,longt,email,password,address,phone);
            myRef.child("Root").child("Warehouse").child(name).setValue(currwarehouse);
                myRef.child("Root").child("Warehouse_products").child(name).setValue("");
            Intent intent = new Intent(this, WarehouseMangerActivity.class);
            startActivity(intent);
        }
    }
}