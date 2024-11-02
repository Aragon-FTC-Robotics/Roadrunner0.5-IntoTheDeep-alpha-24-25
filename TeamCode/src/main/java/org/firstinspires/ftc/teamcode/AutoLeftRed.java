package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

class Claw {
    Servo claw1;
    enum ClawState {CLOSE, OPEN}
    ClawState currentClawState;
    double openPos = 0.85;
    double closePos = 1;
    public void init(HardwareMap hm) {
        claw1 = hm.get(Servo.class, "claw1");
        currentClawState = ClawState.OPEN;
    }
    public void clawClose() {claw1.setPosition(closePos);}
    public void clawOpen() {claw1.setPosition(openPos);}
}
class Bar {
    DcMotor barmotor;
    int parallelpos = -1100;
    int wallpos = -420;
    int startpos = -130;
    int clipposDOWN = -1400;
    int clipposUP = -1250;
    int groundpos = -1850;

    public void init(HardwareMap hm) {
        barmotor = hm.get(DcMotor.class, "fourBar");
        barmotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        barmotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        barmotor.setPower(0.6);
    }
    public void barParallel() {barmotor.setTargetPosition(parallelpos);barmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barWall() {barmotor.setTargetPosition(wallpos);barmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barStart() {barmotor.setTargetPosition(startpos);barmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barClipDown() {barmotor.setTargetPosition(clipposDOWN);barmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barClipUp() {barmotor.setTargetPosition(clipposUP);barmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barGround() {barmotor.setTargetPosition(groundpos);barmotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
}
class Slides {
    DcMotor slideLeft;
    DcMotor slideRight;
    int highPos = 1200;
    int medPos = 440;
    int lowPos = 0;
    public void init(HardwareMap hm) {
        slideLeft = hm.get(DcMotor.class, "slideLeft");
        slideRight = hm.get(DcMotor.class, "slideRight");
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void slidesHigh(){
        slideLeft.setTargetPosition(highPos-10);
        slideRight.setTargetPosition(highPos);
        slideLeft.setPower(0.8);
        slideRight.setPower(0.8);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void slidesMed(){
        slideLeft.setTargetPosition(medPos-10);
        slideRight.setTargetPosition(medPos);
        slideLeft.setPower(0.6);
        slideRight.setPower(0.6);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void slidesLow(){
        slideLeft.setTargetPosition(lowPos);
        slideRight.setTargetPosition(lowPos);
        slideLeft.setPower(0.4);
        slideRight.setPower(0.4);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
class Wrist{
    Servo wristServo;
    double pos;
    double speed;
    double groundpos, highpos, wallpos, clippos, grabpos;
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
    public void wristGround() {wristServo.setPosition(groundpos);pos=groundpos;}
    public void wristHigh() {wristServo.setPosition(highpos);pos=highpos;}
    public void wristWall() {wristServo.setPosition(wallpos);pos=wallpos;}
    public void wristClip() {wristServo.setPosition(clippos);pos=clippos;}
    public void wristGrab() {wristServo.setPosition(grabpos);pos=grabpos;}
}
public class AutoLeftRed extends LinearOpMode {
    HardwareMap hm;
    Claw claw;
    Bar bar;
    Slides slides;
    Wrist wrist;
    public void highBucket() {
        bar.barClipUp();
        slides.slidesHigh();
        
    }
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
                .strafeRight(10)
                .forward(5)
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(myTrajectory);
    }
}
