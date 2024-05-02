package com.android.optimaldistributionrelationalsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.alaa.RatingActivity;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityStatusBinding;

public class StatusActivity extends AppCompatActivity {

    ActivityStatusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(StatusActivity.this, RatingActivity.class);
                startActivity(intent);
            }
        });
    }
}