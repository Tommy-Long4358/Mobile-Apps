package com.example.hangmanv3;

import java.util.Random;

public class Hangman {
    public static int DEFAULT_GUESSES = 6;
    private String [] words = { "ANDROID", "JAVA", "APP", "MOBILE" };
    private String word;
    private boolean [] indexesGuessed;
    private int guessesAllowed;
    private int guessesLeft;
    public Hangman(int guesses ) {
        // Default to 6 guesses if parameter is less than 0
        if( guesses > 0 )
            guessesAllowed = guesses;
        else
            guessesAllowed = DEFAULT_GUESSES;

        // GuessesLeft is set to guessedAllowed as the player hasn't guessed yet
        guessesLeft = guessesAllowed;

        Random random = new Random( );

        // Choose random word
        int index = random.nextInt( words.length );
        word = words[index];

        // Boolean array on which letter was guessed or not yet
        indexesGuessed = new boolean[word.length( )];
    }
    public int getGuessesAllowed( ) {
        return guessesAllowed;
    }
    public int getGuessesLeft( ) {
        return guessesLeft;
    }

    public void guess( char c ) {
        boolean goodGuess = false;
        for( int i = 0; i < word.length( ); i++ ) {
            if( !indexesGuessed[i] && c == word.charAt( i ) ) {
                indexesGuessed[i] = true;
                goodGuess = true;
            }
        }
        if( !goodGuess )
            guessesLeft--;
    }
    public String currentIncompleteWord( ) {
        String guess = "";
        for( int i = 0; i < word.length( ); i++ )
            if( indexesGuessed[i] )
                guess += word.charAt( i ) + " ";
            else
                guess += "_ ";
        return guess;
    }
    public int gameOver( ) {
        boolean won = true;
        for( int i = 0; i < indexesGuessed.length; i++ )
            if( indexesGuessed[i] == false ) {
                won = false;
                break;
            }
        if( won ) // won
            return 1;
        else if( guessesLeft == 0 ) // lost
            return -1;
        else // game not over
            return 0;
    }
}

