package com.example.hangmanv6;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GameResultFragment extends Fragment
{
    private TextView gameResultTV;
    private float ourFontsize = 50f;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState ) {
        setUpFragmentGui( container );
        return super.onCreateView( inflater, container,
                savedInstanceState ) ;
    }

    public void setUpFragmentGui( ViewGroup container ) {
        if( gameResultTV == null ) {
            // Add to the root View of the fragment
            gameResultTV = new TextView(getActivity());

            // Center the TextView
            gameResultTV.setGravity( Gravity.CENTER );

            // Set text size
            gameResultTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, ourFontsize);

            // Add to ViewGroup
            container.addView( gameResultTV );
        }
    }
    public void onStart( ) {
        super.onStart( );
        gameResultTV.setText( "GOOD LUCK" );
    }
    public void setResult( String result ) {
        gameResultTV.setText( result );
    }
}
