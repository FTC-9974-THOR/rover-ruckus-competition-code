package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.rigidlime.Arm;
import org.firstinspires.ftc.teamcode.hardware.rigidlime.Lift;
import org.ftc9974.thorcore.robot.drivetrains.TankDrive2Wheel;

@TeleOp(name = "Tele Op")
public class RigidLimeTeleOp extends OpMode {

    private TankDrive2Wheel rb;
    private Lift lift;
    private Arm arm;

    @Override
    public void init() {
        rb = new TankDrive2Wheel(hardwareMap);
        rb.setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);
        lift = new Lift(hardwareMap);
        arm = new Arm(hardwareMap);
    }

    @Override
    public void loop() {
        rb.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

        if (gamepad1.left_bumper) {
            lift.move(-1);
        } else if (gamepad1.right_bumper) {
            lift.move(1);
        } else {
            lift.move(0);
        }

        if (gamepad1.a) {
            arm.tilt(-1);
        } else if (gamepad1.b) {
            arm.tilt(1);
        } else {
            arm.tilt(0);
        }

        if (gamepad1.x) {
            arm.extend(0.01);
        } else if (gamepad1.y) {
            arm.extend(-0.01);
        }

        if (gamepad1.dpad_left) {
            arm.grab();
        } else if (gamepad1.dpad_right) {
            arm.release();
        }

        telemetry.addData("Encoder", lift.getPosition());
        telemetry.addData("Lift bottomed out", lift.bottomedOut());
        telemetry.addData("Arm bottomed out", arm.bottomedOut());
        telemetry.addData("Arm extension", arm.getExtension());
    }
}
