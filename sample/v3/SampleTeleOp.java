package org.firstinspires.ftc.teamcode.sample.v3;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import ftc.electronvolts.util.Function;
import ftc.electronvolts.util.Functions;
import ftc.evlib.hardware.servos.ServoControl;
import ftc.evlib.opmodes.AbstractTeleOp;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/18/16
 *
 * A sample TeleOp program that will allow you to control 2 motors with the left and right joysticks
 * This one also has controls for 2 servos:
 * arm servo:
 * dpad_left - go to LEFT
 * dpad_down - go to MIDDLE
 * dpad_right - go to RIGHT
 *
 * leg servo:
 * a button - go to DOWN
 * b button - go to MIDDLE
 * x button - go to UP
 *
 * I guess you could say that this opmode cost us ...
 *  ... an arm and a leg!
 */

@TeleOp(name="SampleTeleOp V3")
public class SampleTeleOp extends AbstractTeleOp<SampleRobotCfg> {
    private ServoControl armServo, legServo;

    @Override
    protected Function getJoystickScalingFunction() {
        //use a squared function for the joystick scaling to allow fine control
        return Functions.squared();
//        return Functions.cubed();
//        return Functions.none();
    }

    @Override
    protected SampleRobotCfg createHardwareCfg() {
        //create a SampleRobotCfg
        return new SampleRobotCfg(hardwareMap);
    }

    @Override
    protected void setup() {
        armServo = robotCfg.getServo(SampleRobotCfg.SampleServoName.ARM_SERVO);
        legServo = robotCfg.getServo(SampleRobotCfg.SampleServoName.LEG_SERVO);
    }

    @Override
    protected void setup_loop() {

    }

    @Override
    protected void go() {

    }

    @Override
    protected void act() {
        //set the motor powers to the joystick values
        robotCfg.getTwoMotors().runMotors(
                driver1.left_stick_y.getValue(),
                driver1.right_stick_y.getValue()
        );

        //if the left dpad was just pressed
        if (driver1.dpad_left.justPressed()) {
            //move the servo arm to the LEFT position
            armServo.goToPreset(SampleRobotCfg.ArmServoPresets.LEFT);
        }
        //if the down dpad was just pressed
        if (driver1.dpad_down.justPressed()) {
            //move the servo arm to the MIDDLE position
            armServo.goToPreset(SampleRobotCfg.ArmServoPresets.MIDDLE);
        }
        //if the right dpad was just pressed
        if (driver1.dpad_right.justPressed()) {
            //move the servo arm to the RIGHT position
            armServo.goToPreset(SampleRobotCfg.ArmServoPresets.RIGHT);
        }


        //if the a button was just pressed
        if (driver1.a.justPressed()) {
            //move the leg servo to the DOWN position
            legServo.goToPreset(SampleRobotCfg.LegServoPresets.DOWN);
        }
        //if the b button was just pressed
        if (driver1.b.justPressed()) {
            //move the leg servo to the MIDDLE position
            legServo.goToPreset(SampleRobotCfg.LegServoPresets.MIDDLE);
        }
        //if the x button was just pressed
        if (driver1.x.justPressed()) {
            //move the leg servo to the UP position
            legServo.goToPreset(SampleRobotCfg.LegServoPresets.UP);
        }
    }

    @Override
    protected void end() {

    }
}