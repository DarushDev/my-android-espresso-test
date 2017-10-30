package com.example.myandroidespressotesting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.myandroidespressotesting.MyRecyclerViewAdapter.EXTRA_COFFEE_IMAGE;
import static com.example.myandroidespressotesting.MyRecyclerViewAdapter.EXTRA_COFFEE_NAME;

/**
 * Created by Lenovo Desktop on 10/30/2017.
 */

public class SingleCoffeeActivity extends AppCompatActivity {

    TextView textViewCoffee;
    ImageView imageViewCoffee;
    public static String TAG = "mytag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_coffee);

        textViewCoffee = (TextView) findViewById(R.id.textViewCoffee);
        imageViewCoffee = (ImageView) findViewById(R.id.imageViewCoffee);
        if (getIntent().getExtras() != null) {
            String coffeeName = getIntent().getExtras().getString(EXTRA_COFFEE_NAME);
            int coffeeImage = getIntent().getExtras().getInt(EXTRA_COFFEE_IMAGE);

            textViewCoffee.setText(coffeeName);
            imageViewCoffee.setImageResource(coffeeImage);
        }



    }
}
