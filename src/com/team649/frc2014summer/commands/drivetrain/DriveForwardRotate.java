/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.drivetrain;

import com.team649.frc2014summer.commands.CommandBase;

/**
 *
 * @author Kabi
 */
public class DriveForwardRotate extends CommandBase {

    private double driveForward;
    private double driveRotation;
    
    public DriveForwardRotate(double driveFow, double driveRot) {
        driveForward = driveFow;
        driveRotation = driveRot;
    }
    protected void initialize() {
        CommandBase.driveTrainSubsystem.driveFwdRot(driveForward,driveRotation);
        
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
      
    }

    protected void interrupted() {
      
    }
    
}
