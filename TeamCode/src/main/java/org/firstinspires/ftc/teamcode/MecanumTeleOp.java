package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Drivetrain drivetrain = new Drivetrain();
        drivetrain.initiate(hardwareMap);
        Intake intake = new Intake();
        intake.initiate(hardwareMap);
        Shooter shooter = new Shooter();
        shooter.initiate(hardwareMap);
        boolean lastX = false;
        int shooterStage = 0;
        boolean lastB = false;
        boolean servoOpen = false;
        waitForStart();
        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Drivetrain
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            drivetrain.run(x, y, rx);

            // Intake
            if (gamepad1.right_trigger > 0.1) {
                intake.setState(Intake.States.IN);
            } else if (gamepad1.left_trigger > 0.1) {
                intake.setState(Intake.States.OUT);
            } else {
                intake.setState(Intake.States.STOP);
            }
            intake.update();

            if (gamepad1.x && !lastX) {
                if (shooterStage == 0) {
                    shooterStage = 1;
                    shooter.setState(Shooter.States.SPOWER);
                } else {
                    shooterStage = (shooterStage % 4) + 1;
                    switch (shooterStage) {
                        case 1:
                            shooter.setState(Shooter.States.SPOWER);
                            break;
                        case 2:
                            shooter.setState(Shooter.States.MPOWER);
                            break;
                        case 3:
                            shooter.setState(Shooter.States.LPOWER);
                            break;
                        case 4:
                            shooter.setState(Shooter.States.MPOWER);
                            break;
                    }
                }
            }
            if (gamepad1.dpad_up) {
                shooterStage = 0;
                shooter.setState(Shooter.States.OFF);
            }
            lastX = gamepad1.x;
            shooter.update();
        }
    }
}
