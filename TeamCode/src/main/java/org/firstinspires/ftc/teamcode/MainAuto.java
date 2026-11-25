package org.firstinspires.ftc.teamcode;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "MainAuto", group = "Autonomous")
public class MainAuto extends LinearOpMode {

    private Follower follower;
    private int pathState = 0;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(82.9152, 133.3998, 270);
    private final Pose point1 = poseFactory.of(132.804, 112.5176, 90);
    private final Pose point1Control1 = poseFactory.of(96.9898, 112.1939, 0);
    private final Pose point2 = poseFactory.of(133.249, 133.2208, 90);
    private final Pose point3 = poseFactory.of(86.7498, 13.5164, 90);
    private final Pose point3Control1 = poseFactory.of(111.0832, 98.6624, 0);
    private final Pose point3Control2 = poseFactory.of(127.6107, 23.8424, 0);
    private final Pose point4Start = poseFactory.of(86.7498, 13.5164, 90);
    private final Pose point4 = poseFactory.of(133.2326, 34.729, 180);

    @Override
    public void runOpMode() {
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        waitForStart();

        while (opModeIsActive()) {
            follower.update();
            updateAutonomousPaths();

            telemetry.addData("Path State", pathState);
            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
    }

    private void updateAutonomousPaths() {
        switch (pathState) {
            case 0:
                follower.follow(path1());
                pathState = 1;
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.follow(path2());
                    pathState = 2;
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.follow(path3());
                    pathState = 3;
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.follow(path4());
                    pathState = 4;
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    pathState = -1;
                }
                break;
        }
    }

    public Path path1() {
        return Paths.curve(start, point1Control1, point1).linear(start, point1);
    }

    public Path path2() {
        return Paths.line(point1, point2).constant(point2);
    }

    public Path path3() {
        return Paths.curve(point2, point3Control1, point3Control2, point3).constant(point3);
    }

    public Path path4() {
        return Paths.line(point4Start, point4).linear(point4Start, point4);
    }
}
