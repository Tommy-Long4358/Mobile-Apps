package com.example.hangmanv3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hangmanv3.GameStateFragment;
import com.example.hangmanv3.Hangman;

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
        // Game State is the top right pane where you guess a letter
        if (getSupportFragmentManager().findFragmentById(R.id.game_state) == null)
        {
            GameStateFragment fragment = new GameStateFragment();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.game_state, fragment , null).commit();
        }

        // Game Result is the bottom right pane where it says warnings
        if (getSupportFragmentManager().findFragmentById(R.id.game_result) == null) {
            GameResultFragment fragment = new GameResultFragment();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.game_result, fragment, null).commit();
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