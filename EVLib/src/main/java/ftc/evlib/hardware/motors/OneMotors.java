package ftc.evlib.hardware.motors;

import com.google.common.collect.ImmutableList;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 9/12/16
 * <p>
 * A subclass of NMotors that provides convenience methods for passing in 1 values.
 */
public class OneMotors extends NMotors {
    public OneMotors(Motor motor, boolean useSpeedMode, Motor.StopBehavior stopBehavior) {
        super(ImmutableList.of(motor), useSpeedMode, stopBehavior);
    }

    public void runMotorsNormalized(double value) {
        runMotorsNormalized(ImmutableList.of(value));
    }

    public void runMotors(double value) {
        runMotors(ImmutableList.of(value));
    }
}