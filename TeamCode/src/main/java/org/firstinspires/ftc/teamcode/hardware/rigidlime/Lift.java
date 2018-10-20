package org.firstinspires.ftc.teamcode.hardware.rigidlime;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.ftc9974.thorcore.robot.Motor;

public class Lift {

    private Motor liftMotor;
    private TouchSensor downSensor;

    public Lift(HardwareMap hardwareMap) {
        liftMotor = new Motor("L-liftMotor", hardwareMap);
        downSensor = hardwareMap.touchSensor.get("L-downSensor");
    }

    public void move(double power) {
        if (bottomedOut()) {
            liftMotor.setPower(Math.min(power, 0));
        } else {
            liftMotor.setPower(power);
        }
    }

    public void moveTo(int target) {
        moveTo(target, 1);
    }

    public void moveTo(int target, double atSpeed) {
        liftMotor.setTargetPosition(target);
        liftMotor.setPower(atSpeed);
    }

    public boolean moving() {
        return liftMotor.isBusy();
    }

    public void resetEncoder() {
        DcMotor.RunMode previousMode = liftMotor.getMode();
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(previousMode);
    }

    public void setMode(DcMotor.RunMode mode) {
        liftMotor.setMode(mode);
    }

    public int getPosition() {
        return liftMotor.getCurrentPosition();
    }

    public boolean bottomedOut() {
        return downSensor.isPressed();
    }
}
