package com.academy.android.fragmentIllegalstatesolution.View;

import com.academy.android.fragmentIllegalstatesolution.Presenter.MainPresenter;
import com.academy.android.fragmentIllegalstatesolution.R;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity
        implements View.OnClickListener {

    private static MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_am_show_dialog).setOnClickListener(this);

        if (presenter == null)
            presenter = new MainPresenter();
        presenter.onTakeView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onTakeView(null);
        if (isFinishing())
            presenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_am_show_dialog:
                presenter.onClick(v);
                break;
        }
    }

}
