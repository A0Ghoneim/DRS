package com.android.optimaldistributionrelationalsystem.WarehouseManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.R;

import java.util.ArrayList;

public class productAdapters extends RecyclerView.Adapter<productAdapters.viewholder>{

    ArrayList<productitem> p;
    Context c;

    public productAdapters(ArrayList<productitem> plist, Context context) {
        this.p = plist;
        this.c = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.product_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final productitem items= p.get(position);
        if (items.getImage()==0){
           holder.image.setImageBitmap(items.getBitmap());
        }
        else {
            holder.image.setImageResource(items.getImage());
        }
        //holder.image.setImageBitmap(items.getProduct_image());
        holder.name.setText(items.getName());
        holder.title.setText(items.getTitle());
        holder.price.setText(items.getQuantity());


    }

    @Override
    public int getItemCount() {
        return p.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,name,price;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.productimage);
            title =itemView.findViewById(R.id.productdesc);
            name =itemView.findViewById(R.id.productname);
            price =itemView.findViewById(R.id.productprice);
        }
    }




}
