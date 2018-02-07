package cu.xkoders.presentationcard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.view.MapView;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.activities.MainActivity;


public class Gallery extends Fragment {
    private MapView mapView;
    private View view;
    private static final String MAP_FILE = "cuba.map";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name)+" - "+getResources().getString(R.string.gallery));
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
