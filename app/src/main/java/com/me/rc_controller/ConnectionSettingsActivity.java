package com.me.rc_controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.me.rc_controller.commands.Commands;
import com.me.rc_controller.commands.Values;

import java.io.IOException;

public class ConnectionSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    public void connect(View view) {
        EditText ipText = findViewById(R.id.ipEditText);
        String ip = ipText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("socketConnection", ip);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}