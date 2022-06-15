package com.example.candystorev5;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView();
    }

    // Build a View dynamically with all the candies
    public void updateView( )
    {
        ArrayList<Candy> candies = dbManager.selectAll( );

        if( candies.size( ) > 0 )
        {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );

            grid.setRowCount( candies.size() );
            grid.setColumnCount( 4 );

            // create arrays of components
            TextView[] ids = new TextView[candies.size()];
            EditText[][] namesAndPrices = new EditText[candies.size( )][2];
            Button[] buttons = new Button[candies.size( )];

            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );

            int width = size.x;
            int i = 0;
            for ( Candy candy : candies ) {
                // create the TextView for the candy's id
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + candy.getId( ) );

                // create the two EditTexts for the candy's name and price
                namesAndPrices[i][0] = new EditText( this );
                namesAndPrices[i][1] = new EditText( this );

                // Set the text inside the EditTexts of each row to the name and price of the candy
                namesAndPrices[i][0].setText( candy.getName() );
                namesAndPrices[i][1].setText( "" + candy.getPrice() );
                namesAndPrices[i][1]
                        .setInputType( InputType.TYPE_CLASS_NUMBER );

                // Give unique ID to each EditText
                namesAndPrices[i][0].setId( 10 * candy.getId() );
                namesAndPrices[i][1].setId( 10 * candy.getId( ) + 1 );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "UPDATE" );

                // Give each button the id of candy
                // match the candy id to update with the button clicked
                buttons[i].setId(candy.getId() );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                // current TextView, 10% of width, minimal height
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndPrices[i][0], ( int ) ( width * .4 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndPrices[i][1], ( int ) ( width * .15 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .35 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                i++;
            }
            scrollView.addView( grid );
            setContentView(scrollView);

        }
    }

    /*
        We retrieve the id of the candy that is being updated.
        Retrieve its edited name and price.
        Update the database.
        Show a Toast for feedback.
        Update the view.
     */
    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve name and price of the candy
            int candyId = v.getId( );

            EditText nameET = ( EditText ) findViewById(10 * candyId);
            EditText priceET = ( EditText ) findViewById( 10 * candyId + 1 );

            // Convert to String
            String name = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            // update candy in database
            try {
                double price = Double.parseDouble( priceString );

                // Update in database
                dbManager.updateById( candyId, name, price );

                // Toast to show update
                Toast.makeText( UpdateActivity.this, "Candy updated",
                        Toast.LENGTH_SHORT ).show();

                // update screen
                updateView( );
            } catch( NumberFormatException nfe ) {
                Toast.makeText( UpdateActivity.this,
                        "Price error", Toast.LENGTH_LONG ).show();
            }
        }
    }
}

