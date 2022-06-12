package com.example.candystorev2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "candyDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CANDY = "candy";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";

    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    public void onCreate( SQLiteDatabase db ) {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_CANDY + "( " + ID;

        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + PRICE + " real )" ;

        db.execSQL( sqlCreate );
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_CANDY );

        // Re-create tables
        onCreate( db );
    }

    // One parameter - a Candy object that we insert into the Candy table
    public void insert( Candy candy ) {
        // Creates or opens a database that will be used for read and writing
        // onCreate, onUpgrade, and onOpen are all called when this method is first time called
        SQLiteDatabase db = this.getWritableDatabase( );

        // SQL insert statement
        String sqlInsert = "insert into " + TABLE_CANDY;

        // Candy table has three columns (id, name, price)
        // id is an int auto-increment so its null
        sqlInsert += " values( null, '" + candy.getName();
        sqlInsert += "', '" + candy.getPrice() + "' )";

        // Executes a SQL statement
        db.execSQL( sqlInsert );
        db.close( );
    }


    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );

        // SQL delete statement
        String sqlDelete = "delete from " + TABLE_CANDY;
        sqlDelete += " where " + ID + " = " + id;

        // Executes a SQL statement
        db.execSQL( sqlDelete );
        db.close( );
    }

    public void updateById( int id, String name, double price ) {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL update statement
        String sqlUpdate = "update " + TABLE_CANDY;

        sqlUpdate += " set " + NAME + " = '" + name + "', ";
        sqlUpdate += PRICE + " = '" + price + "'";
        sqlUpdate += " where " + ID + " = " + id;

        // Executes a SQL statement
        db.execSQL( sqlUpdate );
        db.close( );
    }

    public ArrayList<Candy> selectAll( ) {
        // Select All statement
        String sqlQuery = "select * from " + TABLE_CANDY;

        SQLiteDatabase db = this.getWritableDatabase( );

        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Candy> candies = new ArrayList<Candy>( );

        // Move this cursor to the next row
        while( cursor.moveToNext() ) {
            Candy currentCandy = new Candy( Integer.parseInt( cursor.getString( 0) ),
                    cursor.getString(1), cursor.getDouble( 2 ) );
            candies.add( currentCandy );
        }

        db.close( );
        return candies;
    }

    public Candy selectById( int id ) {
        // Construct sqlQuery, a select query
        // Select row in candy table whose id value is id
        String sqlQuery = "select * from " + TABLE_CANDY;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );

        // call rawQuery to execute the select query
        // returns a cursor
        Cursor cursor = db.rawQuery( sqlQuery, null );
        Candy candy = null;

        //  Move this cursor to first row. Like a loop for rows and columns of a table
        // Returns false if there is not another row to process
        if( cursor.moveToFirst() )
            candy = new Candy( Integer.parseInt( cursor.getString(0) ),
                    cursor.getString(1), cursor.getDouble( 2 ) );
        return candy;
    }
}
