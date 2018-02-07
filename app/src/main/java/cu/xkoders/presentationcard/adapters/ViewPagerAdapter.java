package cu.xkoders.presentationcard.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cu.xkoders.presentationcard.fragments.Briefing;
import cu.xkoders.presentationcard.fragments.Corporativos;
import cu.xkoders.presentationcard.fragments.Services;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment mFragment = null;
        switch (position) {
            case 0:
                Corporativos corporativos= new Corporativos();
                mFragment = corporativos;
                break;
            case 1:
                Corporativos corporativos1= new Corporativos();
                mFragment = corporativos1;
                break;

        }
        return mFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
