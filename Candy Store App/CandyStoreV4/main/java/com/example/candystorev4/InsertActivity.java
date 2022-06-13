package com.example.candystorev4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {
    // DatabaseManager object
    private DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager(this);
        setContentView( R.layout.activity_insert );


    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText nameEditText = (EditText) findViewById(R.id.input_name);
        EditText priceEditText = (EditText) findViewById(R.id.input_price);

        // Convert to string
        String nameString = nameEditText.getText().toString();
        String priceString = priceEditText.getText().toString();

        // insert new candy in database
        try {
            double price = Double.parseDouble( priceString );

            // Candy object
            Candy candy = new Candy( 0, nameString, price );

            // Insert into database via insert() method
            dbManager.insert( candy );

            // Make and show insertion with a Toast
            // LENGTH_SHORT shows this Toast for 3 seconds before disappearing
            Toast.makeText( this, "Candy added", Toast.LENGTH_SHORT ).show();

        }
        catch( NumberFormatException nfe )
        {
            Toast.makeText( this, "Price error", Toast.LENGTH_LONG ).show();
        }

        // Get the ArrayList of candies from the database
        ArrayList<Candy> candies = dbManager.selectAll();

        // for loop with logcat to show every candy in the database
        for( Candy candy : candies )
            Log.w("MainActivity", "candy = " + candy.toString( ) );


        // clear data
        nameEditText.setText( "" );
        priceEditText.setText( "" );
    }


    public void goBack( View v ) {
        this.finish();

    }
}
