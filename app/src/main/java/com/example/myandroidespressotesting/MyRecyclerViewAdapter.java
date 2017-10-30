package com.example.myandroidespressotesting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    String[] coffeeNames;
    int[] coffeImages;
    Context context;
    public static String EXTRA_COFFEE_NAME = "coffeeName";
    public static String EXTRA_COFFEE_IMAGE = "coffeeImage";

    public MyRecyclerViewAdapter(Context context, String[] texts, int[] coffeImages){
        this.coffeeNames = texts;
        this.coffeImages = coffeImages;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(coffeeNames[position]);
        holder.imageView.setImageResource(coffeImages[position]);
    }

    @Override
    public int getItemCount() {
        return coffeeNames.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        ImageView imageView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            final Context context = itemView.getContext();
            textView = itemView.findViewById(R.id.item_text);
            imageView = itemView.findViewById(R.id.item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, SingleCoffeeActivity.class);
                    intent.putExtra(EXTRA_COFFEE_NAME, coffeeNames[position]);
                    intent.putExtra(EXTRA_COFFEE_IMAGE, coffeImages[position]);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }
}
