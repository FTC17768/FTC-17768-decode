package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp(name = "PIDF_Tuning", group="LinearOpMode")
public class PIDF_Tuning extends LinearOpMode {
    private PIDController controller;

    private DcMotorEx pidMotor;

//    Gobilda 5204 Series
    public static double ticks_in_degree = 1993.6 / 360.0;

    //    default
    public static double p=0, i=0, d=0;
    public static double f=0;

//    arm
//    public static double p=0.2, i=0.7, d=0.03;
//    public static double f=0.6;

//    extend
//    public static double p=0.008, i=0.001, d=0.001;
//    public static double f=0.06;

    public static int target = 0;

    @Override
    public void runOpMode() {
        waitForStart();

        // Change this to the motor you want to tune
        pidMotor = hardwareMap.get(DcMotorEx.class, "arm");

        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        while (opModeIsActive()) {
            controller.setPID(p, i ,d);
            int pidMotorPos = pidMotor.getCurrentPosition();
            double pid = controller.calculate(pidMotorPos, target);
            double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

            double power = pid + ff;

            pidMotor.setPower(power);

            telemetry.addData("motor ", pidMotor);
            telemetry.addData("pos ", pidMotorPos);
            telemetry.addData("target", target);
            telemetry.update();
        }
    }
}
