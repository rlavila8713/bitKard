package cu.xkoders.presentationcard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.activities.MainActivity;


public class About extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name)+" - "+getResources().getString(R.string.action_about));
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
