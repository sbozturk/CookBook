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
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.name.setText(recipes.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.createAdapter();

                Intent Intent = new Intent(view.getContext(), ShowRecipeActivity.class);
                view.getContext().startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (recipes == null) ? 0 : recipes.size();

        //If you are ok with returning 0 as size when values is null. We can add this code instead of "return values.size();"
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String mItem;
        private TextView name;

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