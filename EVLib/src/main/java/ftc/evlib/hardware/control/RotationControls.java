package ftc.evlib.hardware.control;

import com.qualcomm.robotcore.hardware.GyroSensor;

import ftc.electronvolts.util.Angle;
import ftc.electronvolts.util.InputExtractor;
import ftc.electronvolts.util.Vector3D;

import static ftc.evlib.driverstation.Telem.telemetry;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 9/20/16
 * <p>
 * Factory class for RotationControl
 */

public class RotationControls {

    public static RotationControl inputExtractor(final InputExtractor<Double> rotation) {
        return new RotationControl() {
            @Override
            public boolean act() {
                return true;
            }

            @Override
            public double getVelocityR() {
                return rotation.getValue();
            }

            @Override
            public Angle getPolarDirectionCorrection() {
                return Angle.fromRadians(0);
            }
        };
    }

    public static RotationControl zero() {
        return constant(0);
    }

    /**
     * rotate at a constant velocity
     *
     * @param velocityR the velocity to rotate at
     * @return the created RotationControl
     */
    public static RotationControl constant(final double velocityR) {
        return new RotationControl() {
            @Override
            public boolean act() {
                return true;
            }

            @Override
            public double getVelocityR() {
                return velocityR;
            }

            @Override
            public Angle getPolarDirectionCorrection() {
                return Angle.fromRadians(0);
            }
        };
    }

    public static RotationControl gyro(GyroSensor gyro, Angle targetHeading) {
        return gyro(gyro, targetHeading, RotationControl.DEFAULT_MAX_ANGULAR_SPEED);
    }

    /**
     * Controls the rotation of a mecanum robot with a gyro sensor
     *
     * @param gyro            the gyro sensor
     * @param targetHeading   the direction to rotate to
     * @param maxAngularSpeed the max speed to rotate at
     * @return the created RotationControl
     */
    public static RotationControl gyro(final GyroSensor gyro, final Angle targetHeading, final double maxAngularSpeed) {
        final double GYRO_GAIN = 1;
        final double GYRO_DEADZONE = 0.01;
        final double minAngularSpeed = 0.1;


        final Vector3D targetHeadingVector = Vector3D.fromPolar2D(1, targetHeading);

        return new RotationControl() {
            private double gyroHeading, rotationCorrection;

            @Override
            public boolean act() {

                //get the gyro heading and convert it to a vector
                gyroHeading = gyro.getHeading();
                Vector3D gyroVector = Vector3D.fromPolar2D(1, Angle.fromDegrees(gyroHeading));

                //find the "signed angular separation", the magnitude and direction of the error
                double angleRadians = Vector3D.signedAngularSeparation(targetHeadingVector, gyroVector).radians();
                telemetry.addData("signed angular separation", angleRadians);

//              This graph shows angle error vs. rotation correction
//              ____________________________
//              | correction.       ____   |
//              |           .      /       |
//              |           .   __/        |
//              | ........__.__|.......... |
//              |      __|  .     error    |
//              |     /     .              |
//              | ___/      .              |
//              |__________________________|
//
//              The following code creates this graph:

                //scale the signedAngularSeparation by a constant
                rotationCorrection = GYRO_GAIN * angleRadians;

                if (Math.abs(rotationCorrection) > maxAngularSpeed) {
                    //cap the rotationCorrection at +/- maxAngularSpeed
                    rotationCorrection = Math.signum(rotationCorrection) * maxAngularSpeed;
                } else if (Math.abs(rotationCorrection) < GYRO_DEADZONE) {
                    //set it to 0 if it is in the deadzone
                    rotationCorrection = 0;
                } else if (Math.abs(rotationCorrection) < minAngularSpeed) {
                    //set it to the minimum if it is below
                    rotationCorrection = Math.signum(rotationCorrection) * minAngularSpeed;
                }

                telemetry.addData("rotationCorrection", rotationCorrection);

                return true;
            }

            @Override
            public double getVelocityR() {
                return rotationCorrection;
            }

            @Override
            public Angle getPolarDirectionCorrection() {
                return Angle.fromDegrees(-gyroHeading);
            }
        };
    }
}