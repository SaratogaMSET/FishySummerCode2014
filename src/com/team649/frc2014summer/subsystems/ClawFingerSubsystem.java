/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.subsystems;

import com.team649.frc2014summer.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kabi
 */
public class ClawFingerSubsystem extends Subsystem {

    public static final int UP = 1;
    public static final int DOWN = 2;
    private DoubleSolenoid clawSolenoid;
    public static final int TIME_TO_ENGAGE_SOLENOID = 0;
    public int state;

    public ClawFingerSubsystem() {
        super("ClawFingerSubsystem");
        clawSolenoid = new DoubleSolenoid(RobotMap.CLAW_FINGER.FORWARD_SOLENOID_CHANNEL, RobotMap.CLAW_FINGER.REVERSE_SOLENOID_CHANNEL);
        state = 2;
    }

    protected void initDefaultCommand() {
    }

    public void setFingerPosition(int givenState) {
        switch (givenState) {
            case (ClawFingerSubsystem.UP):
                clawSolenoid.set(DoubleSolenoid.Value.kReverse);
                state = 1;
                break;
            case (ClawFingerSubsystem.DOWN):
                clawSolenoid.set(DoubleSolenoid.Value.kForward);
                state = 2;
                break;
        }
    }
}
