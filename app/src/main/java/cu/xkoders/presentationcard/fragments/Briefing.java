package cu.xkoders.presentationcard.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.activities.MainActivity;


public class Briefing extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_briefing, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name)+" - "+getResources().getString(R.string.briefing));
        return view;
    }
}
