package cu.xkoders.presentationcard.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.adapters.ViewPagerAdapter;
import cu.xkoders.presentationcard.components.SlidingTabLayout;
import cu.xkoders.presentationcard.interfaces.OnActionPerformed;

public class Directory extends AppCompatActivity implements OnActionPerformed {
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;

    //posteriormente este dato saldra de una db
    CharSequence Titles[] = {"Corporativos", "Profesionales"};
    int Numboftabs = Titles.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);*/


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public void onActionPerformed(int ACTION, Bundle bundle) {

    }
}
