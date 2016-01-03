package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class friendlyAutonomous extends LinearOpMode {

    Servo scoopServo;
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorVertical;
    DcMotor motorExtend;
    DcMotor motorRetract;
    GyroSensor gyro;
    TouchSensor touch;

    public void turn(int angle, double power) {
        if (angle > 0) {
            //TURN RIGHT
        }
        else {
            //TURN LEFT
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //*************INIT*************\\
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

        //Wait for start
        waitForStart();

        //*************AUTONOMOUS CODE*************\\
        // Drive forward 48 in
        // turn right 45 deg
        // drive forward 24 in
        // turn 45 deg
        // drive forward 18 in
        // lift arm 30 deg
        // dump scoop left
        // lower arm -30 deg
        // turn -90 deg
        // drive backwards 20 in.



    }
}
