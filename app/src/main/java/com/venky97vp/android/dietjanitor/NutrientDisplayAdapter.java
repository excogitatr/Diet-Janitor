package com.venky97vp.android.dietjanitor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

/**
 * Created by Ganesh on 02-04-2017.
 */

public class NutrientDisplayAdapter extends ExpandableRecyclerAdapter<Stats, FoodDetails, StatsViewHolder, FoodDetailsViewHolder> {
    public NutrientDisplayAdapter(@NonNull List<Stats> parentList) {
        super(parentList);
    }

    LayoutInflater layoutInflater;

    public NutrientDisplayAdapter(Context context, @NonNull List<com.venky97vp.android.dietjanitor.Stats> parentList) {
        super(parentList);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public com.venky97vp.android.dietjanitor.StatsViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View parentView = layoutInflater.inflate(R.layout.stats_view, parentViewGroup, false);
        return new com.venky97vp.android.dietjanitor.StatsViewHolder(parentView);
    }

    @NonNull
    @Override
    public FoodDetailsViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View childView = layoutInflater.inflate(R.layout.food_details, childViewGroup, false);
        return new FoodDetailsViewHolder(childView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull StatsViewHolder parentViewHolder, int parentPosition, @NonNull Stats parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull FoodDetailsViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull FoodDetails child) {
        childViewHolder.bind(child);
    }
}
