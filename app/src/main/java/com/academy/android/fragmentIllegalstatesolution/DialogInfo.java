package com.academy.android.fragmentIllegalstatesolution;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DialogInfo extends DialogFragment implements View.OnClickListener {

    private static final String TITLE_KEY = "TITLE_KEY";

    private static final String MESSAGE_KEY = "MESSAGE_KEY";

    private static final String IS_CANCELABLE_KEY = "IS_CANCELABLE_KEY";

    private static final String INFO_BUTTON_TEXT_KEY = "INFO_BUTTON_TEXT_KEY";

    private static final String REQUEST_CODE_KEY = "REQUEST_CODE_KEY";

    public static String TAG = DialogInfo.class.getSimpleName();

    private OnDialogInfoClickedListener mListener;

    public static DialogInfo newInstance(String title, String message, String infoButtonText,
            boolean isCancelable, int requestCode) {
        DialogInfo dialog = new DialogInfo();

        Bundle args = new Bundle();

        args.putString(TITLE_KEY, title);
        args.putString(MESSAGE_KEY, message);

        args.putString(INFO_BUTTON_TEXT_KEY, infoButtonText);
        args.putBoolean(IS_CANCELABLE_KEY, isCancelable);
        args.putInt(REQUEST_CODE_KEY, requestCode);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        // get the saved fields
        String titleText = getArguments().getString(TITLE_KEY, "");
        String messageText = getArguments().getString(MESSAGE_KEY, "");
        String positiveText = getArguments().getString(INFO_BUTTON_TEXT_KEY,
                getString(R.string.global_close));
        boolean isCancelable = getArguments().getBoolean(IS_CANCELABLE_KEY, true);

        // inflate & get the views
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.dialog_info, container, false);
        TextView titleTextView = (TextView) view.findViewById(R.id.tv_di_dialog_title);
        TextView messageTextView = (TextView) view.findViewById(R.id.tv_di_dialog_message);
        TextView positiveTextView = (TextView) view.findViewById(R.id.btn_di_ok);

        // Set the strings
        if (TextUtils.isEmpty(titleText)) {
            titleTextView.setVisibility(View.GONE);
        } else {
            titleTextView.setVisibility(View.VISIBLE);
            titleTextView.setText(titleText);
        }
        messageTextView.setText(messageText);
        positiveTextView.setText(positiveText);
        positiveTextView.setOnClickListener(this);
        setCancelable(isCancelable);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        //Utils check if activity or parent fragment implements interface callbacks
        //return value - activity or fragment that implement it.
        mListener = FragmentUtils.getParent(this,
                OnDialogInfoClickedListener.class);
        if (mListener == null) {
            throw new ClassCastException(activity.toString() +
                    "must implement OnDialogInfoClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_di_ok:
                int requestCode = getArguments().getInt(REQUEST_CODE_KEY);
                mListener.onDialogInfoButtonClicked(requestCode);
                break;
        }
    }

    public interface OnDialogInfoClickedListener {

        void onDialogInfoButtonClicked(int requestCode);
    }
}
