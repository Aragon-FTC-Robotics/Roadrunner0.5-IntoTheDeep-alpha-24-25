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
@Autonomous(name="v\uD83D\uDCA3\uD83D\uDCA3\uD83D\uDCA3\uD83D\uDCA3\uD83D\uDCA3", group="Autopathred")
public class AutoLeftRed extends LinearOpMode {
    double openPos = 0.85;
    double closePos = 1;

    int parallelpos = -1100;
    int wallpos = -420;
    int startpos = -130;
    int clipposDOWN = -1400;
    int clipposUP = -1250;
    int groundpos = -1850;


    int highPos = 1200;
    int medPos = 440;
    int lowPos = 0;

    double speed = 0.01;
    double pos = 0;
    double wgroundpos = 0.73;
    double highpos = 0.612;
    double wwallpos = 0.776;
    double clippos = 0.990;
    double grabpos = 0.755;

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
        Trajectory myTrajectory = drive.trajectoryBuilder(startPose)
                .addDisplacementMarker(() -> {
                    clawClose();
                })
                .splineToConstantHeading(new Vector2d(-5, -35),Math.toRadians(90)) //to front of clip
                .addDisplacementMarker(() -> {
                    clipDown();
                }) //bar clippos
                .splineToConstantHeading(new Vector2d(-5, -33),Math.toRadians(90)) // to lcip pos
                .addDisplacementMarker(() -> {
                    clipUp();
                }) //bar upclippos
                .splineToConstantHeading(new Vector2d(-5, -35),Math.toRadians(90))
                .addDisplacementMarker(() -> {
                    groundThing();
                }) // bar ground and clip open
                .splineToSplineHeading(new Pose2d(-48, -37, Math.toRadians(90)), Math.toRadians(90)) // 1st neutral sample
                .addDisplacementMarker(() -> {
                    clawClose();
                }) // claw closed
                .splineToSplineHeading(new Pose2d(-53, -53, Math.toRadians(45)),Math.toRadians(90)) //to bucket
                .addDisplacementMarker(() -> {
                    highBucket();
                    clawOpen();
                }) // Bar up and claw open
                .addDisplacementMarker(() -> {
                    groundThing();
                }) //bar ground
                .splineToSplineHeading(new Pose2d(-58, -37, Math.toRadians(90)),Math.toRadians(90)) // 2nd neutral sample
                .addDisplacementMarker(() -> {
                    clawClose();
                }) // claw closed
                .splineToSplineHeading(new Pose2d(-53, -53, Math.toRadians(45)),Math.toRadians(90)) //to bucket
                .addDisplacementMarker(() -> {
                    highBucket();
                    clawOpen();
                }) // Bar up and claw open
                .addDisplacementMarker(() -> {
                    groundThing();
                }) //bar ground
                .splineToSplineHeading(new Pose2d(-57, -33, Math.toRadians(140)),Math.toRadians(90)) //3rd neutral sample
                .addDisplacementMarker(() -> {
                    clawClose();
                }) // claw closed
                .splineToSplineHeading(new Pose2d(-53, -53, Math.toRadians(45)),Math.toRadians(180)) //to bucket
                .addDisplacementMarker(() -> {
                    highBucket();
                    clawOpen();
                }) // Bar up and claw open
                .addDisplacementMarker(() -> {
                    groundThing();
                }) //bar ground
                .splineToSplineHeading(new Pose2d(-60, -45, Math.toRadians(0)),Math.toRadians(45))
                .build();

        waitForStart();

        if(isStopRequested()) return;

        drive.followTrajectory(myTrajectory);
    }
    public void clawClose() {claw.setPosition(closePos);}
    public void clawOpen() {claw.setPosition(openPos);}
    public void barParallel() {bar.setTargetPosition(parallelpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barWall() {bar.setTargetPosition(wallpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barStart() {bar.setTargetPosition(startpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barClipDown() {bar.setTargetPosition(clipposDOWN);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barClipUp() {bar.setTargetPosition(clipposUP);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
    public void barGround() {bar.setTargetPosition(groundpos);bar.setMode(DcMotor.RunMode.RUN_TO_POSITION);}
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
        barClipUp();
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
        barGround();
        wristGround();
    }

    public void wristGround() {wristServo.setPosition(groundpos);pos=groundpos;}
    public void wristHigh() {wristServo.setPosition(highpos);pos=highpos;}
    public void wristWall() {wristServo.setPosition(wallpos);pos=wallpos;}
    public void wristClip() {wristServo.setPosition(clippos);pos=clippos;}
    public void wristGrab() {wristServo.setPosition(grabpos);pos=grabpos;}
}
