package com.example.webcontentv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    private final String URL = "https://www.nasa.gov/rss/dyn/educationnews.rss";
    private ArrayList<Item> items;
    private ListView listView;

    /** A method to read the contents of an URL on a separate thread and display that data in the main thread
     *
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);

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
            ArrayList<String> titles = new ArrayList<String>();

            // Add all titles to an ArrayList
            for (Item item : items)
                titles.add(item.getTitle());

            /*
            ListAdapter interface is a bridge between the list of data and a ListView.
             */
            // Populate the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);

            // Sets the ListAdapter to ListView. Stores the list of data
            // backing this ListView and produces a View for each item in the list of data
            listView.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(this, "Sorry - No data found", Toast.LENGTH_LONG).show();
        }
    }
}