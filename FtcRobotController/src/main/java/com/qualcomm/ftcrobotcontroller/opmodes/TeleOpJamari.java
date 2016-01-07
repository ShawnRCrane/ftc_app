package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TeleOpJamari extends OpMode {
    Servo doorServoL;
    Servo doorServoR;
    Servo scoopServo;
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorVertical;
    DcMotor motorExtend;
    DcMotor motorRetract;
    GyroSensor gyro;
    TouchSensor touch;
    double scoopZero;
    double rDoorZero;
    double lDoorZero;

    @Override
    public void init() {

        scoopZero = scoopServo.getPosition();
        rDoorZero = doorServoR.getPosition();
        lDoorZero = doorServoL.getPosition();
        gyro.calibrate();
    }

    @Override
    public void loop() {
        //*************SET VALUABLES*************\\
        float leftStick = gamepad1.left_stick_y * (float) 1.1;
        float rightStick = gamepad1.right_stick_y * (float) 1.1;
        float rightStick2 = gamepad2.right_stick_y / (float) 1.5;
        float rightTrigger2 = gamepad2.right_trigger;
        double leftStick2 = gamepad2.left_stick_x / 1.8;
        float leftTrigger2 = gamepad2.left_trigger;
        boolean rightBumper2 = gamepad2.right_bumper;
        boolean leftBumper2 = gamepad2.left_bumper;
        boolean buttonY2 = gamepad2.y;



        //*************PRINT TO PHONE*************\\
        telemetry.addData("Heading", gyro.getHeading());
        telemetry.addData("RawX", gyro.rawX());
        telemetry.addData("RawY", gyro.rawY());
        telemetry.addData("RawZ", gyro.rawZ());
        telemetry.addData("Touch", touch.getValue());
        telemetry.addData("ServoLeft", doorServoL.getPosition());
        telemetry.addData("ServoRight",doorServoR.getPosition());
        telemetry.addData("ScoopServo", scoopServo.getPosition());

        //*************DRIVER CODE*************\\
        if (rightStick < 0.20 && rightStick > -0.20) //First Dead-Zone for the Right Motor
            rightStick = 0;
        if (leftStick < 0.20 && leftStick > -0.20) //Dead-Zone for the Left Motor
            leftStick = 0;
        if (rightStick > 0.95) //Second Dead-Zone for the Right Motor
            rightStick = 1;
        if (rightStick < -0.95)
            rightStick = -1;
        if (leftStick > 0.95) //Second Dead-Zone for the Left Motor
            leftStick = 1;
        if (leftStick < -0.95)
            leftStick = -1;

        //Set the drive motors
        motorRight.setPower(-rightStick);
        motorLeft.setPower(-leftStick);


/*
        motorRetract.setPower(leftTrigger2);
        motorExtend.setPower(-rightTrigger2);
        if (leftBumper2)
            motorRetract.setPower(-1);
        if (rightBumper2)
            motorExtend.setPower(1);
*/

        //*************OPERATOR CODE*************\\

        //Scoop Servos
        scoopServo.setPosition(scoopZero + (leftStick2 / (float) 3.75));




        //Logic to extend and retract the arm
        if (buttonY2) //If the Y button is pressed take up slack
        {
            if (rightTrigger2 > 0.20)
            {
                motorRetract.setPower(rightTrigger2);
            }
            else if (leftTrigger2 > 0.20)
            {
                motorExtend.setPower(-leftTrigger2);
            }
            else
            {
                motorExtend.setPower(0);
                motorRetract.setPower(0);
            }
        }
        else //else extend or retract the arm
        {
            if (rightTrigger2 > 0.20) {
                motorRetract.setPower(rightTrigger2);
                motorExtend.setPower(rightTrigger2 - (rightTrigger2 / (float) 1.75));
            } else if (leftTrigger2 > 0.20) {
                motorExtend.setPower(-leftTrigger2);
                motorRetract.setPower(-leftTrigger2 + (leftTrigger2 / (float) 1.4));
            }
            else
            {
                motorExtend.setPower(0);
                motorRetract.setPower(0);
            }
        }
        if (touch.isPressed() && rightStick2 > 0) //Arm safety code
            rightStick2 = 0;

        //Set the motor that raises and lowers the arm
        if (rightStick2 < 0.10 && rightStick2 > -0.10)
            rightStick2 = 0;
        motorVertical.setPower(rightStick2);
    }

}