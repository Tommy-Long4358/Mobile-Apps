package com.example.tictactoev5;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

// Class "is" a GridLayout.
public class ButtonGridAndTextView extends GridLayout {

    // The number of rows and columns in buttons
    private int side;

    // 2D Array of buttons
    private Button[][] buttons;

    // A TextView
    private TextView status;

    /**
     *
     * @param context - instantiate the widgets of the View (Buttons and TextView)
     *                  An activity is a Context
     * @param width - Dimensions of the View
     * @param newSide - number of rows and columns
     * @param listener - Sets up event handling
     */
    public ButtonGridAndTextView(Context context, int width, int newSide,
                                 OnClickListener listener ) {
        super(context);

        side = newSide;

        // Set # of rows and columns of this GridLayout
        setColumnCount(side);
        setRowCount(side + 1);

        // Create the buttons and add them to this GridLayout
        //YOUR CODE

        buttons = new Button[side][side];
        for( int row = 0; row < side; row++ )
        {
            for( int col = 0; col < side; col++ )
            {
                buttons[row][col] = new Button(context);

                buttons[row][col].setTextSize(width * 0.2f);

                buttons[row][col].setOnClickListener(listener);

                // create and add TextView at the bottom
                addView(buttons[row][col], width, width);
            }
        }


        // set up layout parameters of 4th row of gridLayout
        //YOUR CODE
        status = new TextView(context);

        // A vertical span at row 3 and spans 1 row
        GridLayout.Spec rowSpec = GridLayout.spec(side, 1);

        // A horizontal span that starts at column 0 and spans 3 columns
        GridLayout.Spec columnSpec = GridLayout.spec(0, side);

        // Defines a rectangle of cells within the grid so that we can place a GUI component
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, columnSpec);

        status.setLayoutParams(lpStatus);

        // set up status' characteristics
        status.setWidth(side * width);
        status.setHeight(side * width / 3);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int) (width * .15));
        status.setText("PLAY !!");

        addView(status);
    }

    // set the text of the TextView status
    public void setStatusText( String text ) {
        status.setText( text );
    }

    // Set the background color of the TextView status
    public void setStatusBackgroundColor( int color ) {
        status.setBackgroundColor( color );
    }

    // Set the text of a particular button
    public void setButtonText( int row, int column, String text ) {
        buttons[row][column].setText( text );
    }

    // button comparison for same equal
    public boolean isButton( Button b, int row, int column ) {
        return ( b == buttons[row][column] );
    }

    // Reset the text of all the buttons to the empty String (for new game)
    public void resetButtons( ) {
        for( int row = 0; row < side; row++ )
            for( int col = 0; col < side; col++ )
                buttons[row][col].setText( "" );
    }

    // Disable or enable all the buttons (for new game)
    public void enableButtons( boolean enabled ) {
        for( int row = 0; row < side; row++ )
            for( int col = 0; col < side; col++ )
                buttons[row][col].setEnabled( enabled );
    }
}
