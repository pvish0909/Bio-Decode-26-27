package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;




@TeleOp(name="MainTeleOp")
public class MainTeleOp extends OpMode {

    private HardwareSetup robot = new HardwareSetup();

    double robotX;
    double robotY;
    double robotRotation;
    double shooterPower = (0.25);
    boolean powerForShooterSwitchState;
    boolean intakeState;

    @Override
    public void init() {
        robot.setup(hardwareMap);
        robot.stopper.setPosition(0.2);
    }



    @Override
    public void loop() {

        /*
        double SHORT_RANGE = 1200;
        double MEDIUM_RANGE = 1800;
        double LONG_RANGE = 2200;
         */

        robotRotation = JoystickSquarer.squarevalue(gamepad1.right_stick_x);
        robotY = JoystickSquarer.squarevalue(-gamepad1.left_stick_y);
        robotX = JoystickSquarer.squarevalue(gamepad1.left_stick_x) * 1.1;


        double frontLeftPowerTemp = robotY + robotX + robotRotation;
        double frontRightPowerTemp = robotY - robotX - robotRotation;
        double backLeftPowerTemp = robotY - robotX + robotRotation;
        double backRightPowerTemp = robotY + robotX - robotRotation;
        double frontMax;
        double backMax;
        double max;

        if (Math.abs(frontLeftPowerTemp) > 1.0 || Math.abs(frontRightPowerTemp) > 1.0 || Math.abs(backLeftPowerTemp) > 1.0 || Math.abs(backRightPowerTemp) > 1.0) {

            frontMax = Math.max(Math.abs(frontLeftPowerTemp), Math.abs(frontRightPowerTemp));
            backMax = Math.max(Math.abs(backLeftPowerTemp), Math.abs(backRightPowerTemp));
            max = Math.max(frontMax, backMax);


            frontLeftPowerTemp = frontLeftPowerTemp / max;
            frontRightPowerTemp = frontRightPowerTemp / max;
            backLeftPowerTemp = backLeftPowerTemp / max;
            backRightPowerTemp = backRightPowerTemp / max;

        }

        robot.setPower(
                frontLeftPowerTemp,
                frontRightPowerTemp,
                backLeftPowerTemp,
                backRightPowerTemp
        );


        if (gamepad1.xWasPressed()) {

            powerForShooterSwitchState = !powerForShooterSwitchState;

            if (powerForShooterSwitchState) {
                robot.shooter.setVelocity(shooterPower*6000);
                robot.shooter2.setVelocity(shooterPower*6000);
            } else {
                robot.shooter.setPower(0);
                robot.shooter2.setPower(0);
            }
        }
        if (gamepad1.b) {
            if (gamepad1.dpadUpWasPressed()) {
                if (shooterPower < 0.451) {
                    shooterPower = shooterPower + 0.05;
                    if (powerForShooterSwitchState) {
                        robot.shooter.setVelocity(shooterPower * 6000);
                        robot.shooter2.setVelocity(shooterPower * 6000);
                    }
                }
            } else if (gamepad1.dpadDownWasPressed()) {
                if (shooterPower > 0.049) {
                    shooterPower = shooterPower - 0.05;
                    if (powerForShooterSwitchState) {
                        robot.shooter.setVelocity(shooterPower * 6000);
                        robot.shooter2.setVelocity(shooterPower * 6000);
                    }
                }
            }
        }
        telemetry.addData("Shooter State", powerForShooterSwitchState);
        telemetry.addData("Shooter Power", shooterPower);
        telemetry.addData("Velocity of Shooter 1", robot.shooter.getVelocity());
        telemetry.addData("Velocity of Shooter 2", robot.shooter2.getVelocity());



        if (gamepad1.yWasPressed()) {
            intakeState = !intakeState;

            if (intakeState) {

                robot.intake.setPower(1);
                robot.intake2.setPower(1);
                robot.intake3.setPower(1);
            } else {

                robot.intake.setPower(0);
                robot.intake2.setPower(0);
                robot.intake3.setPower(0);
            }
        }

        if (gamepad1.dpad_up && !gamepad1.b){
            robot.stopper.setPosition(0.34);
        }
        else if (gamepad1.dpad_down && !gamepad1.b) {
            robot.stopper.setPosition(0.15);
        }

        /*
        if (gamepad1.a) {
            robot.stopper.setPosition(0.34);
            telemetry.addData("Stopper State", "PRESSED (0.35)");
        } else {
            robot.stopper.setPosition(0.20);
            telemetry.addData("Stopper State", "RELEASED (0.20)");
        }
        */

        if (gamepad1.rightBumperWasPressed()){
            if (robot.hood.getPosition() < 0.91) {
                robot.hood.setPosition((robot.hood.getPosition()) + 0.1);
            }
        }
        if (gamepad1.leftBumperWasPressed()){
            if (robot.hood.getPosition() > 0.11){
                robot.hood.setPosition((robot.hood.getPosition()) - 0.1);
            }
        }

        if (gamepad1.dpad_left) {
            robot.turret.setPower(0.4);
        }
        else if (gamepad1.dpad_right){
            robot.turret.setPower(-0.4);
        }
        else{
            robot.turret.setPower(0);
        }
        telemetry.addData("Servo Actual Position", robot.stopper.getPosition());


        telemetry.update();





    }


}
