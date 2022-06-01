package com.example.mortgagev1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class DataActivity extends AppCompatActivity
{
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_data);
    }
    public void goBack(View v)
    {
        // Closes this activity nad pops it off the stack;
        // the screen for the prior activity is shown.
        this.finish();
    }
}
