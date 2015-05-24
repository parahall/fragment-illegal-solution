package com.academy.android.fragmentIllegalstatesolution.View;

import com.academy.android.fragmentIllegalstatesolution.R;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;


public class BaseActivity extends ActionBarActivity
        implements DialogViewInterface, DialogInfo.OnDialogInfoClickedListener {

    private static String TAG = BaseActivity.class.getSimpleName();

    private ViewGroup mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mContainer = (ViewGroup) findViewById(R.id.dialog_container);
    }

    /**
     * Show/hide Dialog Info
     */
    public void showDialogInfo(String title, String message, Drawable image, String positiveButton,
            boolean isCancelable, int requestCode,
            DialogInfo.OnDialogInfoClickedListener presenter) {
        DialogInfo dialogInfo = DialogInfo.newInstance(getApplicationContext(), title, message,
                image, positiveButton, isCancelable, requestCode, presenter);
        showDialog(dialogInfo);
    }

    @Override
    public void onDialogInfoButtonClicked(int requestCode) {
        hideDialogInfo();
    }

    public void hideDialogInfo() {
        hideDialog();
    }

    @Override
    public void onBackPressed() {
        if (mContainer != null && mContainer.getChildCount() > 0) {
            mContainer.removeAllViews();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void showDialog(View dialog) {
        try {
            mContainer.addView(dialog);
        } catch (NullPointerException exception) {
            throw new NullPointerException("Presenter must have container in XML file");
        }
    }

    @Override
    public void hideDialog() {
        ViewGroup container = (ViewGroup) findViewById(R.id.dialog_container);
        if (container != null) {
            container.removeAllViews();
        }
    }
}

