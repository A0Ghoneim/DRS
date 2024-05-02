package com.android.optimaldistributionrelationalsystem.Order;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Driver;
import com.android.optimaldistributionrelationalsystem.data.Driver_track;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.android.optimaldistributionrelationalsystem.data.Warehouse;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityDemorderBinding;
import com.android.optimaldistributionrelationalsystem.DriverSignup.MapsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DemoOrderActivity extends AppCompatActivity{

    RecyclerView lstItems;
    ActivityDemorderBinding binding;
    String sessionId;
    Store sessionstore;
    DatabaseReference myRef;
    DatabaseReference driverref;
    DatabaseReference warehouseref;
    DatabaseReference storeref;
    ArrayList<Store> storearray;
    ArrayList<Warehouse> warearray;
    ArrayList<Driver_track> trackarray;
    ArrayList<myItems> list_of_items;
    AdRecycler adapters;
    Driver signed_driver;
    Object[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDemorderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionId = (String) getIntent().getStringExtra("EXTRA_SESSION_ID");
        Log.v("DDsession" , sessionId);

        storearray = new ArrayList<>();
        warearray = new ArrayList<>();
        trackarray = new ArrayList<>();
        list_of_items = new ArrayList<>();
        signed_driver = new Driver();


     /*   adapters = new AdRecycler(list_of_items, this,this);
        lstItems.setAdapter(adapters);
        lstItems.setLayoutManager(new LinearLayoutManager(this));
*/

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        driverref = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        warehouseref = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        storeref = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        storeref.child("Root").child("Store").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
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
                warehouseref.child("Root").child("Warehouse").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
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
                        driverref.child("Root").child("Driver").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                            @Override
                            public void onSuccess(DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    String dname = ds.getValue(Driver.class).getName();
                                    if (dname.equals(sessionId)) {

                                        signed_driver = ds.getValue(Driver.class);
                                    }
                                 //   Log.v("DDadress",signed_driver.getAddress());
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        }).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                trackarray = signed_driver.getTrack_array();
                                array = new Object[trackarray.size()];
                                for (int i=0;i<trackarray.size();i++){
                                    Driver_track dt = new Driver_track();
                                    dt = trackarray.get(i);
                                    if (dt.isIsstore()) {
                                        for (int j = 0; j < storearray.size(); j++) {
                                            if (storearray.get(j).getName().equals(dt.getName())){
                                                myItems lastitem = new myItems("1",storearray.get(j).getName(),"500",storearray.get(j).getLat(),storearray.get(i).getLongt(),50);
                                                 array[dt.getRank()-1] = lastitem;
                                                //list_of_items.add(dt.getRank()-1,lastitem);
                                            }
                                        }
                                    }
                                    else {
                                        for (int j = 0; j < warearray.size(); j++) {
                                            if (warearray.get(j).getName().equals(dt.getName())) {
                                                myItems lastitem = new myItems("1", warearray.get(j).getName(), "500", warearray.get(j).getLat(), warearray.get(j).getLongt(), 50);
                                                array[dt.getRank()-1] = lastitem;
                                                // list_of_items.add(dt.getRank()-1,lastitem);
                                            }
                                        }

                                    }
                                }
                                for (int i=0;i<trackarray.size();i++){
                                    list_of_items.add((myItems) array[i]);
                                }
                                adapters = new AdRecycler(list_of_items, DemoOrderActivity.this);
                                binding.lstItems.setAdapter(adapters);
                                binding.lstItems.setLayoutManager(new LinearLayoutManager(DemoOrderActivity.this));
                            }
                        });
                    }
                });

            }
        });










        Toast.makeText(getApplicationContext(), sessionId, Toast.LENGTH_LONG).show();


      //  FillList();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        ArrayList<myItems> lmi = new ArrayList<>();
        lmi.add(new myItems("1","mystore","40",30.18547,31.878458,60));
        lmi.add(new myItems("1","mystore","40",30.18547,31.878458,60));
        lmi.add(new myItems("1","mystore","40",30.18547,31.878458,60));

     /*   adapters = new AdRecycler(lmi, DemoOrderActivity.this);
        binding.lstItems.setAdapter(adapters);
        binding.lstItems.setLayoutManager(new LinearLayoutManager(DemoOrderActivity.this));*/


    }// end oncreate








    public void FillList() {
        try {

      /*      adapters = new AdRecycler(list_of_items, this,this);
            lstItems.setAdapter(adapters);
            lstItems.setLayoutManager(new LinearLayoutManager(this));
/*
            myItems items = new myItems();
            List<Map<String, String>> prolist = new ArrayList<Map<String, String>>();
            String[] from = {"OrderId", "StoreName", "Quantity", "SalePrice"};
            int[] views = {R.id.txtordersid, R.id.txtstorename, R.id.txtquantity, R.id.txtSalePrice,};


            for (int i = 0; i < lsr.size(); i++) {
                Map<String, String> datanum = new HashMap<String, String>();
                datanum.put("OrderId", lsr.get(i).OrderId);
                datanum.put("StoreName", String.valueOf(lsr.get(i).StoreName));
                datanum.put("Quantity", String.valueOf(lsr.get(i).Quantity));
                datanum.put("SalePrice", String.valueOf(lsr.get(i).SalePrice));
                prolist.add(datanum);
            }//end for loop

            final SimpleAdapter simpleAdapter = new SimpleAdapter(DemoOrderActivity.this,
                    prolist, R.layout.mylist_items, from, views); //ربطنا مع layoutبتاع ال list
            lstItems.setAdapter(simpleAdapter);
            */
        }// end try
        catch (Exception ex) {
            Toast.makeText(DemoOrderActivity.this, ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }//end catch
    }// end filllist



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(DemoOrderActivity.this, MapsActivity.class);
                startActivity(intent);

                break;
        }// end switch
    }// end onClick
}// end class