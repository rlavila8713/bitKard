package cu.xkoders.presentationcard.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cu.xkoders.presentationcard.R;

public class Contacts extends AppCompatActivity {
    RadioButton phone1;
    RadioButton phone2;
    RadioGroup radioGroup;
    boolean flag_phone_1, flag_phone_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        setToolbar();

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);


        CardView email_card = (CardView) findViewById(R.id.email_card);
        assert email_card != null;
        email_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("Getting access to Email!!!");


                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("plain/text");

                i.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{getResources().getString(R.string.cliente_email)});

                i.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        "Saludos");

                i.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Estoy interesado en sus servicios");

                startActivity(i);
            }
        });


        CardView phone_card = (CardView) findViewById(R.id.phone_card);
        assert phone_card != null;
        phone_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showSnackBar("Getting access to Phone!!!");

                final PickPhoneDialog dialog = new PickPhoneDialog(Contacts.this);

                dialog.setTitle(getResources().getString(R.string.cliente_phone_header))
                        //Use this if you want to set a text message
                        //.setMessage("Put your text message here.")

                        //Use this for a custom layout resource
                        //.setCustomViewResource(R.layout.dialog_test_layout);

                        //Or pass the View
                        //.setCustomView(yourView);

                        //Set cancelable on touch outside (default true)
                        //.dismissOnTouchOutside(false)
                        .setupPositiveButton(getResources().getString(R.string.action_acept), new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                //int option = radioGroup.getCheckedRadioButtonId();
                                showSnackBar("Calling to " + dialog.getPhoneSelected());
                                Intent i = new Intent(Intent.ACTION_CALL);
                                i.setData(Uri.parse("tel: " + dialog.getPhoneSelected()));
                                //set flag call


                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.
                                    return;
                                }

                                startActivity(i);
                            }

                        })

                        .setupNegativeButton(getResources().getString(R.string.action_decline), new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }

                        });
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.show();
            }
        });

        CardView web_card = (CardView) findViewById(R.id.web_card);
        assert web_card != null;
        web_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("Getting access to Web!!!");
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.cliente_web)));
                startActivity(i);
            }
        });

        CardView face_card = (CardView) findViewById(R.id.face_card);
        assert face_card != null;
        face_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar("Getting access to Face!!!");
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.cliente_social)));
                startActivity(i);
            }
        });
    }

    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }


    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
