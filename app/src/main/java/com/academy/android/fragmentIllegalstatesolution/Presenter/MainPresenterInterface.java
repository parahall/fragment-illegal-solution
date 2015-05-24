package com.academy.android.fragmentIllegalstatesolution.Presenter;

import com.academy.android.fragmentIllegalstatesolution.Model.ImageAvailableEvent;
import com.academy.android.fragmentIllegalstatesolution.View.MainActivity;

public interface MainPresenterInterface {
    void onTakeView(MainActivity view);
    void onImageAvailable(ImageAvailableEvent event);
}
