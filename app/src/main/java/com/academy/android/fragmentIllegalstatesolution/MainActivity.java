package com.academy.android.fragmentIllegalstatesolution;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.net.URL;

public class MainActivity extends BaseActivity
        implements View.OnClickListener, DialogInfo.OnDialogInfoClickedListener {

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
                new DownloadTask().execute();
                break;
        }
    }

    @Override
    public void onDialogInfoButtonClicked(int requestCode) {
        hideDialogInfo();
    }


    private class DownloadTask extends AsyncTask<String, Void, Drawable> {

        private final String TAG = DownloadTask.class.getSimpleName();

        @Override
        protected Drawable doInBackground(String... params) {
            try {
                return BitmapDrawable
                        .createFromStream(new URL(params[0]).openStream(), "bitmap.jpg");
            } catch (Exception e) {
                Log.e(TAG, "Unable to download image.", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            if (!isCancelled() && drawable != null) {
                showDialogInfo("Title", "Hello DroidCon", getString(R.string.global_ok), true,
                        DEFAULT_REQUEST_CODE);
            }
        }
    }

}
