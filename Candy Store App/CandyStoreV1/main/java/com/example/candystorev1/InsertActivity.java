package com.example.candystorev1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert );
    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText nameEditText = (EditText) findViewById(R.id.input_name);
        EditText priceEditText = (EditText) findViewById(R.id.input_price);

        // Convert to string
        String name = nameEditText.getText().toString();
        String price = priceEditText.getText().toString();

        // insert new candy in database

        // clear data
        nameEditText.setText( "" );
        priceEditText.setText( "" );
    }


    public void goBack( View v ) {
        this.finish();

    }
}
