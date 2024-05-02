package com.android.optimaldistributionrelationalsystem.DriverSignup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Driver;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.android.optimaldistributionrelationalsystem.data.Warehouse;
import com.android.optimaldistributionrelationalsystem.Order.DemoOrderActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DriverActivity extends AppCompatActivity {
    private String dbemail="111",dbpassword="111";
    private String uemail="0",upassword="0";
    private EditText email , password;
    private Button signup;
    private Button login;
    DatabaseReference myRef;
    ArrayList<Driver> driverarray;
    ArrayList<Store> storearray;
    ArrayList<Warehouse> warearray;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eman);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        driverarray = new ArrayList<>();
        storearray = new ArrayList<>();
        warearray = new ArrayList<>();

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        myRef.child("Root").child("Store").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Store s1 = new Store();
                    s1=ds.getValue(Store.class);
                    s1.slocation=new Location(s1.name);
                    s1.slocation.setLongitude(s1.getLongt());
                    s1.slocation.setLatitude(s1.getLat());
                    Log.v("logger" , s1.getEmail());
                    storearray.add(s1);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

            }
        });




        myRef.child("Root").child("Warehouse").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Warehouse w = new Warehouse();
                    w=ds.getValue(Warehouse.class);
                    w.whlocation=new Location(w.name);
                    w.whlocation.setLatitude(w.getLat());
                    w.whlocation.setLongitude(w.getLongt());
                    warearray.add(w);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

            }
        });




        myRef.child("Root").child("Driver").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()) {
                        //Toast.makeText(getApplicationContext(), "sucess reg", Toast.LENGTH_LONG).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        driverarray.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Driver thisdrive = new Driver();
                            thisdrive = ds.getValue(Driver.class);
                            driverarray.add(thisdrive);

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

        initial();
    }

    private void initial() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(DriverActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uemail=email.getText().toString();
                upassword=password.getText().toString();


                System.out.println(driverarray);

                Log.v("entered email",uemail );
                Log.v("entered password",upassword );
                Log.v("db email",dbemail );
                Log.v("db password",dbpassword );
                for (int i = 0; i< driverarray.size(); i++) {
                    Log.v("count i",String.valueOf(i) );

                    if (uemail.equals(driverarray.get(i).getName()) && upassword.equals(driverarray.get(i).getPassword())) {


                        Intent intent = new Intent();
                        intent.setClass(DriverActivity.this, DemoOrderActivity.class);
                        intent.putExtra("EXTRA_SESSION_ID", driverarray.get(i).getName());
                  //      arstore as = new arstore(storearray);
                    //    arware aw = new arware(warearray);
                      //  intent.putParcelableArrayListExtra("KEY_NAME_ST", storearray);
                      //  intent.putParcelableArrayListExtra("KEY_NAME_WH", warearray);
                        //  intent.putExtra("KEY_NAME_WH", aw);
                        startActivity(intent);

                        break;
                    } else if (i==(driverarray.size()-1)) {
                        Toast.makeText(getApplicationContext(), "failed login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(DriverActivity.this, DriverActivity.class);
                        startActivity(intent);

                    }
                }
                finish();

            }
        });


    }

}