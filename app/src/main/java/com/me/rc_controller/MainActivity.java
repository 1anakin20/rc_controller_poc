package com.me.rc_controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.me.rc_controller.commands.Commands;
import com.me.rc_controller.commands.Values;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final Values VALUES;
    private ActivityResultLauncher<Intent> connectionSetting;
    private Commands commands;

    public MainActivity() {
        this.VALUES = new Values(
                "27.145",
                "49.890",
                "1200",
                "400",
                "4",
                "400",
                "400",
                "5",
                "10",
                "28",
                "34",
                "58",
                "64",
                "40",
                "52",
                "46"
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        connectionSetting = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String ip = (String) data.getExtras().get("socketConnection");
                        try {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            commands = new Commands(ip, 12345, VALUES);
                        } catch (IOException e) {
                            Toast toast = Toast.makeText(this, "Couldn't connect. Try again", Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }
                    }


                    Toast toast = Toast.makeText(this, "connected", Toast.LENGTH_SHORT);
                    toast.show();
                    Button connectionButton = findViewById(R.id.connectionButton);
                    connectionButton.setText(R.string.disconnectButton);
                    connectionButton.setOnClickListener((View view) -> {
                        try {
                            commands.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Couldn't disconnect", Toast.LENGTH_LONG).show();
                        }
                        ((Button) view).setText(R.string.connectButton);
                        connectionButton.setOnClickListener(this::configureConnection);
                    });

                    OnTouchListenerCommand onTouchListenerCommand = new OnTouchListenerCommand(commands);
                    findViewById(R.id.forwardButton).setOnTouchListener(onTouchListenerCommand);
                    findViewById(R.id.reverseButton).setOnTouchListener(onTouchListenerCommand);
                    findViewById(R.id.leftButton).setOnTouchListener(onTouchListenerCommand);
                    findViewById(R.id.rightButton).setOnTouchListener(onTouchListenerCommand);
                }
        );

        setContentView(R.layout.activity_main);
    }

    public void configureConnection(View view) {
        Intent intent = new Intent(this, ConnectionSettingsActivity.class);
        connectionSetting.launch(intent);
    }
}