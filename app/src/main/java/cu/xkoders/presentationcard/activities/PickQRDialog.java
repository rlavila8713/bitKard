package cu.xkoders.presentationcard.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import cu.xkoders.presentationcard.R;
import cu.xkoders.presentationcard.constants.FRAGMENTS_ACTIONS;
import cu.xkoders.presentationcard.interfaces.OnActionPerformed;

public class PickQRDialog extends AlertDialog implements View.OnClickListener {

    OnActionPerformed listener;

    private Context mContext;
    private TextView mTitle;

    private String title;
    private View customView;
    private Integer customResId;

    private Button.OnClickListener mPositiveClickListener;
    private Button.OnClickListener mNegativeClickListener;

    private String positiveText;
    private String negativeText;
    private boolean canDismiss = true;

    private ImageView qr_send, qr_receive;

    public PickQRDialog(Context context) {
        super(context);
        this.mContext = context;
        listener = (OnActionPerformed) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.dialog_layout_pick_qr_method);

        qr_send = (ImageView) findViewById(R.id.qr_send);
        qr_receive = (ImageView) findViewById(R.id.qr_receive);

        mTitle = (TextView) findViewById(android.R.id.text1);
        mTitle.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf"));


        qr_send.setOnClickListener(this);
        qr_receive.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (title != null) {
            mTitle.setText(title);
        } else {
            mTitle.setVisibility(View.GONE);
        }


        this.setCanceledOnTouchOutside(canDismiss);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

    }

    public PickQRDialog setTitle(String t) {
        this.title = t;
        return this;
    }

    public PickQRDialog setupPositiveButton(String text, Button.OnClickListener listener) {
        this.positiveText = text;
        this.mPositiveClickListener = listener;
        return this;
    }

    public PickQRDialog setupNegativeButton(String text, Button.OnClickListener listener) {
        this.negativeText = text;
        this.mNegativeClickListener = listener;
        return this;
    }

    public PickQRDialog setCustomView(View v) {
        this.customView = v;
        return this;
    }

    public PickQRDialog setCustomViewResource(int ResId) {
        this.customResId = ResId;
        return this;
    }

    public PickQRDialog dismissOnTouchOutside(boolean dismiss) {
        this.canDismiss = dismiss;
        return this;
    }

    public View getCustomView() {
        return this.customView;
    }

    @Override
    public void onClick(View v) {

        Bundle b = new Bundle();
        switch (v.getId())
        {
            case R.id.qr_send:
                b.putInt("pos", 0);

                break;
            case R.id.qr_receive:
                b.putInt("pos", 1);

                break;
        }
        listener.onActionPerformed(FRAGMENTS_ACTIONS.SHOW_ACTION_QR, b);
    }
}