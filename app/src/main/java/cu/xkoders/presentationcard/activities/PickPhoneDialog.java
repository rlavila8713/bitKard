package cu.xkoders.presentationcard.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import cu.xkoders.presentationcard.R;

public class PickPhoneDialog extends AlertDialog {

    private Context mContext;
    private TextView mTitle;
    private TextView mContent;
    private Button mPositive;
    private Button mNegative;
    private FrameLayout mCustomContainer;
    private ScrollView mScrollText;

    private String title;
    private String contentText;
    private View customView;
    private Integer customResId;

    private Button.OnClickListener mPositiveClickListener;
    private Button.OnClickListener mNegativeClickListener;

    private String positiveText;
    private String negativeText;
    private boolean canDismiss = true;

    private RadioGroup radioGroup;
    private RadioButton cliente_phone_1, cliente_phone_2;

    public PickPhoneDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_layout_pick_phone);
        radioGroup = (RadioGroup)findViewById(R.id.cliente_phone_group);
        cliente_phone_1 = (RadioButton) findViewById(R.id.cliente_phone_1);
        cliente_phone_2 = (RadioButton) findViewById(R.id.cliente_phone_2);

        mTitle = (TextView) findViewById(android.R.id.text1);
        mContent = (TextView) findViewById(android.R.id.text2);
       // mCustomContainer = (FrameLayout) findViewById(R.id.content);
        mPositive = (Button) findViewById(android.R.id.button2);
        mNegative = (Button) findViewById(android.R.id.button1);
        //mScrollText = (ScrollView) findViewById(R.id.scrolltext);


        mTitle.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
       // mContent.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Regular.ttf"));

    }

    @Override
    public void onStart() {
        super.onStart();
        if (title != null) {
            mTitle.setText(title);
        } else {
            mTitle.setVisibility(View.GONE);
        }

       /* if (contentText != null) {
            mContent.setText(contentText);
        } else {
            //mScrollText.setVisibility(View.GONE);
        }

        /*if (customView != null && customResId == null) {
            mCustomContainer.addView(customView);
        } else if (customView == null && customResId != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = inflater.inflate(customResId, null, false);
            mCustomContainer.addView(customView);
        } else if (customView == null && customResId == null) {
            mContent.setVisibility(View.GONE);
        }*/

        if (positiveText != null && mPositiveClickListener != null) {
            mPositive.setText(positiveText);
            mPositive.setOnClickListener(mPositiveClickListener);
        } else {
            mPositive.setVisibility(View.GONE);
        }

        if (negativeText != null && mNegativeClickListener != null) {
            mNegative.setText(negativeText);
            mNegative.setOnClickListener(mNegativeClickListener);
        } else {
            mNegative.setVisibility(View.GONE);
        }
        this.setCanceledOnTouchOutside(canDismiss);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

    }

    public PickPhoneDialog setTitle(String t) {
        this.title = t;
        return this;
    }

    public PickPhoneDialog setMessage(String m) {
        this.contentText = m;
        return this;
    }

    public PickPhoneDialog setupPositiveButton(String text, Button.OnClickListener listener) {
        this.positiveText = text;
        this.mPositiveClickListener = listener;
        return this;
    }

    public PickPhoneDialog setupNegativeButton(String text, Button.OnClickListener listener) {
        this.negativeText = text;
        this.mNegativeClickListener = listener;
        return this;
    }

    public PickPhoneDialog setCustomView(View v) {
        this.customView = v;
        return this;
    }

    public PickPhoneDialog setCustomViewResource(int ResId) {
        this.customResId = ResId;
        return this;
    }

    public PickPhoneDialog dismissOnTouchOutside(boolean dismiss) {
        this.canDismiss = dismiss;
        return this;
    }

    public String getPhoneSelected()
    {
        return ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
    }

    public View getCustomView() {
        return this.customView;
    }

}