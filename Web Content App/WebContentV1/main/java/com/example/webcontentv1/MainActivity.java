package com.example.webcontentv1;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    /** Creates a SAXParser object to read data from the test.xml file and parse start and end tags and characters.
     *
     * @param savedInstanceState
     */
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        try {
            // Create SAXParserFactory object to make a SAXParser object
            SAXParserFactory factory = SAXParserFactory.newInstance( );
            SAXParser saxParser = factory.newSAXParser( );

            // SAXHandler object
            SAXHandler handler = new SAXHandler( );

            // Open an inputstream to read data from the test.xml file in the raw directory
            InputStream is = getResources( ).openRawResource( R.raw.test );
            saxParser.parse( is, handler );

            ArrayList<Item> items = handler.getItems();

            for (Item item: items)
            {
                Log.w("MainActivity", item.getTitle() + "; " + item.getLink());
            }
        }
        catch ( Exception e ) {
            Log.w( "MainActivity", "e = " + e.toString( ) );
        }
    }
}