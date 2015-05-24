package com.academy.android.fragmentIllegalstatesolution.Model;

import com.academy.android.fragmentIllegalstatesolution.BusProvider;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;

public class MyIntentService extends IntentService {

    private static DownloadTask downloadTask;

    private static final String URL =
            "http://media.paperblog.fr/i/457/4572881/960x800-wallpapers-android-superman-L-2klB99.jpeg";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Stop existing download, if it exists.
        if (downloadTask != null) {
            downloadTask.cancel(true);
            downloadTask = null;
        }
        // Trigger a background download of an image for the new location.
        downloadTask = new DownloadTask();
        downloadTask.execute(URL);
    }


    private static class DownloadTask extends AsyncTask<String, Void, Drawable> {

        private static final String TAG = DownloadTask.class.getSimpleName();

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
                BusProvider.getInstance().post(new ImageAvailableEvent(drawable));
            }
        }
    }
}
