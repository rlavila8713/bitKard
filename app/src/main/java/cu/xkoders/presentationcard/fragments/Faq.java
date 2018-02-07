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


public class Faq extends Fragment {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name)+" - "+getResources().getString(R.string.faq));
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_faq, container, false);


        // get the listview
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return v;
    }

    /*
    * Preparing the list data
    */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add(getResources().getString(R.string.faq_question1));
        listDataHeader.add(getResources().getString(R.string.faq_question2));
        listDataHeader.add(getResources().getString(R.string.faq_question3));
        listDataHeader.add(getResources().getString(R.string.faq_question4));
        listDataHeader.add(getResources().getString(R.string.faq_question5));


        // Adding child data
        List<String> faq_question1 = new ArrayList<String>();
        faq_question1.add(getResources().getString(R.string.faq_question1_answer));

        List<String> faq_question2 = new ArrayList<String>();
        faq_question2.add(getResources().getString(R.string.faq_question2_answer));

        List<String> faq_question3 = new ArrayList<String>();
        faq_question3.add(getResources().getString(R.string.faq_question3_answer));

        List<String> faq_question4 = new ArrayList<String>();
        faq_question4.add(getResources().getString(R.string.faq_question4_answer1));
        faq_question4.add(getResources().getString(R.string.faq_question4_answer2));
        faq_question4.add(getResources().getString(R.string.faq_question4_answer3));
        faq_question4.add(getResources().getString(R.string.faq_question4_answer4));

        List<String> faq_question5 = new ArrayList<String>();
        faq_question5.add(getResources().getString(R.string.faq_question5_answer));


        listDataChild.put(listDataHeader.get(0), faq_question1); // Header, Child data
        listDataChild.put(listDataHeader.get(1), faq_question2);
        listDataChild.put(listDataHeader.get(2), faq_question3);
        listDataChild.put(listDataHeader.get(3), faq_question4);
        listDataChild.put(listDataHeader.get(4), faq_question5);
    }
}
