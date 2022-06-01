package com.example.mortgagev2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
public class DataActivity extends AppCompatActivity
{
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        //__________________________________
        //___________________________________
    }

    public void updateView()
    {
        Mortgage mortgage = MainActivity.mortgage;

        if( mortgage.getYears( ) == 10 )
        {
            RadioButton rb10 = ( RadioButton ) findViewById( R.id.ten );
            rb10.setChecked( true );
        }
        else if ()
        {

        }

        EditText amountET = ( EditText ) findViewById( R.id.data_amount );
        amountET.setText( "" + mortgage.getAmount( ) );

        //…………………………………………………………
}
    public void updateMortgageObject( )
    {
        Mortgage mortgage = MainActivity.mortgage;

        RadioButton rb10 = ( RadioButton ) findViewById( R.id.ten );
        RadioButton rb15 = ( RadioButton ) findViewById( R.id.fifteen );

        int years = 30;
        if( rb10.isChecked( ) )
            years = 10;
        else if ()
        //……………………………………

        mortgage.setYears( years );

        EditText amountET = ( EditText ) findViewById( R.id.data_amount );
        String amountString = amountET.getText( ).toString( );
        //………………………………………………………………..

        try
        {
            float amount = Float.parseFloat( amountString );
            mortgage.setAmount( amount );

            // ………………………………………………………………..
        }
        catch( NumberFormatException nfe )
        {
            mortgage.setAmount( 100000.0f );
            mortgage.setRate( .035f );
        }
    }
    public void goBack( View v )
    {
        //________________________
        //__________________________
    }
}
