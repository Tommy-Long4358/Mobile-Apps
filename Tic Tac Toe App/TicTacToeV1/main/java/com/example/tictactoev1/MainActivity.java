package com.example.tictactoev1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    private Button[][] buttons;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        // setContentView( R.layout.activity_main );
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
        GridLayout gridLayout = new GridLayout( this );

        gridLayout.setColumnCount( TicTacToe.SIDE );
        gridLayout.setRowCount( TicTacToe.SIDE );

        // Create the buttons and add them to gridLayout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        for( int row = 0; row < TicTacToe.SIDE; row++ ) {
            for( int col = 0; col < TicTacToe.SIDE; col++ ) {
                buttons[row][col] = new Button(this );
                gridLayout.addView(buttons[row][col], w, w);
            }
        }
        // Set gridLayout as the View of this Activity
        setContentView( gridLayout );
    }
}