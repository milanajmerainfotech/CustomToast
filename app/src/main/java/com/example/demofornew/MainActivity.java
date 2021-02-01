package com.example.demofornew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.demoliabrery.CustomToast;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


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


        socket.connect();


        Button button = findViewById(R.id.button_clic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socket.on("message", onNewMessage);
                socket.emit("message", "New Message Sent.");
            }
        });
    }
    Activity context = MainActivity.this;
    public Emitter.Listener onNewMessage = args -> {
        Log.d("ExceptionMilan", "Cliled");
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
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
            }
        });
    };

    @Override
    protected void onDestroy() {
        socket.disconnect();
        socket.off("message", onNewMessage);
        Toast.makeText(this, "User Disconnected To Socket IO", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}