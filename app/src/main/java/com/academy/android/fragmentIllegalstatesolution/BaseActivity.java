package com.academy.android.fragmentIllegalstatesolution;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;


public class BaseActivity extends ActionBarActivity {

    /**
     * Show/hide Dialog Info
     */
    public void showDialogInfo(String title, String message, String positiveButton,
                               boolean isCancelable, int requestCode) {

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(DialogInfo.TAG) == null) {
            DialogInfo dialogInfo = DialogInfo.newInstance(title, message, positiveButton,
                    isCancelable, requestCode);
            dialogInfo.show(fm, DialogInfo.TAG);
        }
    }

    public void hideDialogInfo() {
        FragmentManager fm = getSupportFragmentManager();
        DialogInfo fragmentDialog = (DialogInfo) fm.findFragmentByTag(DialogInfo.TAG);
        if (fragmentDialog != null) {
                fragmentDialog.dismiss();
        }
    }

}
