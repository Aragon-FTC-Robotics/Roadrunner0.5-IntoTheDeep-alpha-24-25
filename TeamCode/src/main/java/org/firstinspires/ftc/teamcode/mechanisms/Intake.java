//USED FOR TESTING TRAINING BOT
//DISREGARD
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor intake1;
    DcMotor intake2;
//    enum IntakeState {IN, OUT};
//    IntakeState currentIntakeState;
    public void init(HardwareMap hm) {
        intake1 = hm.get(DcMotor.class, "intake1");
        intake2 = hm.get(DcMotor.class, "intake2");
//        intake1.setDirection(DcMotorSimple.Direction.REVERSE);
//        currentIntakeState = Intake;
    }
    public void Loop(Gamepad gp1, Gamepad gp2) {
        if (gp1.a) {
            intake1.setPower(0.8);
            intake2.setPower(0.8);
        } else if (gp1.b){
            intake1.setPower(-0.8);
            intake2.setPower(-0.8);
        } else {
            intake1.setPower(0);
            intake2.setPower(0);
        }
    }

//    public void setState(IntakeState state){
//        currentIntakeState = state;
//    }
}
