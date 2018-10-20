package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.rigidlime.Lift;
import org.ftc9974.thorcore.robot.drivetrains.TankDrive2Wheel;

@Autonomous(name = "Auto")
public class RigidLimeLandAuto extends LinearOpMode {

    private TankDrive2Wheel rb;
    private Lift lift;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new TankDrive2Wheel(hardwareMap);
        lift = new Lift(hardwareMap);

        waitForStart();

        if (!lift.bottomedOut()) {
            lift.move(1);
            while (!lift.bottomedOut() && !isStopRequested()) {
                idle();
            }
            lift.move(0);
        }

        lift.resetEncoder();
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.moveTo(-48000);
        while (lift.moving()) {
            telemetry.addData("Encoder", lift.getPosition());
            telemetry.update();
            idle();
        }
        lift.move(0);
        rb.setModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setModes(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setTargets(3000, -3000);
        rb.drive(0.9, 1);

        while ((rb.isLeftBusy() || rb.isRightBusy()) && isStopRequested()) {
            idle();
        }
    }
}
