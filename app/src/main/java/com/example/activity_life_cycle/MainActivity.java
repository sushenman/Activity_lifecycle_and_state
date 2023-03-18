package com.example.activity_life_cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private ImageButton dessert_button;
//private ActivityMainBinding binding;
private TextView total_amount;
private TextView sold_amount;
    private   int counter ;
    private int total_sold;
    //create arraylist

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dessert_button = findViewById(R.id.dessert_button);
        total_amount = findViewById(R.id.total_amount);
        sold_amount = findViewById(R.id.sold_amount);

        dessert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDessertClicked();
            }

            private void onDessertClicked() {
                counter++;
                    total_sold = total_sold + currentDessert.getPrice();
                    sold_amount.setText(String.valueOf(counter));
                    total_amount.setText(String.valueOf("$"+total_sold));
                showCurrentDessert();
//                    Dessert dessert = new Dessert();

//                for(int i=0; i<dessert.allDesserts.size();i++)
//                {
//                    Log.d(TAG, "onDessertClicked: "+dessert.allDesserts.get(i).getImageId());
//                }
            }
            private void showCurrentDessert() {
                // revenue
                // dessertsSold
                Dessert newDessert = allDesserts.get(0);
                for (Dessert dessert : allDesserts) {
                    if (counter >= dessert.getStartProductionAmount()) {
                        newDessert = dessert;
                    }
                    // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                    // you'll start producing more expensive desserts as determined by startProductionAmount
                    // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                    // than the amount sold.
                    else break;
                }
                // If the new dessert is actually different than the current dessert, update the image
                if (!newDessert.equals(currentDessert)) {
                    currentDessert = newDessert;
                    dessert_button.setImageResource(newDessert.getImageId());                }
            }
        });
        if(savedInstanceState !=null){
            //output show in this stage using getInt
            counter = savedInstanceState.getInt("COUNTER");
            total_sold= savedInstanceState.getInt("TOTAL");
            sold_amount.setText(" "+counter);
            total_amount.setText("$"+total_sold);
            Log.d(TAG, "save: "+sold_amount.getEditableText());
            Log.d(TAG, "save: "+total_amount.getEditableText());
        }

        Log.d(TAG, "onCreate: ");
    }
    public class Dessert {
        private final int imageId;
        private final int price;
        private final int startProductionAmount;
        public Dessert(int imageId, int price, int startProductionAmount) {
            this.imageId = imageId;
            this.price = price;
            this.startProductionAmount = startProductionAmount;
        }
        public int getImageId() {
            return imageId;
        }
        public int getPrice() {
            return price;
        }
        public int getStartProductionAmount() {
            return startProductionAmount;
        }
    }
    private final List<Dessert> allDesserts = Arrays.asList(
            new Dessert(R.drawable.cupcake, 5, 0),
            new Dessert(R.drawable.donut, 10, 5),
            new Dessert(R.drawable.eclair, 15, 20),
            new Dessert(R.drawable.froyo, 30, 50),
            new Dessert(R.drawable.gingerbread, 50, 100),
            new Dessert(R.drawable.honeycomb, 100, 200),
            new Dessert(R.drawable.icecreamsandwich, 500, 500),
            new Dessert(R.drawable.jellybean, 1000, 1000),
            new Dessert(R.drawable.kitkat, 2000, 2000),
            new Dessert(R.drawable.lollipop, 3000, 4000),
            new Dessert(R.drawable.marshmallow, 4000, 8000),
            new Dessert(R.drawable.nougat, 5000, 16000),
            new Dessert(R.drawable.oreo, 6000, 20000)
    );
    private Dessert currentDessert = allDesserts.get(0);
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        //data stores in the state so that we can use in the line 34(input store data)
        outState.putInt("COUNTER", counter);
        outState.putInt("TOTAL", total_sold);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.first:
                onShare();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void onShare() {
        Log.d(TAG, "onShare: ");
     Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "I just sold " + counter + " desserts, earning $" + total_sold + " in the process!");
        intent.setType("text/plain");
        startActivity(intent);

    }
//    private void showCurrentDessert() {
//        // revenue
//        // dessertsSold
//        Dessert newDessert = allDesserts.get(0);
//        for (Dessert dessert : allDesserts) {
//            if (desserts_sold >= dessert.getStartProductionAmount()) {
//                newDessert = dessert;
//            }
//            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
//            // you'll start producing more expensive desserts as determined by startProductionAmount
//            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
//            // than the amount sold.
//            else break;
//        }
//        // If the new dessert is actually different than the current dessert, update the image
//        if (!newDessert.equals(currentDessert)) {
//            currentDessert = newDessert;
//            binding.dessertButton.setImageResource(newDessert.getImageId());
//        }
//    }


}