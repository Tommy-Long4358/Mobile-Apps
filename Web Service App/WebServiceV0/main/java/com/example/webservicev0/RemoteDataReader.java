package com.example.webservicev0;

import java.io.BufferedReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RemoteDataReader {
    // URL of website
    private String urlString;

    /** Creates a full URL string for the website
     *
     * @param baseUrl - "https://"
     * @param cityString - city name
     * @param keyName - key name
     * @param key - key
     */
    public RemoteDataReader( String baseUrl, String cityString,
                             String keyName, String key ) {

        String urlString = baseUrl + cityString + keyName + key;
    }

    /** Return the JSON as a String
     *
     * @return JSON as a string
     */
    public String getData( )
    {
        try
        {
            // Establish the connection
            URL url = new URL( urlString );
            HttpURLConnection con = ( HttpURLConnection ) url.openConnection();
            con.connect( );

            int code = con.getResponseCode();
            if (code != 200)
            {
                Log.w("MainActivity", String.valueOf(code));
                throw new IOException("Invalid response from server: " + code);
            }

            // Get the input stream and prepare to read
            InputStream is = con.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Read the data
            String dataRead = new String( );
            String line = br.readLine( );
            while ( line != null ) {
                dataRead += line;
                line = br.readLine( );
            }

            is.close( );

            con.disconnect( );

            return dataRead;
        }
        catch( Exception e )
        {
            return "";
        }
    }
}
