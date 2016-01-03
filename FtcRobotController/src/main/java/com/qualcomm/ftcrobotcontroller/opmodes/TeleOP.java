package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class TeleOP extends OpMode {

    Servo scoopServo;
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorVertical;
    DcMotor motorExtend;
    DcMotor motorRetract;
    GyroSensor gyro;
    TouchSensor touch;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorVertical = hardwareMap.dcMotor.get("motorVertical");
        motorExtend = hardwareMap.dcMotor.get("motorExtend");
        motorRetract = hardwareMap.dcMotor.get("motorRetract");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        gyro = hardwareMap.gyroSensor.get("gyro");
        touch = hardwareMap.touchSensor.get("touch");
        scoopServo = hardwareMap.servo.get("scoopServo");
        gyro.calibrate();
    }

    @Override
    public void loop() {
        //*************SET VALUABLES*************\\
        float leftStick = gamepad1.left_stick_y;
        float rightStick = gamepad1.right_stick_y;
        float rightStick2 = gamepad2.right_stick_y;
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


        //*************DRIVER CODE*************\\
        if (rightStick < 0.20 && rightStick > -0.20) //Dead-Zone for the Right Motor
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

        //Logic to extend and retract the arm
        if (buttonY2) //If the Y button is pressed take up slack
        {
            if (rightTrigger2 > 0.20)
            {
                motorExtend.setPower(rightTrigger2);
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
                motorExtend.setPower(rightTrigger2 - (rightTrigger2 / 1.75));
            } else if (leftTrigger2 > 0.20) {
                motorExtend.setPower(-leftTrigger2);
                motorRetract.setPower(-leftTrigger2 + (leftTrigger2 / 1.5));
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