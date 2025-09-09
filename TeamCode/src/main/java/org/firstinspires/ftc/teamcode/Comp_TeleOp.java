package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Comp_TeleOp", group="LinearOpMode")
public class Comp_TeleOp extends LinearOpMode {
    private DcMotor rfd;
    private DcMotor lfd;
    private DcMotor rbd;
    private DcMotor lbd;
    private DcMotor arm;
    private Servo wrist;
    private Servo fingers;

    @Override
    public void runOpMode() {
        waitForStart();

        double speed = 0.4;

        rfd = hardwareMap.get(DcMotor.class, "right_front_drive");
        rbd = hardwareMap.get(DcMotor.class, "right_back_drive");
        lfd = hardwareMap.get(DcMotor.class, "left_front_drive");
        lbd = hardwareMap.get(DcMotor.class, "left_back_drive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        wrist = hardwareMap.get(Servo.class, "wrist");
        fingers = hardwareMap.get(Servo.class, "fingers");

        rfd.setDirection(DcMotor.Direction.REVERSE);
        rbd.setDirection(DcMotor.Direction.REVERSE);

        while (opModeIsActive()) {
            //Put Run Code Here
            float forward = gamepad1.left_stick_y;
            float strafe = gamepad1.left_stick_x;
            float steer = gamepad1.right_stick_x;
            double speed_slow = 0.25;
            double speed_normal = speed;
            double speed_fast = 0.7;

            if (gamepad1.x) {
                speed = speed_slow;
            } else if (gamepad1.a) {
                speed = speed_normal;
            } else if (gamepad1.b) {
                speed = speed_fast;
            }

            rfd.setPower(speed * (forward + steer + strafe));
            lfd.setPower(speed * (forward - (steer + strafe)));
            rbd.setPower(speed * (forward + (steer - strafe)));
            lbd.setPower(speed * (forward - (steer - strafe)));

            arm.setPower(gamepad2.left_stick_y);
            wrist.setPosition(gamepad2.right_stick_y);
            if (gamepad2.left_bumper) {
                fingers.setPosition(0);
            }
            if (gamepad2.right_bumper) {
                fingers.setPosition(1);
            }
        }
    }
}
