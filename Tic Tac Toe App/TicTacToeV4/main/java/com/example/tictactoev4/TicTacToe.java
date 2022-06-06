package com.example.tictactoev4;

public class TicTacToe
{
    // Row and Column length of the game
    public static final int SIDE = 3;

    // Player's turn
    private int turn;

    // 2D Array game board
    private int [][] game;

    // Constructor
    public TicTacToe( ) {
        game = new int[SIDE][SIDE];
        resetGame( );
    }

    // If the play is legal and the board cell is not taken,
    // plays and returns the old value of turn
    public int play( int row, int col ) {
        // Current player's turn
        int currentTurn = turn;

        // if its greater than 0 and less than the side and its not occupied,
        // put the player's number onto that row and column
        if( row >= 0 && col >= 0 && row < SIDE && col < SIDE
                && game[row][col] == 0 ) {
            game[row][col] = turn;

            // alternate turns
            if( turn == 1 )
                turn = 2;
            else
                turn = 1;

            // return next player's turn
            return currentTurn;
        }
        else
            // invalid spot
            return 0;
    }

    // Checks rows, columns, and diagonals for winner
    public int whoWon( ) {
        int rows = checkRows( );
        if ( rows > 0 )
            return rows;

        int columns = checkColumns( );
        if( columns > 0 )
            return columns;

        int diagonals = checkDiagonals( );
        if( diagonals > 0 )
            return diagonals;

        // no winner yet
        return 0;
    }

    // checks every row in the grid
    protected int checkRows( ) {
        for( int row = 0; row < SIDE; row++ )
            if ( game[row][0] != 0 && game[row][0] == game[row][1]
                    && game[row][1] == game[row][2] )
                return game[row][0];
        return 0;
    }

    // checks every column in the grid
    protected int checkColumns( ) {
        for( int col = 0; col < SIDE; col++ )
            if ( game[0][col] != 0 && game[0][col] == game[1][col]
                    && game[1][col] == game[2][col] )
                return game[0][col];
        return 0;
    }

    // checks every diagonal in the grid
    protected int checkDiagonals( ) {
        if ( game[0][0] != 0 && game[0][0] == game[1][1]
                && game[1][1] == game[2][2] )
            return game[0][0];
        if ( game[0][2] != 0 && game[0][2] == game[1][1]
                && game[1][1] == game[2][0] )
            return game[2][0];
        return 0;
    }

    // determines if there is atleast one cell not taken on the board
    public boolean canNotPlay( ) {
        boolean result = true;
        for (int row = 0; row < SIDE; row++)
            for( int col = 0; col < SIDE; col++ )
                if ( game[row][col] == 0 )
                    result = false;
        return result;
    }

    // determines if a game is over by if there is a winner or all spaces are taken
    public boolean isGameOver( ) {
        return canNotPlay( ) || ( whoWon( ) > 0 );
    }

    // reset the board
    public void resetGame( ) {
        for (int row = 0; row < SIDE; row++)
            for( int col = 0; col < SIDE; col++ )
                game[row][col] = 0;
        turn = 1;
    }

    // return string of who won
    public String result()
    {
        if (whoWon() == 1)
        {
            return "Player 1 Wins!";
        }
        else if (whoWon() == 2)
        {
            return "Player 2 Wins!";
        }
        else if (canNotPlay())
        {
            return "Tie!";
        }

        return "PLAY !!";
    }


}

