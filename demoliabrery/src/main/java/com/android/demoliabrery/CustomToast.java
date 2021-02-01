package com.android.demoliabrery;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class CustomToast {
    public static final int CUSTOM_TOAST_LAYOUT = R.layout.toast_layout;

    static View view;

    public static void createToast(Context context, CharSequence text) {
        view = setCustomeToastLayout(context, text);
    }

    public static View setCustomeToastLayout(Context context, CharSequence text){
        View view = LayoutInflater.from(context).inflate(CUSTOM_TOAST_LAYOUT, null);
        TextView textView = view.findViewById(R.id.text_toast);
        textView.setText(text);
        view.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.GONE);
            }
        },1000);
        return view;
    }

    public void showCustomToast(){

    }

}
