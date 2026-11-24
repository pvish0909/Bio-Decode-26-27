package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.IMU.Parameters;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class HardwareSetup {

    //public IMU imu;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotorEx shooter;
    public DcMotorEx shooter2;
    public CRServo intake;
    public DcMotor intake2;
    public CRServo intake3;
    public Servo stopper;
    public Servo hood;
    public DcMotor turret;

    public void setup(HardwareMap hwMap) {

        frontLeft = hwMap.get(DcMotor.class, "leftFront");
        frontRight = hwMap.get(DcMotor.class, "rightFront");
        backLeft = hwMap.get(DcMotor.class, "leftRear");
        backRight = hwMap.get(DcMotor.class, "rightRear");
        shooter = hwMap.get(DcMotorEx.class, "TopFlywheel");
        shooter2 = hwMap.get(DcMotorEx.class, "BottomFlywheel");
        intake = hwMap.get(CRServo.class, "intakeDiddy2");
        intake3 = hwMap.get(CRServo.class, "intakeDiddy1");
        intake2 = hwMap.get(DcMotor.class, "Intake");
        stopper = hwMap.get(Servo.class, "Stopper1");
        hood = hwMap.get(Servo.class, "Hood");
        turret = hwMap.get(DcMotor.class, "Turret");
        //imu = hwMap.get(IMU.class, "imu");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        shooter.setDirection(DcMotorEx.Direction.FORWARD); //change this if the shooter goes the wrong way
        shooter2.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake3.setDirection(DcMotorSimple.Direction.FORWARD);
        intake2.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );
            //imu.initialize(new IMU.Parameters(RevOrientation));
    }


    public void setPower(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower){
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public double getHeading(){
        //return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        return 0;
    }

}

// things to add + constants to fix: TICKS_PER_TURN, naming motors correctly on hardware, changing turn to use Gyro sensor, incorporate gryo into auto, changing proportional floor beased on weight
// VELOCITIES FOR SHOOTING, DIRECTION FOR USBFACINGDIRECTION, DIRECTION FOR LOGOFACINGDIRECTION