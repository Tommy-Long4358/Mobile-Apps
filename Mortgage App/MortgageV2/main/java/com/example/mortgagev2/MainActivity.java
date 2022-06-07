package com.example.mortgagev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Global mortgage object
    public static Mortgage mortgage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mortgage = new Mortgage();
        setContentView(R.layout.activity_main);
    }

    public void onStart()
    {
        super.onStart();
        updateView();

    }

    public void updateView( )
    {
        TextView amountTV = ( TextView ) findViewById( R.id.amount );
        amountTV.setText( "" + mortgage.getFormattedAmount( ) );

        TextView yearsTV = ( TextView ) findViewById( R.id.years );
        yearsTV.setText( "" + mortgage.getYears( ) );

        TextView rateTV = ( TextView ) findViewById( R.id.rate );
        rateTV.setText( "" + mortgage.getRate() * 100 + "%" );

        TextView monthlyTV = ( TextView ) findViewById( R.id.monthly_payment);
        monthlyTV.setText( "" + mortgage.formattedMonthlyPayment() );

        TextView totalTV = ( TextView ) findViewById( R.id.total_payment);
        totalTV.setText( "" + mortgage.formattedTotalPayment() );

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