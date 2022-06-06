package com.example.tictactoev3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TicTacToe tttGame;
    private Button[][] buttons;
    private TextView status;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        // setContentView( R.layout.activity_main );

        // Instantiate TicTacToe object
        tttGame = new TicTacToe();
        buildGuiByCode( );
    }
    public void buildGuiByCode( ) {
        // Get width of the screen
        Point size = new Point( );

        //YOUR CODE – Retrieve the width of the screen
        // chain method call
        /*
        getDefaultDisplay() is a method of WindowManager class that returns a
        Display object and it contains a getSize() method to get the size of the screen
         */
        getWindowManager().getDefaultDisplay().getSize(size);

        //YOUR CODE – Assign one third of the width of the screen to a variable w
        int w = size.x / TicTacToe.SIDE;

        // Create the layout manager as a GridLayout
        // Construct a GridLayout within the app environment defined by context
        GridLayout gridLayout = new GridLayout( this );

        // Set the number of rows and columns for the GridLayout
        gridLayout.setColumnCount( TicTacToe.SIDE );

        // Extra row is needed for playing
        gridLayout.setRowCount( TicTacToe.SIDE + 1);

        // Instantiate a ButtonHandler object
        ButtonHandler bh = new ButtonHandler();

        // Create the buttons and add them to gridLayout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        for( int row = 0; row < TicTacToe.SIDE; row++ ) {
            for( int col = 0; col < TicTacToe.SIDE; col++ ) {
                // New button object
                buttons[row][col] = new Button(this );

                // Set the textsize for each button to w * 0.2
                buttons[row][col].setTextSize(w * 0.2f);

                // Register the event for each button
                buttons[row][col].setOnClickListener(bh);

                // Adding a View to the ViewGroup and uses w for height and width
                gridLayout.addView(buttons[row][col], w, w);
            }
        }
        // Set gridLayout as the View of this Activity
        setContentView( gridLayout );


        // set up layout parameters of 4th row of gridLayout
        status = new TextView( this );

        // A vertical span at row 3 and spans 1 row
        GridLayout.Spec rowSpec = GridLayout.spec( TicTacToe.SIDE, 1 );

        // A horizontal span that starts at column 0 and spans 3 columns
        GridLayout.Spec columnSpec = GridLayout.spec( 0, TicTacToe.SIDE );

        // Defines a rectangle of cells within the grid so that we can place a GUI component
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams( rowSpec, columnSpec );

        status.setLayoutParams( lpStatus );

        // set up status' characteristics
        status.setWidth( TicTacToe.SIDE * w);
        status.setHeight(TicTacToe.SIDE * w / 3);
        status.setGravity( Gravity.CENTER );
        status.setBackgroundColor( Color.GREEN );
        status.setTextSize( ( int ) ( w * .15 ) );
        status.setText( "PLAY !!" );

        gridLayout.addView( status );
    }

    // Once update is called, it updates the text of that row and column of a button to be "X"
    public void update(int row, int col)
    {
        // Plays the game and return player turn number based on legal rules
        int turn = tttGame.play(row, col);

        // Write the symbol to the Button of that certain player
        if (turn == 1)
        {
            buttons[row][col].setText("X");
        }
        else if (turn == 2)
        {
            buttons[row][col].setText("O");
        }

        // disable button clicking if game is over like if winner is determined or if all cell blocks are taken
        if (tttGame.isGameOver())
        {

            status.setBackgroundColor(Color.RED);
            enableButtons(false);
            status.setText(tttGame.result());

        }

        Log.w("MainActivity", "Inside update: " + row + ", " + col);

    }

    //Implement the ButtonHandler event
    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            Log.w( "MainActivity", "Inside onClick, v = " + v );

            /*
             Loop through 2D array of buttons until we find the Button that was clicked
             which is in this case View v and update the View by calling update()
             */
            for( int row = 0; row < TicTacToe.SIDE; row++ )
                for( int column = 0; column < TicTacToe.SIDE; column++ )
                    if ( v == buttons[row][column] )
                        update(row, column);
        }
    }

    public void enableButtons( boolean enabled ) {
        for( int row = 0; row < TicTacToe.SIDE; row++ )
            for( int col = 0; col < TicTacToe.SIDE; col++ )

                // enables us to either enable or disable a Button
                /*
                if true - button calling method is enabled
                if false - button calling method is disabled
                 */
                buttons[row][col].setEnabled( enabled );
    }
}