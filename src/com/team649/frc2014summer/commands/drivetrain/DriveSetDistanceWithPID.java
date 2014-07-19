/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team649.frc2014summer.commands.drivetrain;

/**
 *
 * @author Kabi
 */
public class DriveSetDistanceWithPID {
    public final double distance;
    private double minDriveSpeed;
    public DriveSetDistanceWithPID(double dis) {
        distance = dis;
        minDriveSpeed = 0.25;
    }
    
    public DriveSetDistanceWithPID(double dis, double minSpeed) {
        distance = dis;
        minDriveSpeed = minSpeed;
    }
    
}
