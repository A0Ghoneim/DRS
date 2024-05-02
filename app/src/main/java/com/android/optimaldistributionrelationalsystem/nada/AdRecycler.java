package com.android.optimaldistributionrelationalsystem.nada;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.databinding.MylistItemsBinding;
import com.android.optimaldistributionrelationalsystem.databinding.RecycleItemBinding;

import java.util.ArrayList;

public class AdRecycler extends RecyclerView.Adapter<AdRecycler.Holder> {

    ArrayList<myItems> pl;

    Context con;

    public AdRecycler(ArrayList<myItems> pl, Context con) {
        this.pl = pl;
        this.con = con;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MylistItemsBinding binding = MylistItemsBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        final myItems items = pl.get(position);

       holder.binding.txtstorename.setText(items.StoreName);
       holder.binding.txtquantity.setText(items.Quantity);
        holder.binding.txtordersid.setText(String.valueOf(items.OrderId));
        holder.binding.txtSalePrice.setText(String.valueOf(items.SalePrice));
        holder.binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+String.valueOf(items.lat)+","+String.valueOf(items.longt)));
                        con.startActivity(intent);
            }
            });
    }
    @Override
    public int getItemCount() {
        return pl.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public MylistItemsBinding binding;

        public Holder(MylistItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}






























/*
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

public class AdRecycler extends RecyclerView.Adapter<AdRecycler.viewHolder> {
    ArrayList<myItems> plist;

    Context context;
    itemClickListener itemClickListener;



    public AdRecycler(ArrayList<myItems> plist, Context context,itemClickListener itemClickListener){
        this.plist=plist;
        this.context=context;
        this.itemClickListener =itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mylist_items,parent,false);
        return new viewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdRecycler.viewHolder holder, int position) {
        final myItems items=plist.get(position);
        holder.sname.setText(items.StoreName);
        holder.oid.setText(items.OrderId);
        holder.quantity.setText(items.Quantity);

    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        itemClickListener itemClickListener;

        TextView  oid,sname,quantity,price;
        Button button;

        public viewHolder(@NonNull View itemView,itemClickListener itemClickListener) {
            super(itemView);

            oid=itemView.findViewById(R.id.txtordersid);
            sname=itemView.findViewById(R.id.txtstorename);
            quantity=itemView.findViewById(R.id.txtquantity);
            button=itemView.findViewById(R.id.button1);
            this.itemClickListener = itemClickListener;
            button.setOnClickListener(this);
            // itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClik(view);
        }
    }
    public interface itemClickListener{
        void onItemClik(View view);
    }
}
*/