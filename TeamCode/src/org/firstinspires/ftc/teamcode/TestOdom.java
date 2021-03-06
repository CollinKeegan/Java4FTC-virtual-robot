package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import net.aspenk12.java4ftc.OdometrySystem;

/**
 * TeleOp op mode to test odometry with three "dead-wheel" encoders.
 *
 * EDITED by bfr to use the OdometrySystem
 */

//@Disabled
@TeleOp(name = "TestOdom", group = "OdomBot")
public class TestOdom extends LinearOpMode {

    EncBot bot = new EncBot();

    public void runOpMode(){
        gamepad1.setJoystickDeadzone(0.05f);
        bot.init(hardwareMap);
        OdometrySystem odom = new OdometrySystem(hardwareMap);
        waitForStart();
        while (opModeIsActive()){
            odom.update();
            telemetry.addData("POSE", "x = %.1f  y = %.1f  h = %.01f", odom.getX(), odom.getY(), Math.toDegrees(odom.getHeading()));
//            telemetry.addData("Back Left", "T = %d  V = %.0f", bot.motors[0].getCurrentPosition(), bot.motors[0].getVelocity());
//            telemetry.addData("Front Left", "T = %d  V = %.0f", bot.motors[1].getCurrentPosition(), bot.motors[1].getVelocity());
//            telemetry.addData("Front Right", "T = %d  V = %.0f", bot.motors[2].getCurrentPosition(), bot.motors[2].getVelocity());
//            telemetry.addData("Back Right", "T = %d  V = %.0f", bot.motors[3].getCurrentPosition(), bot.motors[3].getVelocity());
//            telemetry.addData("Right Enc", "T = %d  V = %.0f", bot.encoders[0].getCurrentPosition(), bot.encoders[0].getVelocity());
//            telemetry.addData("Left Enc", "T = %d  V = %.0f", bot.encoders[1].getCurrentPosition(), bot.encoders[1].getVelocity());
//            telemetry.addData("X Enc", "T = %d  V = %.0f", bot.encoders[2].getCurrentPosition(), bot.encoders[2].getVelocity());
            telemetry.update();
            double px = gamepad1.left_stick_x;
            double py = -gamepad1.left_stick_y;
            double pa = gamepad1.left_trigger - gamepad1.right_trigger;
            bot.setDrivePower(px, py, pa);
        }
    }
}
