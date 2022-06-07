package com.example.tictactoev5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    private TicTacToe game;
    private ButtonGridAndTextView tttView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        ButtonHandler bh = new ButtonHandler( );
        Point size = new Point();
        game = new TicTacToe();

        getWindowManager().getDefaultDisplay().getSize(size);

        int w = size.x / TicTacToe.SIDE;

        tttView = new ButtonGridAndTextView(this, w, TicTacToe.SIDE, bh);
        tttView.setStatusText(game.result());

        setContentView( tttView );
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            for( int row = 0; row < TicTacToe.SIDE; row++ )
            {
                for( int column = 0; column < TicTacToe.SIDE; column++ ) {

                    if( tttView.isButton( (Button) v, row, column ))
                    {
                        int play = game.play( row, column );
                        if( play == 1 )
                            tttView.setButtonText(row, column, "X");

                        else if( play == 2 )
                            tttView.setButtonText(row, column, "O");

                        if( game.isGameOver() ) {
                            tttView.setStatusBackgroundColor(Color.RED );
                            tttView.enableButtons(false);
                            tttView.setStatusText( game.result() );
                            showNewGameDialog( ); // offer to play again
                        }
                    }
                }
            }
        }
    }

    public void showNewGameDialog()
    {
        // Pop up a dialog box
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // Title of alert
        alert.setTitle("This is fun!");

        // Message of alert
        alert.setMessage("Play again?");

        PlayDialog playAgain = new PlayDialog();

        // reset the game and buttons
        alert.setPositiveButton("YES", playAgain);

        // end the game
        alert.setNegativeButton("NO", playAgain);

        // Shows the alert
        alert.show();
    }

    private class PlayDialog implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int id)
        {
            // Reset game is id is -1, end the game if its -2
            if (id == -1)
            {
                // Reset the board
                game.resetGame();

                // Make all buttons clickable
                tttView.enableButtons(true);

                // Gets rid of "X" and "O"
                tttView.resetButtons();

                // Set the bottom TextView as green colored
                tttView.setBackgroundColor(Color.GREEN);

                // Display the "PLAY!!" message
                tttView.setStatusText(game.result());
            }
            else if (id == -2)
            {
                // End the app
                MainActivity.this.finish();
            }
        }
    }

}