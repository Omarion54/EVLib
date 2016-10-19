package org.firstinspires.ftc.teamcode.sample.v2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import ftc.electronvolts.statemachine.StateMachine;
import ftc.electronvolts.statemachine.StateName;
import ftc.electronvolts.util.Distance;
import ftc.evlib.opmodes.AbstractAutoOp;

/**
 * This file was made by the electronVolts, FTC team 7393
 * Date Created: 10/18/16
 *
 * A sample autonomous that drives forward for 2 feet.
 * This one uses a custom builder
 */

@Autonomous(name="SampleAuto V2")
public class SampleAuto extends AbstractAutoOp<SampleRobotCfg> {

    //define all the possible states for the state machine
    private enum S implements StateName {
        DRIVE,
        STOP
    }

    @Override
    public StateMachine buildStates() {
        //create a new builder for the states, starting with the DRIVE state
        //this time we are using the custom builder and passing it the robotCfg
        SampleStateMachineBuilder b = new SampleStateMachineBuilder(S.DRIVE, robotCfg);

        //define the DRIVE state to drive for 2 feet and move to the STOP state

        //the old code without the custom buider:
//        b.add(EVStates.drive(S.DRIVE, Distance.fromFeet(2), S.STOP, SampleRobotCfg.MAX_SPEED, robotCfg.getTwoMotors(), 0.5));

        //the new code (more readable and less tedious to type):
        b.addDrive(S.DRIVE, Distance.fromFeet(2), S.STOP, 0.5);

        //define the STOP state to be empty (and never exit) so the state machine will stop
        b.addStop(S.STOP);
        return b.build();
    }

    @Override
    protected SampleRobotCfg createHardwareCfg() {
        return new SampleRobotCfg(hardwareMap);
    }

    @Override
    protected void setup_loop() {

    }

    @Override
    protected void go() {

    }

    @Override
    protected void act() {

    }

    @Override
    protected void end() {

    }
}