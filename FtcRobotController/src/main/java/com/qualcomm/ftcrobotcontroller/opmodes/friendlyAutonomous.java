package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class friendlyAutonomous extends LinearOpMode {
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
    public void runOpMode() throws InterruptedException {

        //##############Init##############
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorVertical = hardwareMap.dcMotor.get("motorVertical");
        motorExtend = hardwareMap.dcMotor.get("motorExtend");
        motorRetract = hardwareMap.dcMotor.get("motorRetract");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        gyro = hardwareMap.gyroSensor.get("gyro");
        touch = hardwareMap.touchSensor.get("touch");
        scoopServo = hardwareMap.servo.get("scoopServo");
        doorServoL = hardwareMap.servo.get("doorServoL");
        doorServoR = hardwareMap.servo.get("doorServoR");
        gyro.calibrate();

        //#############Wait For Start#############
        waitForStart();

        //#############CODE#################
        `

    }
}


