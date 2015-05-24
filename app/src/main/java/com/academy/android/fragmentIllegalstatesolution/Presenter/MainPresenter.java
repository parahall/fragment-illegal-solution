package com.academy.android.fragmentIllegalstatesolution.Presenter;

import com.academy.android.fragmentIllegalstatesolution.BusProvider;
import com.academy.android.fragmentIllegalstatesolution.Model.ImageAvailableEvent;
import com.academy.android.fragmentIllegalstatesolution.Model.MyIntentService;
import com.academy.android.fragmentIllegalstatesolution.R;
import com.academy.android.fragmentIllegalstatesolution.View.MainActivity;
import com.squareup.otto.Subscribe;

import android.content.Intent;
import android.view.View;

public class MainPresenter
        implements MainPresenterInterface {

    private static final int DEFAULT_REQUEST_CODE = 0;

    private MainActivity view;

    public void onTakeView(MainActivity view) {
        this.view = view;
    }

    public void onResume() {
        BusProvider.getInstance().register(this);
    }

    public void onPause() {
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    @Override
    public void onImageAvailable(ImageAvailableEvent event) {
        if (event.image != null && view != null) {
            view.showDialogInfo("Title", "Hello DroidCon", event.image,
                    view.getString(R.string.global_ok),
                    true, DEFAULT_REQUEST_CODE, view);
        }
    }

    public void onClick(View v) {
        if (view != null) {
            switch (v.getId()) {
                case R.id.btn_am_show_dialog:
                    view.startService(
                            new Intent(view.getApplicationContext(), MyIntentService.class));
                    break;
            }
        }
    }
}
