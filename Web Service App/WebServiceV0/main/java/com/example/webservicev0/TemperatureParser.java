package com.example.webservicev0;

import org.json.JSONException;
import org.json.JSONObject;
public class TemperatureParser {
    public static final double ZERO_K = -273.15;
    private final String MAIN_KEY = "main";
    private final String TEMPERATURE_KEY = "temp";
    private JSONObject jsonObject;

    /** TempeartureParser constructor that uses JSON to retrieve data from a link and made it formatted as JSONObject
     *
     * @param json - data to be turned into JSONObject
     */
    public TemperatureParser( String json )
    {
        try
        {
            jsonObject = new JSONObject(json);
        }
        catch( JSONException jsonException ) {
        }
    }

    /** Retrieve the current temperature within the JSON string
     *
     * @return the tempearture in Kelvin from the JSONObject "temp" key
     */
    public double getTemperatureK( )
    {
        try
        {

            JSONObject jsonMain = jsonObject.getJSONObject(MAIN_KEY);
            return jsonMain.getDouble(TEMPERATURE_KEY);
        }
        catch( Exception jsonException )
        {
            return 25 - ZERO_K;
        }
    }

    public int getTemperatureC( ) {
        return ( int ) ( getTemperatureK( ) + ZERO_K + 0.5 );
    }

    public int getTemperatureF( ) {
        return ( int ) ( ( getTemperatureK( ) + ZERO_K ) * 9 / 5 + 32 + 0.5 );
    }
}

