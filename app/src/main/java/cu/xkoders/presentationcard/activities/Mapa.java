package cu.xkoders.presentationcard.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.components.MyMarker;

public class Mapa extends AppCompatActivity {
    private MapView mapView;
    private TileCache tileCache;
    private TileRendererLayer tileRendererLayer;
    private View mCustomMarkerView;
    private ImageView mMarkerImageView;
    private String ImageUrl = "https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidGraphicFactory.createInstance(getApplication());
        setContentView(R.layout.activity_mapa);
        setToolbar();


        mCustomMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        mMarkerImageView = (ImageView) mCustomMarkerView.findViewById(R.id.profile_image);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mapView.setClickable(true);

        // create a tile cache of suitable size
        tileCache = AndroidUtil.createTileCache(this, "mapcache",
                mapView.getModel().displayModel.getTileSize(), 1f,
                mapView.getModel().frameBufferModel.getOverdrawFactor());

        mapView.getModel().mapViewPosition.setZoomLevel((byte) 15);
        mapView.getMapZoomControls().setZoomLevelMin((byte) 14);
        mapView.getMapZoomControls().setZoomLevelMax((byte) 20);

        String filepath = Environment.getExternalStorageDirectory().getPath() + "/maps/gvc.map";
        // tile renderer layer using internal render theme
        tileRendererLayer = new TileRendererLayer(tileCache,
                mapView.getModel().mapViewPosition, false, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setMapFile(new File(filepath));
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.OSMARENDER);

        // only once a layer is associated with a mapView the rendering starts
        mapView.getLayerManager().getLayers().add(tileRendererLayer);

        //mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.getMapScaleBar().setVisible(false);


        /*mapView.getModel().mapViewPosition.setCenter(new LatLong(20.88722, -76.26306));

        MyMarker marker = new MyMarker(this, new LatLong(20.88722, -76.26306), AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.ic_location)), 0, 0);*/

        mapView.getModel().mapViewPosition.setCenter(new LatLong(21.12272, -75.82139));

        MyMarker marker = new MyMarker(this, new LatLong(21.12272, -75.82139), AndroidGraphicFactory.convertToBitmap(getResources().getDrawable(R.drawable.ic_location)), 0, 0);

        mapView.getLayerManager().getLayers().add(marker);
    }

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }


}
