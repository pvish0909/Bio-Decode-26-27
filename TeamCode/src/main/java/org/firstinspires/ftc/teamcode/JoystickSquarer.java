package org.firstinspires.ftc.teamcode;

public class JoystickSquarer{

    public static double squarevalue(double JoystickValue){

        boolean isNegative = JoystickValue < 0;

        double squaredvalue = JoystickValue * JoystickValue;

        if (isNegative){
            squaredvalue = squaredvalue * -1;
        }

        return squaredvalue;
    }

}
