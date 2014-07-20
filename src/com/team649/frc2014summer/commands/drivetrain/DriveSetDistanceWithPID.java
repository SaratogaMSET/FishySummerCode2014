/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.drivetrain;

import com.team649.frc2014.pid_control.PIDController649;
import com.team649.frc2014summer.commands.CommandBase;
import com.team649.frc2014summer.subsystems.DriveTrainSubsystem;

/**
 *
 * @author Kabi
 */
public class DriveSetDistanceWithPID extends CommandBase{
    public final double distance;
    private double minDriveSpeed;
    private PIDController649 pid;
    
    
    public DriveSetDistanceWithPID(double dis) {
        distance = dis;
        minDriveSpeed = 0.25;
    }
    
    public DriveSetDistanceWithPID(double dis, double minSpeed) {
        distance = dis;
        minDriveSpeed = minSpeed;
    }
    
    protected void initialize() {
    pid = CommandBase.driveTrainSubsystem.getPID();
    pid.setPID(DriveTrainSubsystem.EncoderBasedDriving.Kp, DriveTrainSubsystem.EncoderBasedDriving.Ki, DriveTrainSubsystem.EncoderBasedDriving.Kd);
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
