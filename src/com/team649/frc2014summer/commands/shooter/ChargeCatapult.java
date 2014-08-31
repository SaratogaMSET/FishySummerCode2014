/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands.shooter;

import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.pid_control.PIDController649;
import com.team649.frc2014summer.subsystems.WinchSubsystem;

/**
 *
 * @author Kabi
 */
public class ChargeCatapult extends CommandBase {

    private final PIDController649 shooterPID;
    private long startTime;

    public ChargeCatapult() {
        shooterPID = shooterSubsystem.getShooterPID();

    }

    protected void initialize() {
        shooterPID.setSetpoint(WinchSubsystem.CLICKS_PAST_LIMIT);
        startTime = System.currentTimeMillis();
        shooterPID.setPID(WinchSubsystem.EncoderBasedPID.Kp, WinchSubsystem.EncoderBasedPID.Ki, WinchSubsystem.EncoderBasedPID.Kd);
        
    }

    protected void execute() {
        if (shooterSubsystem.isShooterCharged()) {
            shooterSubsystem.setPower(1);
        }
        shooterPID.enable();
    }

    protected boolean isFinished() {
        return (shooterPID.onTarget());
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
