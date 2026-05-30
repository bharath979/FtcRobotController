package org.firstinspires.ftc.teamcode.Subsystems;

import android.icu.util.TimeZone;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gate {
    public enum States{
        OPEN,
        CLOSE
    }
    private States state = States.CLOSE;

    Servo gateServo;

    public long timeSnapshot;

    public long gateTime;
    public long deltaT;

    public static double openPosition = 1;
    public static double closePostion = 0;

    public void initiate(HardwareMap hardwareMap){
        gateServo = hardwareMap.servo.get("gateServo");
    }
    public void setState(States newState){
        state = newState;
        timeSnapshot = System.currentTimeMillis();
        gateTime = 1000;
    }
    public void update(){
        switch (state) {
            case OPEN:
                gateServo.setPosition(openPosition);
                deltaT = System.currentTimeMillis() - timeSnapshot;
                if (deltaT > gateTime){
                    setState(States.CLOSE);
                }
                break;
            case CLOSE:
                gateServo.setPosition(closePostion);
                break;
        }
    }
    public States getState(){
        return state;
    }
}
