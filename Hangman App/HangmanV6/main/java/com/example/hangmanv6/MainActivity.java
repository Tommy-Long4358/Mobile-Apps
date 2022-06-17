package com.example.hangmanv6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
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
        // Game State is the top right pane where you guess a letter
        if (getSupportFragmentManager().findFragmentById(R.id.game_state) == null)
        {
            GameStateFragment fragment = new GameStateFragment();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.game_state, fragment , null).commit();
        }

        // Game Result is the bottom right pane
        if (getSupportFragmentManager().findFragmentById(R.id.game_result) == null)
        {
            GameResultFragment fragment = new GameResultFragment();
            getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.game_result, fragment, null).commit();
        }

        // Background fragment for giving a warning
        if( getSupportFragmentManager().findFragmentByTag("background") == null ) {
            BackgroundFragment fragment = new BackgroundFragment();
            getSupportFragmentManager().beginTransaction( ).setReorderingAllowed(true).add(fragment, "background").commit();
        }
    }

    // Do nothing method now
    public void play(View view) {

    }

    // no button needed to be pressed after typing a letter
    public void play()
    {
        // Get letter that the user guessed
        EditText input = (EditText) findViewById(R.id.letter);
        Editable userText = input.getText();

        // User only allowed to enter only one letter at a time
        if (userText != null && userText.length() > 0) {
            // User only allowed to enter only one letter at a time
            char letter = userText.charAt(0);

            game.guess(letter);

            // Update status of the number of guesses left
            TextView status = (TextView) findViewById(R.id.status);
            status.setText("" + game.getGuessesLeft());

            // update incomplete word
            // Get a reference to the fragment and to the
            // GameStateFragment of the id of the TextView that exists in it
            GameStateFragment gsFragment = (GameStateFragment)
                    getSupportFragmentManager().findFragmentById(R.id.game_state);

            // Get a reference to the fragment's view
            View gsFragmentView = gsFragment.getView();

            // Get a reference to the TextView of the fragment's view
            TextView gameStateTV = (TextView)
                    gsFragmentView.findViewById(R.id.state_of_game);

            // Set TextView of the fragment to the incomplete word not fully guessed
            gameStateTV.setText(game.currentIncompleteWord());

            // clear EditText
            input.setText("");


            // check if there is only one guess left
            if (game.getGuessesLeft() == 1) {
                // Get reference to background fragment
                BackgroundFragment background = (BackgroundFragment)
                        getSupportFragmentManager().findFragmentByTag("background");

                // Get reference to game result fragment
                GameResultFragment grFragment = (GameResultFragment)
                        getSupportFragmentManager().findFragmentById(R.id.game_result);

                // retrieve warning and display it
                // have game result fragment display the warning of the background fragment
                grFragment.setResult(background.warning());
            }

            int result = game.gameOver();
            if (result != 0) /* game is over */ {
                GameResultFragment grFragment = (GameResultFragment)
                        getSupportFragmentManager().findFragmentById(R.id.game_result);

                // update TextView in result fragment
                if (result == 1)
                    grFragment.setResult("YOU WON");
                else if (result == -1)
                    grFragment.setResult("YOU LOST");

                // delete hint in EditText
                input.setHint("");
            }
        }
    }

    public Hangman getGame()
    {
        return game;
    }
}