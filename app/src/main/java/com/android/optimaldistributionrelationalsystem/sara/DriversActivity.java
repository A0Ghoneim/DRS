package com.android.optimaldistributionrelationalsystem.sara;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.R;

import java.util.ArrayList;

public class DriversActivity extends AppCompatActivity {

    ArrayList<driveritem> driveritems;
    RecyclerView recyclerView;
    DrAdapter adp;
//  View view;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        recyclerView = findViewById(R.id.drivers);

        driveritems = new ArrayList<>();
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Ali"));
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Mohamed"));
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Mahmoud"));
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Mazen"));
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Malek"));
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Mohamed"));
        driveritems.add(new driveritem(R.drawable.baseline_person_24, "Mazen"));


        adp = new DrAdapter(driveritems, this);
        recyclerView.setAdapter(adp);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //for Back button on Actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // view =findViewById(R.id.myProgressButton);
        //view.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {
        //  ProgressButton progressButton =new ProgressButton(Drivers.this,view);

        //progressButton.buttonActivated();
        //Handler handler =new Handler();
        //handler.postDelayed(new Runnable() {
        //  @Override
        //public void run() {
        //  progressButton.buttonFinished();
        // }
        //}, 3000);
        //}
        //});


    }
    public void Click(View v){
        if(v.getId()==R.id.bb){
            Toast.makeText(getApplicationContext(), "I am Ready", Toast.LENGTH_SHORT).show();
        }

    }


    //for Back button on Actionbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //for progress-button
    //@Override
    //public void ItemClick(View v){
    //v =findViewById(R.id.myProgressButton);
    //v.setOnClickListener(new View.OnClickListener() {
    //@Override
    //public void onClick(View view) {
    //ProgressButton progressButton =new ProgressButton(Drivers.this,view);
    //progressButton.buttonActivated();


    //new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
    //    @Override
    //      public void run() {
    //progressButton.buttonFinished();
}
//    },4000);
//          }
//            });
//}