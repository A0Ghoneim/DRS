package com.android.optimaldistributionrelationalsystem.shahand;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.optimaldistributionrelationalsystem.data.Order;
import com.android.optimaldistributionrelationalsystem.data.Product;
//import com.android.optimaldistributionrelationalsystem.data.Store;
import com.android.optimaldistributionrelationalsystem.data.Store;
import com.android.optimaldistributionrelationalsystem.databinding.ActivityViewItemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewItemActivity extends AppCompatActivity {

    ActivityViewItemBinding binding;

    ArrayList<orderItem> orderItems;
    AdapterRecycler adapter;
    DatabaseReference myRef;
    DatabaseReference odbref;
   // ArrayList<Product> productarr;

    StorageReference mstorageReference;
    StorageReference AstorageReference;
    StorageReference imrefrence;
    Product p1;
    ArrayList<Product> plusarray;
    ArrayList<Order> order_final_list;
    String sessionId;
    Store sessionstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionstore = (Store) getIntent().getSerializableExtra("KEY_NAME");
        Log.v("storefinderr",sessionstore.getAddress());

        p1 = new Product();
//adel

   plusarray = new ArrayList<>();
   order_final_list = new ArrayList<>();


        // adel
        odbref = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        myRef.child("Root").child("Product").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.v(" arkam_product" ,  " kman ");
                    Log.v(" arkam_product" ,  Objects.requireNonNull(ds.getValue(Product.class)).getName());
                    Product myproduct = new Product();




                    myproduct.setId(Objects.requireNonNull(Objects.requireNonNull(ds.child("id").getValue()).toString()));
                    myproduct.setDescription(Objects.requireNonNull(ds.getValue(Product.class)).getDescription());
                    myproduct.setImage_number(Objects.requireNonNull(ds.getValue(Product.class)).getImage_number());
                    myproduct.setQuantity(Objects.requireNonNull(ds.getValue(Product.class)).getQuantity());
                    myproduct.setName(Objects.requireNonNull(ds.getValue(Product.class)).getName());




                    p1=ds.getValue(Product.class);

                    plusarray.add(p1);


                    Log.v(" arkam_product" ,  myproduct.getId());
                    Log.v(" arkam_product" ,  myproduct.getName());
                    Log.v(" arkam_product" ,  myproduct.getDescription());
                    Log.v(" arkam_product" ,  myproduct.getQuantity());

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            //    Toast.makeText(getApplicationContext(), "failed read", Toast.LENGTH_LONG).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (int i = 0; i< plusarray.size(); i++) {
                    int  imref = plusarray.get(i).getImage_number();
                    //Bitmap imbit = productarr.get(i).getProduct_image();
                    String pname = plusarray.get(i).getName();
                    String pid = plusarray.get(i).getId();
                    // mstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/45555/Peugeot_504.jpg");
                    mstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/" + pid +"/"+pname+".jpg");
                    if (imref!=0){
                        orderItems.add(new orderItem(imref,pname,0,pid));
                    }
                    else {
                        imrefrence = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/" + pid +"/"+pname+".jpg");
                        try {
                            final File localfile = File.createTempFile(pname,"jpg");
                            mstorageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    orderItems.add(new orderItem(bitmap,pname,0,pid));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                   // Toast.makeText(getApplicationContext(), "get fail ", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                    adapter = new AdapterRecycler(orderItems, ViewItemActivity.this);
                                    binding.recyclerView.setAdapter(adapter);
                                    binding.recyclerView.setLayoutManager(new GridLayoutManager(ViewItemActivity.this, 2));
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }
                }
                adapter = new AdapterRecycler(orderItems, ViewItemActivity.this);
                binding.recyclerView.setAdapter(adapter);
                binding.recyclerView.setLayoutManager(new GridLayoutManager(ViewItemActivity.this, 2));
             //   Toast.makeText(getApplicationContext(), String.valueOf(plusarray.size())+"products inside", Toast.LENGTH_SHORT).show();

            }
        });



       /* myRef.child("Root").child("Product").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()) {

                        //temp





                        //temp



                        //Toast.makeText(getApplicationContext(), "sucess reg", Toast.LENGTH_LONG).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        //productarr.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Log.v(" arkam_product" ,  " kman ");
                            Log.v(" arkam_product" ,  Objects.requireNonNull(ds.getValue(Product.class)).getName());
                            Product myproduct = new Product();




                                myproduct.setId(Objects.requireNonNull(Objects.requireNonNull(ds.child("id").getValue()).toString()));
                                myproduct.setDescription(Objects.requireNonNull(ds.getValue(Product.class)).getDescription());
                             myproduct.setImage_number(Objects.requireNonNull(ds.getValue(Product.class)).getImage_number());
                                myproduct.setQuantity(Objects.requireNonNull(ds.getValue(Product.class)).getQuantity());
                                myproduct.setName(Objects.requireNonNull(ds.getValue(Product.class)).getName());




                           p1=ds.getValue(Product.class);

                            plusarray.add(p1);


                            Log.v(" arkam_product" ,  myproduct.getId());
                            Log.v(" arkam_product" ,  myproduct.getName());
                            Log.v(" arkam_product" ,  myproduct.getDescription());
                            Log.v(" arkam_product" ,  myproduct.getQuantity());

                        }
                        Toast.makeText(getApplicationContext(), String.valueOf(plusarray.size())+"products inside", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "product no exist", Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    Toast.makeText(getApplicationContext(), "failed read", Toast.LENGTH_LONG).show();
                }

            }
        });*/
       // Toast.makeText(getApplicationContext(), String.valueOf(plusarray.size())+"products", Toast.LENGTH_SHORT).show();

        orderItems = new ArrayList<>();



      //  Toast.makeText(getApplicationContext(), String.valueOf(orderItems.size())+"orders", Toast.LENGTH_SHORT).show();


    /*    orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));
        orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));
        orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));
        orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));
        orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));
        orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));
        orderItems.add(new orderItem(R.drawable.phone,"Iphone",0));

*/

        adapter = new AdapterRecycler(orderItems, this);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // there no thing left put list of data in adapter

        onClick();
    }

    private void onClick() {

        binding.iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i =0;i<orderItems.size();i++){
                    Log.v("zad",String.valueOf(orderItems.get(i).getNumber()));
                    if (orderItems.get(i).getNumber()>0){
                        String oid = sessionId + orderItems.get(i).getProduct_id();
                        Log.v("storefinderr",sessionstore.getAddress());
                        Order o1 = new Order(oid,sessionstore,orderItems.get(i).product_id,orderItems.get(i).getNumber());
                        odbref.child("Root").child("Order").child(oid).setValue(o1);
                    }
                }

             /*   Intent intent = new Intent();
                intent.setClass(ViewItemActivity.this, Status2Activity.class);
                startActivity(intent);*/
            }
        });
    }
}