package com.android.optimaldistributionrelationalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.optimaldistributionrelationalsystem.alaa.RatingActivity;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityStatus2Binding;

public class Status2Activity extends AppCompatActivity {

    ActivityStatus2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatus2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Status2Activity.this, RatingActivity.class);
                startActivity(intent);
            }
        });
    }

}