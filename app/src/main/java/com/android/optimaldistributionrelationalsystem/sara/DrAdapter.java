package com.android.optimaldistributionrelationalsystem.sara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.R;

import java.util.ArrayList;

public class DrAdapter extends RecyclerView.Adapter<DrAdapter.viewHolder> {

    ArrayList<driveritem> pl;

    Context con;


    public DrAdapter(ArrayList<driveritem> pl, Context con) {
        this.pl = pl;
        this.con = con;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.driver_item, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DrAdapter.viewHolder holder, int position) {
        final driveritem items = pl.get(position);
        holder.driverimage.setImageResource(items.getDriverimage());
        holder.drivername.setText(items.getDrivername());


    }

    @Override
    public int getItemCount() {
        return pl.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView driverimage;
        TextView drivername;
        Button button;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            driverimage = itemView.findViewById(R.id.driverimage);
            drivername = itemView.findViewById(R.id.drivername);
            button=itemView.findViewById(R.id.bb);


        }
    }
}