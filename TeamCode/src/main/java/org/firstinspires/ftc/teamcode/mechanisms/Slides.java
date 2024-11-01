package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    DcMotor slideLeft;
    DcMotor slideRight;
    SlideState currentSlideState;
    public enum SlideState {LOW, MED, HIGH};

    public void init(HardwareMap hm) {
        slideLeft = hm.get(DcMotor.class, "slideLeft");
        slideRight = hm.get(DcMotor.class, "slideRight");
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        setState(SlideState.LOW);
    }

    public void Loop(Gamepad gp1, Gamepad gp2) {
        if(gp2.b) {
            setState(SlideState.LOW);
        } else if (gp2.dpad_up) {
            setState(SlideState.HIGH);
        } else if (gp2.dpad_right) {
            setState(SlideState.MED);
        }
        if (gp2.left_stick_button && gp2.right_stick_button) {
//            slideFallandReset();
        }

        switch(currentSlideState) {
            case LOW:
                lowPosition();
                break;
            case MED:
                medPosition();
                break;
            case HIGH:
                highPosition();
                break;
            default:
                setState(SlideState.LOW);
                break;
        }

    }
//    private void slideFallandReset() throws InterruptedException {slideLeft.setTargetPosition(-40);slideRight.setTargetPosition(-40);slideLeft.setPower(0.4);slideRight.setPower(0.4);Thread.sleep(500);slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);}
    private void highPosition() {
        int highPos = 1200;
        slideLeft.setTargetPosition(1190);
        slideRight.setTargetPosition(highPos);

        slideRight.setPower(.8);
        slideLeft.setPower(.8);

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void medPosition() {
        int medPos = 440;
        slideLeft.setTargetPosition(430);
        slideRight.setTargetPosition(medPos);

        slideRight.setPower(.6);
        slideLeft.setPower(.6);

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void lowPosition() {
        int lowPos = 0;
        slideLeft.setTargetPosition(lowPos);
        slideRight.setTargetPosition(lowPos);

        slideRight.setPower(.4);
        slideLeft.setPower(.4);

        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void setState(SlideState state) {
        currentSlideState = state;
    }
    public int getLpos() {return slideLeft.getCurrentPosition();}
    public int getRpos() {return slideRight.getCurrentPosition();}
}