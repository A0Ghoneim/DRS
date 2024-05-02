package com.android.optimaldistributionrelationalsystem.WarehouseManager;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.optimaldistributionrelationalsystem.R;
import com.android.optimaldistributionrelationalsystem.data.Order;
import com.android.optimaldistributionrelationalsystem.data.Product;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
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
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class WarehouseMangerActivity extends AppCompatActivity {

    ArrayList<productitem> productlist;
    ArrayList<Product> ppproductlist;

    ArrayList<Product> prlist;
    productAdapters adapters;

    RecyclerView playout;

    ArrayList<orderitem> orderitems;
    ArrayList<Order> order_list;
    OrderAdapter adapter;
    RecyclerView play;

    MeowBottomNavigation bottomNavigation;
    RelativeLayout home, order;
    ConstraintLayout add;

    ActivityResultLauncher<String> Alancher;
    DatabaseReference myRef;
    DatabaseReference dbproRef;
    DatabaseReference pairedorderref;
    DatabaseReference productorderref;
    StorageReference mstorageReference;
    StorageReference AstorageReference;
    StorageReference imrefrence;
    Product currproduct;
    EditText ep, eq, eb, ed;
    ImageView im2;
    Uri imuri;
    String sessionId;
    Product p1;
    productitem pi1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_manger);

        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Toast.makeText(getApplicationContext(), sessionId, Toast.LENGTH_LONG).show();

        order_list=new ArrayList<>();

        myRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        pairedorderref = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        productorderref = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();


        //mstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/45555/Peugeot_504.jpg");
        AstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference();
     /*   myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        bottomNavigation = findViewById(R.id.bottomNavigation);
        home = findViewById(R.id.home);
        add = findViewById(R.id.add);
        order = findViewById(R.id.order);

        //first page will appear
        bottomNavigation.show(1, true);


        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_local_grocery_store_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_content_paste_24));


        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @SuppressLint("NewApi")
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()) {

                    case 1:

                        home.setVisibility(View.VISIBLE);
                        add.setVisibility(View.GONE);
                        order.setVisibility(View.GONE);

                        getWindow().setStatusBarColor(Color.parseColor("#439A97"));
                        break;

                    case 2:
                        home.setVisibility(View.GONE);
                        add.setVisibility(View.VISIBLE);
                        order.setVisibility(View.GONE);

                        getWindow().setStatusBarColor(Color.parseColor("#439A97"));

                        break;
                    case 3:
                        home.setVisibility(View.GONE);
                        add.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);

                        getWindow().setStatusBarColor(Color.parseColor("#439A97"));
                        break;

                }


                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()) {
                    case 1:
                        home.setVisibility(View.VISIBLE);
                        add.setVisibility(View.GONE);
                        order.setVisibility(View.GONE);

                        break;

                }
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()) {
                    case 2:
                        home.setVisibility(View.GONE);
                        add.setVisibility(View.VISIBLE);
                        order.setVisibility(View.GONE);

                        break;

                }
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()) {
                    case 3:
                        home.setVisibility(View.GONE);
                        add.setVisibility(View.GONE);
                        order.setVisibility(View.VISIBLE);

                        break;

                }
                return null;
            }
        });

        playout = findViewById(R.id.product);

        p1 = new Product();
        ppproductlist = new ArrayList<>();
        prlist = new ArrayList<>();
        productlist = new ArrayList<>();

        dbproRef = FirebaseDatabase.getInstance("https://mygrad-58c63-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        dbproRef.child("Root").child("Warehouse_products").child(sessionId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    p1 = ds.getValue(Product.class);
                  /*  pi1.setId(p1.getId());
                    pi1.setName(p1.getName());
                    pi1.setQuantity(p1.getQuantity());
                    pi1.setTitle(p1.getDescription());
                    pi1.setImage(p1.getImage_number());*/
                    ppproductlist.add(p1);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                for (int i = 0; i < ppproductlist.size(); i++) {
                    int imref = ppproductlist.get(i).getImage_number();
                    //Bitmap imbit = productarr.get(i).getProduct_image();
                    String pname = ppproductlist.get(i).getName();
                    String pid = ppproductlist.get(i).getId();
                    String pdesc = ppproductlist.get(i).getDescription();
                    String pquant = ppproductlist.get(i).getQuantity();
                    // mstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/45555/Peugeot_504.jpg");
                    mstorageReference = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/" + pid + "/" + pname + ".jpg");
                    if (imref != 0) {
                        productlist.add(new productitem(imref, pid, pname, pdesc, pquant));
                    } else {
                        imrefrence = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/" + pid + "/" + pname + ".jpg");
                        try {
                            final File localfile = File.createTempFile(pname, "jpg");
                            mstorageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                    productlist.add(new productitem(bitmap, pid, pname, pdesc, pquant));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                  //  Toast.makeText(getApplicationContext(), "get fail ", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                    adapters = new productAdapters(productlist, WarehouseMangerActivity.this);
                                    playout.setAdapter(adapters);
                                    playout.setLayoutManager(new GridLayoutManager(WarehouseMangerActivity.this, 2));
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }
                adapters = new productAdapters(productlist, WarehouseMangerActivity.this);
                playout.setAdapter(adapters);
                playout.setLayoutManager(new GridLayoutManager(WarehouseMangerActivity.this, 2));
            }
        });


                /*
        imrefrence = FirebaseStorage.getInstance("gs://mygrad-58c63.appspot.com/").getReference().child("images/products/45555/Peugeot_504.jpg");
        try {
            final File localfile = File.createTempFile("Peugeot_504","jpg");
            mstorageReference.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                    productlist.add(new productitem("504","pego","great car","12",bitmap));
                    Toast.makeText(getApplicationContext(), "upload sucess ", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "upload fail ", Toast.LENGTH_SHORT).show();

                }
            }).addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                    adapters = new productAdapters(productlist, WarehouseMangerActivity.this);
                    playout.setAdapter(adapters);
                    playout.setLayoutManager(new GridLayoutManager(WarehouseMangerActivity.this, 2));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

                 */
   /*     productitem p1 = new productitem(R.drawable.smart, "1", "Smart Watch", "it contain a microphone that filters out background noise as well as built-in accelerometers and optical sensors capable of detecting taps and pinches", "70");
        productitem p2 = new productitem(R.drawable.phone, "2", "Mobile Phone", "with 14 inch screen and fingerprint sensor and a high quality camera", "150");
        productitem p3 = new productitem(R.drawable.laptop, "3", "Laptop", "high performance CPU with 8 cores 16 threads a 360 flip screen and a powerful GPU", "12");
        productitem p4 = new productitem(R.drawable.airpods, "4", "Airpods", "pure sound and long lasting battery for 8 hours of play time to listen to your favourite songs", "30");
        productlist = new ArrayList<>();

        productlist.add(p1);
        productlist.add(p2);
        productlist.add(p3);
        productlist.add(p4);

        productlist.add(new Product(R.drawable.smart, "5", "Smart Watch", "it contain a microphone that filters out background noise as well as built-in accelerometers and optical sensors capable of detecting taps and pinches", "800$"));
        productlist.add(new Product(R.drawable.phone, "6", "Mobile Phone", "it contain a microphone that filters out background noise as well as built-in accelerometers and optical sensors capable of detecting taps and pinches", "800$"));
        productlist.add(new Product(R.drawable.laptop, "7", "Laptop", "it contain a microphone that filters out background noise as well as built-in accelerometers and optical sensors capable of detecting taps and pinches", "800$"));
        productlist.add(new Product(R.drawable.airpods, "8", "Airpods", "it contain a microphone that filters out background noise as well as built-in accelerometers and optical sensors capable of detecting taps and pinches", "800$"));
*/

/*
        adapters = new productAdapters(productlist, this);
        playout.setAdapter(adapters);
        playout.setLayoutManager(new GridLayoutManager(this, 2));  */
        play = findViewById(R.id.rv_order);
        orderitems = new ArrayList<>();



        pairedorderref.child("Root").child("Paired_orders").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                   if(ds.child("whname").child("name").getValue().equals(sessionId)) {
                   String s1 = (String) ds.child("product").getValue();
                    String s2 = (String) ds.child("sname").child("name").getValue();
                    Long s3 = (Long) ds.child("quantity").getValue();
                       orderitems.add(new orderitem(s1, String.valueOf(s3),s2));
                   }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
            /*    for (int i=0;i<order_list.size();i++) {
                    Order o2 = new Order();
                    o2 = order_list.get(i);
                   orderitems.add(new orderitem(o2.getProduct(), String.valueOf(o2.getQuantity()),o2.getSname().getName()));
                }*/
                adapter = new OrderAdapter(orderitems, WarehouseMangerActivity.this);
                play.setAdapter(adapter);
                play.setLayoutManager(new LinearLayoutManager(WarehouseMangerActivity.this));
            }
        });




     /*   orderitems.add(new orderitem(R.drawable.airpods, "Airpods", "Quantity: 100", "Price: 500", "Store Code: 2", "Address: Cairo"));
        orderitems.add(new orderitem(R.drawable.laptop, "Laptop", "Quantity: 200", "Price: 11000", "Store Code: 8", "Address: Cairo"));
        orderitems.add(new orderitem(R.drawable.smart, "Smart Watch", "300", "1200", "6", "Cairo"));
        orderitems.add(new orderitem(R.drawable.phone, "phone", "133", "2000", "1", "Cairo"));
        orderitems.add(new orderitem(R.drawable.airpods, "Airpods", "341", "500", "9", "Cairo"));
        orderitems.add(new orderitem(R.drawable.smart, "Smart Watch", "78", "800", "7", "Cairo"));
        orderitems.add(new orderitem(R.drawable.laptop, "Laptop", "30", "31000", "5", "Cairo"));
        orderitems.add(new orderitem(R.drawable.phone, "Phone", "12", "5000", "4", "Cairo"));*/





        im2 = (ImageView)findViewById(R.id.special_image);
        Alancher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imuri=result;
                im2.setImageURI(result);
            }
        });



        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("makan", "elgdeed gdeeed");
                Alancher.launch("image/*");
            }
        });

    }



    public void On(View v){
        if(v.getId()==R.id.btn_add){
             ep = (EditText) findViewById(R.id.edit_product);
            String product = ep.getText().toString();
             eq = (EditText) findViewById(R.id.quantity);
            String quantity = eq.getText().toString();
            eb = (EditText) findViewById(R.id.barcode);
            String barcode = eb.getText().toString();
             ed = (EditText) findViewById(R.id.description);
            String description = ed.getText().toString();

            if (product.isEmpty()) {
                ep.setError(getString(R.string.required));

            } else if (quantity.isEmpty()) {
                eq.setError(getString(R.string.required));

            }
            else if (barcode.isEmpty()) {
                eb.setError(getString(R.string.required));

            }else {
                StorageReference storageReference = AstorageReference.child("images/products/"+barcode+"/"+product+".jpg");
                storageReference.putFile(imuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "upload sucess ", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "upload faliure ", Toast.LENGTH_SHORT).show();

                    }
                });
               currproduct = new Product(barcode,product,description,quantity);
                myRef.child("Root").child("Warehouse_products").child(sessionId).child(barcode).setValue(currproduct);
                myRef.child("Root").child("Product").child(sessionId).child(barcode).setValue(currproduct);
            }
        }
            Toast.makeText(getApplicationContext(), "Product has been added ", Toast.LENGTH_SHORT).show();
        }

    }
