package cu.xkoders.presentationcard.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.constants.FRAGMENTS_ACTIONS;
import cu.xkoders.presentationcard.fragments.Briefing;
import cu.xkoders.presentationcard.fragments.Faq;
import cu.xkoders.presentationcard.fragments.Home;
import cu.xkoders.presentationcard.fragments.Services;
import cu.xkoders.presentationcard.interfaces.OnActionPerformed;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnActionPerformed {

    FloatingActionButton fab_test;
    ImageView imageView;
    Bitmap bitmap;
    public final static int QRcodeHeigth = 200;
    public final static int QRcodeWidth = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null)
            drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.contenedor, new Home()).commit();


        fab_test = (FloatingActionButton) findViewById(R.id.fab_facebook_option);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null)
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showQrDialog();
        }


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager = getSupportFragmentManager();

        switch (id) {
            case R.id.nav_home:
                manager.beginTransaction().replace(R.id.contenedor, new Home()).commit();
                break;
            case R.id.nav_briefing:
                manager.beginTransaction().replace(R.id.contenedor, new Briefing()).commit();
                break;
            case R.id.nav_contacts:
                startActivity(new Intent(MainActivity.this, cu.xkoders.presentationcard.activities.Contacts.class));
                break;
            case R.id.nav_services:
                manager.beginTransaction().replace(R.id.contenedor, new Services()).commit();
                break;
            case R.id.nav_directory:
                startActivity(new Intent(this, Directory.class));
                break;
            case R.id.nav_faq:
                manager.beginTransaction().replace(R.id.contenedor, new Faq()).commit();
                break;
            case R.id.nav_location:
                startActivity(new Intent(MainActivity.this, Mapa.class));
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_about:
                showQrDialog();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null)
            drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showQrDialog() {
        final PickQRDialog dialog = new PickQRDialog(MainActivity.this);

        dialog.setTitle(getResources().getString(R.string.title_activity_qr_code))
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
                        dialog.dismiss();
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

    private void showQrBimap(Bitmap bitmap) {
        final SelectedQRDialog dialog = new SelectedQRDialog(MainActivity.this);
        dialog.setQrBitmap(bitmap);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();

    }

    @Override
    public void onActionPerformed(int ACTION, Bundle bundle) {
        boolean call_flag = false;
        boolean showQrBitmap = false;
        int pos;
        Intent i = null;
        switch (ACTION) {
            case FRAGMENTS_ACTIONS.SHOW_ACTION_PROVIDER:
                pos = bundle.getInt("pos", 0);
                switch (pos) {
                    case 0:
                        i = new Intent(Intent.ACTION_SEND);
                        i.setType("plain/text");

                        i.putExtra(android.content.Intent.EXTRA_EMAIL,
                                new String[]{getResources().getString(R.string.cliente_email)});

                        i.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                "Saludos");

                        i.putExtra(android.content.Intent.EXTRA_TEXT,
                                "Estoy interesado en sus servicios");
                        break;
                    case 1:
                        i = new Intent(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel: " + getResources().getString(R.string.cliente_phone1)));
                        //set flag call
                        call_flag = true;
                        break;
                    case 2:
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.cliente_social)));
                        break;
                    case 3:
                        i = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.cliente_web)));
                        break;

                }

                if (call_flag && ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                break;
            case FRAGMENTS_ACTIONS.SHOW_ACTION_QR:
                pos = bundle.getInt("pos", 0);
                switch (pos) {
                    case 0:
                        try {
                            bitmap = TextToImageEncode("reinier");
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                        showQrBitmap = true;
                        showQrBimap(bitmap);

                        break;
                    case 1:
                        i = new Intent(this, QrCodeScannerActivity.class);
                        break;

                }

                break;
        }

        if(!showQrBitmap)
            startActivity(i);

    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeHeigth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();

        Log.v("bitMatrixWidth",bitMatrixWidth+"");
        Log.v("bitMatrixHeight",bitMatrixHeight+"");

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;
            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ? getResources().getColor(android.R.color.black) : getResources().getColor(android.R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, QRcodeWidth, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
