package com.android.demoliabrery;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomToast {
    public static final int CUSTOM_TOAST_LAYOUT = R.layout.toast_layout;

    static View view;
    public static View setCustomeToastLayout(Context context, CharSequence text){
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout,null);
        TextView textV = (TextView) view.findViewById(R.id.text_toast);
        textV.setText(text);

        Toast mToast = new Toast(context);
        mToast.setView(view);
        mToast.setGravity(Gravity.BOTTOM, 0, 256);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
        return view;
    }

    public static void createToast(Context context, CharSequence text) {
        view = setCustomeToastLayout(context, text);
    }

    public void showCustomToast(){

    }

}
