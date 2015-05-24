package com.academy.android.fragmentIllegalstatesolution.View;

import com.academy.android.fragmentIllegalstatesolution.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogInfo extends FrameLayout implements View.OnClickListener {

    public static String TAG = DialogInfo.class.getSimpleName();

    private OnDialogInfoClickedListener presenter;

    private String mTitle;

    private String mMessage;

    private Drawable mImage;

    private String mInfoButtonText;

    private boolean isCancelable;

    private int requestCode;

    private final ImageView mImageView;

    private final TextView mTitleTextView;

    private final TextView mPositiveTextView;

    public static DialogInfo newInstance(Context context, String title, String message,
            Drawable image, String infoButtonText,
            boolean isCancelable, int requestCode, OnDialogInfoClickedListener presenter) {
        DialogInfo dialog = new DialogInfo(context);
        dialog.mTitle = title;
        dialog.mMessage = message;
        dialog.mImage = image;
        dialog.mInfoButtonText = infoButtonText;
        dialog.isCancelable = isCancelable;
        dialog.requestCode = requestCode;
        dialog.presenter = presenter;
        return dialog;
    }

    public DialogInfo(Context context) {
        super(context);
        // inflate & get the views
        View.inflate(context, R.layout.dialog_info, this);
        mImageView = (ImageView) findViewById(R.id.iv_di_dialog_image);
        mTitleTextView = (TextView) findViewById(R.id.tv_di_dialog_title);
        mPositiveTextView = (TextView) findViewById(R.id.btn_di_ok);
        mPositiveTextView.setOnClickListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initContent();
    }

    public void initContent(){
        if (TextUtils.isEmpty(mTitle)) {
            mTitleTextView.setVisibility(View.GONE);
        } else {
            mTitleTextView.setVisibility(View.VISIBLE);
            mTitleTextView.setText(mTitle);
        }
        mImageView.setImageDrawable(mImage);
        mPositiveTextView.setText(mInfoButtonText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_di_ok:
                presenter.onDialogInfoButtonClicked(requestCode);
        }
    }

    public interface OnDialogInfoClickedListener {

        void onDialogInfoButtonClicked(int requestCode);
    }
}
