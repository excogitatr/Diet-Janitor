package com.venky97vp.android.dietjanitor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;


import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ganesh on 02-04-2017.
 */

public class StatsViewHolder extends ParentViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    public TextView Nutrient,Available,Required ;
    public StatsViewHolder(@NonNull View itemView) {
        super(itemView);
        Nutrient=(TextView) itemView.findViewById(R.id.id_nutrient_name);
        Available=(TextView) itemView.findViewById(R.id.id_available_grams);
        Required=(TextView) itemView.findViewById(R.id.id_requred_grams);

    }
    public void bind(Stats stats){

        Nutrient.setText(stats.getNutrientName());
        Available.setText(stats.getActual());
        Required.setText(stats.getRequired());
    }
}
