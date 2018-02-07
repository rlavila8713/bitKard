package cu.xkoders.presentationcard.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;


import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.activities.MainActivity;
import cu.xkoders.presentationcard.interfaces.OnActionPerformed;
import cu.xkoders.presentationcard.constants.FRAGMENTS_ACTIONS;

public class Home extends Fragment implements View.OnClickListener {

    OnActionPerformed listener;
    FloatingActionButton fab_facebook_option, fab_web_option, fab_email_option;
    FloatingActionButton fab_more_options;
    boolean fab_buttons_out;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnActionPerformed) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name) + " - " + getResources().getString(R.string.home));
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab_buttons_out = false;
        getView().findViewById(R.id.fab_email_option).setOnClickListener(this);
        //getView().findViewById(R.id.fab_phone).setOnClickListener(this);
        getView().findViewById(R.id.fab_facebook_option).setOnClickListener(this);
        getView().findViewById(R.id.fab_web_option).setOnClickListener(this);

        fab_facebook_option = (FloatingActionButton) getView().findViewById(R.id.fab_facebook_option);
        fab_web_option = (FloatingActionButton) getView().findViewById(R.id.fab_web_option);
        fab_email_option = (FloatingActionButton) getView().findViewById(R.id.fab_email_option);
        fab_more_options = (FloatingActionButton) getView().findViewById(R.id.fab_more_options);

        fab_more_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fab_facebook_option_show_anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_facebook_option_show);
                Animation fab_facebook_option_hide_anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_facebook_option_hide);

                Animation fab_web_option_show_anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_web_option_show);
                Animation fab_web_option_hide_anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_web_option_hide);

                Animation fab_email_option_show_anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_email_option_show);
                Animation fab_email_option_hide_anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fab_email_option_hide);

                FrameLayout.LayoutParams layoutParams_fab_facebook_option = (FrameLayout.LayoutParams) fab_facebook_option.getLayoutParams();
                FrameLayout.LayoutParams layoutParams_fab_web_option = (FrameLayout.LayoutParams) fab_web_option.getLayoutParams();
                FrameLayout.LayoutParams layoutParams_fab_email_option = (FrameLayout.LayoutParams) fab_email_option.getLayoutParams();

                if (fab_buttons_out) {
                    layoutParams_fab_facebook_option.rightMargin -= (int) (fab_facebook_option.getWidth() * 1.7);
                    layoutParams_fab_facebook_option.bottomMargin -= (int) (fab_facebook_option.getHeight() * 0.25);
                    fab_facebook_option.setLayoutParams(layoutParams_fab_facebook_option);
                    fab_facebook_option.startAnimation(fab_facebook_option_hide_anim);

                    layoutParams_fab_web_option.rightMargin -= (int) (fab_facebook_option.getWidth() * 1.5);
                    layoutParams_fab_web_option.bottomMargin -= (int) (fab_facebook_option.getHeight() * 1.5);
                    fab_web_option.setLayoutParams(layoutParams_fab_web_option);
                    fab_web_option.startAnimation(fab_web_option_hide_anim);

                    layoutParams_fab_email_option.rightMargin -= (int) (fab_facebook_option.getWidth() * 0.25);
                    layoutParams_fab_email_option.bottomMargin -= (int) (fab_facebook_option.getHeight() * 1.7);
                    fab_email_option.setLayoutParams(layoutParams_fab_email_option);
                    fab_email_option.startAnimation(fab_email_option_hide_anim);

                    fab_buttons_out = !fab_buttons_out;
                } else
                {
                    layoutParams_fab_facebook_option.rightMargin += (int) (fab_facebook_option.getWidth() * 1.7);
                    layoutParams_fab_facebook_option.bottomMargin += (int) (fab_facebook_option.getHeight() * 0.25);
                    fab_facebook_option.setLayoutParams(layoutParams_fab_facebook_option);
                    fab_facebook_option.startAnimation(fab_facebook_option_show_anim);

                    layoutParams_fab_web_option.rightMargin += (int) (fab_facebook_option.getWidth() * 1.5);
                    layoutParams_fab_web_option.bottomMargin += (int) (fab_facebook_option.getHeight() * 1.5);
                    fab_web_option.setLayoutParams(layoutParams_fab_web_option);
                    fab_web_option.startAnimation(fab_web_option_show_anim);

                    layoutParams_fab_email_option.rightMargin += (int) (fab_facebook_option.getWidth() * 0.25);
                    layoutParams_fab_email_option.bottomMargin += (int) (fab_facebook_option.getHeight() * 1.7);
                    fab_email_option.setLayoutParams(layoutParams_fab_email_option);
                    fab_email_option.startAnimation(fab_email_option_show_anim);

                    fab_buttons_out = !fab_buttons_out;
                }

                fab_facebook_option.setClickable(true);
            }
        });

    }

    @Override
    public void onClick(View v) {
        Bundle b = new Bundle();
        int pos = 0;
        switch (v.getId()) {
            case R.id.fab_email_option:
                b.putInt("pos", 0); //0 - "rlavila1387@gmail.com"
                pos = 0;
                break;
            case R.id.fab_facebook_option:
                b.putInt("pos", 2); //2 - "face"
                pos = 1;
                break;

            case R.id.fab_web_option:
                b.putInt("pos", 3); //3 - "web"
                pos = 3;
                break;


        }
        Log.v("POS-HOME", pos + "");
        listener.onActionPerformed(FRAGMENTS_ACTIONS.SHOW_ACTION_PROVIDER, b);
    }
}
