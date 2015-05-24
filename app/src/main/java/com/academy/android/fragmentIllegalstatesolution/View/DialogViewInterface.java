package com.academy.android.fragmentIllegalstatesolution.View;

import android.view.View;

public interface DialogViewInterface {
    void showDialog(View dialog) throws InterruptedException;
    void hideDialog();
}
