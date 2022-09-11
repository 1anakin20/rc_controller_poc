package com.me.rc_controller.commands;

public class Values {
    // RC car params
    final String frequency;
    final String deadFrequency;
    
    //final  
    final String syncBurstUS;
    final String syncSpacingUS;
    final String syncTotal;
    
    final String spacingUS;
    final String burstUS;
    
    // Movement params
    final String stop;
    
    final String forward;
    final String forwardLeft;
    final String forwardRight;
    
    final String left;
    final String right;
    
    final String reverse;
    final String backwardsLeft;
    final String backwardsRight;

    public Values(String frequency, String deadFrequency, String syncBurstUS, String syncSpacingUS, String syncTotal, String spacingUS, String burstUS, String stop, String forward, String forwardLeft, String forwardRight, String left, String right, String reverse, String backwardsLeft, String backwardsRight) {
        this.frequency = frequency;
        this.deadFrequency = deadFrequency;
        this.syncBurstUS = syncBurstUS;
        this.syncSpacingUS = syncSpacingUS;
        this.syncTotal = syncTotal;
        this.spacingUS = spacingUS;
        this.burstUS = burstUS;
        this.stop = stop;
        this.forward = forward;
        this.forwardLeft = forwardLeft;
        this.forwardRight = forwardRight;
        this.left = left;
        this.right = right;
        this.reverse = reverse;
        this.backwardsLeft = backwardsLeft;
        this.backwardsRight = backwardsRight;
    }
}
