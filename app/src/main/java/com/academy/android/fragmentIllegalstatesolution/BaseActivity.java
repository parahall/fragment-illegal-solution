package com.academy.android.fragmentIllegalstatesolution;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;


public class BaseActivity extends ActionBarActivity {

    protected boolean mIsActivityPaused = true;

    protected boolean mIsActivityDestroyed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsActivityDestroyed = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActivityPaused = true;
    }


    @Override
    protected void onDestroy() {
        mIsActivityDestroyed = true;
        super.onDestroy();
    }


    /**
     * Show/hide Dialog Info
     */
    public void showDialogInfo(String title, String message, String positiveButton,
                               boolean isCancelable, int requestCode) {

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(DialogInfo.TAG) == null && !mIsActivityDestroyed) {
            DialogInfo dialogInfo = DialogInfo.newInstance(title, message, positiveButton,
                    isCancelable, requestCode);
            dialogInfo.show(fm, DialogInfo.TAG);
        }
    }

    public void hideDialogInfo() {
        FragmentManager fm = getSupportFragmentManager();
        DialogInfo fragmentDialog = (DialogInfo) fm.findFragmentByTag(DialogInfo.TAG);
        if (fragmentDialog != null) {
            if (mIsActivityPaused) {
                fragmentDialog.dismissAllowingStateLoss();
            } else {
                fragmentDialog.dismiss();
            }
        }
    }


    /**
     * Commit the fragment according to activity lifecycle:
     * - if paused then commit allow state lose
     * - if destroy do not commit the fragment
     *
     * @return true if committed
     */
    public boolean commitFragmentAccordingToLifeCycle(FragmentTransaction fragmentTransaction) {
        if (mIsActivityDestroyed) {
            return false;
        }

        if (mIsActivityPaused) {
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            fragmentTransaction.commit();
        }

        return true;
    }

}
