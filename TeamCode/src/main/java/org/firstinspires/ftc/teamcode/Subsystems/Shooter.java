package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    public enum States {
        LPOWER,
        MPOWER,
        SPOWER,
        OFF,
    }
    private States state = States.OFF;
    DcMotor topShooterMotor;
    DcMotor bottomShooterMotor;
    public static double lPower = 0.85;
    public static double mPower = 0.7;
    public static double sPower = 0.5;


    public void initiate(HardwareMap hardwareMap) {
        bottomShooterMotor = hardwareMap.dcMotor.get("bottomShooterMotor");
        topShooterMotor = hardwareMap.dcMotor.get("topShooterMotor");
    }

    public void setState(States newState) {
        state = newState;
    }

    public void update() {
        switch (state) {
            case LPOWER:
                bottomShooterMotor.setPower(lPower);
                topShooterMotor.setPower(-lPower);
                break;
            case MPOWER:
                bottomShooterMotor.setPower(mPower);
                topShooterMotor.setPower(-mPower);
                break;
            case SPOWER:
                bottomShooterMotor.setPower(sPower);
                topShooterMotor.setPower(-sPower);
                break;
            case OFF:
                bottomShooterMotor.setPower(0);
                topShooterMotor.setPower(0);

        }
    } // <-- update() ends here

    public States getState() {
        return state;
    }
}