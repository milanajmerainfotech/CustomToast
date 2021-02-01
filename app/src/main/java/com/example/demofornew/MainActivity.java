package com.example.demofornew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.demoliabrery.CustomToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        CustomToast.createToast(MainActivity.this, "This Is Toast");
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}