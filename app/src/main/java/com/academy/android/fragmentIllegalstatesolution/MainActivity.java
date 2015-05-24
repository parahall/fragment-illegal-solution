package com.academy.android.fragmentIllegalstatesolution;

import com.squareup.otto.Subscribe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity
        implements View.OnClickListener, DialogInfo.OnDialogInfoClickedListener {

    private static final int DEFAULT_REQUEST_CODE = 0;

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_am_show_dialog).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_am_show_dialog:
                startService(new Intent(this, MyIntentService.class));
                break;
        }
    }

    @Subscribe
    public void onImageAvailable(ImageAvailableEvent event) {
        if (event.image != null) {
            showDialogInfo("Title", "Hello DroidCon", event.image, getString(R.string.global_ok),
                    true, DEFAULT_REQUEST_CODE);
        }
    }


    @Override
    public void onDialogInfoButtonClicked(int requestCode) {
        hideDialogInfo();
    }
}
