package com.android.optimaldistributionrelationalsystem.amal;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Order;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityOrderBinding;
import com.android.optimaldistributionrelationalsystem.sara.DriversActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    ActivityResultLauncher<String> Alancher;
    StorageReference mstorageReference;
    DatabaseReference myRef;
    Order currorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
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


        Alancher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                 binding.imageView2.setImageURI(result);
            }
        });

        binding.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("makan", "elasl eksb");
                Alancher.launch("image/*");
                            }
        });

        onClick();
    }

    private void onClick() {

        String product = binding.product.getText().toString();
        String quantity = binding.quantity.getText().toString();
        String price = binding.price.getText().toString();
        String description = binding.description.getText().toString();

        if (product.isEmpty()) {
            binding.product.setError(getString(R.string.required));

        } else if (quantity.isEmpty()) {
            binding.quantity.setError(getString(R.string.required));

        } else if (price.isEmpty()) {
            binding.price.setError(getString(R.string.required));

        } else {
            Intent intent = new Intent(this, DriversActivity.class);
            startActivity(intent);
        }
    }
}