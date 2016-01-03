package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import  com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

public class TeleOpJames extends OpMode{

    DcMotor motorRight;
    DcMotor motorLeft;
    GyroSensor gyro;
    DeviceInterfaceModule ifm;

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        gyro = hardwareMap.gyroSensor.get("gyro");
    }

    public void GyroSafe() {

    }

    @Override
    public void loop() {


        float leftStick = -gamepad1.left_stick_y;
        float rightStick = -gamepad1.right_stick_y;

        if (rightStick < 0.10 && rightStick > -0.10)
            rightStick = 0;
        if (leftStick < 0.10 && leftStick > -0.10)
            leftStick = 0;

        motorRight.setPower(rightStick);
        motorLeft.setPower(leftStick);
    }

}
