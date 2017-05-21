package com.bysoftware.cookbook.android.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bysoftware.cookbook.android.R;
import com.bysoftware.cookbook.android.activity.MainActivity;
import com.bysoftware.cookbook.android.activity.ShowRecipeActivity;

import java.util.ArrayList;

/**
 * Created by bugra on 13.5.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> recipes = null;

    public RecyclerViewAdapter(ArrayList<String> recipes) {
        this.recipes = recipes;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.name.setText(recipes.get(position * 2));

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String recipeID = (String) recipes.get((position * 2) + 1);
                Intent Intent = new Intent(view.getContext(), ShowRecipeActivity.class);
                Intent.putExtra("key", recipeID);
                view.getContext().startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int halfCountOfList = recipes.size() / 2;
        int finalCount = halfCountOfList + recipes.size() % 2;
        return finalCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String mItem;
        private TextView name, key;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.list_item_text);

        }

        public void setItem(String item) {
            mItem = item;
            name.setText(item);
        }
    }
}