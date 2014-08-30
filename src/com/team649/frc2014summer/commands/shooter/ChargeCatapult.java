/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team649.frc2014summer.commands.shooter;

import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.pid_control.PIDController649;

/**
 *
 * @author Kabi
 */
public class ChargeCatapult extends CommandBase {

    private final PIDController649 shooterPID;

    public ChargeCatapult() {
        shooterPID = shooterSubsystem.getShooterPID();
        
    }
    protected void initialize() {
        shooterPID.enable();
        shooterPID.setSetpoint(shooterSubsystem.ChargedPulses);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
