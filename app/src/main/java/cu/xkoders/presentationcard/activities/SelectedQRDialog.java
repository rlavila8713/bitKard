package cu.xkoders.presentationcard.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.constants.FRAGMENTS_ACTIONS;
import cu.xkoders.presentationcard.interfaces.OnActionPerformed;

public class SelectedQRDialog extends AlertDialog {

    OnActionPerformed listener;

    private Context mContext;
    private TextView mTitle;

    private String title;
    private Bitmap bitmap;
    private View customView;
    private Integer customResId;

    private Button.OnClickListener mPositiveClickListener;
    private Button.OnClickListener mNegativeClickListener;

    private boolean canDismiss = true;

    private ImageView qr_selected;

    public SelectedQRDialog(Context context) {
        super(context);
        this.mContext = context;
        listener = (OnActionPerformed) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_layout_qr_selected);
        qr_selected = (ImageView) findViewById(R.id.qr_selected);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (bitmap != null) {
            qr_selected.setImageBitmap(bitmap);
        } else {
            qr_selected.setVisibility(View.GONE);
        }
        this.setCanceledOnTouchOutside(canDismiss);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

    }

    public SelectedQRDialog setQrBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        return this;
    }

    public SelectedQRDialog setTitle(String t) {
        this.title = t;
        return this;
    }


}