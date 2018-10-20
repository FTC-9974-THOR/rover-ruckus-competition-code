package org.firstinspires.ftc.teamcode.hardware.rigidlime;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.ftc9974.thorcore.robot.Motor;

public class Arm {

    private Motor tiltMotor;
    private Servo extensionServo, grabberServo;
    private double extensionPos;
    private TouchSensor touchSensor;

    public Arm(HardwareMap hardwareMap) {
        tiltMotor = new Motor("A-tiltMotor", hardwareMap);
        extensionServo = hardwareMap.servo.get("A-extensionServo");
        grabberServo = hardwareMap.servo.get("A-grabberServo");
        touchSensor = hardwareMap.touchSensor.get("A-touchSensor");
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extensionPos = 1;
    }

    public void tilt(double power) {
        if (bottomedOut()) {
            tiltMotor.setPower(Math.max(0, power));
        } else {
            tiltMotor.setPower(power);
        }
    }

    public boolean bottomedOut() {
        return touchSensor.isPressed();
    }

    public void grab() {
        grabberServo.setPosition(0.9);
    }

    public void release() {
        grabberServo.setPosition(0.5);
    }

    public void extend(double delta) {
        extensionPos = Range.clip(extensionPos + delta, 0, 0.99);
        extensionServo.setPosition(extensionPos);
    }

    public double getExtension() {
        return extensionPos;
    }
}
