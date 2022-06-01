package com.example.mortgagev1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void modifyData( View v )
    {

        /*
        Intent - Constructs an Intent that is intended to execute a class modeled
        by type ? (probably some Activity class) that is in the same application package
        as context
         */
        Intent myIntent = new Intent( this, DataActivity.class );

        // Launches a new activity using intent as the Intent to start it.
        this.startActivity( myIntent );
    }
}