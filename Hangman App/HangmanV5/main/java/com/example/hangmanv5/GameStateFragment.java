package com.example.hangmanv5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GameStateFragment extends Fragment {
    public GameStateFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game_state,
                container, false);
    }


    public void onStart( ) {
        super.onStart();

        // Get View of the fragment
        View fragmentView = getView();

        // Assign the fragmentView ID to a TextView
        TextView gameStateTV = (TextView) fragmentView.findViewById(R.id.state_of_game);

        // Get a reference to the parent Activity of this fragment and cast to MainActivity
        MainActivity fragmentActivity = (MainActivity) getActivity();

        // Set the TextView to the game's incomplete word
        gameStateTV.setText(fragmentActivity.getGame().currentIncompleteWord());
    }

}
