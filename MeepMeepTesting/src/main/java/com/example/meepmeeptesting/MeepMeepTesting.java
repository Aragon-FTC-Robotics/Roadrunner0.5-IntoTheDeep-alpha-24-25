package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-23.5, -60, Math.toRadians(-90)))
                        .splineToConstantHeading(new Vector2d(-5, -39),Math.toRadians(90)) //to front of clip
                        .splineToConstantHeading(new Vector2d(-5, -25),Math.toRadians(90)) // to lcip pos
                        .splineToConstantHeading(new Vector2d(-5, -34.5),Math.toRadians(90))
                        .waitSeconds(0.1)
                        .splineToSplineHeading(new Pose2d(-48, -32, Math.toRadians(90)), Math.toRadians(90)) // 1st neutral sample
                        .waitSeconds(0.1)
                        .splineToSplineHeading(new Pose2d(-50, -45, Math.toRadians(45)),Math.toRadians(90)) //to bucket
                        .waitSeconds(0.5)
                        .splineToSplineHeading(new Pose2d(-59, -33,Math.toRadians(90)), -10) // 2nd neutral sample
                        .waitSeconds(2)
                        .splineToSplineHeading(new Pose2d(-50, -45, Math.toRadians(45)),Math.toRadians(90)) //to bucket
                        .waitSeconds(2)
                        .splineToSplineHeading(new Pose2d(57, -43, Math.toRadians(0)),Math.toRadians(45))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}