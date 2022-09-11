package com.me.rc_controller.commands;

import java.io.IOException;
import java.net.Socket;


public class Commands {
    private final String json = "    [\n" +
            "    {\n" +
            "        \"frequency\": {frequency}\n" +
            "        \"dead_frequency\": {dead_frequency},\n" +
            "        \"burst_us\": {sync_burst_us},\n" +
            "        \"spacing_us\": {sync_spacing_us},\n" +
            "        \"repeats\": {sync_total}\n" +
            "    },\n" +
            "    {\n" +
            "        \"frequency\": {frequency},\n" +
            "        \"dead_frequency\": {dead_frequency},\n" +
            "        \"burst_us\": {burst_us},\n" +
            "        \"spacing_us\": {spacing_us},\n" +
            "        \"repeats\": {repeats}\n" +
            "    }\n" +
            "    ]";

    private final Socket socket;
    private final Values values;


    public Commands(String ip, int port, Values values) throws IOException {
        this.values = values;
        this.socket = new Socket();
        socket.connect(new java.net.InetSocketAddress(ip, port));
        System.out.println("Connected");
    }

    public void close() throws IOException {
        socket.close();
    }

    public void forward() {
        send(values.forward);
    }

    public void forwardLeft() {
        send(values.forwardLeft);
    }

    public void forwardRight() {
        send(values.forwardRight);
    }

    public void left() {
        send(values.left);
    }

    public void right() {
        send(values.right);
    }

    public void reverse() {
        send(values.reverse);
    }

    public void reverseLeft() {
        send(values.backwardsLeft);
    }

    public void reverseRight() {
        send(values.backwardsRight);
    }

    public void stop() {
        send(values.stop);
    }

    private void send(String value) {
        String formatedJSON = format(value);
        System.out.println(formatedJSON);
        try {
            socket.getOutputStream().write(formatedJSON.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String format(String value) {
        String data = json;
        data = data.replace("{frequency}", values.frequency);
        data = data.replace("{dead_frequency}", values.deadFrequency);
        data = data.replace("{sync_burst_us}", values.syncBurstUS);
        data = data.replace("{sync_spacing_us}", values.syncSpacingUS);
        data = data.replace("{sync_total}", values.syncTotal);
        data = data.replace("{spacing_us}", values.spacingUS);
        data = data.replace("{burst_us}", values.burstUS);
        data = data.replace("{dead_frequency}", values.stop);
        data = data.replace("{repeats}", value);
        return data.replace("\n", "").replace(" ", "");
    }

}
