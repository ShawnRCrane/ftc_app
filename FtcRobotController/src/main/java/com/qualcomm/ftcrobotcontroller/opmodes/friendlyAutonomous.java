package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

public class friendlyAutonomous extends LinearOpMode {

    Servo doorServoL;
    Servo doorServoR;
    Servo scoopServo;
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorRight2;
    DcMotor motorLeft2;
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
        motorRight2 = hardwareMap.dcMotor.get("motorRight2");
        motorLeft2 = hardwareMap.dcMotor.get("motorLeft2");
        motorVertical = hardwareMap.dcMotor.get("motorVertical");
        motorExtend = hardwareMap.dcMotor.get("motorExtend");
        motorRetract = hardwareMap.dcMotor.get("motorRetract");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);
//        motorLeft2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        gyro = hardwareMap.gyroSensor.get("gyro");
        touch = hardwareMap.touchSensor.get("touch");
        scoopServo = hardwareMap.servo.get("scoopServo");
        doorServoL = hardwareMap.servo.get("doorServoL");
        doorServoR = hardwareMap.servo.get("doorServoR");
        doorServoL.setPosition(0);
        doorServoR.setPosition(1);
        scoopServo.setPosition(0.5);
        gyro.calibrate();

        //#############Wait For Start#############
        waitForStart();
//        motorLeft2.setTargetPosition(1440);

        /*
        motorLeft2.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_WRITE);
        do {
            waitOneFullHardwareCycle();
        } while (motorLeft2.getController().getMotorControllerDeviceMode() == DcMotorController.DeviceMode.WRITE_ONLY);
        do {
            motorLeft2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle(); //Needed within all loop
        } while(motorLeft2.getCurrentPosition() != 0 ); //Ensures encoder is zero
*/
        motorLeft2.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitOneFullHardwareCycle();
        motorLeft2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft.setPower(1);
        motorLeft2.setPower(1);
        motorRight.setPower(1);
        motorRight2.setPower(1);

        motorLeft2.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitOneFullHardwareCycle();

        int pos = 1;

        while (pos < 1000000000) {
            pos = motorLeft2.getCurrentPosition();
            telemetry.addData("pos", pos);
        }

        motorLeft2.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitOneFullHardwareCycle();

        motorLeft.setPower(0);
        motorLeft2.setPower(0);
        motorRight.setPower(0);
        motorRight2.setPower(0);

        //try {sleep(1000);}catch(Exception e) {}
/*
        motorLeft2.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.WRITE_ONLY);
        waitOneFullHardwareCycle();
        motorLeft.setPower(0);
        motorLeft2.setPower(0);

        motorLeft2.getController().setMotorControllerDeviceMode(DcMotorController.DeviceMode.READ_ONLY);
        waitOneFullHardwareCycle();
        pos = motorLeft2.getCurrentPosition();
        telemetry.addData("pos", pos);
  */

        //#############CODE#################
        /*motorLeft.setPower(1);
        motorRight.setPower(1);
        wait(0); // forward 24 inches
        motorLeft.setPower(0);
        motorRight.setPower(0);

        motorLeft.setPower(1);
        motorRight.setPower(1);
        wait(0); // Turn 56 degrees
        motorLeft.setPower(0);
        motorRight.setPower(0);

        motorLeft.setPower(1);
        motorRight.setPower(1);
        wait(0); //Forward 6 inches
        motorLeft.setPower(0);
        motorRight.setPower(0);

        motorLeft.setPower(1);
        motorRight.setPower(1);
        wait(0); // Turn 90 degrees
        motorLeft.setPower(0);
        motorRight.setPower(0);

        motorLeft.setPower(1);
        motorRight.setPower(1);
        wait(0); //Forward 8 inches
        motorLeft.setPower(0);
        motorRight.setPower(0);
        */
    }
}


