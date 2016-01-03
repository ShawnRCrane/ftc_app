package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class autonomous extends OpMode{
    DcMotor motorRight_1;
    DcMotor motorRight_2;
    DcMotor motorLeft_1;
    DcMotor motorLeft_2;


    @Override
    public void init() {
        motorRight_1 = hardwareMap.dcMotor.get("motorRight_1");
        motorRight_2 = hardwareMap.dcMotor.get("motorRight_2");
        motorLeft_1 = hardwareMap.dcMotor.get("motorLeft_1");
        motorLeft_2 = hardwareMap.dcMotor.get("motorLeft_2");
        motorLeft_1.setDirection(DcMotor.Direction.REVERSE);
        motorLeft_2.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveMotors (double right1, double right2, double left1, double left2) {


        motorRight_1.setPower(right1);
        motorRight_2.setPower(right2);
        motorLeft_1.setPower(left1);
        motorLeft_2.setPower(left2);

    }

    @Override
    public void start() {



        moveMotors(0.75, 0.75, 0.75, 0.75);

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        moveMotors(0., 0., 0.75, 0.75);

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        moveMotors(0.75, 0.75, 0.75, 0.75);

        try {
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    @Override
    public void loop() {
        telemetry.addData("Encoder" ,motorLeft_1.getCurrentPosition());
    }
}