package com.venky97vp.android.dietjanitor;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ExpandedRecycler extends AppCompatActivity implements FoodObjects {
    RecyclerView ExpandedStatsView;
    NutrientDisplayAdapter adapter;

    DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    User value;
    private FirebaseAuth mAuth;
    ArrayList<Stats> list;
    ArrayList<FoodDetails> fd1,fd2,fd3,fd4,fd5,fd6,fd7;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_recycler);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = database.getReference().child("users").child(mAuth.getCurrentUser().getUid());

        fd1 = new ArrayList<FoodDetails>();
        fd1.add(new FoodDetails("White Goat", String.valueOf(234.42)));
        fd1.add(new FoodDetails("Idly", String.valueOf(421.75)));
        fd2 = new ArrayList<FoodDetails>();
        fd2.add(new FoodDetails("White Goat", String.valueOf(298.42)));
        fd2.add(new FoodDetails("Idly", String.valueOf(451.75)));
        fd3 = new ArrayList<FoodDetails>();
        fd3.add(new FoodDetails("White Goat", String.valueOf(124.42)));
        fd3.add(new FoodDetails("Idly", String.valueOf(41.75)));
        fd4 = new ArrayList<FoodDetails>();
        fd4.add(new FoodDetails("White Goat", String.valueOf(124.42)));
        fd4.add(new FoodDetails("Idly", String.valueOf(41.75)));
        fd5 = new ArrayList<FoodDetails>();
        fd5.add(new FoodDetails("White Goat", String.valueOf(124.42)));
        fd5.add(new FoodDetails("Idly", String.valueOf(41.75)));
        fd6 = new ArrayList<FoodDetails>();
        fd6.add(new FoodDetails("White Goat", String.valueOf(124.42)));
        fd6.add(new FoodDetails("Idly", String.valueOf(41.75)));
        fd7 = new ArrayList<FoodDetails>();
        fd7.add(new FoodDetails("White Goat", String.valueOf(124.42)));
        fd7.add(new FoodDetails("Idly", String.valueOf(41.75)));
        list = new ArrayList<Stats>();

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                value = dataSnapshot.getValue(User.class);
//                NumberFormat formatter = new DecimalFormat("#0.00");
//                //Log.d(TAG, "Value is: " + value);
//                list.add(new Stats(fd1, "Calorie",formatter.format(value.Calories),"21.2"));
//                list.add(new Stats(fd2, "Protein",formatter.format(value.Proteins),"21.2"));
//                list.add(new Stats(fd3, "Fat",formatter.format(value.Fats),"21.2"));
//                list.add(new Stats(fd4, "Carbohydrate",formatter.format(value.Carbohydrates),"21.2"));
//                list.add(new Stats(fd5, "Sodium",formatter.format(value.Sodium),"21.2"));
//                list.add(new Stats(fd6, "Potassuim",formatter.format(value.Potassium),"21.2"));
//                list.add(new Stats(fd7, "Cholestrol",formatter.format(value.Cholestrol),"21.2"));
////                if (value != null) {
////
////                } else {
////                    startActivity(new Intent(getApplicationContext(),UserInfoActivity.class));
////                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                //Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
        User user = CurrentUser.user;

        list.add(new Stats(fd1,"Calorie","0",String.valueOf(user.Calories)));
        list.add(new Stats(fd2,"Protein","0",String.valueOf(user.Proteins)));
        list.add(new Stats(fd3,"Fat","0",String.valueOf(user.Fats)));
        list.add(new Stats(fd4,"Carbohydrate","0",String.valueOf(user.Carbohydrates)));
        list.add(new Stats(fd5,"Sodium","0",String.valueOf(user.Sodium)));
        list.add(new Stats(fd6,"Potassuim","0",String.valueOf(user.Potassium)));
        list.add(new Stats(fd7,"Cholestrol","0",String.valueOf(user.Cholestrol)));

        adapter = new NutrientDisplayAdapter(this, list);
        ExpandedStatsView = (RecyclerView) findViewById(R.id.idForExpandRecyclerView);
        ExpandedStatsView.setLayoutManager(new LinearLayoutManager(this));
        ExpandedStatsView.setAdapter(adapter);

    }
}
