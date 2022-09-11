package com.me.rc_controller;

import android.view.MotionEvent;
import android.view.View;

import com.me.rc_controller.commands.Commands;

public class OnTouchListenerCommand implements View.OnTouchListener {
    private final Commands commands;
    private boolean isForwardPressed = false;
    private boolean isReversePressed = false;
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    public OnTouchListenerCommand(Commands commands) {
        this.commands = commands;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int buttonId = view.getId();
        boolean setPress;


        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            setPress = true;
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            setPress = false;
        } else {
            return false;
        }

        if (buttonId == R.id.forwardButton) {
            isForwardPressed = setPress;
        } else if (buttonId == R.id.reverseButton) {
            isReversePressed = setPress;
        } else if (buttonId == R.id.leftButton) {
            isLeftPressed = setPress;
        } else if (buttonId == R.id.rightButton) {
            isRightPressed = setPress;
        }
        sendCommand();
        return true;
    }

    private void sendCommand() {
        if (isLeftPressed && isForwardPressed) {
            commands.forwardLeft();
        }
        else if (isReversePressed && isLeftPressed) {
            commands.reverseLeft();
        }
        else if (isForwardPressed && isRightPressed) {
            commands.forwardRight();
        }
        else if (isRightPressed && isReversePressed) {
            commands.reverseRight();
        }
        else if (isRightPressed) {
            commands.right();
        }
        else if (isLeftPressed) {
            commands.left();
        }
        else if (isForwardPressed) {
            commands.forward();
        }
        else if (isReversePressed) {
            commands.reverse();
        }
        else {
            commands.stop();
        }

    }
}
