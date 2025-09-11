package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class lime extends OpMode {

    private Limelight3A lime;


    @Override
    public void init() {
        lime = hardwareMap.get(Limelight3A.class, "Lime");
        lime.pipelineSwitch( 8);
    }

    @Override
    public  void start() {
        lime.start();
    }

    @Override
    public  void loop() {
        LLResult llResult = lime.getLatestResult();
        if (llResult != null & llResult.isValid()) {
            telemetry.addData("target X offset" , llResult.getTx());
            telemetry.addData("target Y offset" , llResult.getTy());
            telemetry.addData("target area offset" , llResult.getTa());
        }
    }
}
