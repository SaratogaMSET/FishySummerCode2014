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
public class DriveSetDistanceWithPID extends CommandBase {

    public final double distance;
    private double minDriveSpeed;
    private double maxDriveSpeed;
    private PIDController649 pid;

    public DriveSetDistanceWithPID(double dis) {
        distance = dis;
        minDriveSpeed = DriveTrainSubsystem.EncoderBasedDriving.MIN_MOTOR_POWER;
        maxDriveSpeed = DriveTrainSubsystem.EncoderBasedDriving.MAX_MOTOR_POWER;
    }

    public DriveSetDistanceWithPID(double dis, double minSpeed, double maxSpeed) {
        distance = dis;
        minDriveSpeed = minSpeed;
        maxDriveSpeed = maxSpeed;
    }

    protected void initialize() {
        
        pid = CommandBase.driveTrainSubsystem.getPID();
        pid.setPID(DriveTrainSubsystem.EncoderBasedDriving.Kp, DriveTrainSubsystem.EncoderBasedDriving.Ki, DriveTrainSubsystem.EncoderBasedDriving.Kd);
        pid.setOutputRange(minDriveSpeed, maxDriveSpeed);
        pid.setSetpoint(distance);
        driveTrainSubsystem.resetEncoders();
        driveTrainSubsystem.startEncoders();
        pid.enable();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return CommandBase.driveTrainSubsystem.getDistance() >= distance;

    }

    protected void end() {
        CommandBase.driveTrainSubsystem.disablePID();
    }

    protected void interrupted() {
        end();
    }

}
