package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {


    Servo claw1;
    enum ClawState {CLOSE, OPEN}
    ClawState currentClawState;

    public void init(HardwareMap hm) {

        claw1 = hm.get(Servo.class, "claw1");
        currentClawState = ClawState.OPEN;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.left_bumper) {
            setState(ClawState.OPEN);
        } else if (gp1.right_bumper) {
            setState(ClawState.CLOSE);
        }
        switch(currentClawState) {
            case OPEN:
                openPosition();
                break;
            case CLOSE:
                closePosition();
                break;
            default:
                setState(ClawState.OPEN);
                break;
        }
    }
    public void setState (ClawState state) {
        currentClawState = state;
    }
    private void openPosition() {
        double openPos = 0.85;
        claw1.setPosition(openPos);
    }
    private void closePosition() {
        double closePos = 1;
        claw1.setPosition(closePos);
    }
}