package com.example.myandroidespressotesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    Button buttonWebsite;
    public static String TAG = "mytag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_coffee);

        textViewCoffee = (TextView) findViewById(R.id.textViewCoffee);
        imageViewCoffee = (ImageView) findViewById(R.id.imageViewCoffee);
        buttonWebsite = (Button) findViewById(R.id.buttonWebsite);

        if (getIntent().getExtras() != null) {
            String coffeeName = getIntent().getExtras().getString(EXTRA_COFFEE_NAME);
            int coffeeImage = getIntent().getExtras().getInt(EXTRA_COFFEE_IMAGE);

            textViewCoffee.setText(coffeeName);
            imageViewCoffee.setImageResource(coffeeImage);
        }

        buttonWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleCoffeeActivity.this, WebviewActivity.class);
                intent.putExtra("url", "http://www.example.com");
                startActivity(intent);
            }
        });


    }
}
