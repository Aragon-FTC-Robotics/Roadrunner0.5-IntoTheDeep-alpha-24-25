package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Wrist {
    Servo wristServo;
    double pos;
    double speed;
    double groundpos, highpos, wallpos, clippos, grabpos;
    enum WristState {GROUND, HIGH, WALL}; //0.248 - high bucket pos, 0.39 - intake from ground pos, 0.487 - intake from wall + hook on top
    WristState currentWristState = WristState.WALL;
    //INTAKE from pit, PLACE on high bar and stuff
    public void init(HardwareMap hm) {
        wristServo = hm.get(Servo.class, "wristServo");
        speed = 0.01;
        pos = 0;
        groundpos = 0.73;
        highpos = 0.612;
        wallpos = 0.776;
        clippos = 0.990;
        grabpos = 0.755;

    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (!(gp1.a&&(gp1.dpad_up||gp2.dpad_right)&&gp2.b)){
            pos += (gp1.left_trigger - gp1.right_trigger) * speed;
            if (pos > 1) {
                pos = 1;
            } else if (pos < 0) {
                pos = 0;
            }
        }
//        wristServo.setPosition(pos);
        if (gp1.a) {
            pos = groundpos; // GROUND
        } else if (gp2.dpad_up || gp2.dpad_right) {
            pos = highpos; // HIGH
        } else if (gp2.b) {
            pos = wallpos; // WALL
        } else if (gp2.a) {
            pos = groundpos;
        } else if (gp2.y) {
            pos = clippos;
        } else if (gp2.x) {
            pos = grabpos;
        }
        wristServo.setPosition(pos);
//        switch (currentWristState) {
//            case GROUND:
//                groundPos();
//                break;
//            case HIGH:
//                highPos();
//                break;
//            case WALL:
//                wallPos();
//                break;
//            default:
//                setState(WristState.HIGH);
//                break;
//        }
    }
    public double getPos() {return pos;}
    public void setState(WristState state) {currentWristState = state;}
//    public void groundPos(){
//        double groundpos = 0.39;
//        wristServo.setPosition(groundpos);
//    }
//    public void highPos(){
//        double highpos = 0.248;
//        wristServo.setPosition(highpos);
//    }
//    public void wallPos(){
//        double wallpos = 0.445;
//        wristServo.setPosition(wallpos);
//    }
}
