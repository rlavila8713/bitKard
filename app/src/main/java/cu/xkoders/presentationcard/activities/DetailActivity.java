package cu.xkoders.presentationcard.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.models.ContactoBK;

public class DetailActivity extends AppCompatActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/

    private static final String EXTRA_NAME = "cu.xkoders.presentationcard.name";
    private static final String EXTRA_DRAWABLE = "cu.xkoders.presentationcard.drawable";

    /**
     * Inicia una nueva instancia de la actividad
     *
     * @param activity Contexto desde donde se lanzará
     * @param obj    Item a procesar
     */
    public static void createInstance(Activity activity, ContactoBK obj) {
        Intent intent = getLaunchIntent(activity, obj);
        activity.startActivity(intent);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     *
     * @param context    Contexto donde se inicia
     * @param contactoBK Identificador de la chica
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, ContactoBK contactoBK) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_NAME, contactoBK.getNombre());
        intent.putExtra(EXTRA_DRAWABLE, contactoBK.getImagen());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactobk_detail);

        setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String name = i.getStringExtra(EXTRA_NAME);
        int idDrawable = i.getIntExtra(EXTRA_DRAWABLE, -1);

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);
        if(collapser!=null)
        collapser.setTitle(name); // Cambiar título

        loadImageParallax(idDrawable);// Cargar Imagen

        // Setear escucha al FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab!=null)

        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSnackBar("Opción de Chatear");
                    }
                }
        );


    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Se carga una imagen aleatoria para el detalle
     */
    private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    }

    /**
     * Proyecta una {@link Snackbar} con el string usado
     *
     * @param msg Mensaje
     */
    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


}
