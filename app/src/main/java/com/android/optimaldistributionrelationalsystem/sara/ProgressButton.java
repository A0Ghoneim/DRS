package com.android.optimaldistributionrelationalsystem.sara;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.optimaldistributionrelationalsystem.R;

public class ProgressButton {

    private CardView cardView;
    private ProgressBar progressBar;
    private ConstraintLayout layout;
    private TextView textView;

    Animation fade_in;

    ProgressButton(Context ct, View view){

        cardView = view.findViewById(R.id.cardView);
        layout = view.findViewById(R.id.constraint_layout);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.textView);

    }
    void buttonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Pending...");
    }
    void buttonFinished(){
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.purple_700));
        progressBar.setVisibility(View.GONE);
        textView.setText("Done");
    }
}