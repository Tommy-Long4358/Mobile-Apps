package com.example.webcontentv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class MainActivity extends AppCompatActivity {
    private final String URL = "https://www.nasa.gov/rss/dyn/educationnews.rss";
    private ArrayList<Item> items;

    /** A method to read the contents of an URL on a separate thread and display that data in the main thread
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Executor encapsulates managing and distributing tasks to different Threads.
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // A Looper manages tasks that a Thread will run. It puts them into a queue for the Task to execute
        Handler handler = new Handler(Looper.getMainLooper());

        // Opens the URL in a different thread since the main thread doesn't allow URLs to be opened.
        // Fills the list with the data from the URL to be put in the main thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Do background here
                try {
                    // Create SAXParserFactory object to make a SAXParser object
                    SAXParserFactory factory = SAXParserFactory.newInstance( );
                    SAXParser saxParser = factory.newSAXParser( );

                    // SAXHandler object
                    SAXHandler handler = new SAXHandler( );

                    // Reads the contents of the URL and uses the SAXHandler to handle events
                    saxParser.parse( URL, handler );

                    items = handler.getItems();
                }
                catch (Exception e)
                {
                    Log.w("MainActivity", e.toString());

                }

                // Run on main thread
                handler.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        // Call displayList
                        displayList(items);
                    }
                });
            }
        });
    }

    /** Displays the title and link from an Item object using the Log class
     *
     * @param items - ArrayList of Item objects
     */
    public void displayList (ArrayList < Item > items)
    {
        if (items != null)
        {
            for (Item item : items)
                Log.w("MainActivity", item.toString());
        }
    }
}