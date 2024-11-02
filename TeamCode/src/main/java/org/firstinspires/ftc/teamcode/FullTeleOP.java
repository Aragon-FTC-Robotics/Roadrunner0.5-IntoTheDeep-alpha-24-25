package org.firstinspires.ftc.teamcode;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.Claw;
import org.firstinspires.ftc.teamcode.mechanisms.Slides;
import org.firstinspires.ftc.teamcode.mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.mechanisms.driveTrain;
import org.firstinspires.ftc.teamcode.mechanisms.fourBar;

@TeleOp(name="FULLOPMOSE pidedition \uD83D\uDCA6", group="Linear OpMode")
public class FullTeleOP extends LinearOpMode {

    public Slides slides = new Slides();
    public fourBar fourbar = new fourBar();
    public driveTrain drivetrain = new driveTrain();
    public Claw claw = new Claw();
    public Wrist wrist = new Wrist();
    public Gamepad gp1;
    public Gamepad gp2;


    @Override
    public void waitForStart() {
        super.waitForStart();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        slides.init(hardwareMap);
        fourbar.init(hardwareMap);
        drivetrain.init(hardwareMap);
        claw.init(hardwareMap);
        wrist.init(hardwareMap);
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        gp1 = gamepad1;
        gp2 = gamepad2;

        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            slides.Loop(gp1, gp2);
            fourbar.Loop(gp1, gp2);
            drivetrain.Loop(gp1,gp2);
            claw.Loop(gp1, gp2);
            wrist.Loop(gp1, gp2);
            telemetry.addData("slidelpos", slides.getLpos());
            telemetry.addData("sliderpos", slides.getRpos());
            telemetry.addData("wristpos", wrist.getPos());
            telemetry.addData("pos:", fourbar.getarmPos());
            telemetry.addData("target:", fourbar.getTarget());
            telemetry.addData("power:", fourbar.getPower());
            telemetry.addData("pid:", fourbar.getpid());
            telemetry.addData("par0: ", drivetrain.getpar0());
            telemetry.addData("par1: ", drivetrain.getpar1());
            telemetry.addData("perp: ", drivetrain.getperp());
            telemetry.update();
        }
    }
}

