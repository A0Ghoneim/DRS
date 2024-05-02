package com.android.optimaldistributionrelationalsystem.ordertoday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.R;

import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ProductHolder> {
    private List<drv_products>drv_productsList;
    private Context context;

    public productAdapter(List<drv_products> drv_productsList,Context context){
        this.drv_productsList = drv_productsList;
        this.context =context;

    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.r_row_order_to_day,parent,false);
        ProductHolder holder =new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        drv_products product = drv_productsList.get(position);
        holder.productimage.setImageResource(product.getProductimage());
        holder.productname.setText(product.getProductname());
        holder.quantity.setText(product.getQuantity());
        holder.price.setText(product.getPrice());

    }

    @Override
    public int getItemCount() {
        return drv_productsList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        TextView productname,quantity,price;
        ImageView productimage;

        public ProductHolder( View itemView) {
            super(itemView);
            productimage =(ImageView) itemView.findViewById(R.id.produc_img);
            productname =(TextView) itemView.findViewById(R.id.produc_name);
            quantity =(TextView) itemView.findViewById(R.id.produc_quant);
            price =(TextView) itemView.findViewById(R.id.prod_price);
        }
    }

}
