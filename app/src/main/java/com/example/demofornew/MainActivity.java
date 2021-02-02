package com.example.demofornew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.android.demoliabrery.CustomToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class MainActivity extends AppCompatActivity {
    public Socket socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            socket = IO.socket("http://chat.socket.io");
            Toast.makeText(this, "User Connected To Socket IO", Toast.LENGTH_SHORT).show();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        /*WebView webView = findViewById(R.id.webView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);*/

        Button button = findViewById(R.id.button_clic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socket.on("message", getOnNewMessage);
                socket.emit("message", "New Message Sent.");
            }
        });
    }
    public Emitter.Listener getOnNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(() -> {
                JSONObject data = (JSONObject) args[0];
                String username;
                String message;
                try {
                    username = data.getString("username");
                    message = data.getString("message");
                    CustomToast.createToast(MainActivity.this, message);
                } catch (JSONException e) {
                    Log.d("ExceptionMilan", e.toString());
                    return;
                }

                // add the message to view
                //addMessage(username, message);
                Toast.makeText(MainActivity.this, message + " (Received)", Toast.LENGTH_SHORT).show();
            });
            Log.d("ExceptionMilan", "Cliled");
        }
    };

    @Override
    protected void onDestroy() {
        socket.disconnect();
        socket.off("message", getOnNewMessage);
        Toast.makeText(this, "User Disconnected To Socket IO", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}