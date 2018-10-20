package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.rigidlime.Lift;

@Autonomous(name = "Reset for match start")
public class RigidLimeResetAuto extends LinearOpMode {

    private Lift lift;

    @Override
    public void runOpMode() throws InterruptedException {
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
    }
}
