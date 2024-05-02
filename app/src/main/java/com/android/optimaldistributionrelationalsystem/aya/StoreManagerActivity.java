package com.android.optimaldistributionrelationalsystem.aya;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.android.optimaldistributionrelationalsystem.data.Warehouse;
import com.android.optimaldistributionrelationalsystem.sara.WarehouseMangerActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StoreManagerActivity extends Activity {

    Button submitButton;
    EditText ename, eemail, epassword, emobileNumber, elocation , eproducttype,ecode;
    DatabaseReference myRef;
    Store currstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
Button b1 = (Button)findViewById(R.id.btn_login);
        b1.setOnClickListener(v -> {
            validation();
        });
    }

    private void validation() {
        ename = (EditText) findViewById(R.id.name);
        String name = ename.getText().toString();
        eemail = (EditText) findViewById(R.id.email);
        String email = eemail.getText().toString();
        epassword = (EditText) findViewById(R.id.password);
        String password = epassword.getText().toString();
        emobileNumber = (EditText) findViewById(R.id.phone_number);
        String phone = emobileNumber.getText().toString();
        elocation = (EditText) findViewById(R.id.location);
        String latitude = elocation.getText().toString();
        eproducttype = (EditText) findViewById(R.id.product_type);
        String longitude = eproducttype.getText().toString();
        ecode = (EditText) findViewById(R.id.code_warehouse);
        String address = ecode.getText().toString();

        if (name.isEmpty()) {
            ename.setError(getString(R.string.required));
        } else if (email.isEmpty()) {
            eemail.setError(getString(R.string.required));
        } else if (password.isEmpty()) {
            epassword.setError(getString(R.string.required));
        } else if (phone.isEmpty()) {
            emobileNumber.setError(getString(R.string.required));
        } else if (latitude.isEmpty()) {
            elocation.setError(getString(R.string.required));
        } else if (longitude.isEmpty()) {
            eproducttype.setError(getString(R.string.required));
        } else if (address.isEmpty()) {
            ecode.setError(getString(R.string.required));
        } else {
            Log.v("ana", "the name is");
            Log.v("ana", name);
            System.out.println("the name is");
            System.out.println(name);
            Double lat = Double.parseDouble(latitude);
            Double longt = Double.parseDouble(longitude);
            currstore = new Store(name,lat,longt,email,password,address,phone);
            myRef.child("Root").child("Store").child(name).setValue(currstore);


            Intent intent = new Intent(this, SelectServecActivity.class);
            startActivity(intent);
        }
    }
    }

/*
        Code = (EditText) findViewById(R.id.storeid);
        Password = (EditText) findViewById(R.id.passwordid);
        address = (EditText) findViewById(R.id.address);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        submitButton = (Button) findViewById(R.id.submitt);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if (validate(Code) && validate(address)
                        && validate(Password) && validate(mobileNumber)) {
                    Toast.makeText(getApplicationContext(), "SuccessFully Register", Toast.LENGTH_LONG).show();
                    if (v.getId() == R.id.submitt) {
                        Intent intent = new Intent(StoreManagerActivity.this, SelectServecActivity.class);
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

}
*/