package cu.xkoders.presentationcard.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.activities.MainActivity;
import cu.xkoders.presentationcard.adapters.ExpandableListAdapter;


public class Services extends Fragment {



    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name)+" - "+getResources().getString(R.string.services));
        View view =inflater.inflate(R.layout.fragment_services, container, false);



        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExpServices);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);



        return view;
    }

    /*
   * Preparing the list data
   */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getResources().getString(R.string.services_1));
        listDataHeader.add(getResources().getString(R.string.services_2));
        listDataHeader.add(getResources().getString(R.string.services_3));
        listDataHeader.add(getResources().getString(R.string.services_4));
        listDataHeader.add(getResources().getString(R.string.services_5));


        // Adding child data
        List<String> services_1 = new ArrayList<String>();
        services_1.add(getResources().getString(R.string.services_1_descripcion));

        List<String> services_2 = new ArrayList<String>();
        services_2.add(getResources().getString(R.string.services_2_descripcion));

        List<String> services_3 = new ArrayList<String>();
        services_3.add(getResources().getString(R.string.services_3_descripcion));

        List<String> services_4 = new ArrayList<String>();
        services_4.add(getResources().getString(R.string.services_4_descripcion));


        List<String> services_5 = new ArrayList<String>();
        services_5.add(getResources().getString(R.string.services_5_descripcion));


        listDataChild.put(listDataHeader.get(0), services_1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), services_2);
        listDataChild.put(listDataHeader.get(2), services_3);
        listDataChild.put(listDataHeader.get(3), services_4);
        listDataChild.put(listDataHeader.get(4), services_5);
    }
}
