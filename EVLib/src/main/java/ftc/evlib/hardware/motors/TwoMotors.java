package ftc.evlib.hardware.motors;

import com.google.common.collect.ImmutableList;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 9/12/16
 * <p>
 * An subclass of NMotorRobot that provides convenience methods for passing in 2 values.
 */
public class TwoMotors extends NMotors {

    //This constructor is private so you cannot pass in a list of motors with an unknown length
//    private TwoMotors(List<Motor> motors, boolean useSpeedMode, Motor.StopBehavior stopBehavior) {
//        super(motors, useSpeedMode, stopBehavior);
//    }

    public TwoMotors(Motor leftMotor, Motor rightMotor, boolean useSpeedMode, Motor.StopBehavior stopBehavior) {
        super(ImmutableList.of(leftMotor, rightMotor), useSpeedMode, stopBehavior);
    }

    public void runMotorsNormalized(double leftValue, double rightValue) {
        runMotorsNormalized(ImmutableList.of(leftValue, rightValue));
    }

    public void runMotors(double leftValue, double rightValue) {
        runMotors(ImmutableList.of(leftValue, rightValue));
    }
}
