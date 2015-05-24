package com.academy.android.fragmentIllegalstatesolution;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener, DialogInfo.OnDialogInfoClickedListener {

    private static final int DEFAULT_REQUEST_CODE = 0;

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
                showDialogInfo("Title","Hello DroidCon",getString(R.string.global_ok),true,DEFAULT_REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onDialogInfoButtonClicked(int requestCode) {
        hideDialogInfo();
    }
}
