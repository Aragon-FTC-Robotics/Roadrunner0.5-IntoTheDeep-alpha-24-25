package org.firstinspires.ftc.teamcode.mechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Config
public class fourBar {

    private PIDController controller;
    public static double p=0.006, i=0.1, d=0.0005;
    public static double f=-0.11;

    public int target = 0;
    private final double ticks_in_degree = 2786.2 / 360; //PLACEHOLDER VALUE
    DcMotorEx fourBar;
    double armPos;
    double power;
    double pid;
    enum BarState {PARALLEL,WALL,START, GROUND, CLIP, CLIPUP}
    int highpos = -1100;
    int lowpos = -420;
    int startpos = -130;
    int backclippos = -1400;
    int upbackclippos = -1250;
    int groundpos = -1850;
    BarState currentBarState;



    public void init(HardwareMap hm) {
        controller = new PIDController(p,i,d);
        fourBar = hm.get(DcMotorEx.class, "fourBar");
        fourBar.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fourBar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // WITHOUT ENCODER!!!
        setState(BarState.START);
    }

    public void setState(BarState state){
        currentBarState = state;
    }

    public void Loop(Gamepad gp1, Gamepad gp2) throws InterruptedException {
        controller.setPID(p,i,d);
        armPos = fourBar.getCurrentPosition();
        pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        power = pid + ff;
        //power=0.5; // CHANGE THIS BACK LATER 5/25/24*/
        if (gp2.b) {
            setState(BarState.WALL);
        } else if (gp2.y) {
            setState(BarState.CLIP);
        } else if (gp2.right_bumper) { //right bumper
            setState(BarState.CLIPUP);
        } else if (gp2.dpad_up || gp2.dpad_right) {
            setState(BarState.PARALLEL);
        } else if (gp2.x) {
            setState(BarState.GROUND);
        } else if (gp2.a) {
            setState(BarState.START);
        }

        switch (currentBarState) {
            case PARALLEL:
                highPosition(power);
                break;
            case WALL:
                lowPosition(power);
                break;
            case START:
                startPosition(power);
                break;
            case GROUND:
                groundPosition(power);
                break;
            case CLIP:
                backClipPosition(power);
                break;
            case CLIPUP:
                upBackClipPosition(power);
                break;
            default:
                setState(BarState.START);
        }
    }
    public double getarmPos() {return armPos;}
    public int getTarget() {return target;}
    public double getPower() {return power;}
    public double getpid() {return pid;}

    private void highPosition(double power){
        fourBar.setTargetPosition(highpos);
        target = highpos;
        fourBar.setPower(power);

//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void lowPosition(double power){
        fourBar.setTargetPosition(lowpos);
        target = lowpos;
        fourBar.setPower(power);

//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void startPosition(double power){
        fourBar.setTargetPosition(startpos);
        target = startpos;
        fourBar.setPower(power);

//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void groundPosition(double power){

        target = groundpos;
        fourBar.setTargetPosition(groundpos);

        fourBar.setPower(power);

//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

//    private void clipPosition(){
//        int clippos = -870;
//
//        fourBar.setTargetPosition(clippos);
//
//        fourBar.setPower(0.5);
//
//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }

    private void backClipPosition(double power){
        target = backclippos;
        fourBar.setTargetPosition(backclippos);

        fourBar.setPower(power);

//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    private void upBackClipPosition(double power){
        target = upbackclippos;
        fourBar.setTargetPosition(upbackclippos);

        fourBar.setPower(power);

//        fourBar.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}