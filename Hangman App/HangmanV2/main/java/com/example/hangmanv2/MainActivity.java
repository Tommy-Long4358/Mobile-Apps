package com.example.hangmanv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Hangman game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ( game == null )
            // Set Hangman game as the number of default guesses the game will give
            game = new Hangman(game.DEFAULT_GUESSES);

        setContentView( R.layout.activity_main );

        // TextView of the status of the game
        TextView status = (TextView) findViewById(R.id.status);

        // Get number of guesses left
        status.setText( ""  + game.getGuessesLeft());

        /* Adding a fragment to an activity:
             Get a reference to the fragment manager for this Activity.
             Create a fragment transaction.
             Create a fragment.
             Have the fragment transaction add the fragment to a container (a ViewGroup).
             Commit the fragment transaction.
         */
        GameStateFragment fragment = new GameStateFragment();
        if (getSupportFragmentManager().findFragmentById(R.id.game_state) == null)
        {
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.game_state, fragment , null).commit();
        }
    }

    // Do nothing method
    // needed for if a button is clicked and it keeps the game running
    public void play( View view ) {
    }

    public Hangman getGame()
    {
        return game;
    }
}