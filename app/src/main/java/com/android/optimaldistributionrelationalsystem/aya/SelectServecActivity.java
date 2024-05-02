package com.android.optimaldistributionrelationalsystem.aya;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.StatusActivity;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.android.optimaldistributionrelationalsystem.shahand.ViewItemActivity;

import java.io.Serializable;

public class SelectServecActivity extends AppCompatActivity {

    String sessionId;
    Store sessionstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_servec);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionstore = (Store) getIntent().getSerializableExtra("KEY_NAME");
        Log.v("storefinderr",sessionstore.getAddress());
          Toast.makeText(getApplicationContext(), sessionId, Toast.LENGTH_LONG).show();

    }

    public void onClick(View v){
        switch(v.getId()){

            case R.id.add:
                Intent intent= new Intent( SelectServecActivity.this, ViewItemActivity.class);
                intent.putExtra("EXTRA_SESSION_ID",sessionId);
                Log.v("storefinderr",sessionstore.getAddress());
                intent.putExtra("KEY_NAME", (Serializable) sessionstore);
                startActivity(intent);
                break;
            case R.id.track:
                Intent inten= new Intent( SelectServecActivity.this, StatusActivity.class);
                inten.putExtra("EXTRA_SESSION_ID",sessionId);
                inten.putExtra("KEY_NAME", (Serializable) sessionstore);
                startActivity(inten);
                break;

        }
    }
}