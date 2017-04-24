package com.venky97vp.android.dietjanitor;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

/**
 * Created by Ganesh on 02-04-2017.
 */

public class FoodDetailsViewHolder extends ChildViewHolder {
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    TextView FoodName,ValueGrams ;
    public FoodDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        FoodName=(TextView)itemView.findViewById(R.id.id_food_name);
        ValueGrams=(TextView)itemView.findViewById(R.id.id_grams_value);
    }
    public void bind(FoodDetails foodDetails){
        FoodName.setText(foodDetails.getFoodName());
        ValueGrams.setText(foodDetails.getValue());
    }
}
