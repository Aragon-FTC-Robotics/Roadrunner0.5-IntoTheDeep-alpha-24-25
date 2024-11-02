package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="Goated Auto", group="Autopathred")
public class AutoLeftRed extends LinearOpMode {
    double openPos = 0.825;
    double closePos = 1;

    int parallelpos = -1200;
    int wallpos = -420;
    int startpos = -150;
    int clipposDOWN = -1400;
    int clipposUP = -1250;
    int groundpos = -1750;


    int highPos = 1200;
    int medPos = 440;
    int lowPos = 0;

    double speed = 0.01;
    double pos = 0;
    double wgroundpos = 0.73;
    double highpos = 0.612;
    double wwallpos = 0.776;
    double clippos = 0.990;
    double grabpos = 0.6725;

    Servo claw;
    DcMotor bar;
    DcMotor slideLeft;
    DcMotor slideRight;
    Servo wristServo;


    public void runOpMode() {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-23.5, -60, Math.toRadians(-90));
        drive.setPoseEstimate(startPose);
        claw = hardwareMap.get(Servo.class, "claw1");
        bar = hardwareMap.get(DcMotor.class, "fourBar");
        slideLeft = hardwareMap.get(DcMotor.class, "slideLeft");
        slideRight = hardwareMap.get(DcMotor.class, "slideRight");
        wristServo = hardwareMap.get(Servo.class, "wristServo");
        TrajectorySequence myTrajectory = drive.trajectorySequenceBuilder(startPose)
                .addTemporalMarker(() -> {
                    clawClose();
                })
//                .waitSeconds(0)
                .splineToConstantHeading(new Vector2d(-5, -39),Math.toRadians(90)) //to front of clip
                .addTemporalMarker(() -> {
                    clipDown();
                }) //bar clippos
                .waitSeconds(0.55)
                .splineToConstantHeading(new Vector2d(-5, -25),Math.toRadians(90)) // to lcip pos
                .addTemporalMarker(() -> {
                    clipUp();
                }) //bar upclippos,
                .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(-5, -33.5),Math.toRadians(90))
                .addTemporalMarker(() -> {
                    clawOpen();
                }) // claw open
                .waitSeconds(0.5)
                .addTemporalMarker(() -> {
                    groundThing();
                }) // bar ground
                .waitSeconds(0.8)
                .splineToSplineHeading(new Pose2d(-48, -32.75, Math.toRadians(90)), Math.toRadians(90)) // 1st neutral sample
                .addTemporalMarker(() -> {
                    clawClose();
                }) // claw closed
                .waitSeconds(0.1)
                .splineToSplineHeading(new Pose2d(-50.7, -45.7, Math.toRadians(45)),Math.toRadians(90)) //to bucket
                .addTemporalMarker(() -> {
                    highBucket();
                }) // Bar up and claw open
                .waitSeconds(1.7)
                .addTemporalMarker(() -> {
                    clawOpen();
                }) //openclaw
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {
                    groundThing();
                }) //bar ground
                .waitSeconds(1)
                .splineToSplineHeading(new Pose2d(-59, -31.5, Math.toRadians(90)),Math.toRadians(135)) // 2nd neutral sample
                .addTemporalMarker(() -> {
                    clawClose();
                }) // claw closed
                .waitSeconds(0.6)
                .splineToSplineHeading(new Pose2d(-50.7, -45.7, Math.toRadians(45)),Math.toRadians(90)) //to bucket
                .addTemporalMarker(() -> {
                    highBucket();
                }) // Bar up and claw open
                .waitSeconds(1.7)
                .addTemporalMarker(() -> {
                    clawOpen();
                }) //openclaw
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {
                    groundThing();

                }) //bar ground
                .waitSeconds(0.2)
                .addTemporalMarker(() -> {
                    wristServo.setPosition(0);

                }) //clip up
                .waitSeconds(1)
                .splineToLinearHeading(new Pose2d(42, -52, Math.toRadians(0)),Math.toRadians(0))
                //.splineToLinearHeading(new Pose2d(40, -50, Math.toRadians(0)),Math.toRadians(0))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectorySequence(myTrajectory);
    }
    public void clawClose() {claw.setPosition(closePos);}
    public void clawOpen() {claw.setPosition(openPos);}
    public void barParallel() {bar.setTargetPosition(parallelpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);bar.setPower(0.6);}
    public void barWall() {bar.setTargetPosition(wallpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);bar.setPower(0.6);}
    public void barStart() {bar.setTargetPosition(startpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);bar.setPower(0.6);}
    public void barClipDown() {bar.setTargetPosition(clipposDOWN);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);bar.setPower(0.6);}
    public void barClipUp() {bar.setTargetPosition(clipposUP);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);bar.setPower(0.6);}
    public void barGround() {bar.setTargetPosition(groundpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);bar.setPower(0.6);}
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

    public void highBucket() {
        barParallel();
        slidesHigh();
        wristHigh();
    }
    public void clipDown() {
        barClipDown();
        wristClip();
    }
    public void clipUp() {
        barClipUp();
        wristClip();
    }
    public void groundThing() {
        barStart();
        wristGrab();
        slidesLow();
    }

    public void wristGround() {wristServo.setPosition(groundpos);pos=groundpos;}
    public void wristHigh() {wristServo.setPosition(highpos);pos=highpos;}
    public void wristWall() {wristServo.setPosition(wallpos);pos=wallpos;}
    public void wristClip() {wristServo.setPosition(clippos);pos=clippos;}
    public void wristGrab() {wristServo.setPosition(grabpos);pos=grabpos;}
}
