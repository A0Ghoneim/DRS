package com.android.optimaldistributionrelationalsystem.alaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityRatingBinding;

public class RatingActivity extends AppCompatActivity {

    ActivityRatingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(RatingActivity.this,FinishedActivity.class);
                startActivity(intent);
            }
        });
    }
}